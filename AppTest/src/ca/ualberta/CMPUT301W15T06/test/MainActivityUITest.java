package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;

public class MainActivityUITest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;

	public MainActivityUITest() {
		super(MainActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
		//startActivity(intent, null, null);

	}
	

	// test button exists
	public void testLayout() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
	}
	 
	//test Approver button layout
	public void testApproverButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, ApproverButton);

	    final ViewGroup.LayoutParams layoutParams =
	           ApproverButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	    assertEquals("Incorrect label of the button", "Approver", view.getText());
	}
	
	/*
	 * Test for US01.01.01 Basic Flow 1
	 * Test for US04.01.01 Basic Flow 1
	 * Test for US04.05.01 Basic Flow 1
	 */
	
	//test Claimant Button layout
	public void testClaimantButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, ClaimantButton);

	    final ViewGroup.LayoutParams layoutParams =
	           ClaimantButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
	    assertEquals("Incorrect label of the button", "Claimant", view.getText());
	}
	
	/*
	 * Test for US01.01.01 Basic Flow 2
	 * Test for US04.01.01 Basic Flow 2
	 * Test for US04.05.01 Basic Flow 2
	 */
	
	 //test button activity
	public void activityTest(){
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		TouchUtils.clickView(this, view1);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton)).performClick();
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		TouchUtils.clickView(this, view2);
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton)).performClick();
	}
	
}
