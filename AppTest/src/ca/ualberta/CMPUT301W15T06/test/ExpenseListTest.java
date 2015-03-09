/*
UA CMPUT 301 Project Group: CMPUT301W15T06

Copyright {2015} {Jingjiao Ni

              Tianqi Xiao

              Jiafeng Wu

              Xinyi Pan 

              Xinyi Wu

              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.

 */
package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import ca.ualberta.CMPUT301W15T06.ClaimantDeleteItemController;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.ClaimantAddItemController;
import ca.ualberta.CMPUT301W15T06.StatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3 2015-02-08


public class ExpenseListTest extends TestCase
{
	
	/*
	   US04.01.01
       As a claimant, I want to make one or more expense items for an expense claim, 
       each of which has a date the expense was incurred, a category, a textual description,
       amount spent, and unit of currency.
	 */
	public void testAddExpense() throws StatusException {
		// add a expense item, check the size to see if add successfully
		// make sure that this expense item has all the information
		Claim nc = new Claim("nc");
		Item testExpense = new Item();
//		testExpense.setDate("2015-01-01");
//		testExpense.setCategory("ground transport");
//		testExpense.setAmount(2.99);
//		testExpense.setCurrency("CAD");
//		testExpense.setDescription("one day bus trip");
		
		ClaimantAddItemController i1 = new ClaimantAddItemController(nc);
		i1.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		assertTrue("Expense List size", nc.getItemSize() == 1);

		assertTrue("Correct date?",testExpense.getDate()=="2015-01-01");
		assertTrue("Correct category?",testExpense.getCategory()=="ground transport");
		assertTrue("Correct amount?", testExpense.getAmount()==2.99);
		assertTrue("Correct currenct?",testExpense.getCurrency()=="CAD");
		assertTrue("Correct description?",testExpense.getDescription()=="one day bus trip");
		
		// satisfy the condition "add one or more item"
		Item testExpense2 = new Item();
//		testExpense2.setDate("2015-01-02");
//		testExpense2.setCategory("accommodation");
//		testExpense2.setAmount(160.0);
//		testExpense2.setCurrency("USD");
//		testExpense2.setDescription("Fairmont");
		
		ClaimantAddItemController i2 = new ClaimantAddItemController(nc);
		i2.addItem("2015-01-02", "accomodation", "Fairmont", 160.0, "USD");
		assertTrue("Expense List size", nc.getItemSize() == 2);
		
		assertTrue("Correct date?",testExpense.getDate()=="2015-01-02");
		assertTrue("Correct category?",testExpense.getCategory()=="accommodation");
		assertTrue("Correct amount?", testExpense.getAmount()==160.0);
		assertTrue("Correct currenct?",testExpense.getCurrency()=="USD");
		assertTrue("Correct description?",testExpense.getDescription()=="Fairmont");
	}
	
	/*
	  US04.06.01
      As a claimant, I want to edit an expense item while changes are allowed.
	 */
	public void testEditExpense() throws StatusException{
		// set up expense information
		Claim c = new Claim("a");
		ClaimantAddItemController claim = new ClaimantAddItemController(c);
		
		Item testExpenseEdit = new Item();
		claim.addItem("2015-01-05", "vehicle rental", "travel purpose", 3000.0, "CNY");
//		testExpenseEdit.setDate("2015-01-05");
//		testExpenseEdit.setCategory("vehicle rental");
//		testExpenseEdit.setAmount(3000.0);
//		testExpenseEdit.setCurrency("CNY");
//		testExpenseEdit.setDescription("travel purpose");
		// edit the information 
		c.setStatus("In progress");
		c.setStatus("Submmited");
		((Item) c.getItem("BMW")).setDate("2015-02-01");
		assertFalse("cannot edit",testExpenseEdit.getDate()== "2015-02-01");		
	}

	
	/*
	  US04.07.01
      As a claimant, I want to delete an expense item while changes are allowed.
	 */
	public void testDeleteExpense() throws StatusException {
		Claim nc = new Claim("nc");
		String expenseName = "bus ticket";
		
		ClaimantAddItemController aic = new ClaimantAddItemController(nc);
		Item expenseTest1 = new Item();
		aic.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD");
		assertTrue("Expense List size", nc.getItemSize() == 1);
		
		ClaimantDeleteItemController dic = new ClaimantDeleteItemController(nc);
		dic.removeItem(expenseTest1);
		assertTrue("Expense List size", nc.getItemSize() == 0);
	}
	
