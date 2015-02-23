package ca.ualberta.CMPUT301W15T06.test;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.ItemList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import ca.ualberta.CMPUT301W15T06.R;

public class ClaimantEditItemUITest extends
		ActivityInstrumentationTestCase2<ClaimantEditItemActivity> {
	
	Instrumentation instrumentation;
	Activity activity;
	EditText item_date;
	Spinner item_category;
	EditText item_des;
	EditText item_amount;
	Spinner item_currency;
	Button item_add_button;
	

	public ClaimantEditItemUITest() {
		super(ClaimantEditItemActivity.class);
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		item_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText));
		item_category = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner));
		item_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText));
		item_amount = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText));
		item_currency = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner));
		item_add_button = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
	}
		
		/*
		   US04.01.01
	       As a claimant, I want to make one or more expense items for an expense claim, 
	       each of which has a date the expense was incurred, a category, a textual description,
	       amount spent, and unit of currency.
		 */
		@SuppressLint("CutPasteId")
		public void addItemUITest () {
			// click button runnable
			final Button item_add_button_1 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					item_add_button_1.performClick();
				}				
			});
			// create two item and set up info
			Item testExpense = new Item();
			Item testExpense2 = new Item();
			ItemList expenseList = new ItemList();
			item_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText));
			testExpense.setDate("2015-01-01");
			item_category = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner));
			testExpense.setCategory("ground transport");
			item_amount = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText));
			testExpense.setAmount(2.99);
			item_currency = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner));
			testExpense.setCurrency("CAD");
			item_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText));
			testExpense.setDescription("one day bus trip");
			assertTrue("Correct date?",testExpense.getDate()=="2015-01-01");
			assertTrue("Correct category?",testExpense.getCategory()=="ground transport");
			assertTrue("Correct amount?", testExpense.getAmount()==2.99);
			assertTrue("Correct currenct?",testExpense.getCurrency()=="CAD");
			assertTrue("Correct description?",testExpense.getDescription()=="one day bus trip");
			
			// click button runnable
			final Button item_add_button_2 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					item_add_button_2.performClick();
				}				
			});
			
			// set up info
			testExpense2.setDate("2015-01-02");
			testExpense2.setCategory("accommodation");
			testExpense2.setAmount(160);
			testExpense2.setCurrency("USD");
			testExpense2.setDescription("Fairmont");
			expenseList.addItem(testExpense2);
			Collection<Item> expense2 = expenseList.getItem();
			
			// get expense list size
			assertTrue("Expense List size", expense2.size() == 2);			
		}
		
		/*
	      US04.02.01
	      As a claimant, I want the category for an expense item to be one of air fare, 
	      ground transport, vehicle rental, private automobile, fuel, parking, registration, 
	      accommodation, meal, or supplies.
	     */
		public void ItemCategoryUITest() {
			
			// click button runnable
			final Button item_add_button = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					item_add_button.performClick();
				}				
			});
			
			// set up category ArrayList
			item_category = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner));
			ArrayList<String> category = new ArrayList<String>();
			category.add("Air Fare");
			category.add("Ground transport");
			category.add("Vehicle Rental");
			category.add("Private automobile");
			category.add("fuel");
			category.add("Fuel");
			category.add("Parking");
			category.add("Registration");
			category.add("Accommodation");
			category.add("Meal");
			category.add("Supplies");
			
			// satisfied category in the spinner
			for (int i = 0; i < item_category.getAdapter().getCount();i++){
	    		String cate = String.valueOf(item_category.getItemAtPosition(i));
	    		assertTrue("Improper category: " + cate, category.contains(cate));
			}
			
		}
		
		/*
		  US04.03.01
	      As a claimant, I want the currency for an expense amount to be one of CAD, 
	      USD, EUR, GBP, CHF, JPY, or CNY.
		 */	
		
		public void currencyUITest() {
			
			// click button runnable
			final Button item_add_button = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
			    public void run() {
					item_add_button.performClick();
				}				
			});
			
			// set up currency ArrayList
			item_currency = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner));
			ArrayList<String> currency = new ArrayList<String>();
			currency.add("CAD");
			currency.add("USD");
			currency.add("EUR");
			currency.add("CHF");
			currency.add("JPY");
			currency.add("CNY");
			
			// satisfied currency in the spinner
			for (int i = 0; i < item_currency.getAdapter().getCount();i++){
				String curr = String.valueOf(item_currency.getItemAtPosition(i));
				assertTrue("Improper category: " + curr, currency.contains(curr));
			}
						
		}
		
		/*
		  US04.04.01
	      As a claimant, I want to manually flag an expense item with an incompleteness 
	      indicator, even if all item fields have values, so that I am reminded that the 
	      item needs further editing.
		 */
		@SuppressLint("CutPasteId")
		public void flagUITest() {
			
			// click button runnable
			final Button item_add_button = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					item_add_button.performClick();
				}				
			});
			
			// default is no flag
			Item testExpenseFlag = new Item();
			assertTrue("no flag",testExpenseFlag.getFlag());
			
			// click button runnable -- click flag
			final Button item_add_button_2 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					item_add_button_2.performClick();
				}				
			});
			
			// add flag
			testExpenseFlag.addFlag();
			assertTrue("has flag", testExpenseFlag.getFlag());	
								
		}
		
		/*
		  US04.05.01
	      As a claimant, I want to view an expense item and its details.
		 */
		public void viewItemUITest() {
			
			// execute thread
			instrumentation.runOnMainSync(new Runnable(){

				@Override
				public void run() {
					// set up info
					Item expenseInfo = new Item();
					expenseInfo.setDate("2015-01-05");
					expenseInfo.setCategory("vehicle rental");
					expenseInfo.setAmount(3000);
					expenseInfo.setCurrency("CNY");
					expenseInfo.setDescription("travel purpose");			
				}
				
			});
			
			instrumentation.waitForIdleSync();
			
			Item expenseInfo = new Item();
			item_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDateEditText));
			expenseInfo.setDate("2015-01-05");
			item_category = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemCategorySpinner));
			expenseInfo.setCategory("vehicle rental");
			item_amount = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemAmountEditText));
			expenseInfo.setAmount(3000);
			item_currency = ((Spinner) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createCurrencySpinner));
			expenseInfo.setCurrency("CNY");
			item_des = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createItemDescriptionEditText));
			expenseInfo.setDescription("travel purpose");		
			
			System.out.println(expenseInfo.getDate()+expenseInfo.getCategory()+expenseInfo.getAmount()+
					expenseInfo.getCurrency()+expenseInfo.getDescription());	
			
	    }
		
		/*
		  US04.06.01
	      As a claimant, I want to edit an expense item while changes are allowed.
		 */
		@SuppressLint("CutPasteId")
		public void editItemUITest() {
			
			// Execute thread
			instrumentation.runOnMainSync(new Runnable() {
				
				@Override
			    public void run() {
					// set up info
					Item expenseInfo = new Item();
				    expenseInfo.setDate("2015-01-05");
				    expenseInfo.setCategory("vehicle rental");
				    expenseInfo.setAmount(3000);
				    expenseInfo.setCurrency("CNY");
				    expenseInfo.setDescription("travel purpose");			
				}
				
			});
			
			instrumentation.waitForIdleSync();
			
			Claim claim = new Claim("one");
			Item testExpenseEdit = new Item();
			claim.addItem(testExpenseEdit);	
			claim.setStatus("In progress");
			((TestCase) claim.getItem("BMW")).setName("fairmont hotel");
			// click button runnable
			final Button item_add_button = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					item_add_button.performClick();
				}				
			});
			assertTrue("Edit successfully",testExpenseEdit.getName()=="fairmont hotel");
			claim.setStatus("Submmited");
			((Item) claim.getItem("BMW")).setDate("2015-02-01");
			// click button runnable
			final Button item_add_button_2 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					item_add_button_2.performClick();
				}				
			});
			assertFalse("cannot edit",testExpenseEdit.getDate()== "2015-02-01");	
		}
		
		/*
		  US04.07.01
	      As a claimant, I want to delete an expense item while changes are allowed.
		 */
		@SuppressLint("CutPasteId")
		public void deleteItemUITest() {
			
			// Execute thread
			instrumentation.runOnMainSync(new Runnable() {
				
				@Override
			    public void run() {
					// set up info
					Item expenseInfo = new Item();
				    expenseInfo.setDate("2015-01-05");
				    expenseInfo.setCategory("vehicle rental");
				    expenseInfo.setAmount(3000);
				    expenseInfo.setCurrency("CNY");
				    expenseInfo.setDescription("travel purpose");			
				}
				
			});
			
			instrumentation.waitForIdleSync();
	
			Claim claim = new Claim("one");
			ItemList expenseList = new ItemList();
			Collection<Item> expense = expenseList.getItem();
			Item testExpenseDel = new Item();
			claim.setStatus("Submmited");
			assertTrue("Expense List size", expense.size() == 1);
			// click button runnable
			final Button item_add_button = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
					public void run() {
						item_add_button.performClick();
					}				
			});
			assertTrue("has", expense.contains(testExpenseDel));
			
			// click button runnable
			final Button item_add_button_2 = ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.FinishItemButton));
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					item_add_button_2.performClick();
				}				
			});
			expenseList.removeItem(testExpenseDel);
			expense = expenseList.getItem();
			assertTrue("Expense List size", expense.size() == 0);
			assertFalse("Test expense still contained?", expense.contains(testExpenseDel));	
		}
		
		/*
		  US05.01.01
	      As a claimant, I want to list all the expense items for a claim, in order of entry, 
	      showing for each expense item: the date the expense was incurred, the category, 
	      the textual description, amount spent, unit of currency, whether there is a photographic 
	      receipt, and incompleteness indicator.
		 */
		
		public void listItemUITEst() {
			ArrayList<Item> itemList = null;
			Claim claim = ClaimListController.getCurrentClaim();
			for (int i = 0; i<itemList.size();i++) {
				String item =((ClaimantItemListActivity) activity).claim.toString();
			}
			
		}


		
		
		

}
