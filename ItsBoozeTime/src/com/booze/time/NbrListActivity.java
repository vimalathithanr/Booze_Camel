package com.booze.time;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

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
import com.booze.util.HttpConnectionUtil;
import com.booze.util.LatLonData;
import com.booze.util.NbrDetailsData;
import com.booze.util.NbrHoodData;

public class NbrListActivity extends Activity {
	private NbrHoodData data;
	private ListView nbrbarslistview;
	private CustomListAdapter nbrBarsListAdapter;
	private ProgressDialog progress;
	private static final String TAG_PRODUCTS = "mastertable";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nbrlist);
		nbrbarslistview = (ListView) findViewById(R.id.nbrbarslistview);
		nbrBarsListAdapter = new CustomListAdapter(NbrListActivity.this,
				R.layout.nbrbarlistviewitem, AppConstants.nbrListData);
		nbrbarslistview.setAdapter(nbrBarsListAdapter);
	}

	class CustomListAdapter extends ArrayAdapter<NbrHoodData> {

		public CustomListAdapter(Context context, int textViewResourceId,
				List<NbrHoodData> objects) {
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
				barName.setText(data.getNbrZip());
				/*
				 * getbar_location.setOnClickListener(new OnClickListener() {
				 * 
				 * @Override public void onClick(View v) {
				 * System.out.println("POSITION " + position);
				 * System.out.println("BAR NAME " +
				 * AppConstants.nbrListData.get(position) .getNbrHoodName());
				 * AppConstants.position = position; for (int i = 0; i <
				 * AppConstants.nbrListData.size(); i++) { fetchLatLon(); } }
				 * });
				 */

				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (HttpConnectionUtil
								.checkInternetConn(NbrListActivity.this)) {
							AppConstants.position = position;
							// AppConstants.latlonListData.clear();
							// AppConstants.latlonListData = null;
							System.out.println("POSITION " + position);
							System.out.println("BAR NAME "
									+ AppConstants.nbrListData.get(position)
											.getNbrHoodName());
							progress = ProgressDialog.show(
									NbrListActivity.this, "", "Loading...",
									true);
							progress.setCancelable(true);
							new Thread() {
								public void run() {
									try {
										AppConstants.nbrDtlList = loadingData(AppConstants.nbrListData
												.get(position).getNbrHoodName());
										System.out.println("I AM HERE ***");
										System.out
												.println("IT HAS TO BE 2 For indianapolis  "
														+ AppConstants.nbrDtlList
																.size());
										// UPDATE REQUIRED : Here Actually i <
										// AppConstants.nbrDtlList
										String req = "";

										for (int i = 0; i < AppConstants.nbrDtlList
												.size(); i++) {
											/*
											 * if(i == 0){ req = "Downtown 46225";
											 * //currently hardcoding }else{ req =
											 * "Shadybrook 46240"; }
											 */
											req = AppConstants.nbrDtlList.get(i)
													.getNbrDtlAddress()
													+ " "
													+ AppConstants.nbrDtlList
															.get(i).getNbrDtlCity()
													+ " "
													+ AppConstants.nbrDtlList
															.get(i)
															.getNbrDtlState()
													+ " "
													+ AppConstants.nbrDtlList
															.get(i).getNbrDtlZip();
											// System.out.println("HERE I AM FETCHING LAT AND LONG "+req);
											fetchLatLon(req);
										}

										runOnUiThread(new Thread() {
											public void run() {
												progress.dismiss();
												if ((AppConstants.nbrDtlList != null)
														&& (AppConstants.nbrDtlList
																.size() > 0)) {
													Intent i = new Intent(
															NbrListActivity.this,
															NbrDetailsActivity.class);
													startActivity(i);
												} else {
													Toast.makeText(
															NbrListActivity.this,
															"No Bars Found",
															Toast.LENGTH_SHORT)
															.show();
												}
											}
										});
									} catch (final Exception e) {
										runOnUiThread(new Thread() {
											@Override
											public void run() {
												progress.dismiss();
												Toast.makeText(NbrListActivity.this,
														"Please try after some time", Toast.LENGTH_SHORT)
														.show();
												e.printStackTrace();
											}
										});
										e.printStackTrace();
									}
								}
							}.start();

						} else {
							Toast.makeText(NbrListActivity.this,
									"No Internet Connection",
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

	private void fetchLatLon(String req) {
		String req_str = req;
		AppConstants.latlongArr = null;
		try {
			AppConstants.latlongArr = HttpConnectionUtil.getLatLong(req_str);
			System.out.println("LATITUDE VALUE " + AppConstants.latlongArr[0]);
			System.out.println("LATITUDE VALUE " + AppConstants.latlongArr[1]);
			LatLonData data = new LatLonData();
			data.setLatitude(AppConstants.latlongArr[0]);
			data.setLongitude(AppConstants.latlongArr[1]);
			AppConstants.latlonListData.add(data);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<NbrDetailsData> loadingData(String str) {
		System.out.println("INDISE MAIN****");
		String getUrl = "http://www.cs.indiana.edu/cgi-pub/vrajasek/boozecamel/get_neigh_details.php?pid="
				+ str;

		System.out.println("URL " + getUrl);
		DefaultHttpClient httpClient = HttpConnectionUtil.getClient();
		ArrayList<NbrDetailsData> nbrDtlsDataList = new ArrayList<NbrDetailsData>();

		HttpResponse response = null;
		getUrl = getUrl.replaceAll(" ", "%20");
		HttpGet getMethod = new HttpGet(getUrl);
		try {
			response = httpClient.execute(getMethod);

			// CONVERT RESPONSE TO STRING
			String result = EntityUtils.toString(response.getEntity());
			System.out.println("RESULT*** " + result);
			JSONObject jObj = new JSONObject(result);
			int success = jObj.getInt("success");
			System.out.println("SUCCESS " + success);
			if (success == 1) {
				JSONArray jArr = jObj.getJSONArray(TAG_PRODUCTS);// mastertable
				for (int i = 0; i < jArr.length(); i++) {
					NbrDetailsData data = new NbrDetailsData();
					JSONObject jO = jArr.getJSONObject(i);
					data.setNbrDtlHoodName(jO.getString("Neighborhood"));
					data.setNbrDtlBarName(jO.getString("bar"));
					data.setNbrDtlAddress(jO.getString("Address"));
					data.setNbrDtlCity(jO.getString("City"));
					data.setNbrDtlState(jO.getString("State"));
					data.setNbrDtlZip(jO.getString("Zip"));
					data.setSpecials(jO.getString("Specials"));
					data.setNbrPhoneNumber(jO.getString("Phone"));
					data.setMonday(jO.getString("Monday"));
					data.setTuesday(jO.getString("Tuesday"));
					data.setWednesday(jO.getString("Wednesday"));
					data.setThursday(jO.getString("Thursday"));
					data.setFriday(jO.getString("Friday"));
					data.setSaturday(jO.getString("Saturday"));
					data.setSunday(jO.getString("Sunday"));
					nbrDtlsDataList.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbrDtlsDataList;
	}
}
