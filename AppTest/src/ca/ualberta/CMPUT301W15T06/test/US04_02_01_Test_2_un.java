package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddItemActivity;
import ca.ualberta.CMPUT301W15T06.R;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class US04_02_01_Test_2_un extends ActivityInstrumentationTestCase2<ClaimantAddItemActivity> {
	
	public US04_02_01_Test_2_un() {
		super(ClaimantAddItemActivity.class);
	}
	
	Instrumentation instrumentation;
	Activity activity;
	EditText item_name;
	EditText item_date;
	Spinner item_category;
	EditText item_des;
	EditText item_amount;
	Spinner item_currency;
	Button finish_button;
	TextView date;
	TextView category;
	TextView desc;
	TextView amount;
	TextView currency;
	
	private SpinnerAdapter cate;
	private SpinnerAdapter curr;
	
	public static final int INITIAL_POSITION = 0; 
	public static final int ADAPTER_COUNT = 9;
	public static final int TEST_POSITION = 5;
	
	private String mSelection;
	private int mPos;
	
	/*
	 * Test for US04.02.01 Basic Flow 7
	 */
	

	// set up method
	protected void setUp() throws Exception
	{
		super.setUp();
		instrumentation = getInstrumentation();
		
		// get a reference to the activity under test. If the activity is not starts, start a new activity
		activity = getActivity();
		
		// get text fields and form widgets
		item_date = (EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText);
		item_category = (Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner);
		item_des = (EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText);
		item_amount = (EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText);
		item_currency = (Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner);	
		finish_button = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton);
	    cate = item_category.getAdapter();
	    curr = item_currency.getAdapter();
	    date = (TextView) activity.findViewById(R.id.createItemDateTextView);
	    category = (TextView) activity.findViewById(R.id.createItemCategoryTextView);
	    desc = (TextView) activity.findViewById(R.id.createItemDescriptionTextView);
	    amount = (TextView) activity.findViewById(R.id.createItemAmountTextView);
	    currency = (TextView) activity.findViewById(R.id.createItemCurrencyTextView);
	    
	    /* This turns off touch mode in the device or emulator. 
	     * If any of your test methods send key events to the application, 
	     * you must turn off touch mode before you start any activities; 
	     * otherwise, the call is ignored.
	     */
	    setActivityInitialTouchMode(false);

	} // end of setUp method
	
	/*
	 * This part is spinner testing
	 * spinner id: createItemCategorySpinner
	 * 			   createCurrencySpinner
	 */
	
	// test pre-condition
	public void testPreConditions() {
		// listeners for two spinners are not null
		assertTrue(item_category.getOnItemSelectedListener() != null);
		assertTrue(item_currency.getOnItemSelectedListener() != null);
		
		// item_category has an adapter
		assertTrue(cate != null);
		assertEquals(cate.getCount(),ADAPTER_COUNT);
		
		// item_currency has an adapter
		assertTrue(curr != null);
		assertEquals(curr.getCount(),ADAPTER_COUNT);
		
	} 
	
	/*
	 * Test for US04.02.01 Basic Flow 7
	 */
	
	// test category spinner
	public void testCategorySpinnerUI() {
		
		// run activity
		activity.runOnUiThread(
			new Runnable() {
		        public void run() {
		        	item_category.requestFocus();
		        	item_category.setSelection(INITIAL_POSITION);
		        }
			});
		
		// make a selection
	    this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	    for (int i = 1; i <= TEST_POSITION; i++) {
	      this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
	    } 
	    
	    // send key event
	    this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	    
		/*
		 * Test for US04.02.01 Basic Flow 8
		 */
	    
	    // check the result
	    mPos = item_category.getSelectedItemPosition();
	    mSelection = (String)item_category.getItemAtPosition(mPos);
	    
	    // get the spinner result text view
	    TextView resultView = (TextView) activity.findViewById(R.id.createItemCategorySpinner);
	    
	    // get text
	    String resultText = (String) resultView.getText();

	    // check whether user's selection equals to the result in text view 
	    assertEquals(resultText,mSelection);

	} 
	
	/*
	 * End of spinner test
	 */
	
	/*
	 * This part is Button testing
	 * Button id: FinishItemButton
	 */
	
	// test layout of button
	public void testFinishButton_layout() {
		// view which is expected to be present on the screen
		final View decorView = activity.getWindow().getDecorView();
		
		// check if it is on screen
	    ViewAsserts.assertOnScreen(decorView, finish_button);

	    // check whether the Button object's width and height attributes match the expected values
	    final ViewGroup.LayoutParams layoutParams = finish_button.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertTrue(View.GONE == finish_button.getVisibility());
	    
	} 

	// test button behavior
	public void testFinishButton_expectInfo() {
		// expected string
	    String expectedInfoText = activity.getString(R.string.title_activity_claimant_add_item);
	    
	    // check if button is visible
	    TouchUtils.clickView(this, finish_button);
	    assertTrue(View.VISIBLE == finish_button.getVisibility());
	    
	    // check if text shown on button equals expected text
	    assertEquals(expectedInfoText.contains(finish_button.getText()), true);
	}
	
	/*
	 * End of button test
	 */

	/*
	 * This part is Text field testing
	 * Text id: createItemDateTextView
	 * 			createItemCategoryTextView
	 * 			createItemDescriptionTextView
	 * 			createItemAmountTextView
	 * 			createItemCurrencyTextView
	 */
	
	// test text view: createItemDateTextView
	public void testCreateItemDateTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, date);
	    assertTrue(View.GONE == date.getVisibility());
	}
	
	// test text view: createItemCategoryTextView
	public void testCreateItemCategoryTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, category);
	    assertTrue(View.GONE == category.getVisibility());
	}
	
	// test text view: createItemDescriptionTextView
	public void testCreateItemDescriionTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, desc);
	    assertTrue(View.GONE == desc.getVisibility());
	}
	
	// test text view: createItemAmountTextView
	public void testCreateItemAmountTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, amount);
	    assertTrue(View.GONE == amount.getVisibility());
	}
	
	// test text view: createItemCurrencyTextView
	public void testCreateItemCurrencyTextView() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, currency);
	    assertTrue(View.GONE == currency.getVisibility());
	}
	/*
	 * End of text view test
	 */
	
	/* There are bugs in this method
	@SuppressWarnings("unused")
	private void testAddButton(String itemName, String itemDate, String itemCate, String itemDes, String itemAmount, String itemCurr) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
		int Pos = cate.getSelectedItemPosition();
		String Selection = (String)i_cate.getItemAtPosition(Pos);
		TextView resultView =
			      (TextView) mActivity.findViewById(
			        ca.ualberta.CMPUT301W15T06.R.id.createItemCategoryTextView
			      );


		item_name.setText(itemName);
		item_date.setText(itemDate);
		String item_cate = (String) resultView.getText();
		item_des.setText(itemDes);
		item_amount.setText(itemAmount);
		String item_currency = (String) resultView.getText();
		
		((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton)).performClick();
	}

	
	//test if add something to list
		@SuppressWarnings("unused")
		public void testClickAddButton(String itemName, String itemDate, String itemCate, String itemDes, String itemAmount, String itemCurr) {
			assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			TextView resultView = (TextView) mActivity.findViewById(
				        ca.ualberta.CMPUT301W15T06.R.id.createItemCategoryTextView);
			item_name.setText(itemName);
			item_date.setText(itemDate);
			String item_cate = (String) resultView.getText();
			item_des.setText(itemDes);
			item_amount.setText(itemAmount);
			String item_currency = (String) resultView.getText();
			((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton)).performClick();
		    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView));	    
		}
	*/

}
