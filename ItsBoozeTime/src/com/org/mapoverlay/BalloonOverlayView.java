/***
 * Copyright (c) 2010 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.org.mapoverlay;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract.Constants;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.booze.time.R;
import com.booze.util.AppConstants;
import com.google.android.maps.OverlayItem;

/**
 * A view representing a MapView marker information balloon.
 * <p>
 * This class has a number of Android resource dependencies:
 * <ul>
 * <li>drawable/balloon_overlay_bg_selector.xml</li>
 * <li>drawable/balloon_overlay_close.png</li>
 * <li>drawable/balloon_overlay_focused.9.png</li>
 * <li>drawable/balloon_overlay_unfocused.9.png</li>
 * <li>layout/balloon_map_overlay.xml</li>
 * </ul>
 * </p>
 * 
 * @author Jeff Gilfelt
 * 
 */
public class BalloonOverlayView extends FrameLayout implements OnClickListener {

	private LinearLayout layout;
	private TextView barName;
	private TextView address;
	//private TextView email_popup;
	//private TextView contact_popup;
	//private TextView more;
	private Context c;
	//private TextView txt_currloc;

	/**
	 * Create a new BalloonOverlayView.
	 * 
	 * @param context
	 *            - The activity context.
	 * @param balloonBottomOffset
	 *            - The bottom padding (in pixels) to be applied when rendering
	 *            this view.
	 */
	public BalloonOverlayView(Context context, int balloonBottomOffset) {

		super(context);
		c = context;
		//Constants.context_var = c;
		setPadding(10, 0, 10, balloonBottomOffset);
		layout = new LinearLayout(context);
		layout.setVisibility(VISIBLE);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.balloon_overlay, layout);
		barName = (TextView) v.findViewById(R.id.barNameM);
		address = (TextView) v.findViewById(R.id.barAddressM);

		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Implement onClick for popup in map
			}
		});

		ImageView close = (ImageView) v.findViewById(R.id.close_img_button);
		close.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				layout.setVisibility(GONE);
			}
		});
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params.gravity = Gravity.NO_GRAVITY;

		addView(layout, params);

	}

	/**
	 * Sets the view data from a given overlay item.
	 * 
	 * @param item
	 *            - The overlay item containing the relevant view data (title
	 *            and snippet).
	 */
	public void setData(OverlayItem item) {
       System.out.println("TITLE "+item.getTitle());
		layout.setVisibility(VISIBLE);
		// if (item.getTitle() != null) {
		System.out.println("HERE IN BALOON VIEWWWWWWWWWWWWWWWWWWW");
		// System.out.println("SIZEEEEE "+ShowMapActivity.dealersDataList.size());
		if(AppConstants.check){
		System.out.println("HERE IN SET DATA MAP "+AppConstants.barListData.get(AppConstants.position).getBarName());
		barName.setText(AppConstants.barListData.get(AppConstants.position).getBarName());
		address.setText(AppConstants.barListData.get(AppConstants.position).getAddress());
		}else{
			System.out.println("GERE I AM SET DATA ***");
			System.out.println("POSTION "+AppConstants.pos);
			System.out.println("SIZE "+AppConstants.nbrDtlList.size());
			//System.out.println("SIZE "+AppConstants.nbrDtlList.get(1).getNbrDtlBarName());
			//System.out.println("getNbrDtlHoodName "+AppConstants.nbrDtlList.get(AppConstants.pos).getNbrDtlHoodName());
			//System.out.println("POSTION "+AppConstants.nbrDtlList.get(AppConstants.position).getNbrDtlBarName());
			barName.setText(AppConstants.nbrDtlList.get(AppConstants.pos).getNbrDtlHoodName());
			address.setText(AppConstants.nbrDtlList.get(AppConstants.pos).getNbrDtlBarName());
		}
	}

	@Override
	public void onClick(View v) {

		
	}
}
