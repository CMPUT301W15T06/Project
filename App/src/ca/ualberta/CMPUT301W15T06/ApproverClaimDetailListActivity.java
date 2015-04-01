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
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
* This <code>ApproverClaimDetailListActivity</code> class is an extended class
* of <code>Activity</code> class. This class controls the User Interface of 
* <code>Claim</code> detail for approvers. This view displays a list of
* <code>Claim</code> details and creates an option menu and return true as 
* the boolean value. It will be used when the approver asks to access to the 
* <code>Claim</code> detail. 
* 
* @author CMPUT301W15T06
* @version 03/16/2015
* @see android.os.Bundle
* @see android.app.Activity
* @see android.view.Menu
*/
public class ApproverClaimDetailListActivity extends Activity {

	private Claim claim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_claim_detail_list);
		
		claim=AppSingleton.getInstance().getCurrentClaim();
		
		
		TextView beginView=(TextView) findViewById(R.id.startDateValueClaimDetailTextView);
		TextView endView=(TextView) findViewById(R.id.endingDateValueClaimDetailTextView);
		beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));
		endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
		ListView listView = (ListView) findViewById(R.id.detailListView);
		ArrayList<Destination> list =AppSingleton.getInstance().getCurrentClaim().getDestinationList();
		final ArrayAdapter<Destination> adapter=new ArrayAdapter<Destination>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_claim_detail_list, menu);
		return true;
	}

}
