package com.booze.time;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.booze.util.AppConstants;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.org.mapoverlay.MyItemizedOverlay;

public class ShowViewAll extends MapActivity {

	private MapView map;
	List<Overlay> mapOverlays;
	MyItemizedOverlay itemizedOverlay;
	private Drawable marker;
	OverlayItem overlayItem;
	GeoPoint point;
	//private int position = 0;
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showbar);
		
		map = (MapView) findViewById(R.id.mapview);
		map.setBuiltInZoomControls(true);
		mapOverlays = map.getOverlays();
		marker = getResources().getDrawable(R.drawable.location_ticker);
		itemizedOverlay = new MyItemizedOverlay(marker, map);
		
		if (!mapOverlays.isEmpty()) {
			mapOverlays.clear();
			map.invalidate();
			System.out.println("111111111111111");
		}
		mapOverlays.clear();
		System.out.println("NBRDTLLIST SIZE "+AppConstants.nbrDtlList.size());
		System.out.println("LATLONGLIST SIZE "+AppConstants.latlonListData.size());
		System.out.println(AppConstants.latlonListData.get(0).getLatitude());
		System.out.println(AppConstants.latlonListData.get(0).getLongitude());


		for(int i = 0; i < AppConstants.nbrDtlList.size(); i++){
			try {
				point = new GeoPoint(
						(int) (Double
								.parseDouble(AppConstants.latlonListData.get(i).getLatitude()) * 1E6),
						(int) (Double
								.parseDouble(AppConstants.latlonListData.get(i).getLongitude()) * 1E6));
				overlayItem = new OverlayItem(
						point,
						AppConstants.nbrDtlList.get(i).getNbrDtlBarName(),
						AppConstants.nbrDtlList.get(i).getNbrDtlHoodName());
				itemizedOverlay.addOverlay(overlayItem);
				mapOverlays.add(itemizedOverlay);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "Sorry, Unable to display maps", Toast.LENGTH_SHORT).show();
				finish();
			}
			
		}
		//System.out.println("IN MAP BAR NAME *** "+AppConstants.barListData.get(AppConstants.position).getBarName());
		final MapController mc = map.getController();
		mc.animateTo(point);
		mc.setZoom(15);
		point = new GeoPoint(
				(int) (Double
						.parseDouble(AppConstants.latlonListData
								.get(0).getLatitude()) * 1E6),
				(int) (Double
						.parseDouble(AppConstants.latlonListData
								.get(0).getLongitude()) * 1E6));
		mc.animateTo(point);
		mc.setZoom(15);
		System.out.println("HERE IS THE CURSORRRR");
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}}
