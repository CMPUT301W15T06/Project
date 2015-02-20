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
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ClaimantAddDestinationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_add_destination);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_add_destination, menu);
		return true;
	}
	
	public void onResume(){
		super.onResume();
		TextView nameView= (TextView) findViewById(R.id.nameValueClaimantClaimDetailTextView);
		TextView beginView=(TextView) findViewById(R.id.startDateValueClaimantClaimDetailTextView);
		TextView endView=(TextView) findViewById(R.id.endingDateValueClaimantClaimDetailTextView);
		nameView.setText(ClaimListController.getCurrentClaim().getName());
		beginView.setText(ClaimListController.getCurrentClaim().getBeginDate());
		endView.setText(ClaimListController.getCurrentClaim().getEndDate());
		ListView listView = (ListView) findViewById(R.id.claimantDetailListView);
		ArrayList<Destination> list =ClaimListController.getCurrentClaim().getDestinationList();
		ArrayAdapter<Destination> adapter=new ArrayAdapter<Destination>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
	}
	
	public void addDestination(View v){
		Intent intent =new Intent(ClaimantAddDestinationActivity.this,ClaimantDestinationReasonActivity.class);
		startActivity(intent);	
	}

}
