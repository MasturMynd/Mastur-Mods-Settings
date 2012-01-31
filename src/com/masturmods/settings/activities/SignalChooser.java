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

public class SignalChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/status-bar/signal/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup signalGroup;
 	private RadioButton signalButton;
 	private Button choose;
 	private Button getSignal;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.signal_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		signalGroup = (RadioGroup) findViewById(R.id.radiogroup_signal);
   		choose = (Button) findViewById(R.id.signal_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = signalGroup.getCheckedRadioButtonId();
   				signalButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(SignalChooser.this, signalButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getSignal = (Button)findViewById(R.id.get_signal);
   		getSignal.setOnClickListener(new OnClickListener() {         
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