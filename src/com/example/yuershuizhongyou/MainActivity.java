package com.example.yuershuizhongyou;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {
	MyGLView view;
	public Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0:
				view.renderer.stopAllThread();
				setContentView(R.layout.activity_main);
				//Intent intent=new Intent(MainActivity.this,LastActivity.class);
				//MainActivity.this.startActivity(intent);
				//finish();
			break;
			
			}
			
		};
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view=new MyGLView(this);
		setContentView(view);
		/*Intent intent=new Intent(MainActivity.this,LastActivity.class);
		MainActivity.this.startActivity(intent);
		finish();*/
	}

	

}
