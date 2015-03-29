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

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
* This <code>ClaimantEditClaimActivity</code> class is an extended class
* of <code>Activity</code> class. This class controls the User Interface of 
* editing <code>Claim</code> detail for claimant. This view displays the 
* detail of <code>Claim</code> and allows claimant to change and update 
* the information. It will be used when the claimant asks to edit the claim
* detail.
* 
* @author CMPUT301W15T06
* @version 03/16/2015
* @see android.app.Activity
* @see android.app.DialogFragment
* @see android.os.Bundle
* @see android.view.Menu
* @see android.view.MenuItem
* @see android.view.View
* @see android.widget.EditText
* @see android.widget.Toast
*/
public class ClaimantEditClaimActivity extends Activity {

	/**
	 * Set a ClaimantEditClaimController object cecc with initial 
	 * default value null.
	 */
	private ClaimantEditClaimController cecc=null;

	/**
	 * Set a EditText object beginView to view and edit the begin date.
	 * The initial default value is null.
	 * 
	 * @see android.widget.EditText
	 */
	private EditText beginView=null;
	/**
	 * Set a EditText object endView to view and edit the end date.
	 * The initial default value is null.
	 * 
	 * @see android.widget.EditText
	 */
	private EditText endView=null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_edit_claim);
		
		cecc=new ClaimantEditClaimController(AppSingleton.getInstance().getCurrentClaim());

		
		beginView=(EditText) findViewById(R.id.editClaimStartingDateEditText);
		endView=(EditText) findViewById(R.id.editClaimEndDateEditText);
		

		beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));
		endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
		
	
		
		beginView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					cecc.editBegin(beginView.getText().toString());
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
					beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));
					
				} catch (WrongEndDateException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Ending Date must after Starting Date!", Toast.LENGTH_LONG).show();
					beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));

				}
			}
		});
		endView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					cecc.editEnd(endView.getText().toString());
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
					
					endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
				} catch (WrongEndDateException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Ending Date must after Starting Date!", Toast.LENGTH_LONG).show();
					
					endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_edit_claim, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	
	/**
	 * Create a DatePickerDialog object to help user(claimant) to enter 
	 * the starting date of the travel.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 * @see android.widget.DatePicker
	 * @see android.app.DialogFragment
	 */
	public void showDatePickerDialogBegin(View v) {
		AppSingleton.getInstance().setDateEditText(beginView);
		AppSingleton.getInstance().setEditDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate());
		DialogFragment newFragment = new EditDatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	/**
	 * Create a DatePickerDialog object to help user(claimant) to enter 
	 * the ending date of the travel.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.widget.EditText
	 * @see android.widget.DatePicker
	 * @see android.app.DialogFragment
	 */
	public void showDatePickerDialogEnd(View v) {
		AppSingleton.getInstance().setDateEditText(endView);
		AppSingleton.getInstance().setEditDate(AppSingleton.getInstance().getCurrentClaim().getEndDate());
		DialogFragment newFragment = new EditDatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
}
