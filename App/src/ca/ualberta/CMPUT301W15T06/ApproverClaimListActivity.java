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
import android.view.Menu;

/**
* This <code>ApproverClaimListActivity</code> class is an extended class
* of <code>Activity</code> class. This class controls the User Interface of 
* <code>ClaimList</code> for approver. This view displays a list of
* <code>Claim</code> and creates an option menu and return true as 
* the boolean value. It will be used when the approver asks to access to 
* the <code>ClaimList</code> .
* 
* @author CMPUT301W15T06
* @version 03/16/2015
* @see android.os.Bundle
* @see android.app.Activity
* @see android.view.Menu
*/
public class ApproverClaimListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_list, menu);
		return true;
	}

}
