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
* @version 03/16/2015
* @see android.os.Bundle
* @see android.app.Activity
* @see android.view.Menu
*/
public class ApproverClaimListActivity extends Activity {

	private static final int APPROVE = 4;
	private static final int RETURN = 3;
	private static final int COMMENT = 2;
	private static final int ITEM_LIST = 1;
	private static final int DETAIL_DESTINATION = 0;

	private ApproverClaimListController aclc=null;
	private User user=null;
	private Claim claim=null;
	private ArrayList<Claim> list;
	private ArrayAdapter<Claim> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_claim_list);
		
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

}
