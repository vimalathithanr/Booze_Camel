package com.booze.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import org.apache.http.HttpVersion;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.net.ConnectivityManager;

public class HttpConnectionUtil {

	public static DefaultHttpClient getClient() {
		DefaultHttpClient ret = null;

		// sets up parameters
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "utf-8");
		params.setBooleanParameter("http.protocol.expect-continue", false);

		// registers schemes for both http and https
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		final SSLSocketFactory sslSocketFactory = SSLSocketFactory
				.getSocketFactory();
		sslSocketFactory
				.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		registry.register(new Scheme("https", sslSocketFactory, 443));

		ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(
				params, registry);
		ret = new DefaultHttpClient(manager, params);
		return ret;
	}

	// For getting the latitude and longitude of a particular address
	public static String[] getLatLong(String address) throws IOException {

		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in));
		System.out.println("Inside Lan N Lon func :adress  " + address);
		System.out.println("Please enter a postcode:");
		StringBuffer input = new StringBuffer();
		String data = null;
		String latLong = null;
		String link = "http://maps.google.com/maps?q="
				+ URLEncoder.encode(address, "UTF-8");
		System.out.println("Link " + link);
		URL url = new URL(link);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				connection.getInputStream()), 8192);
		if (connection.getResponseCode() == 200) {
			System.out.println("Success");
			while ((data = br.readLine()) != null)
				input.append(data);
		}
		int x = input.indexOf("lat:");
		int y = input.indexOf("}", x);

		latLong = input.substring(x, y);
		latLong = latLong.replaceAll("lat:", "");
		latLong = latLong.replaceAll("lng:", "");
		String[] array = latLong.split(",");
		System.out.println("Latitude: " + array[0] + " and Longitude: "
				+ array[1] + " for " + address);
		return array;
	}

	public static String getDayOfTheWeek() {
		String str = "";
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("Day IN INT " + day);
		switch (day) {
		case 1:
			str = "SUNDAY";
			break;
		case 2:
			str = "MONDAY";
			break;
		case 3:
			str = "TUESDAY";
			break;
		case 4:
			str = "WEDNSEDAY";
			break;
		case 5:
			str = "THURSDAY";
			break;
		case 6:
			str = "FRIDAY";
			break;
		case 7:
			str = "SATURDAY";
			break;

		default:
			str = "NO OFFERS FOUND";
			break;
		}
		System.out.println("SWITCH RETURNING VALU "+str);
		return str;
	}
	
	public static boolean checkInternetConn(Context ctx){
		
		ConnectivityManager conMgr = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (conMgr.getActiveNetworkInfo() == null
				|| (!conMgr.getActiveNetworkInfo().isAvailable())
				|| (!conMgr.getActiveNetworkInfo().isConnected())) {
			return false; //if there is no internet connection
		}
		return true;
	}
}
