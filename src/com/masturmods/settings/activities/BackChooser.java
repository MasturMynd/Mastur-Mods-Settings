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

public class BackChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/navigation-bar/back/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup backGroup;
 	private RadioButton backButton;
 	private Button choose;
	private Button getBack;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.back_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		backGroup = (RadioGroup) findViewById(R.id.radiogroup_back);
   		choose = (Button) findViewById(R.id.back_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = backGroup.getCheckedRadioButtonId();
   				backButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(BackChooser.this, backButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getBack = (Button)findViewById(R.id.get_back);
   		getBack.setOnClickListener(new OnClickListener() {         
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