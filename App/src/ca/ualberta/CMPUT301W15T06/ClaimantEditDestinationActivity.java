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

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
* This <code>ClaimantEditDestinationActivity</code> class is an extended class
* of <code>Activity</code> class. This class controls the User Interface of 
* adding Destination. The interface need Bundle, EditText, option menu bar 
* and finishBbutton. 
* 
* @author CMPUT301W15T06
* @version 04/07/2015
* @see android.os.Bundle
* @see android.app.Activity
* @see android.text.Editable
* @see android.text.TextWatcher
* @see android.view.Menu
* @see android.view.View
* @see android.widget.EditText
* @see android.widget.Toast
*/
public class ClaimantEditDestinationActivity extends Activity {

	/**
	 * Set a ClaimantAddDestinationController object cadc with initial 
	 * default value null.
	 */
	private ClaimantEditDestinationController cedc=null;
	private Destination dest=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_destination_reason);
		
		setTitle(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
				+"<-"+AppSingleton.getInstance().getUserName());

		
		dest=AppSingleton.getInstance().getCurrentDestination();
		cedc=new ClaimantEditDestinationController(dest);
		
		final EditText destinationView= (EditText) findViewById(R.id.DestinationEditText);
		final EditText reasonView=(EditText) findViewById(R.id.ReasonEditText);
		
		destinationView.setText(dest.getName());
		reasonView.setText(dest.getReason());
		
		TextWatcher tw=new TextWatcher() {
			
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
					cedc.editDestination(destinationView.getText().toString(), reasonView.getText().toString());
				} catch (StatusException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "Can't make change to a 'Submitted' or 'Approved' claim!", Toast.LENGTH_LONG).show();
				}catch (NetWorkException e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}	
				
			}
		};
		
		destinationView.addTextChangedListener(tw);
		reasonView.addTextChangedListener(tw);
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
	
	

}
