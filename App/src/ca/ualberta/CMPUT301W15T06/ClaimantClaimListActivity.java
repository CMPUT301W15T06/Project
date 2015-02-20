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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ClaimantClaimListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_claim_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_claim_list, menu);
		return true;
	}
	
	protected void onResume() {
		super.onResume();
		//set list view of claimlist
		ListView listView = (ListView) findViewById(R.id.claimListView);
		final ArrayList<Claim> list =ClaimListController.getClaimList().getClaimList();
		ArrayAdapter<Claim> adapter=new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ClaimListController.setCurrentClaim(list.get(position));
				AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantClaimListActivity.this);
				builder.setTitle(R.string.title_claim_dialog);
				builder.setItems(R.array.claim_dialog_array, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (which ==0){
							Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantAddDestinationActivity.class);
							startActivity(intent);				
						}else if (which==1){
							Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantItemListActivity.class);
							startActivity(intent);
						}
						
					}
				});
				builder.create();  
				builder.show();
				
			}
			
		});
	}
	
	public void addClaim(MenuItem m){
		Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantAddClaimActivity.class);
		startActivity(intent);
	}

}
