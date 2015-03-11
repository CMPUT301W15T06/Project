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
import java.util.Comparator;




import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ClaimantClaimListActivity extends Activity {
	private ClaimantClaimListController cclc=null;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_claim_list);
		
		
		
		//set list view of claimlist
		ListView listView = (ListView) findViewById(R.id.claimListView);
		final ArrayList<Claim> list =AppSingleton.getInstance().getClaimList().getClaimList();
		final ArrayAdapter<Claim> adapter=new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1,list);
		adapter.sort(sortClaim());
		listView.setAdapter(adapter);
		
		AppSingleton.getInstance().getClaimList().addListener(new Listener() {
			
			@Override
			public void update() {
				adapter.sort(sortClaim());
				adapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AppSingleton.getInstance().setCurrentClaim(list.get(position));
				Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantItemListActivity.class);
				startActivity(intent);
			}
			
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AppSingleton.getInstance().setCurrentClaim(list.get(position));
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantClaimListActivity.this);
				builder.setTitle(R.string.title_claim_dialog);
				builder.setItems(R.array.claim_dialog_array, new DialogInterface.OnClickListener() {
					
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (which ==0){
							Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantClaimDetailActivity.class);
							startActivity(intent);				
						}else if (which==1){
							
						}else if (which==2){
							if (list.get(position).getStatus().equals("Submitted")||list.get(position).getStatus().equals("Approved")){
								Toast.makeText(getApplicationContext(), "Can't submit a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
							}else{
								cclc=new ClaimantClaimListController(list.get(position));
								if(checkForWarn(list.get(position))){
									AlertDialog.Builder adb = new AlertDialog.Builder(ClaimantClaimListActivity.this);
									adb.setMessage("You are trying to submit an expense claim that there are fields with missing values or there are any incompleteness indicators on the expense items,are you sure you want to submit?");
									adb.setCancelable(true);
									adb.setPositiveButton("Submit", new OnClickListener(){
										@Override
										public void onClick(DialogInterface dialog, int which) {
											try {
												cclc.submit();
											} catch (StatusException e) {
												Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
											}										
										}										
									});
									adb.setNegativeButton("Cancel", new OnClickListener() {					
										@Override
										public void onClick(DialogInterface dialog, int which) {
										}
									});
									
									adb.show();
								}else{
									try {
										cclc.submit();
									} catch (StatusException e) {
										Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
									}
								}
							}
						}else if (which ==3){
							Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantEditClaimActivity.class);
							startActivity(intent);
							
						}else if (which==4){
							cclc=new ClaimantClaimListController(AppSingleton.getInstance().getClaimList());
							try {
								cclc.delete(list.get(position));
							} catch (StatusException e) {
								Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
							}
						}
						
					}
				});
				builder.create();  
				builder.show();
				return false;
			}
			
		});
	}

	private Comparator<? super Claim> sortClaim() {
		return new Comparator<Claim>() {
			
			@Override
			public int compare(Claim lhs, Claim rhs) {
				// TODO Auto-generated method stub
				
				int lh=AppSingleton.getYear(lhs.getBeginDate())*10000+AppSingleton.getMonth(lhs.getBeginDate())*100+AppSingleton.getDay(lhs.getBeginDate());
				int rh=AppSingleton.getYear(rhs.getBeginDate())*10000+AppSingleton.getMonth(rhs.getBeginDate())*100+AppSingleton.getDay(rhs.getBeginDate());
				if(lh<rh){
					return 1;
				}
				return -1;			
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_claim_list, menu);
		return true;
	}
	

	
	public void addClaim(MenuItem m){
		Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantAddClaimActivity.class);
		startActivity(intent);
	}

	private boolean checkForWarn(Claim claim){
		boolean result=false;
		if(claim.getMissValue()){
			result=true;
		}
		
		for(Item item:claim.getItemList()){
			if(item.getMissValue()){
				result=true;
			}
			if(item.getFlag()){
				result=true;
			}
		}
		
		for(Destination dest:claim.getDestinationList()){
			if(dest.getMissValue()){
				result=true;
			}
		}
		return result;
	}
	
	public Dialog getDialog() {
		return dialog;
	}
	
	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args){
		super.onPrepareDialog(id, dialog, args);
		this.dialog = dialog;
	}
}
