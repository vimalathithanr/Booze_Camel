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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.booze.util.AppConstants;
import com.booze.util.HttpConnectionUtil;
import com.booze.util.NbrDetailsData;

public class NbrDetailsActivity extends Activity implements OnClickListener {
	private NbrDetailsData data;
	private ListView nbrDtlsListView;
	private CustomListAdapter nbrDtlslistadapter;
	private ProgressDialog progress;
	private Button viewall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nbrdtlslist);
		nbrDtlsListView = (ListView) findViewById(R.id.barslistview);
		nbrDtlslistadapter = new CustomListAdapter(NbrDetailsActivity.this,
				R.layout.nbrbarlistviewitem, AppConstants.nbrDtlList);
		nbrDtlsListView.setAdapter(nbrDtlslistadapter);

		viewall = (Button) findViewById(R.id.viewall);
		viewall.setOnClickListener(this);
	}

	class CustomListAdapter extends ArrayAdapter<NbrDetailsData> {

		public CustomListAdapter(Context context, int textViewResourceId,
				List<NbrDetailsData> objects) {
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
					view = inflater.inflate(R.layout.nbrbarlistviewitem, null);
				} else {
					// Use convertView if it is available
					view = convertView;
				}

				TextView barName = (TextView) view.findViewById(R.id.bar_name);
				ImageView getbar_location = (ImageView) view
						.findViewById(R.id.getbar_location);
				data = getItem(position);
				System.out.println("position  ** " + position);
				barName.setText(data.getNbrDtlBarName());

				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// System.out.println("POSITION "+position);
						// System.out.println("BAR NAME "+AppConstants.barListData.get(position).getBarName());
						// Toast.makeText(BarsList.this, position,
						// Toast.LENGTH_SHORT).show();
						AppConstants.position = position;
						Intent i = new Intent(NbrDetailsActivity.this,
								NbrDesc.class);
						startActivity(i);
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
			}

			return view;
		}
	}

	@Override
	public void onClick(View v) {

		if (v == viewall) {
			if (HttpConnectionUtil.checkInternetConn(NbrDetailsActivity.this)) {
				Intent i = new Intent(this, ShowViewAll.class);
				startActivity(i);
			}
		} else {
			Toast.makeText(NbrDetailsActivity.this, "No Internet Connection",
					Toast.LENGTH_SHORT).show();
		}
	}
}
