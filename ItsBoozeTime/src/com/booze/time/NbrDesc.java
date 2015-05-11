package com.booze.time;

import com.booze.util.AppConstants;
import com.booze.util.HttpConnectionUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NbrDesc extends Activity implements OnClickListener {
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
		baroffers = (TextView) findViewById(R.id.baroffers); // AppConstants.nbrDtlList
		getMap = (Button) findViewById(R.id.getMap);
		getMap.setOnClickListener(this);
		barname.setText(AppConstants.nbrDtlList.get(AppConstants.position)
				.getNbrDtlBarName());
		baraddress.setText("Address: \n"
				+ AppConstants.nbrDtlList.get(AppConstants.position)
						.getNbrDtlAddress()
				+ "\n"
				+ AppConstants.nbrDtlList.get(AppConstants.position)
						.getNbrDtlCity()
				+ "\n"
				+ AppConstants.nbrDtlList.get(AppConstants.position)
						.getNbrDtlState()
				+ "\n"
				+ AppConstants.nbrDtlList.get(AppConstants.position)
						.getNbrDtlZip()
				+ "\n"
				+ AppConstants.nbrDtlList.get(AppConstants.position)
						.getNbrPhoneNumber()+ "\n"
				+ "Neighbourhood: "
				+ AppConstants.nbrDtlList.get(AppConstants.position)
						.getNbrDtlHoodName());

		if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase("Monday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position).getMonday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getMonday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getMonday().length() > 1)) {
				baroffers.setText("Offer on Monday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getMonday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Tuesday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position).getTuesday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getTuesday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getTuesday().length() > 1)) {
				baroffers.setText("Offer on Tuesday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getTuesday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Wednesday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position)
					.getWednesday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getWednesday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getWednesday().length() > 1)) {
				baroffers.setText("Offer on Wednesday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getWednesday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Thursday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position)
					.getThursday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getThursday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getThursday().length() > 1)) {
				baroffers.setText("Offer on Thursday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getThursday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Friday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position).getFriday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getFriday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getFriday().length() > 1)) {
				baroffers.setText("Offer on Friday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getFriday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Saturday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position)
					.getSaturday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getSaturday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getSaturday().length() > 1)) {
				baroffers.setText("Offer on Saturday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getSaturday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		} else if (HttpConnectionUtil.getDayOfTheWeek().equalsIgnoreCase(
				"Sunday")) {
			if (AppConstants.nbrDtlList.get(AppConstants.position).getSunday() != null
					&& !(AppConstants.nbrDtlList.get(AppConstants.position)
							.getSunday().equalsIgnoreCase("null"))
					&& (AppConstants.nbrDtlList.get(AppConstants.position)
							.getSunday().length() > 1)) {
				baroffers.setText("Offer on Sunday : \n"
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getSunday());
			}
			  else if (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials() != null &&
			  !(AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().equalsIgnoreCase("null")) &&
			  (AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials().length() > 1)) {
			  baroffers.setText("Today's Offer \n:" +
			  AppConstants.nbrDtlList.get(AppConstants.position)
			  .getSpecials()); }
			 else {
				baroffers.setText("No offers");
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		fetchLatLon();
	}

	public void fetchLatLon() {
		progress = ProgressDialog.show(this, "", "Loading...", true);
		progress.setCancelable(true);
		new Thread() {
			public void run() {
				String req_str = AppConstants.nbrDtlList.get(
						AppConstants.position).getNbrDtlAddress()
						+ ','
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getNbrDtlCity()
						+ ','
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getNbrDtlState()
						+ ","
						+ AppConstants.nbrDtlList.get(AppConstants.position)
								.getNbrDtlZip();
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
							Intent i = new Intent(NbrDesc.this,
									ShowBarLocation.class);
							i.putExtra("FromNbrDesc", true);
							startActivity(i);
							finish();
						} else {
							Toast.makeText(NbrDesc.this,
									"Unable to get the location",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		}.start();
	}
}
