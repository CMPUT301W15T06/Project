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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Loader;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This <code>ClaimantClaimListActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>ClaimList</code> for claimant. This view displays a list of
 * <code>Claim</code>, creates an option menu, have add a new claim option,
 * set a <code>checkForWarn</code> method to check if there is any warning 
 * and error and return a boolean value. It will be used when the claimant 
 * asks to access to the <code>ClaimList</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.util.ArrayList
 * @see java.util.Comparator
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.app.AlertDialog
 * @see android.app.Dialog
 * @see android.content.DialogInterface
 * @see android.content.DialogInterface.OnClickListener
 * @see android.content.Intent
 * @see android.view.Menu
 * @see android.view.MenuItem
 * @see android.view.View
 * @see android.view.View.OnLongClickListener
 * @see android.widget.AdapterView
 * @see android.widget.AdapterView.OnItemClickListener
 * @see android.widget.AdapterView.OnItemLongClickListener
 * @see android.widget.ArrayAdapter
 * @see android.widget.ListView
 * @see android.widget.Toast
 */
public class ClaimantClaimListActivity extends Activity {
	
	/**
	 * Set a ClaimantClaimListController object cclc with initial 
	 * default value null.
	 */
	private ClaimantClaimListController cclc=null;
	/**
	 * Set a Dialog object dialog
	 * 
	 * @see android.app.Dialog
	 * @see android.content.DialogInterface
	 * @see android.content.DialogInterface.OnClickListener
	 */
	private Dialog dialog;
	
	private ProgressDialog pg =null;
	
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
				// TODO Auto-generated method stub
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
							AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantClaimListActivity.this);
							builder.setTitle("Choose the Tags");
							builder.setMultiChoiceItems(AppSingleton.getInstance().getClaimList().toTagList(),list.get(position).toCheckArray(),new DialogInterface.OnMultiChoiceClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which, boolean isChecked) {
									// TODO Auto-generated method stub
									if (isChecked){
										cclc.addTag(AppSingleton.getInstance().getClaimList().getTagList().get(which).getID());
		
									}else if(AppSingleton.getInstance().getCurrentClaim().getTagIDList().contains(AppSingleton.getInstance().getClaimList().getTagList().get(which).getID())){
										cclc.removeTag(AppSingleton.getInstance().getClaimList().getTagList().get(which).getID());
									}
								}
							});
							
							builder.create();  
							builder.show();
							cclc=new ClaimantClaimListController(AppSingleton.getInstance().getCurrentClaim());
							
						}else if (which==2){
							if (list.get(position).getStatus().equals("Submitted")||list.get(position).getStatus().equals("Approved")){
								Toast.makeText(getApplicationContext(), "Can't submit a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
							}else{
								cclc=new ClaimantClaimListController(list.get(position));
								if(checkForWarn(list.get(position))){
									AlertDialog.Builder adb = new AlertDialog.Builder(ClaimantClaimListActivity.this);
									adb.setMessage("You are trying to submit an expense claim that there are fields with missing values or there are any incompleteness indicators on the expense items,are you sure you want to submit?");
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

	/**
	 * Sort the <code>ClaimList</code> by the starting date of the claim.
	 * 
	 * @return a Comparator object
	 * @see java.util.Comparator
	 */
	private Comparator<? super Claim> sortClaim() {
		return new Comparator<Claim>() {
			
			@Override
			public int compare(Claim lhs, Claim rhs) {
				// TODO Auto-generated method stub
				return rhs.getBeginDate().compareTo(lhs.getBeginDate());			
				
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_claim_list, menu);
		return true;
	}
	

	/**
	 * Add a claim to the <code>ClaimList</code> by calling the 
	 * <code>ClaimantAddClaimActivity</code> class.
	 * 
	 * @param m  a MenuItem object
	 * @see android.content.Intent
	 * @see android.view.Menu
	 * @see android.view.MenuItem
	 */
	public void addClaim(MenuItem m){
		Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantAddClaimActivity.class);
		startActivity(intent);
	}

	//http://stackoverflow.com/questions/9814821/show-progressdialog-android Author:dldnh
	public void pushOnline(MenuItem m){
		pg =ProgressDialog.show(this, "Pushing Onling...","Please wait patiently :)", true);
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		  
		        try {
		        	new ESClient().insertClaimList(AppSingleton.getInstance().getClaimList());
		        	AppSingleton.getInstance().setSuc(true);
		        	
		        } catch (Exception e) {
		        	AppSingleton.getInstance().setSuc(false);
		        
		        }
		        
		        runOnUiThread(new Runnable() {
		            @Override
		            public void run()
		            {
		              pg.dismiss();
		            }
		          });
		    }
		});

		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		if(AppSingleton.getInstance().isSuc()){
			Toast.makeText(getApplicationContext(), "Push online successfully!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(getApplicationContext(), "Push Online Failed,No Internet Connection!", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void manageTag(MenuItem m){
		Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantTagListActivity.class);
		startActivity(intent);	
	}
	/**
	 * This method will check the missValue and flag to justify if the claim,
	 * item and destination have any errors.
	 * 
	 * @param claim  a Claim object
	 * @return result  a boolean variable
	 */
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
			if(item.getRecipt().getPhotoStr()==null){
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
	
	/**
	 * Return the Dialog object dialog. This method will be used when 
	 * other class need to use or display the dialog. 
	 * 
	 * @return dialog  a Dialog variable
	 * @see android.app.Dialog
	 * @see android.content.DialogInterface
	 * @see android.content.DialogInterface.OnClickListener
	 */
	public Dialog getDialog() {
		return dialog;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args){
		super.onPrepareDialog(id, dialog, args);
		this.dialog = dialog;
	}
}
