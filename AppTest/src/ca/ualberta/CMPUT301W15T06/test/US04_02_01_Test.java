package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

public class US04_02_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	Button ApproverButton;
	Button ClaimantButton;
	Intent intent;
	
	public US04_02_01_Test () {
		super(MainActivity.class);
	}
	
	// set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);

		intent = new Intent(getInstrumentation().getTargetContext(),MainActivity.class);
	}

	/*
	 * Test for US01.02.01 Basic Flow 1
	 */

	// test button exists
	public void testLayout() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		// test Approver button layout
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, ApproverButton);
		final ViewGroup.LayoutParams layoutParams = ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height,WindowManager.LayoutParams.WRAP_CONTENT);

		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver",view.getText());
		
		/*
		 * Test for US01.02.01 Basic Flow 2
		 */
		// test Claimant Button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams1 = ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height,WindowManager.LayoutParams.WRAP_CONTENT);

		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant",view1.getText());
		
		// test if the button can create next activity
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		// open current activity.
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});

		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);

		/*
		 * Test for US01.02.01 Basic Flow 3
		 */

		// view which is expected to be present on the screen
		final View decorView2 = nextActivity.getWindow().getDecorView();

		ListView clv = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView2, clv);
		// check whether the Button object's width and height attributes match
		// the expected values
		final ViewGroup.LayoutParams layoutParams2 = clv.getLayoutParams();
		assertEquals(layoutParams2.width,WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams2.height,WindowManager.LayoutParams.WRAP_CONTENT);

		/*
		 * Test for US01.02.01 Basic Flow 4
		 */
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		assertNotNull(claimList.performClick());
		
		/*
		 * Test for US01.02.01 Basic Flow 5
		 */
		
		
		
		}
	}

