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

import com.masturmods.settings.activities.DataChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataChooser extends MasturModsSettingsActivity {

 	private static List<DataIcon> mDataIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.data_chooser);

		final ListView listView = (ListView)findViewById(R.id.dataView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File DataFolder = new File(sdcard + "/MasturMods/DataIcons");
		mDataIcons = getDataIcons(DataFolder);
		
		adapter.setListItems(mDataIcons);
		listView.setAdapter(adapter);
		
		if (mDataIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<DataIcon> getDataIcons(final File path) {
		final File[] DataFolder = path.listFiles();
		List<DataIcon> DataIcons = new ArrayList<DataIcon>();
		
		if (DataFolder != null) {
			
			for (final File file : DataFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						DataIcon DataIcon = new DataIcon();
						DataIcon.setIcon(icon);
						DataIcon.setName(file.getName());
						DataIcons.add(DataIcon);
					}
				}
			}
		}
		return DataIcons;
	}

	private class DataIcon {
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
		private List<DataIcon> DataIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return DataIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return DataIcons.get(position);
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

			final DataIcon DataIcon = DataIcons.get(position);
			holder.mTitle.setText(DataIcon.getName());
			holder.mImage.setImageDrawable(DataIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<DataIcon> list) { 
			DataIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}