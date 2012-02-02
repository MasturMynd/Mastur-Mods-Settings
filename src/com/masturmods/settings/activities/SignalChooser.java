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

import com.masturmods.settings.activities.SignalChooser;
import com.masturmods.settings.MasturModsSettingsActivity;
import com.masturmods.settings.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SignalChooser extends MasturModsSettingsActivity {

 	private static List<SignalIcon> mSignalIcons;
 	
 	@Override
	public void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.signal_chooser);

		final ListView listView = (ListView)findViewById(R.id.signalView);
		final ListAdapter adapter = new ListAdapter(getApplicationContext());
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final File SignalFolder = new File(sdcard + "/MasturMods/SignalIcons");
		mSignalIcons = getSignalIcons(SignalFolder);
		
		adapter.setListItems(mSignalIcons);
		listView.setAdapter(adapter);
		
		if (mSignalIcons.isEmpty()) {
			findViewById(R.id.emptyView).setVisibility(View.VISIBLE);
		}
	}
	
	private List<SignalIcon> getSignalIcons(final File path) {
		final File[] SignalFolder = path.listFiles();
		List<SignalIcon> SignalIcons = new ArrayList<SignalIcon>();
		
		if (SignalFolder != null) {
			
			for (final File file : SignalFolder) {
				
				if (file.getAbsolutePath().toLowerCase().endsWith(".zip")) {
					Drawable icon = null;
					
					try {
						final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						icon = new BitmapDrawable(bitmap);                      
					} catch (Exception e) {
					}
					
					if (icon != null) {
						SignalIcon SignalIcon = new SignalIcon();
						SignalIcon.setIcon(icon);
						SignalIcon.setName(file.getName());
						SignalIcons.add(SignalIcon);
					}
				}
			}
		}
		return SignalIcons;
	}

	private class SignalIcon {
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
		private List<SignalIcon> SignalIcons;

		public ListAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return SignalIcons.size();
		}

		@Override
		public Object getItem(int position) {
			return SignalIcons.get(position);
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

			final SignalIcon SignalIcon = SignalIcons.get(position);
			holder.mTitle.setText(SignalIcon.getName());
			holder.mImage.setImageDrawable(SignalIcon.getIcon());

			return convertView; 
		}

		public void setListItems(List<SignalIcon> list) { 
			SignalIcons = list; 
		}

		public class ViewHolder {
			private TextView mTitle;
			private ImageView mImage;

		}
	}
}