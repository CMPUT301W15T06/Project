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

public class GetLocationByMapActivity extends Activity {

	private int width;
	private int height;
	private TextView tv;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_location_by_map);
		
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

	private double transYToLoc(float x) {
		// TODO Auto-generated method stub
		double y= ((((height/2)-x)/(height/2))*Math.PI);
		
		return (((((Math.atan(Math.pow(Math.E, y)))/Math.PI)*180)-45)*2);
	}
	
	private double transXToLoc(float x) {
		// TODO Auto-generated method stub
		return (((width/2)-x)/(width/2))*180;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
	
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
