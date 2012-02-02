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

import com.masturmods.settings.activities.RecentChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecentChooser extends MasturModsSettingsActivity {

 	private static List<RecentIcon> mRecentIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.recent_chooser);

		final ListView listView = (ListView)findViewById(R.id.recentView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File RecentFolder = new File(sdcard + "/MasturMods/RecentButtons");
		mRecentIcons = getRecentIcons(RecentFolder);
		
		adapter.setListItems(mRecentIcons);
		listView.setAdapter(adapter);
		
		if (mRecentIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<RecentIcon> getRecentIcons(final File path) {
		final File[] RecentFolder = path.listFiles();
		List<RecentIcon> RecentIcons = new ArrayList<RecentIcon>();
		
		if (RecentFolder != null) {
			
			for (final File file : RecentFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						RecentIcon RecentIcon = new RecentIcon();
						RecentIcon.setIcon(icon);
						RecentIcon.setName(file.getName());
						RecentIcons.add(RecentIcon);
					}
				}
			}
		}
		return RecentIcons;
	}

	private class RecentIcon {
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
		private List<RecentIcon> RecentIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return RecentIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return RecentIcons.get(position);
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

			final RecentIcon RecentIcon = RecentIcons.get(position);
			holder.mTitle.setText(RecentIcon.getName());
			holder.mImage.setImageDrawable(RecentIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<RecentIcon> list) { 
			RecentIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}