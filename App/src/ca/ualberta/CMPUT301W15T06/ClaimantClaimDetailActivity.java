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
 
 /**
 * This <code>ClaimantClaimDetailActivity</code> class is an extended class
 * of <code>Activity</code> class. This class will control the interface
 * of <code>Claim</code> detail for claimant. This view displays 
 * <code>Claim</code> details, including claimant's name, travel begin, 
 * end date and destination. It also creates an option menu. It will be 
 * used when the claimant asks to access to the <code>Claim</code> detail. 
 * The associated class including <code>Claim</code> and <code>Destination</code>.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see java.util.ArrayList
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.content.Intent
 * @see android.view.Menu
 * @see android.view.View
 * @see android.widget.ArrayAdapter
 * @see android.widget.EditText
 * @see android.widget.ListView
 * @see android.widget.TextView
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

public class ClaimantClaimDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_add_destination);
		
		TextView nameView= (TextView) findViewById(R.id.nameValueClaimantClaimDetailTextView);
		TextView beginView=(TextView) findViewById(R.id.startDateValueClaimantClaimDetailTextView);
		TextView endView=(TextView) findViewById(R.id.endingDateValueClaimantClaimDetailTextView);
		nameView.setText(AppSingleton.getInstance().getCurrentClaim().getName());
		beginView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate()));
		endView.setText(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getEndDate()));
		ListView listView = (ListView) findViewById(R.id.claimantDetailListView);
		ArrayList<Destination> list =AppSingleton.getInstance().getCurrentClaim().getDestinationList();
		final ArrayAdapter<Destination> adapter=new ArrayAdapter<Destination>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		

		AppSingleton.getInstance().getCurrentClaim().addListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				adapter.notifyDataSetChanged();
			}
		});
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_add_destination, menu);
		return true;
	}
	
	/**
	 * Set up a addDestination view. This method will be called when the 
	 * claimant wants to add a new <code>Destination</code> to a current
	 * <code>Claim</code>.
	 * 
	 * @param v  a View object
	 * @see android.view.View
	 * @see android.content.Intent
	 */
	public void addDestination(View v){
		Intent intent =new Intent(ClaimantClaimDetailActivity.this,ClaimantAddDestinationActivity.class);
		startActivity(intent);	
	}

}
