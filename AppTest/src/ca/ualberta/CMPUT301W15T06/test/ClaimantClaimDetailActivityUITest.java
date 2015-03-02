package ca.ualberta.CMPUT301W15T06.test;


import ca.ualberta.CMPUT301W15T06.ClaimantAddDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class ClaimantClaimDetailActivityUITest extends
		ActivityInstrumentationTestCase2<ClaimantClaimDetailActivity> {

	public ClaimantClaimDetailActivityUITest() {
		super(ClaimantClaimDetailActivity.class);
		// TODO Auto-generated constructor stub
	}

	Activity activity;
	Instrumentation instrumentation;
	Button AddDestination;
	
	//set up
	protected void setUp() throws Exception{
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		AddDestination = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);	
	}
	
	// US01.02.01 test 'Add a Destination' button layout
	public void testApproverButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, AddDestination);

	    final ViewGroup.LayoutParams layoutParams =
	    		AddDestination.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
	    assertEquals("Incorrect label of the button", "Add a destination", view.getText());
	}
	
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
		  nextActivity .finish();
		}
}