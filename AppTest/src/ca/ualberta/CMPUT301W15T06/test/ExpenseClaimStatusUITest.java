package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import ca.ualberta.CMPUT301W15T06.*;;

public class ExpenseClaimStatusUITest extends
		ActivityInstrumentationTestCase2<ClaimantItemListActivity> {
	Activity activity;
	Context context;

	public ExpenseClaimStatusUITest(String name) {
		super(ClaimantItemListActivity.class);
	}

/*	protected void setUp() throws Exception {
		super.setUp();
		Claim claim1 = new Claim("abc");
		ClaimListController.addClaim("1","2,","3");	
		
		Intent intent = new Intent();
		intent.putExtra("Index", 0);
		setActivityIntent(intent);
		activity = getActivity();
	}
	public void testSubmitWarning() {
		Claim claim = ClaimListController.getCurrentClaim();
		Item expense = claim.getCurrentItem();
		//trigger
		final Button button = (Button) activity.findViewById(R.id.submitClaimButton);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});
	}*/
}

