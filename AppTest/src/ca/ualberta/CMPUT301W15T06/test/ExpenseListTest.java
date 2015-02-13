package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.ItemList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

//https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3 2015-02-08


public class ExpenseListTest extends TestCase
{
	public void testExpenseList() {
		ItemList expenseList = new ItemList();
		Collection<Item> expense = expenseList.getItem();
		assertTrue("Empty Expense List", expense.size() == 0);
	}
	
	/*
	   US04.01.01
       As a claimant, I want to make one or more expense items for an expense claim, 
       each of which has a date the expense was incurred, a category, a textual description,
       amount spent, and unit of currency.
	 */
	public void testAddExpense() {
		// add a expense item, check the size to see if add successfully
		ItemList expenseList = new ItemList();
		String expenseName = "bus ticket";
		String expenseName2 = "hotel";
		Item testExpense = new Item(expenseName);
		Item testExpense2 = new Item(expenseName2);
		expenseList.addItem(testExpense);
		Collection<Item> expense = expenseList.getItem();
		assertTrue("Expense List size", expense.size() == 1);
		assertTrue("Test expense not contained", expense.contains(testExpense));	
		
		// make sure that this expense item has all the information
		testExpense.setDate("2015-01-01");
		testExpense.setCategory("ground transport");
		testExpense.setAmount(2.99);
		testExpense.setCurrency("CAD");
		testExpense.setDescription("one day bus trip");
		assertTrue("Correct date?",testExpense.getDate()=="2015-01-01");
		assertTrue("Correct category?",testExpense.getCategory()=="ground transport");
		assertTrue("Correct amount?", testExpense.getAmount()==2.99);
		assertTrue("Correct currenct?",testExpense.getCurrency()=="CAD");
		assertTrue("Correct description?",testExpense.getDescription()=="one day bus trip");
		
		// satisfy the condition "add one or more item"
		testExpense2.setDate("2015-01-02");
		testExpense2.setCategory("accommodation");
		testExpense2.setAmount(160);
		testExpense2.setCurrency("USD");
		testExpense2.setDescription("Fairmont");
		expenseList.addItem(testExpense2);
		Collection<Item> expense2 = expenseList.getItem();
		assertTrue("Expense List size", expense2.size() == 2);
	}
	
	/*
	  US04.06.01
      As a claimant, I want to edit an expense item while changes are allowed.
	 */
	public void testEditExpense(){
		// set up expense information
		Claim claim = new Claim("one");
		Item testExpenseEdit = new Item("BMW");
		claim.addItem(testExpenseEdit);	
		testExpenseEdit.setDate("2015-01-05");
		testExpenseEdit.setCategory("vehicle rental");
		testExpenseEdit.setAmount(3000);
		testExpenseEdit.setCurrency("CNY");
		testExpenseEdit.setDescription("travel purpose");
		// edit the information 
		claim.setStatus("In progress");
		((TestCase) claim.getItem("BMW")).setName("fairmont hotel");
		assertTrue("Edit successfully",testExpenseEdit.getName()=="fairmont hotel");
		claim.setStatus("Submmited");
		((Item) claim.getItem("BMW")).setDate("2015-02-01");
		assertFalse("cannot edit",testExpenseEdit.getDate()== "2015-02-01");		
	}

	
	/*
	  US04.07.01
      As a claimant, I want to delete an expense item while changes are allowed.
	 */
	public void testDeleteExpense() {
		ItemList expenseList = new ItemList();
		String expenseName = "bus ticket";
		Item testExpenseDel = new Item(expenseName);
		expenseList.addItem(testExpenseDel);
		Collection<Item> expense = expenseList.getItem();
		assertTrue("Expense List size", expense.size() == 1);
		assertTrue("", expense.contains(testExpenseDel));
		expenseList.removeItem(testExpenseDel);
		expense = expenseList.getItem();
		assertTrue("Expense List size", expense.size() == 0);
		assertFalse("Test expense still contained?", expense.contains(testExpenseDel));	
	}
	
	/*
	  US04.08.01
	  As a claimant, I want the entry of an expense item to have minimal 
	  required navigation in the user interface.
	  
	  We could test this use case manually, it just need 2 steps.
	  (See story board as reference)
	 */
	
