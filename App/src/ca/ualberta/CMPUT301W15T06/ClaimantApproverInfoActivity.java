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
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
* This <code>ClaimantApproverInfoActivity</code> class is an extended class
* of <code>Activity</code> class. This class will control the interface
* of <code>Claim</code> approver information detail for claimant. 
* This view displays the content of the approval status and creates an 
* option menu. It will be used when the claimant asks to access to 
* the information detail of approval status.
* 
* @author CMPUT301W15T06
* @version 04/07/2015
* @see java.util.ArrayList
* @see java.util.Comparator
* @see android.os.Bundle
* @see android.app.Activity
* @see android.view.Menu
* @see android.widget.ArrayAdapter
* @see android.widget.ListView
*/
public class ClaimantApproverInfoActivity extends Activity {

	/**
	 * Set a Comments object comments that records the comments that approver added to the claim.
	 */
	private Comments comments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_approver_info);
		
		setTitle(AppSingleton.formatDate(AppSingleton.getInstance().getCurrentClaim().getBeginDate())
				+"<-"+AppSingleton.getInstance().getUserName());
		comments=AppSingleton.getInstance().getCurrentClaim().getComments();

		
		ListView listView = (ListView) findViewById(R.id.approverListView);
		final ArrayList<Comment> list =comments.getCommentList();
		final ArrayAdapter<Comment> adapter=new ArrayAdapter<Comment>(this, android.R.layout.simple_list_item_1,list);
		adapter.sort(sortComments());
		listView.setAdapter(adapter);
	}

	/**
	 * This method will compare all the comments and sort them with their created date.
	 * 
	 * @return a sorted list of comments
	 * @see java.util.Comparator
	 */
	private Comparator<? super Comment> sortComments() {
		// TODO Auto-generated method stub
		return new Comparator<Comment>() {
			
			@Override
			public int compare(Comment lhs, Comment rhs) {
				// TODO Auto-generated method stub
				return rhs.getDate().compareTo(lhs.getDate());			
				
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.	
		return false;
	}

}
