package com.masturmods.settings.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.masturmods.settings.R;

public class BatteryChooser extends Activity {
	private RadioGroup batteryGroup;
	private RadioButton batteryButton;
	private Button choose;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery_chooser);
		batteryGroup = (RadioGroup)findViewById(R.id.radiogroup_battery);
		choose = (Button)findViewById(R.id.battery_choose);
		choose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int selectedId = batteryGroup.getCheckedRadioButtonId();
				batteryButton = (RadioButton)findViewById(selectedId);
			}
		});
	}
}