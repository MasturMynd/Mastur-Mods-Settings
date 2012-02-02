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

import com.masturmods.settings.activities.HomeChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeChooser extends MasturModsSettingsActivity {

 	private static List<HomeIcon> mHomeIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.home_chooser);

		final ListView listView = (ListView)findViewById(R.id.homeView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File HomeFolder = new File(sdcard + "/MasturMods/HomeButtons");
		mHomeIcons = getHomeIcons(HomeFolder);
		
		adapter.setListItems(mHomeIcons);
		listView.setAdapter(adapter);
		
		if (mHomeIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<HomeIcon> getHomeIcons(final File path) {
		final File[] HomeFolder = path.listFiles();
		List<HomeIcon> HomeIcons = new ArrayList<HomeIcon>();
		
		if (HomeFolder != null) {
			
			for (final File file : HomeFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						HomeIcon HomeIcon = new HomeIcon();
						HomeIcon.setIcon(icon);
						HomeIcon.setName(file.getName());
						HomeIcons.add(HomeIcon);
					}
				}
			}
		}
		return HomeIcons;
	}

	private class HomeIcon {
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
		private List<HomeIcon> HomeIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return HomeIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return HomeIcons.get(position);
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

			final HomeIcon HomeIcon = HomeIcons.get(position);
			holder.mTitle.setText(HomeIcon.getName());
			holder.mImage.setImageDrawable(HomeIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<HomeIcon> list) { 
			HomeIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}