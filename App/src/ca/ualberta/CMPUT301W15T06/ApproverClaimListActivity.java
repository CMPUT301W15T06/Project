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
import android.content.Intent;
import android.text.method.DigitsKeyListener;
import android.view.InputEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
* This <code>ApproverClaimListActivity</code> class is an extended class
* of <code>Activity</code> class. This class controls the User Interface of 
* <code>User</code> for approver. This view displays a list of
* <code>Claim</code> and creates an option menu and return true as 
* the boolean value. It will be used when the approver asks to access to 
* the <code>User</code> .
* 
* @author CMPUT301W15T06
* @version 04/07/2015
* @see java.util.ArrayList
* @see java.util.Comparator
* @see android.os.Bundle
* @see android.app.Activity
* @see android.view.Menu
* @see android.app.AlertDialog
* @see android.app.Dialog
* @see android.content.DialogInterface
* @see android.content.Intent
* @see android.text.method.DigitsKeyListener
* @see android.view.InputEvent
* @see android.view.View
* @see android.widget.AdapterView
* @see android.widget.ArrayAdapter
* @see android.widget.EditText
* @see android.widget.ListView
* @see android.widget.Toast
* @see android.widget.AdapterView.OnItemClickListener
*/
public class ApproverClaimListActivity extends Activity {
	
