package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemDetailActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US04_06_01_Test extends
			ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button ClaimantButton;
	Button UserButton;
	Instrumentation instrumentation;
	MainActivity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	Menu menu;
	View View1;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	Button FinishButton;
	User u;


	public US04_06_01_Test() {
		super(MainActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		AppSingleton.getInstance().setTest(true);
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(false);
		ApproverButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		UserButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		u = AppSingleton.getInstance().getCurrentUser();

	}
	
	public void test040601() {

		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// click user button
				assertTrue(UserButton.performClick());	

			}	
		});


		// click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		
		// get current activity			
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
			}
		});

		// start next activity
		final ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);

		// layout of claim list
		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		//get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
				claimList.getChildAt(0).performClick();
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);

				ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				
				/*
				 * Test for US 04.06.01 Basic Flow 1
				 */
				// get item list layout
				final ListView itemlist = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				thirdActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						
						/*
						 * Test for US 04.06.01 Basic Flow 2
						 */
						// click on item
						itemlist.getChildAt(0).performClick();		
					}
				});
				
				/*
				 * Test for US 04.06.01 Basic Flow 3
				 */
				// check option array
				AlertDialog d = (AlertDialog) thirdActivity.getDialog();
				assertNotNull(d);
				
				ListView choose = (ListView) ilv.findViewById(ca.ualberta.CMPUT301W15T06.R.array.item_dialog_array);
				assertNotNull(choose);
				
				/*
				 * Test for US 04.06.01 Basic Flow 4
				 */
				// click one "Details"
				choose.getChildAt(2).performClick();
				ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantItemDetailActivity.class.getName(), null, false);
				
				/*
				 * Test for US 04.06.01 Basic Flow 5
				 */
				// start claimant item detail activity
				ClaimantItemDetailActivity bbb = (ClaimantItemDetailActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
				// check if the activity is started
				assertNotNull(bbb);
				
				
				/*
				 * Test for US 04.06.01 Basic Flow 6
				 */
				// test button layout
				final Button edit = (Button) bbb.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemButton);
				assertNotNull(edit);
				// get decorview 
				final View decorView = bbb.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(decorView, edit);
				final ViewGroup.LayoutParams layoutParams =
						edit.getLayoutParams();
				assertNotNull(layoutParams);
				// check layout
				assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
				Button view = (Button) bbb.findViewById(ca.ualberta.CMPUT301W15T06.R.id.edit);
				assertEquals("Incorrect label of the button", "Edit this Item", view.getText());
				
				Claim claim = new Claim();
				int count_before = claim.getItemList().size();
				thirdActivity.runOnUiThread(new Runnable() {
					//open edit item layout
					@Override
					public void run() {
						edit.performClick();
						ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantEditItemActivity.class.getName(), null, false);
						ClaimantEditItemActivity ccc = (ClaimantEditItemActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
						assertNotNull(ccc);		
						
						/*
						 * Test for US 04.06.01 Basic Flow 7
						 */
						//test TextView
						final View ddvv = ccc.getWindow().getDecorView();
						TextView Date = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDateTextView);
						ViewAsserts.assertOnScreen(ddvv, Date);
						assertNotNull(Date.getVisibility());
						TextView Cate = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemCategoryTextView);
						ViewAsserts.assertOnScreen(ddvv, Cate);
						assertNotNull(Date.getVisibility());
						TextView des = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDescriptionTextView);
						ViewAsserts.assertOnScreen(ddvv, des);
						assertNotNull(des.getVisibility());
						TextView Amount = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemAmountTextView);
						ViewAsserts.assertOnScreen(ddvv, Amount);
						assertNotNull(Amount.getVisibility());
						TextView currency = (TextView) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemCurrencyTextView);
						ViewAsserts.assertOnScreen(ddvv, currency);
						assertNotNull(currency.getVisibility());
						//test EditView
						EditText da = ((EditText) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDateEditText));
						assertNotNull(da);
						EditText de = ((EditText) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDescriptionEditText));
						assertNotNull(de);
						EditText am = ((EditText) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemAmountEditText));
						assertNotNull(am);
						/*
						* Test for US 04.06.01 Basic Flow 8
						*/
						//fill blank
						//test spinner
						Spinner spinner = (Spinner) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editCategorySpinner);
						assertNotNull(spinner);
						spinner.setSelection(0,true);
						Spinner spinner1 = (Spinner) ccc.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editCurrencySpinner);
						assertNotNull(spinner1);
						spinner1.setSelection(0,true);
						final String date1 = "2014-01-01";
						final String des1 = "b";
						final int amount1 = 111;
						da.setText(date1);
						de.setText(des1);
						am.setText(amount1);

						ccc.finish();
					}
					});
				/*
				 * Test for US 04.06.01 Basic Flow 9
				 */
					int count_after = claim.getItemList().size();
					assertEquals(count_before,count_after);
					
					bbb.finish();
			}
		});
		
		activity.finish();
	}
	
}
		