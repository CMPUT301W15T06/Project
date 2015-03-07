package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;

import ca.ualberta.CMPUT301W15T06.R;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;

public class US01_02_01_Test_2 extends
		ActivityInstrumentationTestCase2<ClaimantClaimDetailActivity> {
	
	public US01_02_01_Test_2() {
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
		activity = getActivity();
		instrumentation = getInstrumentation();
		AddDestination = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);	
		nameView= (TextView) activity.findViewById(R.id.nameValueClaimantClaimDetailTextView);
		beginView=(TextView) activity.findViewById(R.id.startDateValueClaimantClaimDetailTextView);
		endView=(TextView) activity.findViewById(R.id.endingDateValueClaimantClaimDetailTextView);
	}
	
	/*
	 * Test for US01.02.01 Basic Flow 7
	 */
	
/*	// test text view: nameValueClaimantClaimDetailTextView
	public void testNameViewTexT_layout() {
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
	}*/
	
	// US01.02.01 test 'Add a Destination' button layout
	public void testDestinationButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, AddDestination);
	    final ViewGroup.LayoutParams layoutParams =
	    		AddDestination.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
	    assertEquals("Incorrect label of the button", "Add a destination", view.getText());
	}

	/*
	 * Test for US01.02.01 Basic Flow 8
	 */
	//US01.02.01 test button activity
	public void testOpenNextActivity() {
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantAddDestinationActivity.class.getName(), null, false);

		  // open current activity.
		  ClaimantClaimDetailActivity myActivity = getActivity();
		  final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		  myActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		  });

		  ClaimantAddDestinationActivity nextActivity = (ClaimantAddDestinationActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
		  // next activity is opened and captured.
		  assertNotNull(nextActivity);
		}
}




