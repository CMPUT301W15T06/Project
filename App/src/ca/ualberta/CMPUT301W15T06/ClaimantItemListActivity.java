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
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ClaimantItemListActivity extends Activity {

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
				adapter.notifyDataSetChanged();
				ClaimListManager.getInstance().save();
			}
		});
		
		AppSingleton.getInstance().getCurrentClaim().addListener(new Listener() {
			
			@Override
			public void update() {
				adapter.notifyDataSetChanged();
				ClaimListManager.getInstance().save();
			}
		});


		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AppSingleton.getInstance().setCurrentItem(list.get(position));
				AppSingleton.getInstance().getCurrentItem().addListener(new Listener() {
					
					@Override
					public void update() {
						adapter.notifyDataSetChanged();

					}
				});
				AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantItemListActivity.this);
				builder.setTitle(R.string.title_item_dialog);
				builder.setItems(R.array.item_dialog_array, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (which ==0){
							
										
						}else if (which==1){
						
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
	

	
	public void addItem(View v){
		Intent intent =new Intent(ClaimantItemListActivity.this,ClaimantAddItemActivity.class);
		startActivity(intent);		
	}
}
