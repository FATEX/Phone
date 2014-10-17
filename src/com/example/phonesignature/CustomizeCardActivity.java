package com.example.phonesignature;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;

public class CustomizeCardActivity extends ActionBarActivity {

	EditText drag;
	AbsoluteLayout drop;
	Button upBtn;
	Button leftBtn;
	Button rightBtn;
	Button downBtn;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_customize_card);
	    upBtn = (Button)findViewById(R.id.upBtn);
	    leftBtn = (Button)findViewById(R.id.leftBtn);
	    rightBtn = (Button)findViewById(R.id.rightBtn);
	    downBtn = (Button)findViewById(R.id.downBtn);
	    drag = (EditText)findViewById(R.id.one);
	    drop = (AbsoluteLayout)findViewById(R.id.bottomlinear);
	    drop.setOnDragListener(new View.OnDragListener() {
	      @Override
	      public boolean onDrag(View v, DragEvent event) {
	        // TODO Auto-generated method stub
	      final int action = event.getAction();
	            switch(action) {
	            case DragEvent.ACTION_DRAG_STARTED:
	            	break;
	            case DragEvent.ACTION_DRAG_EXITED:
	            	break;
	            case DragEvent.ACTION_DRAG_ENTERED:
	            	break;
	            case DragEvent.ACTION_DROP:{
	            	drop.setX(event.getX());
	            	drop.setY(event.getY());
	            	return(true);
	            }
	            case DragEvent.ACTION_DRAG_ENDED:{
	            	return(true);
	            }
	            default:
	            	break;
	            }
	            return true;
	      }});
	        drag.setOnTouchListener(new OnTouchListener() {
	      @Override
	      public boolean onTouch(View v, MotionEvent arg1) {
	        // TODO Auto-generated method stub
	        ClipData data = ClipData.newPlainText("", "");
	        View.DragShadowBuilder shadow = new View.DragShadowBuilder(drag);
	        v.startDrag(data, shadow, null, 0);
	        return false;
	      }
	    });
	}
	  
	  public void moveUp(View view){
		  drop.setY(drop.getY()-10);
	  }
	  
	  public void moveLeft(View view){
		  drop.setX(drop.getX()-10);
	  }
	  
	  public void moveRight(View view){
		  drop.setX(drop.getX()+10);
	  }
	  
	  public void moveDown(View view){
		  drop.setY(drop.getY()+10);
	  }
}
