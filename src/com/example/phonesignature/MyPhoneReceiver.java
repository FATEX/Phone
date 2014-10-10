package com.example.phonesignature;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;

public class MyPhoneReceiver extends BroadcastReceiver {
	String a;
	@Override
	public void onReceive(final Context context, final Intent intent) {
	    Bundle extras = intent.getExtras();
	    if (extras != null){
	    	String state = extras.getString(TelephonyManager.EXTRA_STATE);
	    	if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
	    		String phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
	    	}
	    	
	    	new Handler().postDelayed(new Runnable() {

	    	     @Override
	    	     public void run() {
	    	    	 Intent i = new Intent(context, IncomingCallActivity.class);
	    		        i.putExtras(intent);
	    		        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		        context.startActivity(i);
	    	     }
	    	 }, 1000);
	    }
	}
}