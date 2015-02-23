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

import ca.ualberta.CMPUT301W15T06.ClaimantDestinationReasonActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

// test for US01.02.01
@SuppressWarnings("unused")
public class ClaimantDestinationReasonActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantDestinationReasonActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText claimant_des;
	EditText claimant_reason;
	
	public ClaimantDestinationReasonActivityUITest() {
		super(ClaimantDestinationReasonActivity.class);
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		claimant_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
		claimant_reason = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
	}

	// fill blank
	private void testAddButton(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinshButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinshButton)).performClick();
	}

	//test if add a new claim to list
	public void testClickAddButton(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinshButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinshButton)).performClick();
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
	}

}
