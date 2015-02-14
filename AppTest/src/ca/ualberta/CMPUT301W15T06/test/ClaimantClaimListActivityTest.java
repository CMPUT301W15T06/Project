package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

public class ClaimantClaimListActivityTest extends
		ActivityInstrumentationTestCase2<ClaimantClaimListActivity> {

	Activity activity;
	Instrumentation instrumentation;
	

	public ClaimantClaimListActivityTest() {
		super(ClaimantClaimListActivity.class);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		instrumentation = getInstrumentation();
		
		
	}

	
	//test claim list 
	public void testClaim(){
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView)).performClick();
	}
}
