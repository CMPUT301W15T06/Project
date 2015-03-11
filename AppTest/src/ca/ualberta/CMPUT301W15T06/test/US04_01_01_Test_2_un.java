package ca.ualberta.CMPUT301W15T06.test;


import ca.ualberta.CMPUT301W15T06.ClaimantAddItemActivity;
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
import android.widget.EditText;
import android.widget.Spinner;



public class US04_01_01_Test_2_un extends
ActivityInstrumentationTestCase2<ClaimantAddItemActivity> {

	public US04_01_01_Test_2_un() {
		super(ClaimantAddItemActivity.class);
	}

	Instrumentation instrumentation;
	Activity activity;
	EditText i_date;
	Spinner i_category;
	EditText i_description;
	EditText i_amount;
	Spinner i_currency;
	Button i_add;
	Button i_add_new;
	
	
	//set up
	protected void setUp() throws Exception{
		super.setUp();
		activity = getActivity();
		instrumentation = getInstrumentation();
		i_add_new = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addItemButton);
	}
	
	
	public void testOpenNextActivity() {
		// register next activity that need to be monitored.
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantAddItemActivity.class.getName(), null, false);

		// open current activity.
		ClaimantAddItemActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addItemButton);
		myActivity.runOnUiThread(new Runnable() {
		  @Override
		  public void run() {
		    // click button and open next activity.
		    button.performClick();
		  }
		});

		ClaimantAddItemActivity nextActivity = (ClaimantAddItemActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
	
	
		i_date = (EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText);
		i_category = (Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner);
		i_description = (EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText);
		i_amount = (EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText);
		i_currency = (Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner);
	    i_add = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
		
		/*
		 * Test for US04.01.01 Basic Flow 7
		 */
		
		// test text view: item date
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, i_date);
		assertTrue(View.GONE == i_date.getVisibility());
		
		
		// test text view: item category
		final View decorView1 = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView1, i_category);
		assertTrue(View.GONE == i_category.getVisibility());
		
		
		// test text view: item description
		final View decorView2 = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView2, i_description);
		assertTrue(View.GONE == i_description.getVisibility());
		
		
		// test text view: item amount
		final View decorView3 = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView3, i_amount);
		assertTrue(View.GONE == i_amount.getVisibility());
		
		
		// test text view: item currency
		final View decorView4 = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView4, i_currency);
		assertTrue(View.GONE == i_currency.getVisibility());
	}
	
	/*
	 * Test for US04.01.01 Basic Flow 8
	 */
		
	// test 'Finish' button layout
	public void testFinishButtonlayout() {
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, i_add);
		final ViewGroup.LayoutParams layoutParams = i_add.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
			    
		Button i_view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
		assertEquals("Incorrect label of the button", "Finish", i_view.getText());
	}
	
	
}
