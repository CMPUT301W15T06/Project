package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantAddItemController;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.StatusException;
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
import android.widget.GridView;
import android.widget.ListView;

public class US04_04_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	Button ApproverButton;
	Button ClaimantButton;
	Intent intent;
	ListView clv;
	
	public US04_04_01_Test () {
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
	 * Test for US04.04.01 Basic Flow 1
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
		 * Test for US04.04.01 Basic Flow 2
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
		 * Test for US04.04.01 Basic Flow 3
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
		 * Test for US04.04.01 Basic Flow 4
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
				 * Test for US04.04.01 Basic Flow 5
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
				
				// check whether the Button object's width and height attributes match
				// the expected values
				final ViewGroup.LayoutParams layoutParams2 = clv.getLayoutParams();
				assertEquals(layoutParams2.width,WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams2.height,WindowManager.LayoutParams.WRAP_CONTENT);

				/*
				 * Test for US04.04.01 Basic Flow 6
				 */
				
				final ListView itemList1 = (ListView) ilActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				assertNotNull(itemList1.performClick());
				ilActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
							
						// build controller
						Claim claim = new Claim("test");
						ClaimantAddItemController caic = new ClaimantAddItemController(claim);
						
						// get ClaimantItemListActivity
						ActivityMonitor activityMonitor1 = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(),null, false);
						final ClaimantItemListActivity ilActivity1 = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 10000);
						
						// get itemListView
						final ListView itemList1 = (ListView) ilActivity1.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);

						// add new item, position: index 0
						try {
							caic.addItem("2015-01-05", "fuel","travel",88.00,"CAD");
						} catch (StatusException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// click new item
						assertNotNull(itemList1.getChildAt(0).performLongClick());
						
						/*
						 * Test for US04.04.01 Basic Flow 7
						 */
						
						// get contextMenu
						final GridView contextMenu = (GridView) ilActivity1.findViewById(ca.ualberta.CMPUT301W15T06.R.array.item_dialog_array);

						// check if user can click this context menu
						assertNotNull(contextMenu.performClick());
						
						/*
						 * Test for US04.04.01 Basic Flow 8
						 */
						
						ilActivity1.runOnUiThread(new Runnable(){

							@Override
							public void run() {
								contextMenu.performItemClick(contextMenu, 0, claimList.getAdapter().getItemId(0));
							}
						});
				
				
				
			}
		});
			}
		});
	}
}
