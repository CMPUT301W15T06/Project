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
import android.test.ViewAsserts;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.ApproverItemListActivity;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.User;
import ca.ualberta.CMPUT301W15T06.Item;

public class ApproverItemListActivityUITest extends ActivityInstrumentationTestCase2<ApproverItemListActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	TextView v;
	
	public ApproverItemListActivityUITest() {
		super(ApproverItemListActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		// Setting the touch mode to true prevents the UI control from taking focus when you click it programmatically in the test method later 
		setActivityInitialTouchMode(true);

		instrumentation = getInstrumentation();
		activity = getActivity();
		v = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ApproverItemList);
	}
	

	// test text view
	public void testInfoTextView_layout() {
	    View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, v);
	    assertTrue(View.GONE == v.getVisibility());
	}
	// There's no button in this activity's layout, so there is no button behavior to be test

}
