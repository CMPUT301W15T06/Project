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

package ca.ualberta.CMPUT301W15T06.test;

import org.junit.Before;

import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

public class ClaimantAddClaimActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantAddClaimActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	
	public ClaimantAddClaimActivityUITest() {
		super(ClaimantAddClaimActivity.class);
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		claimant_name = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameEditText));
		claimant_starting_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateEditText));
		claimant_ending_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndDateEditText));
	}

	// fill blank (Test US01.01.01 & US01.04.01 & US01.05.01)
	@SuppressWarnings("unused")
	private void testAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton));
		
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton)).performClick();
	}

	//test if add a new claim to list (US01.03.01 & US 01.06.01)
	public void testClickAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton));
		
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton)).performClick();
	    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));	    
	}
}
