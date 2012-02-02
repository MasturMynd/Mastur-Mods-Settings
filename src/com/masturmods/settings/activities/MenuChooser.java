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

import com.masturmods.settings.activities.MenuChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuChooser extends MasturModsSettingsActivity {

 	private static List<MenuIcon> mMenuIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.menu_chooser);

		final ListView listView = (ListView)findViewById(R.id.menuView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File MenuFolder = new File(sdcard + "/MasturMods/MenuButtons");
		mMenuIcons = getMenuIcons(MenuFolder);
		
		adapter.setListItems(mMenuIcons);
		listView.setAdapter(adapter);
		
		if (mMenuIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<MenuIcon> getMenuIcons(final File path) {
		final File[] MenuFolder = path.listFiles();
		List<MenuIcon> MenuIcons = new ArrayList<MenuIcon>();
		
		if (MenuFolder != null) {
			
			for (final File file : MenuFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						MenuIcon MenuIcon = new MenuIcon();
						MenuIcon.setIcon(icon);
						MenuIcon.setName(file.getName());
						MenuIcons.add(MenuIcon);
					}
				}
			}
		}
		return MenuIcons;
	}

	private class MenuIcon {
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
		private List<MenuIcon> MenuIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return MenuIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return MenuIcons.get(position);
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

			final MenuIcon MenuIcon = MenuIcons.get(position);
			holder.mTitle.setText(MenuIcon.getName());
			holder.mImage.setImageDrawable(MenuIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<MenuIcon> list) { 
			MenuIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}