	/*
	  US05.01.01
      As a claimant, I want to list all the expense items for a claim, in order of entry, 
      showing for each expense item: the date the expense was incurred, the category, 
      the textual description, amount spent, unit of currency, whether there is a photographic 
      receipt, and incompleteness indicator.
	 */
	public void testOrderedExpenseList() throws ParseException {	
		// sample test data 1
		String expenseName1 = "BMW";
		Item expenseTest1 = new Item(expenseName1);
		expenseTest1.setDate("2015-01-05");
		expenseTest1.setCategory("vehicle rental");
		expenseTest1.setAmount(3000);
		expenseTest1.setCurrency("CNY");
		expenseTest1.setDescription("travel purpose");
		
		// sample test data 2
		String expenseName2 = "bus ticket";
		Item expenseTest2 = new Item(expenseName2);
		expenseTest2.setDate("2015-01-01");
		expenseTest2.setCategory("ground transport");
		expenseTest2.setAmount(2.99);
		expenseTest2.setCurrency("CAD");
		expenseTest2.setDescription("one day bus trip");
		
		// sample test data 3
		String expenseName3 = "fairmont hotel";
		Item expenseTest3 = new Item(expenseName3);
		expenseTest3.setDate("2015-01-02");
		expenseTest3.setCategory("accommodation");
		expenseTest3.setAmount(160);
		expenseTest3.setCurrency("USD");
		expenseTest3.setDescription("Fairmont");
		
		// add all three expense to the claim
		Claim claim1 = new Claim("NEW");
		claim1.addItem(expenseTest1);	
		claim1.addItem(expenseTest2);	
		claim1.addItem(expenseTest3);	
		
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
		assertEquals("expense Test 1 date:",localDate1,claim1.getItemDate(expenseTest1));
		assertEquals("expense Test 1 category","vehicle rental",claim1.getItemCategory(expenseTest1));
		assertEquals("expense Test 1 amount",3000,claim1.getItemAmount(expenseTest1));
		assertEquals("expense Test 1 currency","CNY",claim1.getItemCurrency(expenseTest1));
		assertEquals("expense Test 1 description", "travel purpose",claim1.getItemDescription(expenseTest1));
		
		assertEquals("expense Test 2 date:",localDate2,claim1.getItemDate(expenseTest2));
		assertEquals("expense Test 2 category","grount transport",claim1.getItemCategory(expenseTest2));
		assertEquals("expense Test 2 amount",2.99,claim1.getItemAmount(expenseTest2));
		assertEquals("expense Test 2 currency","CAD",claim1.getItemCurrency(expenseTest2));
		assertEquals("expense Test 2 description", "one day bus trip",claim1.getItemDescription(expenseTest2));
		
		assertEquals("expense Test 3 date:",localDate3,claim1.getItemDate(expenseTest3));
		assertEquals("expense Test 3 category","accommodation",claim1.getItemCategory(expenseTest3));
		assertEquals("expense Test 3 amount",160,claim1.getItemAmount(expenseTest3));
		assertEquals("expense Test 3 currency","USD",claim1.getItemCurrency(expenseTest3));
		assertEquals("expense Test 3 description", "Fairmont",claim1.getItemDescription(expenseTest3));
		
		System.out.println(claim1.getItemDate(expenseTest1)+claim1.getItemCategory(expenseTest1)+
				claim1.getItemAmount(expenseTest1)+claim1.getItemCurrency(expenseTest1)+
			    claim1.getItemDescription(expenseTest1));	
		
		System.out.println(claim1.getItemDate(expenseTest2)+claim1.getItemCategory(expenseTest2)+
				claim1.getItemAmount(expenseTest2)+claim1.getItemCurrency(expenseTest2)+
			    claim1.getItemDescription(expenseTest2));	
		
		System.out.println(claim1.getItemDate(expenseTest3)+claim1.getItemCategory(expenseTest3)+
				claim1.getItemAmount(expenseTest3)+claim1.getItemCurrency(expenseTest3)+
			    claim1.getItemDescription(expenseTest3));	
		
	}


}
