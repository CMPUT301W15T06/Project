package ca.ualberta.CMPUT301W15T06;

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
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

		final TextView tv=(TextView)findViewById(R.id.llTextView);

		image.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Location location=new Location("");
				location.setLatitude(transYToLoc(event.getY()));
				location.setLongitude(transXToLoc(event.getX()));
				try {
					AppSingleton.getInstance().getMapController().setLocation(location);
					tv.setText("Lat: " + transYToLoc(event.getY())
							+ "\nLong: " +transXToLoc(event.getX()));
				} catch (NetWorkException e) {
					throw new RuntimeException(e);
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(GetLocationByMapActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}
				
				
				return true;
			}

			
		});
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
		getMenuInflater().inflate(R.menu.get_location_by_map, menu);
		return true;
	}

}
