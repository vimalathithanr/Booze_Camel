package com.booze.time;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.booze.util.AppConstants;
import com.booze.util.BarData;
import com.booze.util.HttpConnectionUtil;
import com.booze.util.NbrHoodData;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnViewProducts;
	private Button btnNeighbourhood;
	private String[] validWords = new String[] { "", "Chicago", "Indianapolis",
			"Boston" };
	private AutoCompleteTextView search_str;
	private ProgressDialog progress;
	private static final String TAG_PRODUCTS = "mastertable";
	private String strMsg = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
		btnNeighbourhood = (Button) findViewById(R.id.btnNeighbourhood);
		btnViewProducts.setOnClickListener(this);
		btnNeighbourhood.setOnClickListener(this);

		search_str = (AutoCompleteTextView) findViewById(R.id.search_str);
		search_str.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, validWords));
		search_str.setValidator(new Validator());
		search_str.setOnFocusChangeListener(new FocusListener());
	}

	class Validator implements AutoCompleteTextView.Validator {

		public boolean isValid(CharSequence text) {
			Log.v("Test", "Checking if valid: " + text);
			Arrays.sort(validWords);
			if (Arrays.binarySearch(validWords, text.toString()) > 0) {
				return true;
			}

			return false;
		}

		public CharSequence fixText(CharSequence invalidText) {
			Log.v("Test", "Returning fixed text");

			/*
			 * I'm just returning an empty string here, so the field will be
			 * blanked, but you could put any kind of action here, like popping
			 * up a dialog?
			 * 
			 * Whatever value you return here must be in the list of valid
			 * words.
			 */
			return "";
		}
	}

	class FocusListener implements View.OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			Log.v("Test", "Focus changed");
			if (v.getId() == R.id.search_str && !hasFocus) {
				Log.v("Test", "Performing validation");
				((AutoCompleteTextView) v).performValidation();
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (HttpConnectionUtil.checkInternetConn(MainActivity.this)) {
			if (v == btnViewProducts) {
				AppConstants.check = true;
				if (search_str.getText().toString().length() > 1) {
					progress = ProgressDialog
							.show(this, "", "Loading...", true);
					progress.setCancelable(true);

					new Thread() {
						public void run() {
							try {
								if (HttpConnectionUtil
										.checkInternetConn(MainActivity.this)) {
									AppConstants.barListData = loadingData(search_str
											.getText().toString());
									runOnUiThread(new Thread() {
										public void run() {
											progress.dismiss();
											if (AppConstants.barListData.size() > 0) {
												System.out
														.println("HERE IA N **** "
																+ AppConstants.barListData
																		.get(0)
																		.getCity());
												Intent i = new Intent(
														MainActivity.this,
														BarsList.class);
												startActivity(i);
											} else {
												Toast.makeText(
														MainActivity.this,
														"No Bars Found",
														Toast.LENGTH_SHORT)
														.show();
											}
										}
									});
								} else {
									strMsg = "No Internet Connection";
								}
							} catch (final Exception e) {
								runOnUiThread(new Thread() {
									@Override
									public void run() {
										progress.dismiss();
										Toast.makeText(MainActivity.this,
												strMsg, Toast.LENGTH_SHORT)
												.show();
										e.printStackTrace();
									}
								});
							}
						}
					}.start();

				} else {
					Toast.makeText(MainActivity.this,
							"Please enter a valid city name",
							Toast.LENGTH_SHORT).show();
				}
			} else if (v == btnNeighbourhood) {
				AppConstants.check = false;
				if (search_str.getText().toString().length() > 1) {
					progress = ProgressDialog
							.show(this, "", "Loading...", true);
					progress.setCancelable(true);
					new Thread() {
						public void run() {
							try {
								AppConstants.nbrListData = loadingData(search_str
										.getText().toString());
								runOnUiThread(new Thread() {
									public void run() {
										progress.dismiss();
										System.out.println("RUN ON UI  THEREAD");
										System.out.println("SIZE OF ARRAYLIST "
												+ AppConstants.barListData.size());
										if (AppConstants.nbrListData.size() > 0) {
											Intent i = new Intent(
													MainActivity.this,
													NbrListActivity.class);
											startActivity(i);
										} else {
											Toast.makeText(MainActivity.this,
													"No Bars Found",
													Toast.LENGTH_SHORT).show();
										}
									}
								});
							} catch (final Exception e) {
								runOnUiThread(new Thread() {
									@Override
									public void run() {
										progress.dismiss();
										Toast.makeText(MainActivity.this,
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
					Toast.makeText(MainActivity.this, "No Bars Found",
							Toast.LENGTH_SHORT).show();
				}
			}
		} else {
			Toast.makeText(MainActivity.this, "No Internet Connection",
					Toast.LENGTH_SHORT).show();
		}
	}

	private ArrayList loadingData(String str) {
		System.out.println("INDISE MAIN****");
		String getUrl = "";
		if (AppConstants.check) {
			getUrl = "http://www.cs.indiana.edu/cgi-pub/vrajasek/boozecamel/get_all_products.php?city="
					+ str;
		} else {
			getUrl = "http://www.cs.indiana.edu/cgi-pub/vrajasek/boozecamel/get_neighbours.php?city="
					+ str;

		}
		System.out.println("URL " + getUrl);
		DefaultHttpClient httpClient = HttpConnectionUtil.getClient();
		ArrayList<BarData> barDataList = new ArrayList<BarData>();
		ArrayList<NbrHoodData> nbrDataList = new ArrayList<NbrHoodData>();

		HttpResponse response = null;
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
				if (AppConstants.check) { // for bars
					for (int i = 0; i < jArr.length(); i++) {
						BarData data = new BarData();
						JSONObject jO = jArr.getJSONObject(i);
						data.setpID(jO.getString("pid"));
						data.setBarName(jO.getString("bar"));
						data.setAddress(jO.getString("Address"));
						data.setCity(jO.getString("City"));
						data.setState(jO.getString("State"));
						data.setZip(jO.getString("Zip"));
						data.setPhoneNum(jO.getString("Phone"));
						data.setNeighBourHood(jO.getString("Neighborhood"));
						data.setSpecials(jO.getString("Specials"));
						data.setMonday(jO.getString("Monday"));
						data.setTuesday(jO.getString("Tuesday"));
						data.setWednesday(jO.getString("Wednesday"));
						data.setThursday(jO.getString("Thursday"));
						data.setFriday(jO.getString("Friday"));
						data.setSaturday(jO.getString("Saturday"));
						data.setSunday(jO.getString("Sunday"));
						barDataList.add(data);
					}
				} else { // for neighbourhood
					System.out.println("I AM In ELSE BLOCK ");
					for (int i = 0; i < jArr.length(); i++) {
						NbrHoodData data = new NbrHoodData();
						JSONObject jO = jArr.getJSONObject(i);
						data.setNbrHoodName(jO.getString("Neighborhood"));
						data.setNbrZip(jO.getString("Zip"));
						System.out.println("NBR NAME "
								+ jO.getString("Neighborhood"));
						nbrDataList.add(data);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (AppConstants.check) {
			return barDataList;
		} else {
			System.out.println("RETURNING VALUE");
			return nbrDataList;
		}
	}
}
