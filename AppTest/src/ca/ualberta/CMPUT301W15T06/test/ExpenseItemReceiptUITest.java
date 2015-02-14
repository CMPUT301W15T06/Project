package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import ca.ualberta.CMPUT301W15T06.ClaimantReciptActivity;

public class ExpenseItemReceiptUITest extends
		ActivityInstrumentationTestCase2<ClaimantReciptActivity> {

	Instrumentation instrumentation;
	Activity activity;
	
	public ExpenseItemReceiptUITest() {
        super(ClaimantReciptActivity.class);
        
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
	}
	
	//test for photo DISPLAY UI
	public void testDisplayPhoto() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverRecieptView));
	}
		
	
	
	//test for take photo ui
	public void testTakePhoto() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.takephoto));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.takephoto)).performClick();
	}
	
	//test for delete photo ui
	public void testDeletePhoto() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.deletePhoto));
		((View) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.deletePhoto)).performClick();
	}
	
	
	

}