package com.masturmods.settings.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.masturmods.settings.activities.BatteryChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BatteryChooser extends MasturModsSettingsActivity {

 	private static List<BatteryIcon> mBatteryIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.battery_chooser);

		final ListView listView = (ListView)findViewById(R.id.batteryView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File batteryFolder = new File(sdcard + "/MasturMods/Batteries");
		mBatteryIcons = getBatteryIcons(batteryFolder);
		
		adapter.setListItems(mBatteryIcons);
		listView.setAdapter(adapter);
		
		if (mBatteryIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<BatteryIcon> getBatteryIcons(final File path) {
		final File[] batteryFolder = path.listFiles();
		List<BatteryIcon> batteryIcons = new ArrayList<BatteryIcon>();
		
		if (batteryFolder != null) {
			
			for (final File file : batteryFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						BatteryIcon batteryIcon = new BatteryIcon();
						batteryIcon.setIcon(icon);
						batteryIcon.setName(file.getName());
						batteryIcons.add(batteryIcon);
					}
				}
			}
		}
		return batteryIcons;
	}

	private class BatteryIcon {
		private String name;
		private Drawable icon;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setIcon(Drawable icon) {
			this.icon = icon;
		}

		public Drawable getIcon() {
			return icon;
		}
	}

	public class ListAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private List<BatteryIcon> batteryIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return batteryIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return batteryIcons.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.row, null);
				holder = new ViewHolder();
				holder.mTitle = (TextView) convertView.findViewById(R.id.text);
				holder.mImage = (ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(holder);
				
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			final BatteryIcon batteryIcon = batteryIcons.get(position);
			holder.mTitle.setText(batteryIcon.getName());
			holder.mImage.setImageDrawable(batteryIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<BatteryIcon> list) { 
			batteryIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}