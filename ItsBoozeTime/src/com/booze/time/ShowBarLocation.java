package com.booze.time;

import java.util.List;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
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

public class ShowBarLocation extends MapActivity {

	private MapView map;
	List<Overlay> mapOverlays;
	MyItemizedOverlay itemizedOverlay;
	private Drawable marker;
	OverlayItem overlayItem;
	GeoPoint point;
	private boolean frmNbrDesc = false;

	// private int position = 0;
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showbar);
		Intent i = getIntent();
		Bundle b = i.getExtras();
		try {
			frmNbrDesc = b.getBoolean("FromNbrDesc");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			frmNbrDesc = false;
			e1.printStackTrace();
		}
		System.out.println("NBR DESC BOOLEAN "+frmNbrDesc);
		try {
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
			point = new GeoPoint(
					(int) (Double.parseDouble(AppConstants.latlongArr[0]) * 1E6),
					(int) (Double.parseDouble(AppConstants.latlongArr[1]) * 1E6));
			if (!frmNbrDesc) {
				overlayItem = new OverlayItem(point, AppConstants.barListData
						.get(AppConstants.position).getBarName(),
						AppConstants.barListData.get(AppConstants.position)
								.getAddress()
								+ ","
								+ AppConstants.barListData.get(
										AppConstants.position).getCity()
								+ ","
								+ AppConstants.barListData.get(
										AppConstants.position).getState());
			} else {
				overlayItem = new OverlayItem(point, AppConstants.nbrDtlList
						.get(AppConstants.position).getNbrDtlBarName(),
						AppConstants.nbrDtlList.get(AppConstants.position)
								.getNbrDtlAddress()
								+ ","
								+ AppConstants.nbrDtlList.get(
										AppConstants.position).getNbrDtlCity()
								+ ","
								+ AppConstants.nbrDtlList.get(
										AppConstants.position).getNbrDtlState());
			}
			itemizedOverlay.addOverlay(overlayItem);
			mapOverlays.add(itemizedOverlay);
//			System.out.println("IN MAP BAR NAME *** "
//					+ AppConstants.barListData.get(AppConstants.position)
//							.getBarName());
			final MapController mc = map.getController();
			mc.animateTo(point);
			mc.setZoom(15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "Unable to show the location ",
					Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
