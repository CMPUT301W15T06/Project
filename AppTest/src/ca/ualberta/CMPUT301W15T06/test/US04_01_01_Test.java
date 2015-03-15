package ca.ualberta.CMPUT301W15T06.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimController;
import ca.ualberta.CMPUT301W15T06.ClaimantAddItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.R;

public class US04_01_01_Test extends ActivityInstrumentationTestCase2<MainActivity>{

	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	ListView clv;
	
	public US04_01_01_Test() {
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

		clv = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
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
				 * Test for US01.02.01 Basic Flow 5
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
				 * Test for US01.02.01 Basic Flow 6
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
				 * Test for US01.02.01 Basic Flow 7
				 */
				
				ClaimantAddItemActivity aiActivity = (ClaimantAddItemActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// next activity is opened and captured.
				assertNotNull(aiActivity);

				// view which is expected to be present on the screen
				final View decorView4 = aiActivity.getWindow().getDecorView();
				
				EditText iDate = (EditText) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText);
				Spinner iCat = (Spinner) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner);
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
				
				/*
				 * Test for US01.02.01 Basic Flow 8
				 */
				
				String itemDate = "2015-03-03";
				String itemDes = "travel";
				String itemAmount = "50.00";
				
				iDate.setText(itemDate);
				iCat.setSelection(4);
				iDes.setText(itemDes);
				iAmount.setText(itemAmount);
				iCurr.setSelection(1);
				
                // test finish button
				final View decorView5 = aiActivity.getWindow().getDecorView();
				final Button addFinish = (Button) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
				
				ViewAsserts.assertOnScreen(decorView5, addFinish);
				
				final ViewGroup.LayoutParams layoutParams3 = addFinish.getLayoutParams();
				assertNotNull(layoutParams3);
				assertEquals(layoutParams3.width,WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams3.height,WindowManager.LayoutParams.WRAP_CONTENT);
				
				Button add = (Button) aiActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
				assertEquals("incorrect label of the button","Finish",add.getText());
				
				aiActivity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						addFinish.performClick();					
					}
					
				});
				
				assertNotNull(aiActivity);
				aiActivity.finish();
				
				
				
				
				/*
				 * add more item
				 */
				
				
				// get ClaimantItemListActivity
				ActivityMonitor activityMonitor5 = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(),null, false);
				final ClaimantItemListActivity il2Activity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor5, 10000);
				
				assertNotNull(il2Activity);
				
				// get item list view
				// view which is expected to be present on the screen
				final View decorView11 = il2Activity.getWindow().getDecorView();
				final ListView itemList11 = (ListView) il2Activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);	
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView11, itemList11);
				

				
				final Button addItem11 = (Button) ilActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addItemButton);
				ilActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// click button and open next activity.
						addItem11.performClick();
					}
				});
				

				
				ClaimantAddItemActivity aiActivity11 = (ClaimantAddItemActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// next activity is opened and captured.
				assertNotNull(aiActivity11);

				// view which is expected to be present on the screen
				final View decorView111 = aiActivity11.getWindow().getDecorView();
				
				EditText iDate1 = (EditText) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText);
				Spinner iCat1 = (Spinner) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner);
				EditText iDes1 = (EditText) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText);
				EditText iAmount1 = (EditText) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText);
				Spinner iCurr1 = (Spinner) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner);
				
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView4, iDate1);
				assertNotNull(iDate1.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iCat1);
				assertNotNull(iCat1.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iDes1);
				assertNotNull(iDes1.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iAmount1);
				assertNotNull(iAmount1.getVisibility());
				
				ViewAsserts.assertOnScreen(decorView4, iCurr1);
				assertNotNull(iCurr1.getVisibility());
				

				
				String itemDate1 = "2015-03-05";
				String itemDes1 = "ticket";
				String itemAmount1 = "2.99";
				
				iDate1.setText(itemDate1);
				iCat1.setSelection(1);
				iDes1.setText(itemDes1);
				iAmount1.setText(itemAmount1);
				iCurr1.setSelection(2);
				
                // test finish button
				final View decorView51 = aiActivity11.getWindow().getDecorView();
				final Button addFinish1 = (Button) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
				
				ViewAsserts.assertOnScreen(decorView51, addFinish1);
				
				final ViewGroup.LayoutParams layoutParams31 = addFinish1.getLayoutParams();
				assertNotNull(layoutParams31);
				assertEquals(layoutParams31.width,WindowManager.LayoutParams.MATCH_PARENT);
				assertEquals(layoutParams31.height,WindowManager.LayoutParams.WRAP_CONTENT);
				
				Button add1 = (Button) aiActivity11.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
				assertEquals("incorrect label of the button","Finish",add1.getText());
				
				aiActivity11.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						addFinish1.performClick();					
					}
					
				});
		
			}
		});
		
		}
	}

