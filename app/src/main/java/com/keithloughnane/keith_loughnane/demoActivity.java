package com.keithloughnane.keith_loughnane;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class demoActivity extends AppCompatActivity
{

	public static final String MESSAGE = "message";
	private int seconds = 0;
	private boolean running;



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//Standard Stuff
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		//Get String from the main intent with a message
		Intent intent = getIntent();
		String message = intent.getStringExtra("message");
		TextView welcomeText = (TextView)findViewById(R.id.DemoWelcomeText);
		welcomeText.setText(message);
		//
		if (savedInstanceState != null)
		{
			seconds = savedInstanceState.getInt("seconds");
			running = savedInstanceState.getBoolean("running");
		}

		running = true;

		runTimer();


		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> listView,
			                        View itemView,
			                        int position, long id)
			{
				Log.d("development","List Pressed");
				if(position == 0)
				{
					//System.out("0 Pressed");
					Log.d("development","Zero Pressed");
				}
			}
		};
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setOnItemClickListener(itemClickListener);


		Log.d("development","Created");
	}

	@Override
	public void onSaveInstanceState(Bundle saveInstanceState)
	{
		saveInstanceState.putInt("seconds",seconds);
		saveInstanceState.putBoolean("running",running);

	}

	@Override
	protected void onStop()
	{
		super.onStop();
		running = false;
		Log.d("development","Stopped");

	}
	@Override
	protected void onStart()
	{
		super.onStart();
		Log.d("development","Started");
	}
	@Override
	protected void onDestroy()
	{
		Log.d("development","Destroying");
	}

	public void onClickStart(View view)
	{
		running = true;
	}
	public void onClickStop(View view)
	{
		running = false;
	}
	public void onClickReset(View view)
	{
		running = false;
		seconds = 0;
	}








	private void runTimer()
	{

		final TextView timeView = (TextView)findViewById(R.id.runTimeText);
		final Handler handler = new Handler();
		handler.post(new Runnable()
		{
			@Override
			public void run()
			{
				int hours = seconds/3600;
				int minutes = (seconds%3600)/60;
				int secs = seconds%60;
				String time = String.format("%d:%02d:%02d", hours, minutes, secs);
				timeView.setText(time);
				if(running)
				{
					seconds++;
				}
				handler.postDelayed(this,1000);
			}
		});


	}
}