	/**
	 * Set a <code>Dialog</code> object to create a Dialog window that uses the default dialog frame style.
	 */
	private Dialog dialog;
	/**
	 * Set an unchangeable integer variable APPROVE to 4.
	 * Set an unchangeable integer variable RETURN to 3.
	 * Set an unchangeable integer variable COMMENT to 2.
	 * Set an unchangeable integer variable ITEM_LIST to 1.
	 * Set an unchangeable integer variable DETAIL_DESTINATION to 0.
	 */
	private static final int APPROVE = 4;
	private static final int RETURN = 3;
	private static final int COMMENT = 2;
	private static final int ITEM_LIST = 1;
	private static final int DETAIL_DESTINATION = 0;
	/**
	 * Set an ApproverClaimListController object aclc with initial default value of null to represent this class.
	 */
	private ApproverClaimListController aclc=null;
	/**
	 * Set an User object user with initial default value of null to represent the user of this application
	 */
	private User user=null;
	/**
	 * Set a Claim object claim with initial default value of null to represent the claim in the claim list
	 */
	private Claim claim=null;
	/**
	 * Set an ArrayList list to contain all the claims that user created.
	 */
	private ArrayList<Claim> list;
	/**
	 * Set an ArrayAdapter adapter to adapt all the claim in the claim list.
	 */
	private ArrayAdapter<Claim> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_claim_list);
		
		
		setTitle("Approver: "+AppSingleton.getInstance().getUserName());
		
		
		
		AppSingleton.getInstance().setUpApprover();
		user=AppSingleton.getInstance().getCurrentUser();
		
		//set list view of claimlist
		ListView listView = (ListView) findViewById(R.id.claimListView);
		list=AppSingleton.getInstance().getNeedApproveList();

		adapter=new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1,list);
		adapter.sort(sortClaim());
		listView.setAdapter(adapter);
		
		for(Claim claim:list){
			claim.addListener(new Listener() {
				
				@Override
				public void update() {
					// TODO Auto-generated method stub

					adapter.sort(sortClaim());
					adapter.notifyDataSetChanged();
				}

			});
		}
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
		
				claim=adapter.getItem(position);				
				aclc=new ApproverClaimListController(claim);
				AppSingleton.getInstance().setCurrentClaim(claim);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ApproverClaimListActivity.this);
				builder.setTitle(R.string.title_claim_dialog);
				builder.setItems(R.array.claim_dialog_array, new DialogInterface.OnClickListener() {
					
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						itemChoice(which);						
					}
				});
				builder.create();  
				builder.show();
			}
			
		});
	}

	/**
	 * This method will return and display a list of claim sorted by date of travel.
	 * 
	 * @return a sorted claim list
	 * @see java.util.Comparator
	 */
	private Comparator<? super Claim> sortClaim() {
		// TODO Auto-generated method stub
		return new Comparator<Claim>() {
			
			@Override
			public int compare(Claim lhs, Claim rhs) {
				// TODO Auto-generated method stub
				return lhs.getBeginDate().compareTo(rhs.getBeginDate());			
				
			}
		};
	}

	/**
	 * This method will gives user options to choose for further functions.
	 * 
	 * @param which  a integer from 0 to 4 with corresponding options
	 */
	private void itemChoice(int which){
		if (which ==DETAIL_DESTINATION){
			Intent intent =new Intent(ApproverClaimListActivity.this,ApproverClaimDetailListActivity.class);
			startActivity(intent);				
		}else if (which==ITEM_LIST){
			Intent intent =new Intent(ApproverClaimListActivity.this,ApproverItemListActivity.class);
			startActivity(intent);
		}else if (which==COMMENT){
			comment();
		}else if (which ==RETURN){
			returnClaim();		
		}else if (which==APPROVE){
			approve();
		}
	}
	
	/**
	 * This will stop user from approving their own claim and check other errors as approver. Throws exceptions if necessary.
	 * 
	 * @throws NetWorkException
	 * @throws WrongApproverException
	 */
	private void approve() {
		// TODO Auto-generated method stub
		if(user.getUserName().equals(claim.getName())){
			Toast.makeText(ApproverClaimListActivity.this, "Cant't approve your own claim!", Toast.LENGTH_LONG).show();
		}else{
			try {
				aclc.approveClaim();
			} catch (NetWorkException e) {
				// TODO Auto-generated catch block
				Toast.makeText(ApproverClaimListActivity.this, "Can't work as Approver when offline!", Toast.LENGTH_LONG).show();
			}catch (WrongApproverException e) {
				// TODO Auto-generated catch block
				Toast.makeText(ApproverClaimListActivity.this, claim.getComments().getUnFinishedComment().getApproverName()
						+" has commited this claim, so only he can return or approve this claim!", Toast.LENGTH_LONG).show();

			}
		}
	}
	
	/**
	 * This will stop user from returning their own claim and check other errors as approver. Throws exceptions if necessary.
	 * 
	 * @throws NetWorkException
	 * @throws WrongApproverException
	 */
	private void returnClaim() {
		// TODO Auto-generated method stub
		if(user.getUserName().equals(claim.getName())){
			Toast.makeText(ApproverClaimListActivity.this, "Cant't return your own claim!", Toast.LENGTH_LONG).show();
		}else{
			try {
				aclc.returnClaim();
			} catch (NetWorkException e) {
				// TODO Auto-generated catch block
				Toast.makeText(ApproverClaimListActivity.this, "Can't work as Approver when offline!", Toast.LENGTH_LONG).show();
			}catch (WrongApproverException e) {
				// TODO Auto-generated catch block
				Toast.makeText(ApproverClaimListActivity.this, claim.getComments().getUnFinishedComment().getApproverName()
						+" has commited this claim, so only he can return or approve this claim!", Toast.LENGTH_LONG).show();

			}
		}
	}

	/**
	 * This will stop user from adding comment to their own claim. It will ask approver to add comment when it is finished.
	 * 
	 */
	private void comment() {
		// TODO Auto-generated method stub
		if(user.getUserName().equals(claim.getName())){
			Toast.makeText(ApproverClaimListActivity.this, "Cant't added comment to your own claim!", Toast.LENGTH_LONG).show();
		}else{
			if(claim.getComments().isFinish()){
				addComment();
			}else{
				Toast.makeText(ApproverClaimListActivity.this, claim.getComments().getUnFinishedComment().getApproverName()
						+" has commited this claim, need him to return or approve this claim!", Toast.LENGTH_LONG).show();

			}
		}
	}

	/**
	 * This will allow approver to add comments to the claim to explain the decision ("Approve" or "Return")
	 */
	private void addComment() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(ApproverClaimListActivity.this);
		builder.setTitle("Enter the Comment");
		final EditText input=new EditText(ApproverClaimListActivity.this);							
		builder.setView(input);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				comment(input);
			}
		});
		builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.create();  
		builder.show();
	}
	
	/**
 	 * This method will create a edible text area to allow user enter text for comment. Throws exceptions if necessary.
 	 * 
 	 * @param input  record input text for comment (like "Incomplete Claim Information")
 	 * @throws NetWorkException
 	 * @see android.widget.EditText
 	 */
	private void comment(EditText input) {
		// TODO Auto-generated method stub
		try {
			aclc.addComment(input.getText().toString());
		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			Toast.makeText(ApproverClaimListActivity.this, "Can't work as Approver when offline!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
	
	/**
	 * This method will return a dialog window for display. 
	 * 
	 * @return a Dialog window that uses the default dialog frame style
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
	
	/**
	 * This method will return the claim list for approver for further use or display
	 * 
	 * @return a list of claim for approver
	 */
	public ArrayList<Claim> getApproverClaimList(){
		return list;
		
	}

}
