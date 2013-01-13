package com.example.phpconnect;

import java.util.concurrent.Delayed;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SendDatabase extends Activity implements OnClickListener {

	private static String getOut = "start";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_database);
		
		//testJAVAToPHPConnect shit = new testJAVAToPHPConnect();
		//shit.setFuckedUpContext(getApplicationContext());
		//testJAVAToPHPConnect.doConnection(getApplicationContext());
		final Button btnStart = (Button) findViewById(R.id.btnStart);
		final TextView txtField = (TextView)findViewById(R.id.txtField);
		
		btnStart.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	//this is for the screening of the result ( just temporary) 
		    	txtField.setText(getOut);
		    	
		    	//html connections are not allowed in the main action.
		    	//There for me make a new thread that will make our request
		    	new Thread(new Runnable() {
					@Override
					public void run() {
						Looper.prepare();
						testJAVAToPHPConnect connector = new testJAVAToPHPConnect();
						getOut = connector.doConnection(getApplicationContext());						
						Looper.myLooper().quit();
					}
				}).start() ;
		    	
		    	
		    	
	    }
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_send_database, menu);
		return true;
	}
	

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
//		testJAVAToPHPConnect shit = new testJAVAToPHPConnect();
//		shit.setFuckedUpContext(getApplicationContext());
//		shit.doInBackground("");
	}
}
