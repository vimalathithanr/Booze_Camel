package com.booze.time;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.booze.util.AppConstants;
import com.booze.util.HttpConnectionUtil;

public class BarDetails extends Activity implements OnClickListener {
	private TextView barname;
	private TextView baraddress;
	private TextView baroffers;
	private Button getMap;
    private ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bardtls);
		barname = (TextView) findViewById(R.id.barname);
		baraddress = (TextView) findViewById(R.id.baraddress);
		baroffers = (TextView) findViewById(R.id.baroffers);
		getMap = (Button) findViewById(R.id.getMap);
		getMap.setOnClickListener(this);
		barname.setText(AppConstants.barListData.get(AppConstants.position)
				.getBarName());
		baraddress.setText("Address: \n"
				+ AppConstants.barListData.get(AppConstants.position)
						.getAddress()
				+ "\n"
				+ AppConstants.barListData.get(AppConstants.position).getCity()
				+ "\n"+ AppConstants.barListData.get(AppConstants.position).getState()+"\n"
				+ AppConstants.barListData.get(AppConstants.position)
						.getZip() +"\n"+ AppConstants.barListData.get(AppConstants.position)
						.getPhoneNum() +"\n"+"Neighbourhood: "+AppConstants.barListData.get(AppConstants.position)
						.getNeighBourHood());
		
		System.out.println("HERE PHONE NUMBER *&*&*&* "+ AppConstants.barListData.get(AppConstants.position)
						.getPhoneNum());
		System.out.println("DAY OF WEEK FUNCTION RETURNS "
				+ HttpConnectionUtil.getDayOfTheWeek());
		// baroffers.setText("Offers: "+AppConstants.barListData.get(AppConstants.position).getSpecials());
		if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase("Monday")) {
			if (AppConstants.barListData.get(AppConstants.position).getMonday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getMonday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getMonday().length() > 1)) {
				baroffers.setText("Offer on Monday : \n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getMonday());
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Tuesday")) {
			if (AppConstants.barListData.get(AppConstants.position)
					.getTuesday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getTuesday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getTuesday().length() > 1)) {
				baroffers.setText("Offer on Tuesday :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getTuesday());
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Wednesday")) {
			if (AppConstants.barListData.get(AppConstants.position)
					.getWednesday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getWednesday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getWednesday().length() > 1)) {
				baroffers.setText("Offer on Wednesday :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getWednesday());
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer : \n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Thursday")) {
			if (AppConstants.barListData.get(AppConstants.position)
					.getThursday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getThursday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getThursday().length() > 1)) {
				baroffers.setText("Offer on Thursday : \n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getThursday());
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Friday")) {
			if (AppConstants.barListData.get(AppConstants.position).getFriday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getFriday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getFriday().length() > 1)) {
				baroffers.setText("Offer on Friday : \n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getFriday());
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer : \n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Saturday")) {
			if (AppConstants.barListData.get(AppConstants.position)
					.getSaturday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSaturday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSaturday().length() > 1)) {
				baroffers.setText("Offer on Saturday :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSaturday());
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Sunday")) {
			System.out.println(" IN ELSE IF SUNDAY ***");
			if (AppConstants.barListData.get(AppConstants.position).getSunday() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSunday().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSunday().length() > 1)) {
				baroffers.setText("Offer on Sunday :"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSunday());
				System.out.println("**********************");
			} else if (AppConstants.barListData.get(AppConstants.position)
					.getSpecials() != null
					&& !(AppConstants.barListData.get(AppConstants.position)
							.getSpecials().equalsIgnoreCase("null"))
					&& (AppConstants.barListData.get(AppConstants.position)
							.getSpecials().length() > 1)) {
				baroffers.setText("Today's Offer :\n"
						+ AppConstants.barListData.get(AppConstants.position)
								.getSpecials());
			} else {
				System.out.println("SETTING NO OFFERS");
				baroffers.setText("No offers");
			}
		} else if (AppConstants.barListData.get(AppConstants.position)
				.getSpecials() != null
				&& !(AppConstants.barListData.get(AppConstants.position)
						.getSpecials().equalsIgnoreCase("null"))
				&& (AppConstants.barListData.get(AppConstants.position)
						.getSpecials().length() > 1)) {
			baroffers.setText("Today's Offer \n:"
					+ AppConstants.barListData.get(AppConstants.position)
							.getSpecials());
		} else {
			System.out.println("SETTING NO OFFERS");
			baroffers.setText("No offers");
		}
	}

	@Override
	public void onClick(View v) {
        
		fetchLatLon();
	}
	
	public void fetchLatLon() {
		progress = ProgressDialog.show(this, "", "Loading...", true);
		progress.setCancelable(true);
		new Thread() {
			public void run() {
				String req_str = AppConstants.barListData.get(AppConstants.position).getAddress() + ',' + AppConstants.barListData.get(AppConstants.position).getCity() + ','
						+ AppConstants.barListData.get(AppConstants.position).getState() + "," + AppConstants.barListData.get(AppConstants.position).getZip();
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
							Intent i = new Intent(BarDetails.this,
									ShowBarLocation.class);
							startActivity(i);
							finish();
						} else {
							Toast.makeText(BarDetails.this,
									"Unable to get the location",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		}.start();
	}
}
