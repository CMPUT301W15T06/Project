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

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.ApproverClaimDetailListActivity;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.Item;

public class ApproverClaimDetailListActivityUITest extends ActivityInstrumentationTestCase2<ApproverClaimDetailListActivity>{

	Instrumentation instrumentation;
	Activity activity;
	
	public ApproverClaimDetailListActivityUITest() {
		super(ApproverClaimDetailListActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		// Setting the touch mode to true prevents the UI control from taking focus when you click it programmatically in the test method later 
		setActivityInitialTouchMode(true);

		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	// test for use case 08.03.01
	// System shows all the details of one expense claim
	public void testSubmittedClaim() {

		// build claim list
		ClaimList cList = new ClaimList();
		
		// build new claim
		Claim test = new Claim("A");
		
		// set start date
		String d1 = "2012-03-27";
		test.setBeginDate(d1);
		
		// set approver name
		test.setApprover("Approver1");
		
		// set state
		test.setStatus("submitted");
		
		// set new expense item
		Item new_item = new Item();
		new_item.setAmount(10);
		new_item.setCategory("traffic");
		new_item.setDate(d1);
		new_item.setCurrency("CAD");
		new_item.setDescription("arrived Edmonton");
		test.addItem(new_item);
		
		// add claim
		cList.addClaim(test);
				
		// get claim from list
		Claim ec = cList.getClaimList().get(0);
		
		// get expense item in this claim
		Item ei = ec.getItemList().get(0);
		// check values saved in expense item
		// It's an error now because our function returns null
		assertTrue("category should be 'traffic'", ei.getCategory().equals("traffic"));
		assertTrue("date should be 2012-03-27", ei.getDate().equals(d1));
		assertTrue("description should be 'arrived Edmonton'", ei.getDescription().equals("arrived Edmonton"));
		assertTrue("amount of spend should be 10", ei.getAmount()==10);
		assertTrue("unit of currency should be 'CAD'", ei.getCurrency().equals("CAD"));
		
		// test UI
		TextView v = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.SeeNameForApprover);
		assertTrue("This view should be bus", v.toString().equals("bus"));
		
		v = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.StartDateApprover);
		assertTrue("This view should be traffic", v.toString().equals("traffic"));
		
		v = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.EndDateApprover);
		assertTrue("This view should be 2012-03-27", v.toString().equals(d1));
		
		v = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationListApprover);
		assertFalse("This view should be a destination list and should not be null", v.toString().equals(null));

	}
	// There's no button in this activity's layout, so there is no button behavior to be test

}
