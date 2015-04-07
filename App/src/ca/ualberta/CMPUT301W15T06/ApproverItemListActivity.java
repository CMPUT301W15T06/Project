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

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This <code>ApproverItemListActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>Item</code> list for approver. This view displays a list of
 * <code>Item</code> and creates an option menu and return true as 
 * the boolean value. It will be used when the approver asks to access to 
 * the <code>Item</code> list. 
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 */
public class ApproverItemListActivity extends Activity {
	/**
	 * Set an unchangeable integer variable PHOTO_RECEIPT to 0.
	 * Set an unchangeable integer variable LOCATION to 1.
	 */
	private static final int PHOTO_RECEIPT = 0;
	private static final int LOCATION = 1;
	/**
	 * Set a <code>Claim</code> object claim which contains all the detail of a claim.
	 */
	private Claim claim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_item_list);
		
		setTitle(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
				+"<-"+AppSingleton.getInstance().getCurrentClaim().getName()+"<-"+"Approver: "+AppSingleton.getInstance().getUserName());

		claim=AppSingleton.getInstance().getCurrentClaim();
	
		ListView listView = (ListView) findViewById(R.id.approverItemListView);
		final ArrayList<Item> list =claim.getItemList();
		final ArrayAdapter<Item> adapter=new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		


		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AppSingleton.getInstance().setCurrentItem(adapter.getItem(position));
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ApproverItemListActivity.this);
				builder.setTitle(R.string.title_item_dialog);
				itemChoice(builder);
				builder.create();  
				builder.show();
				
			}
			
		});
	}
	
	/**
	 * This method will gives user options to choose for further functions.
	 * 
	 * @param builder  a integer from 0 and 1 with coresponding options
	 */
	public void itemChoice(Builder builder){
		builder.setItems(R.array.approver_item_dialog_array, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				click(which);			
			}

			
		});
	}
	
	/**
	 * This method will allows approver to get a photo receipt or the location of the expense item after choosing an option.
	 */
	private void click(int which) {
		if (which==PHOTO_RECEIPT){
			Intent intent =new Intent(ApproverItemListActivity.this,ClaimantReceiptActivity.class);
			startActivity(intent);					
		
		}else if (which==LOCATION){
			if(AppSingleton.getInstance().getCurrentItem().getLocation()==null){
				Toast.makeText( ApproverItemListActivity.this, "This item doesn't have geolocation!", Toast.LENGTH_LONG).show();		
			}else{
				AppSingleton.getInstance().setLocation(AppSingleton.getInstance().getCurrentItem().getLocation());
				Intent intent =new Intent(ApproverItemListActivity.this,ShowLocationActivity.class);
				startActivity(intent);
			}
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.	
		return false;
	}

}
