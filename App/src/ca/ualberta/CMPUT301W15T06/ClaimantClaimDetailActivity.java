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

import java.util.ArrayList;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
* This <code>ClaimantClaimDetailActivity</code> class is an extended class
* of <code>Activity</code> class. This class will control the interface
* of <code>Claim</code> detail for claimant. This view displays 
* <code>Claim</code> details, including claimant's name, travel begin, 
* end date and destination. It also creates an option menu. It will be 
* used when the claimant asks to access to the <code>Claim</code> detail. 
* The associated class including <code>Claim</code> and <code>Destination</code>.
* 
* @author CMPUT301W15T06
* @version 03/16/2015
* @see java.util.ArrayList
* @see android.os.Bundle
* @see android.app.Activity
* @see android.content.Intent
* @see android.view.Menu
* @see android.view.View
* @see android.widget.ArrayAdapter
* @see android.widget.EditText
* @see android.widget.ListView
* @see android.widget.TextView
*/
public class ClaimantClaimDetailActivity extends Activity {

	
	private static final int SHOW_LOCATION = 2;
	private static final int SET_LOCATION_BY_GPS = 0;
	private static final int SET_LOCATION_BY_MAP = 1;
	private ClaimantClaimDetailController ccdc=null;
	private Claim claim;
	private ArrayList<Destination> list;
	private AlertDialog.Builder adb;
	private AlertDialog dia;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_add_destination);
		

		claim=AppSingleton.getInstance().getCurrentClaim();
		ccdc=new ClaimantClaimDetailController(claim);
		
		TextView beginView=(TextView) findViewById(R.id.startDateValueClaimantClaimDetailTextView);
		TextView endView=(TextView) findViewById(R.id.endingDateValueClaimantClaimDetailTextView);
		beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));
		endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
		ListView listView = (ListView) findViewById(R.id.claimantDetailListView);
		list =AppSingleton.getInstance().getCurrentClaim().getDestinationList();
		final ArrayAdapter<Destination> adapter=new ArrayAdapter<Destination>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		

		claim.addListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				adapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AppSingleton.getInstance().setCurrentDestination(list.get(position));
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantClaimDetailActivity.this);
				itemChoice(builder);
				builder.create();  
				builder.show();
				
			}

			
		});

		
	}

	
	private void itemChoice(Builder builder) {
		// TODO Auto-generated method stub
		builder.setItems(R.array.dest_dialog_array, new DialogInterface.OnClickListener() {
			


			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (which ==SET_LOCATION_BY_GPS){
					final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
					Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
					
					adb = new AlertDialog.Builder(ClaimantClaimDetailActivity.this);
					adb.setMessage("Please send a location!");
					adb.setPositiveButton("Stop waiting for location", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							lm.removeUpdates(listener);
						}
					});
					dia=adb.create();  
					dia.show();
					if (location != null){
						
						try {
							ccdc.setLocation(location);
							dia.setMessage("Location set as:\n Lat: " + location.getLatitude()
									+ "\nLong: " + location.getLongitude());
						} catch (NetWorkException e) {
							// TODO Auto-generated catch block
							throw new RuntimeException(e);
						} catch (StatusException e) {
							// TODO Auto-generated catch block
							Toast.makeText( ClaimantClaimDetailActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
						}
						
					
					}				
					
					lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);

										
				}else if (which==SET_LOCATION_BY_MAP){
					AppSingleton.getInstance().setMapController(new MapController() {
						
						@Override
						public void setLocation(Location location) throws NetWorkException, StatusException {
							// TODO Auto-generated method stub
							AppSingleton.getInstance().getCurrentDestination().setLocation(location);
						}
					});
					Intent intent =new Intent(ClaimantClaimDetailActivity.this,GetLocationByMapActivity.class);
					startActivity(intent);					
				
				}else if (which==SHOW_LOCATION){
					if(AppSingleton.getInstance().getCurrentDestination().getLocation()==null){
						Toast.makeText( ClaimantClaimDetailActivity.this, "This destination doesn't have geolocation!", Toast.LENGTH_LONG).show();		
					}else{
						AppSingleton.getInstance().setLocation(AppSingleton.getInstance().getCurrentDestination().getLocation());
						Intent intent =new Intent(ClaimantClaimDetailActivity.this,ShowLocationActivity.class);
						startActivity(intent);
					}
				}
				
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_add_destination, menu);
		return true;
	}
	
	/**
	 * Set up a addDestination view. This method will be called when the 
	 * claimant wants to add a new <code>Destination</code> to a current
	 * <code>Claim</code>.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.content.Intent
	 */
	public void addDestination(View v){
		try {
			ccdc.addDestination();
			Intent intent =new Intent(ClaimantClaimDetailActivity.this,ClaimantEditDestinationActivity.class);
			startActivity(intent);
		} catch (StatusException e) {
			// TODO Auto-generated catch block
			Toast.makeText( ClaimantClaimDetailActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
	}
	
	private final LocationListener listener = new LocationListener() {
		public void onLocationChanged (Location location) {

			if (location != null) {
				try {
					ccdc.setLocation(location);
					dia.setMessage("Location set as:\n Lat: " + location.getLatitude()
							+ "\nLong: " + location.getLongitude());
				} catch (NetWorkException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText( ClaimantClaimDetailActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
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
