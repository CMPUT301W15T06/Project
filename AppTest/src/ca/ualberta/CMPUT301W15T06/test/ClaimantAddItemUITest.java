package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddItemActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import ca.ualberta.CMPUT301W15T06.R;


public class ClaimantAddItemUITest extends
		ActivityInstrumentationTestCase2<ClaimantAddItemActivity>
{
	Instrumentation instrumentation;
	Activity activity;
	EditText item_name;
	EditText item_date;
	Spinner item_category;
	EditText item_des;
	EditText item_amount;
	Spinner item_currency;

	public ClaimantAddItemUITest()
	{

		super(ClaimantAddItemActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		item_name = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemNameEditText));
		item_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText));
		item_category = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner));
		item_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText));
		item_amount = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText));
		item_currency = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner));	
		// http://developer.android.com/tools/testing/activity_test.html 2015-02-13
	    cate = item_category.getAdapter();
	}
	
	// http://developer.android.com/tools/testing/activity_test.html 2015-01-13
	private ClaimantAddItemActivity mActivity;
	private Spinner i_cate;
	private SpinnerAdapter cate;
	public static final int INITIAL_POSITION = 0;
	
	public void testSpinnerUI() {
		mActivity.runOnUiThread(
			new Runnable() {
		        public void run() {
		          i_cate.requestFocus();
		          i_cate.setSelection(INITIAL_POSITION);
		        }
			});
	}

	
	// fill blank
	@SuppressWarnings("unused")
	private void testAddButton(String itemName, String itemDate, String itemCate, String itemDes, String itemAmount, String itemCurr) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
		int Pos = i_cate.getSelectedItemPosition();
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
		    assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.ApproverClaimList));	    
		}

}
