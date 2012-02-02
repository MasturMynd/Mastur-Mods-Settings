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

import com.masturmods.settings.activities.WifiChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WifiChooser extends MasturModsSettingsActivity {

 	private static List<WifiIcon> mWifiIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.wifi_chooser);

		final ListView listView = (ListView)findViewById(R.id.wifiView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File WifiFolder = new File(sdcard + "/MasturMods/WiFiIcons");
		mWifiIcons = getWifiIcons(WifiFolder);
		
		adapter.setListItems(mWifiIcons);
		listView.setAdapter(adapter);
		
		if (mWifiIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<WifiIcon> getWifiIcons(final File path) {
		final File[] WifiFolder = path.listFiles();
		List<WifiIcon> WifiIcons = new ArrayList<WifiIcon>();
		
		if (WifiFolder != null) {
			
			for (final File file : WifiFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						WifiIcon WifiIcon = new WifiIcon();
						WifiIcon.setIcon(icon);
						WifiIcon.setName(file.getName());
						WifiIcons.add(WifiIcon);
					}
				}
			}
		}
		return WifiIcons;
	}

	private class WifiIcon {
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
		private List<WifiIcon> WifiIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return WifiIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return WifiIcons.get(position);
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

			final WifiIcon WifiIcon = WifiIcons.get(position);
			holder.mTitle.setText(WifiIcon.getName());
			holder.mImage.setImageDrawable(WifiIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<WifiIcon> list) { 
			WifiIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}