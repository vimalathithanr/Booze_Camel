package com.booze.util;

import java.util.ArrayList;

public class AppConstants {
	public static ArrayList<BarData> barListData = new ArrayList<BarData>();
	public static String latlongArr[];//contains latitude and longitudes
	public static int position = 0;
	public static boolean check = false; //false if neighbour hood clicked
	public static ArrayList<NbrHoodData> nbrListData = new ArrayList<NbrHoodData>();
	public static ArrayList<LatLonData> latlonListData = new ArrayList<LatLonData>();//contains lat& lon values
	public static ArrayList<NbrDetailsData> nbrDtlList = new ArrayList<NbrDetailsData>();
	public static int pos; //used for view all maps index
}
