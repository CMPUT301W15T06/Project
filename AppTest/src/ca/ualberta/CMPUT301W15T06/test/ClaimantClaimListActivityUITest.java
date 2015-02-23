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

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.Item;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

public class ClaimantClaimListActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantClaimListActivity> {

	Activity activity;
	Instrumentation instrumentation;
	

	public ClaimantClaimListActivityUITest() {
		super(ClaimantClaimListActivity.class);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		activity = getActivity();
		instrumentation = getInstrumentation();
		setActivityInitialTouchMode(true);	
	}

	
	//test claim list US02.01.01 & US02.02.01
	public void testClaim(){
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView)).performClick();
	}
	
}
