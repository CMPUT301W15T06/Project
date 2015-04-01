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

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Typeface;
import android.text.method.DigitsKeyListener;
import android.text.style.BulletSpan;
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
import android.widget.TextView;
import android.widget.Toast;

/**
 * This <code>ClaimantClaimListActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>User</code> for claimant. This view displays a list of
 * <code>Claim</code>, creates an option menu, have add a new claim option,
 * set a <code>checkForWarn</code> method to check if there is any warning 
 * and error and return a boolean value. It will be used when the claimant 
 * asks to access to the <code>User</code>.
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
	
	private User user;
	/**
	 * Set a Dialog object dialog
	 * 
	 * @see android.app.Dialog
	 * @see android.content.DialogInterface
	 * @see android.content.DialogInterface.OnClickListener
	 */
	private Dialog dialog;
	
	private ProgressDialog pg =null;
	
	private ArrayList<Claim> list=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_claim_list);
		
		
		user=AppSingleton.getInstance().getCurrentUser();
		cclc=new ClaimantClaimListController(user);
		
		//set list view of claimlist
		ListView listView = (ListView) findViewById(R.id.claimListView);
		setList();
		final ArrayAdapter<Claim> adapter=new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1,list);
		adapter.sort(sortClaim());
		listView.setAdapter(adapter);

		user.addListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				setList();
				adapter.clear();
				adapter.addAll(list);
				adapter.sort(sortClaim());
				adapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AppSingleton.getInstance().setCurrentClaim(adapter.getItem(position));
				
				Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantItemListActivity.class);
				startActivity(intent);
			}
			
		});
		
	}

	private void setList() {
		// TODO Auto-generated method stub
		list=new ArrayList<Claim>();
		if (user.isFilter()){
			for (Claim claim:user.getClaimList()){
				boolean show =false;
				for (Long l:claim.getTagIDList()){
					if (user.getFilterTagIDList().contains(l)){
						show=true;
					}
				}
				if(show){
					list.add(claim);
				}
			}
		}else{
			for (Claim claim:user.getClaimList()){
				list.add(claim);
			}
		}
	}

	/**
	 * Sort the <code>User</code> by the starting date of the claim.
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
	 * Add a claim to the <code>User</code> by calling the 
	 * <code>ClaimantAddClaimActivity</code> class.
	 * 
	 * @param m  a MenuItem object
	 * @see android.content.Intent
	 * @see android.view.Menu
	 * @see android.view.MenuItem
	 */
	public void addClaim(MenuItem m){
		try {
			cclc.addClaim();
		} catch (NetWorkException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}	
		Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantEditClaimActivity.class);
		startActivity(intent);
	}

	//http://stackoverflow.com/questions/9814821/show-progressdialog-android Author:dldnh
	public void pushOnline(MenuItem m){
		pg =ProgressDialog.show(this, "Pushing Onling...","Please wait patiently :)", true);
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		  
		        try {
		        	new ESClient().pushUser(AppSingleton.getInstance().getCurrentUser());
		        	AppSingleton.getInstance().setSuc(true);
		        	
		        } catch (Exception e) {
		        	AppSingleton.getInstance().setSuc(false);
		        
		        }
		        
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
	
	
	public void filter(MenuItem m){
		final AlertDialog.Builder builder = new AlertDialog.Builder(ClaimantClaimListActivity.this);
		builder.setTitle(user.isFilter()?"Filter Model":"Show All Model");
		builder.setMultiChoiceItems(user.toTagList(),user.toCheckArray(),new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				try{
					if (isChecked){
						cclc.addTag(user.getTagList().get(which).getID());
					}else if(user.getFilterTagIDList().contains(user.getTagList().get(which).getID())){
						cclc.removeTag(user.getTagList().get(which).getID());
					}
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
			}
		});
		
		builder.setPositiveButton("Filter Model",
				new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.setNegativeButton("Show All Model", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		final AlertDialog dialog =builder.create();  
		dialog.show();
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					cclc.filter();
				} catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
				dialog.setTitle("Filter Model");
			}
		});
		dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					cclc.showAll();
				} catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
				dialog.setTitle("Show All Model");
				
			}
		});
	}
	
	public void manageTag(MenuItem m){
		Intent intent =new Intent(ClaimantClaimListActivity.this,ClaimantTagListActivity.class);
		startActivity(intent);	
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
