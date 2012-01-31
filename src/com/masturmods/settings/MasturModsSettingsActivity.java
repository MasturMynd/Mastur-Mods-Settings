package com.masturmods.settings;

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.masturmods.settings.R;
import com.masturmods.settings.activities.*;

public class MasturModsSettingsActivity extends Activity {
	String url = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=7NN6B7L7YXQ82&lc=US&item_name=Mastur%20Mods&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
    private Button button_email;
	private Button button_donate;
	private Button battery;
	private Button data;
	private Button signal;
	private Button wifi;
	private Button back;
	private Button home;
	private Button menu;
	private Button recent;
	private Button search;
	@Override
   	public void onCreate(Bundle savedInstanceState) {
   		super.onCreate(savedInstanceState);
   		setContentView(R.layout.main);
   		File masturMods = new File(Environment.getExternalStorageDirectory(), "/MasturMods/");
   		if(!masturMods.exists()) {
   		    masturMods.mkdirs();
   		}
   		File batteryDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/Batteries");
   		if(!batteryDir.exists()) {
   		    batteryDir.mkdirs();
   		}
   		File dataDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/DataIcons");
   		if(!dataDir.exists()) {
   		    dataDir.mkdirs();
   		}
   		File signalDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/SignalIcons");
   		if(!signalDir.exists()) {
   		    signalDir.mkdirs();
   		}
   		File wifiDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/WiFiIcons");
   		if(!wifiDir.exists()) {
   		    wifiDir.mkdirs();
   		}
   		File backDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/BackButtons");
   		if(!backDir.exists()) {
   		    backDir.mkdirs();
   		}
   		File homeDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/HomeButtons");
   		if(!homeDir.exists()) {
   		    homeDir.mkdirs();
   		}
   		File menuDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/MenuButtons");
   		if(!menuDir.exists()) {
   		    menuDir.mkdirs();
   		}
   		File recentDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/RecentButtons");
   		if(!recentDir.exists()) {
   		    recentDir.mkdirs();
   		}
   		File searchDir = new File(Environment.getExternalStorageDirectory(), "/MasturMods/SearchButtons");
   		if(!searchDir.exists()) {
   		    searchDir.mkdirs();
   		}
   		button_donate = (Button)findViewById(R.id.button_donate);
   		button_donate.setOnClickListener(new OnClickListener() {         
   			@Override
   			public void onClick(View v){
   				try {
   					i.setData(u);
   					startActivity(i);
   				} catch (ActivityNotFoundException e) {
   					Toast.makeText(context, "Browser not found.", Toast.LENGTH_SHORT);
   				}
   			} 
   		});
   		final Intent emailIntent = new Intent( android.content.Intent.ACTION_SEND);
   		button_email = (Button)findViewById(R.id.button_email);
   		button_email.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v){
   				emailIntent.setType("message/rfc822");
   				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "masturmynd@gmail.com" });
   				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Mastur Mods Settings");
   				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
   				startActivity(emailIntent);
   			}
   		});
   		final Intent batteryIntent = new Intent(this, BatteryChooser.class);
   		battery = (Button)findViewById(R.id.battery);
   		battery.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(batteryIntent);
   			}
   		});
   		final Intent dataIntent = new Intent(this, DataChooser.class);
   		data = (Button)findViewById(R.id.data);
   		data.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(dataIntent);
   			}
   		});
   		final Intent signalIntent = new Intent(this, SignalChooser.class);
   		signal = (Button)findViewById(R.id.signal);
   		signal.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(signalIntent);
   			}
   		});
   		final Intent wifiIntent = new Intent(this, WifiChooser.class);
   		wifi = (Button)findViewById(R.id.wifi);
   		wifi.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(wifiIntent);
   			}
   		});
   		final Intent backIntent = new Intent(this, BackChooser.class);
   		back = (Button)findViewById(R.id.back);
   		back.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(backIntent);
   			}
   		});
   		final Intent homeIntent = new Intent(this, HomeChooser.class);
   		home = (Button)findViewById(R.id.home);
   		home.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(homeIntent);
   			}
   		});
   		final Intent menuIntent = new Intent(this, MenuChooser.class);
   		menu = (Button)findViewById(R.id.menu);
   		menu.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(menuIntent);
   			}
   		});
   		final Intent recentIntent = new Intent(this, RecentChooser.class);
   		recent = (Button)findViewById(R.id.recent);
   		recent.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(recentIntent);
   			}
   		});
   		final Intent searchIntent = new Intent(this, SearchChooser.class);
   		search = (Button)findViewById(R.id.search);
   		search.setOnClickListener(new OnClickListener() {
   			@Override
   			public void onClick(View v) {
   				startActivity(searchIntent);
   			}
   		});
   	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
    protected void onDestroy() {
    	super.onDestroy();
    }
}