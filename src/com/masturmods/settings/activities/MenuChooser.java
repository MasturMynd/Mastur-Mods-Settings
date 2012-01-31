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

public class MenuChooser extends MasturModsSettingsActivity {
	String url = "http://masturmods.us.to/downloads/masturmods-settings/navigation-bar/menu/";
    Intent i = new Intent(Intent.ACTION_VIEW);
    Uri u = Uri.parse(url);
    Context context = this;
 	private RadioGroup menuGroup;
 	private RadioButton menuButton;
 	private Button choose;
 	private Button getMenu;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.menu_chooser);
 		addListenerOnButton();
 	}
 	
	public void addListenerOnButton() {
		menuGroup = (RadioGroup) findViewById(R.id.radiogroup_menu);
   		choose = (Button) findViewById(R.id.menu_choose);
   		
   		choose.setOnClickListener(new OnClickListener() {
   			
   			public void onClick(View v) {
   				int selectedId = menuGroup.getCheckedRadioButtonId();
   				menuButton = (RadioButton) findViewById(selectedId);
   				Toast.makeText(MenuChooser.this, menuButton.getText(), Toast.LENGTH_SHORT).show();
   			}
   		});
   		getMenu = (Button)findViewById(R.id.get_menu);
   		getMenu.setOnClickListener(new OnClickListener() {         
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