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
		claimant_name = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameEditText));
		claimant_starting_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateEditText));
		claimant_ending_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndDateEditText));
	}

	// fill blank
	@SuppressWarnings("unused")
	private void testAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
		
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton)).performClick();
	}

	//test if add a new claim to list
	public void testClickAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
		
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton)).performClick();
	    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));	    
	}
}
