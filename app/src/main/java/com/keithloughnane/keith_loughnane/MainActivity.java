package com.keithloughnane.keith_loughnane;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		Log.d("development","Application Started0");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public void onBackPressed()
	{
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START))
		{
			drawer.closeDrawer(GravityCompat.START);
		} else
		{
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item)
	{
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera)
		{
			// Handle the camera action
		} else if (id == R.id.nav_gallery)
		{

		} else if (id == R.id.nav_slideshow)
		{

		} /*else if (id == R.id.nav_manage)
		{

		} */ else if (id == R.id.nav_share)
		{

		} else if (id == R.id.nav_send)
		{

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	public void buttonClicked(View view)
	{
		gitDataManager gitDM = new gitDataManager();
		System.out.println("clicked");
		TextView welcomeText = (TextView) findViewById(R.id.welcomeText);

		Spinner techSpinner = (Spinner) findViewById(R.id.spinner);

		//welcomeText.setText(String.valueOf(techSpinner.getSelectedItem()));
		//welcomeText.setText("Clicked!");
		//welcomeText.setText(String.valueOf(gitDM.getBrands(String.valueOf(techSpinner.getSelectedItem()))));

		List<String> brandList = gitDM.getBrands("blah");
		StringBuilder brandsFormatted = new StringBuilder();
		for(String brand : brandList)
		{
			brandsFormatted.append(brand).append('\n');
		}

		welcomeText.setText(brandsFormatted);


		//intent
		Intent intent = new Intent(this, demoActivity.class);
		intent.putExtra(demoActivity.MESSAGE,"this is the message passed");
		startActivity(intent);



	}

	public void setLanguage(View view)
	{
		Locale locale = new Locale("ga");
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		this.getResources().updateConfiguration(config, null);

	}

	public void sendEmail(View view)
	{
		Log.d("sendEmail","sendEmail started");
		Intent intent = new Intent(Intent.ACTION_SEND);
		Log.d("development","A");
		intent.setType("text/plain");
		Log.d("development","B");
		intent.putExtra(Intent.EXTRA_EMAIL, "keith.loughnane@gmail.com");
		intent.putExtra(Intent.EXTRA_SUBJECT,"job");
		intent.putExtra(Intent.EXTRA_TEXT, "Love the app, would love to hire you");
		Log.d("development","C");
		startActivity(intent);
		Log.d("development","E");
	}
}
