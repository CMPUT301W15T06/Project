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
package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ClaimantItemListActivity extends Activity {

	/**
	 * Set a FlagController object fc with the initial 
	 * default value of null.
	 */
	private FlagController fc=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_item_list);
		
		
		ListView listView = (ListView) findViewById(R.id.itemListView);
		final ArrayList<Item> list =AppSingleton.getInstance().getCurrentClaim().getItemList();
		final ArrayAdapter<Item> adapter=new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		
		
		AppSingleton.getInstance().getCurrentClaim().addListener(new Listener() {
			
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
				builder.setItems(R.array.item_dialog_array, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (which ==0){
							fc=new FlagController(list.get(position));
							try {
								fc.changeFlag();
							} catch (StatusException e) {
								Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
							}
										
						}else if (which==1){
							Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantReceiptActivity.class);
							startActivity(intent);					
						
						}else if (which==2){
							Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantItemDetailActivity.class);
							startActivity(intent);
						}
						
					}
				});
				builder.create();  
				builder.show();
				
			}
			
		});
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
		Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantAddItemActivity.class);
		startActivity(intent);		
	}
}
