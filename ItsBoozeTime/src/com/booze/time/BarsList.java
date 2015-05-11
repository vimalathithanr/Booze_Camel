package com.booze.time;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.booze.util.AppConstants;
import com.booze.util.BarData;
import com.booze.util.HttpConnectionUtil;

public class BarsList extends Activity {
	private BarData data;
	private ListView barslistview;
	private CustomListAdapter barslistadapter;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barslist);
		barslistview = (ListView) findViewById(R.id.barslistview);
		barslistadapter = new CustomListAdapter(BarsList.this,
				R.layout.barlistviewitem, AppConstants.barListData);
		barslistview.setAdapter(barslistadapter);

	}

	class CustomListAdapter extends ArrayAdapter<BarData> {

		public CustomListAdapter(Context context, int textViewResourceId,
				List<BarData> objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			View view = null;
			try {
				if (convertView == null) {
					LayoutInflater inflater = (LayoutInflater) this
							.getContext().getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.barlistviewitem, null);
				} else {
					// Use convertView if it is available
					view = convertView;
				}

				TextView barName = (TextView) view.findViewById(R.id.bar_name);
				ImageView getbar_location = (ImageView) view
						.findViewById(R.id.getbar_location);
				data = getItem(position);
				System.out.println("position  ** "+position);
				barName.setText(data.getBarName());
				getbar_location.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(HttpConnectionUtil.checkInternetConn(BarsList.this)){
						System.out.println("POSITION "+position);
						System.out.println("BAR NAME "+AppConstants.barListData.get(position).getBarName());
						AppConstants.position = position;
						fetchLatLon();
						}else{
							Toast.makeText(BarsList.this, "No Internet Connection",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(HttpConnectionUtil.checkInternetConn(BarsList.this)){
						AppConstants.position = position;
						Intent i = new Intent(BarsList.this, BarDetails.class);
						startActivity(i);
						}else{
							Toast.makeText(BarsList.this, "No Internet Connection",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
			}

			return view;
		}
	}

	public void fetchLatLon() {
		progress = ProgressDialog.show(this, "", "Loading...", true);
		progress.setCancelable(true);
		new Thread() {
			public void run() {
				String req_str = data.getAddress() + ',' + data.getCity() + ','
						+ data.getState() + "," + data.getZip();
				try {
					AppConstants.latlongArr = HttpConnectionUtil
							.getLatLong(req_str);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				runOnUiThread(new Thread() {
					public void run() {
						progress.dismiss();
						if ((AppConstants.latlongArr != null)
								&& (AppConstants.latlongArr.length > 1)) {
							Intent i = new Intent(BarsList.this,
									ShowBarLocation.class);
							startActivity(i);
						} else {
							Toast.makeText(BarsList.this,
									"Unable to get the location",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		}.start();
	}
}
