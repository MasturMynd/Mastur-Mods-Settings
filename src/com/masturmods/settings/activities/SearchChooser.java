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

import com.masturmods.settings.activities.SearchChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchChooser extends MasturModsSettingsActivity {

 	private static List<SearchIcon> mSearchIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.search_chooser);

		final ListView listView = (ListView)findViewById(R.id.searchView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File SearchFolder = new File(sdcard + "/MasturMods/SearchButtons");
		mSearchIcons = getSearchIcons(SearchFolder);
		
		adapter.setListItems(mSearchIcons);
		listView.setAdapter(adapter);
		
		if (mSearchIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<SearchIcon> getSearchIcons(final File path) {
		final File[] SearchFolder = path.listFiles();
		List<SearchIcon> SearchIcons = new ArrayList<SearchIcon>();
		
		if (SearchFolder != null) {
			
			for (final File file : SearchFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						SearchIcon SearchIcon = new SearchIcon();
						SearchIcon.setIcon(icon);
						SearchIcon.setName(file.getName());
						SearchIcons.add(SearchIcon);
					}
				}
			}
		}
		return SearchIcons;
	}

	private class SearchIcon {
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
		private List<SearchIcon> SearchIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return SearchIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return SearchIcons.get(position);
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

			final SearchIcon SearchIcon = SearchIcons.get(position);
			holder.mTitle.setText(SearchIcon.getName());
			holder.mImage.setImageDrawable(SearchIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<SearchIcon> list) { 
			SearchIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}