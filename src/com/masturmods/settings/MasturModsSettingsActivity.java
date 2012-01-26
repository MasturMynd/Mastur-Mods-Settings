package com.masturmods.settings;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.masturmods.settings.R;

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
   		battery = (Button)findViewById(R.id.battery);
   		battery.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.battery_chooser);
   			}
   		});
   		data = (Button)findViewById(R.id.data);
   		data.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.data_chooser);
   			}
   		});
   		signal = (Button)findViewById(R.id.signal);
   		signal.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.signal_chooser);
   			}
   		});
   		wifi = (Button)findViewById(R.id.wifi);
   		wifi.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.wifi_chooser);
   			}
   		});
   		back = (Button)findViewById(R.id.back);
   		back.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.back_chooser);
   			}
   		});
   		home= (Button)findViewById(R.id.home);
   		home.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.home_chooser);
   			}
   		});
   		menu = (Button)findViewById(R.id.menu);
   		menu.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.menu_chooser);
   			}
   		});
   		recent = (Button)findViewById(R.id.recent);
   		recent.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.recent_chooser);
   			}
   		});
   		search = (Button)findViewById(R.id.search);
   		search.setOnClickListener(new OnClickListener() {
   			public void onClick(View v) {
   			setContentView(R.layout.search_chooser);
   			}
   		});
   	}
	public void resume() {
    }

    public void pause() {
    }
}