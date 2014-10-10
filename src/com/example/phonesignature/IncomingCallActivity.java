package com.example.phonesignature;

import com.example.phonesignature.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class IncomingCallActivity extends Activity{
	private String LOG_TAG = "PhoneCallListener";
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_incoming_call);
		PhoneCallListener phoneListener = new PhoneCallListener(this);
	    TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
	    telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
	    img = (ImageView)findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.incoming_call, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void hideImg(){
		System.out.println("in hide image");
		img.setVisibility(4);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	private class PhoneCallListener extends PhoneStateListener {

	    private boolean isPhoneCalling = false;
	    private IncomingCallActivity activity;
	    public PhoneCallListener(IncomingCallActivity activity){
	    	this.activity = activity;
	    }

	    @Override
	    public void onCallStateChanged(int state, String incomingNumber) {

	        if (TelephonyManager.CALL_STATE_RINGING == state) {
	            // phone ringing
	            Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
	        }

	        if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
	            // active
	            Log.i(LOG_TAG, "OFFHOOK");
	            isPhoneCalling = true;
	            activity.hideImg();
	        }

	        if (TelephonyManager.CALL_STATE_IDLE == state) {
	            // run when class initial and phone call ended, need detect flag
	            // from CALL_STATE_OFFHOOK
	            Log.i(LOG_TAG, "IDLE number");

	            if (isPhoneCalling) {

	                Handler handler = new Handler();

	                //Put in delay because call log is not updated immediately when state changed
	                // The dialler takes a little bit of time to write to it 500ms seems to be enough
	                handler.postDelayed(new Runnable() {

	                    @Override
	                    public void run() {
	                        // get start of cursor
	                          Log.i("CallLogDetailsActivity", "Getting Log activity...");
	                            String[] projection = new String[]{Calls.NUMBER};
	                            Cursor cur = getContentResolver().query(Calls.CONTENT_URI, projection, null, null, Calls.DATE +" desc");
	                            cur.moveToFirst();
	                            String lastCallnumber = cur.getString(0);
	                    }
	                },500);

	                isPhoneCalling = false;
	                activity.onPause();
	            }

	        }
	    }
	}
}


