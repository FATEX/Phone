package com.example.phonesignature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.digitalaria.gama.carousel.Carousel;

public class MainActivity extends Activity {

	private Carousel carousel;
	private ImageAdapter adapter;
	private int[] musicCover = { R.drawable.cover1, R.drawable.cover2,
	        R.drawable.cover3, R.drawable.cover4, R.drawable.cover5};

	private void init()
	{
	    // create the carousel object.
	    carousel = (Carousel) findViewById(R.id.carousel);

	    // configurations for the carousel.
	    carousel.setType(Carousel.TYPE_COVERFLOW);
	    carousel.setOverScrollBounceEnabled(true);
	    carousel.setInfiniteScrollEnabled(false);
	    carousel.setItemRearrangeEnabled(true);

	    // set images for the carousel.
	    adapter = new ImageAdapter(this);
	    carousel.setAdapter(adapter);

	    // change the first selected position. (optional)
	    carousel.setCenterPosition(3);
	    Intent i = new Intent(this, CustomizeCardActivity.class);
	    startActivity(i);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyPhoneReceiver phone = new MyPhoneReceiver();
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public class ImageAdapter extends BaseAdapter {
	    private Context mContext;

	    public ImageAdapter(Context c)
	    {
	        mContext = c;
	    }

	    @Override
	    public int getCount() {
	        return musicCover.length;
	    }

	    @Override
	    public Object getItem(int position) {
	        return null;
	    }

	    @Override
	    public long getItemId(int position) {
	        return musicCover[position];
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view = convertView;
	        if (view == null) {
	            view = LayoutInflater.from(mContext).inflate(R.layout.carousel_item, parent, false);
	            view.setLayoutParams(new Carousel.LayoutParams(250, 250));

	            ViewHolder holder = new ViewHolder();
	            holder.imageView = (ImageView)view.findViewById(R.id.itemImage);

	            view.setTag(holder);
	        }

	        ViewHolder holder = (ViewHolder)view.getTag();
	        holder.imageView.setImageResource(musicCover[position]);

	        return view;
	    }

	    private class ViewHolder {
	        ImageView imageView;
	    }
	}
}
