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

public class DataChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/status-bar/data/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup dataGroup;
 	private RadioButton dataButton;
 	private Button choose;
 	private Button getData;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.data_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		dataGroup = (RadioGroup) findViewById(R.id.radiogroup_data);
   		choose = (Button) findViewById(R.id.data_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = dataGroup.getCheckedRadioButtonId();
   				dataButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(DataChooser.this, dataButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getData = (Button)findViewById(R.id.get_data);
   		getData.setOnClickListener(new OnClickListener() {         
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