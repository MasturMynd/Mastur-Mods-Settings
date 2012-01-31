package com.masturmods.settings.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

public class WifiChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/status-bar/wi-fi/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup wifiGroup;
 	private RadioButton wifiButton;
 	private Button choose;
 	private Button getWifi;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.wifi_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		wifiGroup = (RadioGroup) findViewById(R.id.radiogroup_wifi);
   		choose = (Button) findViewById(R.id.wifi_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = wifiGroup.getCheckedRadioButtonId();
   				wifiButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(WifiChooser.this, wifiButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getWifi = (Button)findViewById(R.id.get_wifi);
   		getWifi.setOnClickListener(new OnClickListener() {         
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
   	}
}