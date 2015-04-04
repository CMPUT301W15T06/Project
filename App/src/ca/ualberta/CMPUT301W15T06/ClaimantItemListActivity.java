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
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This <code>ClaimantItemListActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>ItemList</code> for claimant. This view displays a list of
 * <code>Item</code>, creates an option menu and have add a new item option.
 * It associated with class <code>FlagController</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.util.ArrayList
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.app.AlertDialog
 * @see android.content.Intent
 * @see android.content.DialogInterface
 * @see android.view.Menu
 * @see android.view.View
 * @see android.widget.AdapterView
 * @see android.widget.ArrayAdapter
 * @see android.widget.Toast
 * @see android.widget.AdapterView.OnItemClickListener
 * @see android.widget.ListView
 */
public class ClaimantItemListActivity extends Activity {

	private static final int PHOTO_RECEIPT = 1;
	private static final int ITEM_DETAIL = 2;
	private static final int CHANGE_FLAG = 0;
	protected static final int SET_LOCATION = 3;
	protected static final int SHOW_LOCATION = 4;
//	/**
//	 * Set a FlagController object 
//	 * default value of null.
//	 */
	private ClaimantItemListController cilc=null;
	private Claim claim=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_item_list);
		
		
		claim=AppSingleton.getInstance().getCurrentClaim();
		cilc=new ClaimantItemListController(claim);
		
		
		
		ListView listView = (ListView) findViewById(R.id.itemListView);
		final ArrayList<Item> list =claim.getItemList();
		final ArrayAdapter<Item> adapter=new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1,list);
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
				AppSingleton.getInstance().setCurrentItem(list.get(position));
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantItemListActivity.this);
				builder.setTitle(R.string.title_item_dialog);
				itemChoice(builder);
				builder.create();  
				builder.show();
				
			}
			
		});
	}
	
	
	public void itemChoice(Builder builder){
		builder.setItems(R.array.item_dialog_array, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (which ==CHANGE_FLAG){					
					changeFlag();							
				}else if (which==PHOTO_RECEIPT){
					Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantReceiptActivity.class);
					startActivity(intent);					
				
				}else if (which==ITEM_DETAIL){
					Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantItemDetailActivity.class);
					startActivity(intent);
				}else if (which==SET_LOCATION){
					AppSingleton.getInstance().setMapController(new MapController() {
						
						@Override
						public void setLocation(Location location) throws NetWorkException, StatusException {
							// TODO Auto-generated method stub
							AppSingleton.getInstance().getCurrentItem().setLocation(location);
						}
					});
					Intent intent =new Intent(ClaimantItemListActivity.this,GetLocationByMapActivity.class);
					startActivity(intent);					
				
				}else if (which==SHOW_LOCATION){
					if(AppSingleton.getInstance().getCurrentItem().getLocation()==null){
						Toast.makeText( ClaimantItemListActivity.this, "This item doesn't have geolocation!", Toast.LENGTH_LONG).show();		
					}else{
						AppSingleton.getInstance().setLocation(AppSingleton.getInstance().getCurrentItem().getLocation());
						Intent intent =new Intent(ClaimantItemListActivity.this,ShowLocationActivity.class);
						startActivity(intent);
					}
				}
				
			}
		});
	}
	
	public void changeFlag(){
		try {
			cilc.changeFlag();
		} catch (StatusException e) {
			Toast.makeText(ClaimantItemListActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_item_list, menu);
		return true;
	}
	
	/**
	 * Add a new item to the <code>ItemList</code> by calling the 
	 * <code>ClaimantAddItemActivity</code> class and set a new intent.
	 * 
	 * @param v  a View object
	 * @see android.content.Intent
	 */
	public void addItem(View v){
		try {
			cilc.addItem();
			Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantEditItemActivity.class);
			startActivity(intent);	
		} catch (StatusException e) {
			// TODO Auto-generated catch block
			Toast.makeText(ClaimantItemListActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}	catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
	}
	
	public void detail(MenuItem m){
		Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantClaimDetailActivity.class);
		startActivity(intent);
	}
	
	public void submitClaim(MenuItem m){
		if (claim.getMissValue()){
			AlertDialog.Builder adb = new AlertDialog.Builder(ClaimantItemListActivity.this);
			adb.setMessage("You are trying to submit an expense claim that there are fields with missing values or there are any incompleteness indicators on the expense items,are you sure you want to submit?");
			adb.setPositiveButton("Submit", new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					sureSubmit();									
				}										
			});
			adb.setNegativeButton("Cancel", new OnClickListener() {					
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			
			adb.show();		
		}else{
			
			sureSubmit();	
		}

	}


	public void sureSubmit(){
		
		try {
			cilc.sumbmit();
		} catch (StatusException e) {
			// TODO Auto-generated catch block
			Toast.makeText(ClaimantItemListActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
	
	}
		
	public void editClaim(MenuItem m){
		if (!AppSingleton.getInstance().isEditable()){
			Toast.makeText(ClaimantItemListActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();			
		}else{
			Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantEditClaimActivity.class);
			startActivity(intent);
		}
		
	}
	public void deleteClaim(MenuItem m){
		try {
			cilc.delete();
			finish();
		} catch (StatusException e) {
			// TODO Auto-generated catch block
			Toast.makeText(ClaimantItemListActivity.this, "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
		}catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
	
	}
	public void changeTag(MenuItem m){
		final User user=AppSingleton.getInstance().getCurrentUser();
		AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantItemListActivity.this);
		builder.setTitle("Choose the Tags");
		builder.setMultiChoiceItems(user.toTagList(),claim.toCheckArray(),new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				try{
					if (isChecked){
						cilc.addTag(user.getTagList().get(which).getID());
					}else if(claim.getTagIDList().contains(user.getTagList().get(which).getID())){
						cilc.removeTag(user.getTagList().get(which).getID());
					}
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}
		});
		
		builder.create();  
		builder.show();
	
	}

}
