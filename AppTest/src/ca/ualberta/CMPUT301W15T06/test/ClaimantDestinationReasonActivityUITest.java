package ca.ualberta.CMPUT301W15T06.test;

import org.junit.Before;

import ca.ualberta.CMPUT301W15T06.ClaimantDestinationReasonActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

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
		claimant_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.DestinationEditText));
		claimant_reason = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ReasonEditText));
	}

	// fill blank
	private void testAddButton(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton)).performClick();
	}

	//test if add a new claim to list
	public void testClickAddButton(String claimantDes, String claimantReason) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
		
		claimant_des.setText(claimantDes);
		claimant_reason.setText(claimantReason);
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton)).performClick();
	    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView));	 

}
