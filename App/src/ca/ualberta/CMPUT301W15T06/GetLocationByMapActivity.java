/*
UA CMPUT 301 Project Group: CMPUT301W15T06

Copyright {2015} {Jingjiao Ni

              Tianqi Xiao

              Jiafeng Wu

              Xinyi Pan 

              Xinyi Wu

              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.

 */

package ca.ualberta.CMPUT301W15T06;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The <code>Destination</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a <code>Destination</code> with a name and a reason. 
 * This method will be called when users need to add a <code>Destination</code>,
 * edit a <code>Destination</code> or access to <code>Destination</code> detail.
 * 
 * @author CMPUT301W15T06
 * @version 004/07/2015
 */
public class GetLocationByMapActivity extends Activity {

	private int width;
	private int height;
	private TextView tv;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location_by_map);
		
		setTitle("User: "+AppSingleton.getInstance().getUserName());


		
		final ImageView image=(ImageView)findViewById(R.id.getLocationImageView);
		ViewTreeObserver vto = image.getViewTreeObserver();
		vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

			public boolean onPreDraw() {
		        // Remove after the first run so it doesn't fire forever
		        image.getViewTreeObserver().removeOnPreDrawListener(this);
		        height = image.getMeasuredHeight();
		        width = image.getMeasuredWidth();	       
		        return true;
		    }
		});

		tv=(TextView)findViewById(R.id.llTextView);

		image.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Location location=new Location("");
				location.setLatitude(transYToLoc(event.getY()));
				location.setLongitude(transXToLoc(event.getX()));
				try {
					AppSingleton.getInstance().getMapController().setLocation(location);
					tv.setText("Lat: " + location.getLatitude()
							+ "\nLong: " +location.getLongitude());
				} catch (NetWorkException e) {
					throw new RuntimeException(e);
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(GetLocationByMapActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}
				
				
				return true;
			}

			
		});
		
		
		final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null){
			
			try {
				AppSingleton.getInstance().getMapController().setLocation(location);
				tv.setText("Lat: " + location.getLatitude()
						+ "\nLong: " +location.getLongitude());
			} catch (NetWorkException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} catch (StatusException e) {
				// TODO Auto-generated catch block
				Toast.makeText( GetLocationByMapActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
			}
			
		
		}				
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
	}

	/**
	 * Set the x axis of the map
	 * 
	 * @param x  a float which used as x axis number of map
	 * @return x axis of the map
	 */
	private double transYToLoc(float x) {
		// TODO Auto-generated method stub
		double y= ((((height/2)-x)/(height/2))*Math.PI);
		
		return (((((Math.atan(Math.pow(Math.E, y)))/Math.PI)*180)-45)*2);
	}
	
	/**
	 * Set the y axis of the map
	 * 
	 * @param y  a float which used as y axis number of map
	 * @return y axis of the map
	 */
	private double transXToLoc(float x) {
		// TODO Auto-generated method stub
		return (((width/2)-x)/(width/2))*180;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
	
	/**
	 * This method will notify all the Listener in both listeners and modelListener about adding a new location. If can not 
	 * process successfully, a warning message will print on the screen.
	 */
	private final LocationListener listener = new LocationListener() {
		public void onLocationChanged (Location location) {

			if (location != null) {
				try {
					AppSingleton.getInstance().getMapController().setLocation(location);
					tv.setText("Lat: " + location.getLatitude()
							+ "\nLong: " +location.getLongitude());
				} catch (NetWorkException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText( GetLocationByMapActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}
			}
		}
		
		public void onProviderDisabled (String provider) {
			
		}
		
		public  void onProviderEnabled (String provider) {
			
		}
		
		public void onStatusChanged (String provider, int status, Bundle extras) {
			
		}
	};

}
