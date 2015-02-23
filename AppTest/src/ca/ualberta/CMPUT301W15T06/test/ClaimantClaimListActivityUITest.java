package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

public class ClaimantClaimListActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantClaimListActivity> {

	Activity activity;
	Instrumentation instrumentation;
	

	public ClaimantClaimListActivityUITest() {
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

		
	//US 09.01.01
	public void testPushOnline() {
		// click button runnable -- click push online
		final Button button1 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));
		activity.runOnUiThread(new Runnable() {
		@Override
		public void run() {
		button1.performClick();
		}
		});
	}


}
