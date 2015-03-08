package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.R;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class US01_03_01_Test_2 extends ActivityInstrumentationTestCase2<ClaimantClaimDetailActivity> {

	public US01_03_01_Test_2() {
		super(ClaimantClaimDetailActivity.class);
	}
	
	Activity activity;
	Instrumentation instrumentation;
	Button AddDestination;
	TextView nameView;
	TextView beginView;
	TextView endView;
	
	//set up
	protected void setUp() throws Exception{
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		AddDestination = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);	
		nameView= (TextView) activity.findViewById(R.id.nameValueClaimantClaimDetailTextView);
		beginView=(TextView) activity.findViewById(R.id.startDateValueClaimantClaimDetailTextView);
		endView=(TextView) activity.findViewById(R.id.endingDateValueClaimantClaimDetailTextView);
	}
	
	/*
	 * Test for US01.03.01 Basic Flow 7
	 */
	
	// test text view: nameValueClaimantClaimDetailTextView
	public void testNameViewTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, nameView);
	    assertTrue(View.GONE == nameView.getVisibility());
	}
	
	// test text view: nameValueClaimantClaimDetailTextView
	public void testBeginViewTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, beginView);
	    assertTrue(View.GONE == beginView.getVisibility());
	}
	
	// test text view: nameValueClaimantClaimDetailTextView
	public void testEndViewTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, endView);
	    assertTrue(View.GONE == endView.getVisibility());
	}
	
	// US01.02.01 test 'Add a Destination' button layout
	public void testApproverButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, AddDestination);

	    final ViewGroup.LayoutParams layoutParams = AddDestination.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
	    assertEquals("Incorrect label of the button", "Add a destination", view.getText());
	}
	

}
