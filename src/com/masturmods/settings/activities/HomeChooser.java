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

public class HomeChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/navigation-bar/home/";
	Intent i = new Intent(Intent.ACTION_VIEW);
	Uri u = Uri.parse(url);
	Context context = this;
 	private RadioGroup homeGroup;
 	private RadioButton homeButton;
 	private Button choose;
 	private Button getHome;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.home_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		homeGroup = (RadioGroup) findViewById(R.id.radiogroup_home);
   		choose = (Button) findViewById(R.id.home_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = homeGroup.getCheckedRadioButtonId();
   				homeButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(HomeChooser.this, homeButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getHome = (Button)findViewById(R.id.get_home);
   		getHome.setOnClickListener(new OnClickListener() {         
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