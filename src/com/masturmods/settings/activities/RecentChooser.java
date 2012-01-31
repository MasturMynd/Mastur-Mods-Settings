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

public class RecentChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/navigation-bar/recent/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup recentGroup;
 	private RadioButton recentButton;
 	private Button choose;
 	private Button getRecent;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.recent_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		recentGroup = (RadioGroup) findViewById(R.id.radiogroup_recent);
   		choose = (Button) findViewById(R.id.recent_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = recentGroup.getCheckedRadioButtonId();
   				recentButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(RecentChooser.this, recentButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getRecent = (Button)findViewById(R.id.get_recent);
   		getRecent.setOnClickListener(new OnClickListener() {         
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