	/*
	  US04.08.01
	  As a claimant, I want the entry of an expense item to have minimal 
	  required navigation in the user interface.
	  
	  We could test this use case manually, it just need 2 steps. 
	  User choose Claimant from main page, then long click one of 
	  claims and select Item list
	  (See story board 4&5 as reference)
	  
	  For minimal required navigation, just need 2 User interface. 
	  One is main page, another is claim list UI.
	 */
	
	/*
	  US05.01.01
      As a claimant, I want to list all the expense items for a claim, in order of entry, 
      showing for each expense item: the date the expense was incurred, the category, 
      the textual description, amount spent, unit of currency, whether there is a photographic 
      receipt, and incompleteness indicator.
	 */
	public void testOrderedExpenseList() throws ParseException, StatusException {	
		// sample test data 1
		String expenseName1 = "BMW";
		Item expenseTest1 = new Item();

		
		// sample test data 2
		String expenseName2 = "bus ticket";
		Item expenseTest2 = new Item();

		
		// sample test data 3
		String expenseName3 = "fairmont hotel";
		Item expenseTest3 = new Item();

		
		// add all three expense to the claim
		Claim testClaim = new Claim("NEW");
		ClaimantAddItemController claim = new ClaimantAddItemController(testClaim); 
		claim.addItem("2015-01-05", "vehicle rental", "travel purpose", 3000.0, "CNY"); 
		claim.addItem("2015-01-01", "ground transport", "one day bus trip", 2.99, "CAD"); 
		claim.addItem("2015-01-02", "accommodation", "Fairmont", 160.0, "USD"); 
		
		
		// http://stackoverflow.com/questions/15925509/java-compare-two-dates 2015-2-12		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		Date localDate1 = date.parse(expenseTest1.getDate()); 
		Date localDate2 = date.parse(expenseTest2.getDate()); 
		Date localDate3 = date.parse(expenseTest3.getDate());	 	
		
		// check the order for the expense list
		if (localDate1.before(localDate2)){
			System.out.println("wrong order");
		}
		if (localDate1.before(localDate3)){
			System.out.println("Wrong order");
		}
		if (localDate2.before(localDate3)){
			System.out.println("true");
		}
		
		// show each expense item information
		assertEquals("expense Test 1 date:",localDate1,testClaim.getItemDate(expenseTest1));
		assertEquals("expense Test 1 category","vehicle rental",testClaim.getItemCategory(expenseTest1));
		assertEquals("expense Test 1 amount",3000.0,testClaim.getItemAmount(expenseTest1));
		assertEquals("expense Test 1 currency","CNY",testClaim.getItemCurrency(expenseTest1));
		assertEquals("expense Test 1 description", "travel purpose",testClaim.getItemDescription(expenseTest1));
		
		assertEquals("expense Test 2 date:",localDate2,testClaim.getItemDate(expenseTest2));
		assertEquals("expense Test 2 category","grount transport",testClaim.getItemCategory(expenseTest2));
		assertEquals("expense Test 2 amount",2.99,testClaim.getItemAmount(expenseTest2));
		assertEquals("expense Test 2 currency","CAD",testClaim.getItemCurrency(expenseTest2));
		assertEquals("expense Test 2 description", "one day bus trip",testClaim.getItemDescription(expenseTest2));
		
		assertEquals("expense Test 3 date:",localDate3,testClaim.getItemDate(expenseTest3));
		assertEquals("expense Test 3 category","accommodation",testClaim.getItemCategory(expenseTest3));
		assertEquals("expense Test 3 amount",160.0,testClaim.getItemAmount(expenseTest3));
		assertEquals("expense Test 3 currency","USD",testClaim.getItemCurrency(expenseTest3));
		assertEquals("expense Test 3 description", "Fairmont",testClaim.getItemDescription(expenseTest3));
		
		System.out.println(testClaim.getItemDate(expenseTest1)+testClaim.getItemCategory(expenseTest1)+
				testClaim.getItemAmount(expenseTest1)+testClaim.getItemCurrency(expenseTest1)+
			    testClaim.getItemDescription(expenseTest1));	
		
		System.out.println(testClaim.getItemDate(expenseTest2)+testClaim.getItemCategory(expenseTest2)+
				testClaim.getItemAmount(expenseTest2)+testClaim.getItemCurrency(expenseTest2)+
			    testClaim.getItemDescription(expenseTest2));	
		
		System.out.println(testClaim.getItemDate(expenseTest3)+testClaim.getItemCategory(expenseTest3)+
				testClaim.getItemAmount(expenseTest3)+testClaim.getItemCurrency(expenseTest3)+
			    testClaim.getItemDescription(expenseTest3));	
		
	}


}
