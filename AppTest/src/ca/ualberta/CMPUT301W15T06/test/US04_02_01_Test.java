package ca.ualberta.CMPUT301W15T06.test;

import java.util.ArrayList;
import java.util.Arrays;

import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantAddItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

@SuppressLint("CutPasteId")
public class US04_02_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	Button ApproverButton;
	Button ClaimantButton;
	Intent intent;
	ListView clv;
	
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
	 * Test for US04.02.01 Basic Flow 1
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
		 * Test for US04.02.01 Basic Flow 2
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
		 * Test for US04.02.01 Basic Flow 3
		 */

		// view which is expected to be present on the screen
		final View decorView2 = nextActivity.getWindow().getDecorView();

		clv = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView2, clv);
		// check whether the Button object's width and height attributes match
		// the expected values
		final ViewGroup.LayoutParams layoutParams2 = clv.getLayoutParams();
		assertEquals(layoutParams2.width,WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams2.height,WindowManager.LayoutParams.WRAP_CONTENT);

		/*
		 * Test for US04.02.01 Basic Flow 4
		 */
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		assertNotNull(claimList.performClick());
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
					
				// build controller
				ClaimantAddClaimController cacc = new ClaimantAddClaimController(new ClaimList());
				// get ClaimantClaimListActivity
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(),null, false);
				final ClaimantClaimListActivity clActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// get claimListView
				final ListView claimList = (ListView) clActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);

				// add new claim, position: index 0
				cacc.addClaim("test", "2014-12-11", "2015-01-05");
				// click new claim
				assertNotNull(claimList.getChildAt(0).performClick());
							
				/*
				 * Test for US04.02.01 Basic Flow 5
				 */
					
				// get ClaimantItemListActivity
				ActivityMonitor activityMonitor1 = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(),null, false);
				final ClaimantItemListActivity ilActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 10000);
					
				assertNotNull(ilActivity);
					
				// get item list view
				// view which is expected to be present on the screen
				final View decorView3 = ilActivity.getWindow().getDecorView();
				final ListView itemList = (ListView) ilActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);	
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView3, itemList);
					
				/*
				 * Test for US04.02.01 Basic Flow 6
				 */
					
				final Button addItem = (Button) ilActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addItemButton);
				ilActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// click button and open next activity.
						addItem.performClick();
					}
				});
				
				/*
				 * Test for US04.01.01 Basic Flow 7
				 */
				
				ClaimantAddItemActivity aiActivity = (ClaimantAddItemActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// next activity is opened and captured.
				assertNotNull(aiActivity);

				// view which is expected to be present on the screen
				final View decorView4 = aiActivity.getWindow().getDecorView();
				
				EditText iDate = (EditText) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText);
				final Spinner iCat = (Spinner) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner);
				EditText iDes = (EditText) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText);
				EditText iAmount = (EditText) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText);
				Spinner iCurr = (Spinner) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner);
				
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView4, iDate);
				assertNotNull(iDate.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iCat);
				assertNotNull(iCat.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iDes);
				assertNotNull(iDes.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iAmount);
				assertNotNull(iAmount.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iCurr);
				assertNotNull(iCurr.getVisibility());
			
				ArrayList<String> categorylist = new ArrayList<String>(
						Arrays.asList("air fare","ground transport","vehicle rental","private automobile",
								"fuel","parking","registration","accommodation","meal","supplies"));
				
				
				iCat.post(new Runnable() {

					@Override
					public void run() {
						iCat.setSelection(2);					
					}
					
				});

				
				
				/*
				 * Test for US04.01.01 Basic Flow 8
				 */
				
				Spinner catSpinner = (Spinner) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner);
				assertEquals("correct category","vehicle rental",catSpinner.getContext());
		
		
		
		}
	});
	}
}

