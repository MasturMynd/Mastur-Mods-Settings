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

public class BatteryChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/status-bar/battery/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup batteryGroup;
 	private RadioButton batteryButton;
 	private Button choose;
 	private Button getBattery;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.battery_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		batteryGroup = (RadioGroup) findViewById(R.id.radiogroup_battery);
   		choose = (Button) findViewById(R.id.battery_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = batteryGroup.getCheckedRadioButtonId();
   				batteryButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(BatteryChooser.this, batteryButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getBattery = (Button)findViewById(R.id.get_battery);
   		getBattery.setOnClickListener(new OnClickListener() {         
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