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

public class SearchChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/navigation-bar/search/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup searchGroup;
 	private RadioButton searchButton;
 	private Button choose;
 	private Button getSearch;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.search_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		searchGroup = (RadioGroup) findViewById(R.id.radiogroup_search);
   		choose = (Button) findViewById(R.id.search_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = searchGroup.getCheckedRadioButtonId();
   				searchButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(SearchChooser.this, searchButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getSearch = (Button)findViewById(R.id.get_search);
   		getSearch.setOnClickListener(new OnClickListener() {         
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