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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.widget.Spinner;
import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.ItemList;
import ca.ualberta.CMPUT301W15T06.R;

// https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3  2015-02-08

public class ExpenseTest extends TestCase {
	public void testExpenseName () {
		String ExpenseName = "bus ticket";
	    Item expense = new Item(ExpenseName);
		assertTrue("Expense Name is not equal", ExpenseName.equals(expense.getName()));
	}
	
	public void testExpenseDate () {
		String ExpenseName = "bus ticket";
		String ExpenseDate = "2015-01-01";
		Item expense = new Item(ExpenseName);
		expense.setDate(ExpenseDate);
		assertTrue("Date is ", ExpenseDate.equals(expense.getDate()));
	}
	
    /*
      US04.02.01
      As a claimant, I want the category for an expense item to be one of air fare, 
      ground transport, vehicle rental, private automobile, fuel, parking, registration, 
      accommodation, meal, or supplies.
     */
	public void testCategory () {
		// the category list that claimant want
		ArrayList<String> categorylist = new ArrayList<String>(
				Arrays.asList("air fare","ground transport","vehicle rental","private automobile",
						"fuel","parking","registration","accommodation","meal","supplies"));
		// add expense
		ItemList expenseList_ = new ItemList();
		String expenseName = "fairmont hotel";
		Item testExpenseCate = new Item(expenseName);
		expenseList_.addItem(testExpenseCate);
		testExpenseCate.setDate("2015-01-08");
		testExpenseCate.setCategory("accomodation");
		testExpenseCate.setAmount(188);
		testExpenseCate.setCurrency("CAD");
		testExpenseCate.setDescription("banff trip");
		// true if category is in the list
		assertTrue("category is in the list",categorylist.contains(testExpenseCate.getCategory()));
		// false if category not in the list
		testExpenseCate.setCategory("eat");
		assertFalse("category not in the list",categorylist.contains(testExpenseCate.getCategory()));
		
	}
	
	public void testAmount () {
		double ExpenseAmount = 3.00;
		String ExpenseName = "bus ticket";
		Item expense = new Item(ExpenseName);
		expense.setAmount(ExpenseAmount);
		assertTrue("Amount",ExpenseAmount == expense.getAmount());		
	}
	
	/*
	  US04.03.01
      As a claimant, I want the currency for an expense amount to be one of CAD, 
      USD, EUR, GBP, CHF, JPY, or CNY.
	 */	
	public void testCurrency() {
		// the currency list that claimant want
		ArrayList<String> currencylist = new ArrayList<String>(
				Arrays.asList("CAD","USD","EUR","GBP","CHF","JPY","CNY"));
		// add expense
		ItemList expenseList_ = new ItemList();
		String expenseName = "car fuel";
		Item testExpenseCurr = new Item(expenseName);
		expenseList_.addItem(testExpenseCurr);
		testExpenseCurr.setDate("2015-01-03");
		testExpenseCurr.setCategory("fuel");
		testExpenseCurr.setAmount(56.88);
		testExpenseCurr.setCurrency("EUR");
		testExpenseCurr.setDescription("for businness trip");
		// true if currency in the list
		assertTrue("curreny is in the list",currencylist.contains(testExpenseCurr.getCurrency()));
		// false if currency not in the list
		testExpenseCurr.setCategory("AUD");
		assertFalse("currency not in the list",currencylist.contains(testExpenseCurr.getCurrency()));
	}
	
	public void testDescription() {
		String ExpenseDes = "lalala";
		String ExpenseName = "bus ticket";
		Item expense = new Item(ExpenseName);
		expense.setDescription(ExpenseDes);
		assertTrue("Description",ExpenseDes.equals(expense.getDescription()));
	}
	
	/*
	  US04.04.01
      As a claimant, I want to manually flag an expense item with an incompleteness 
      indicator, even if all item fields have values, so that I am reminded that the 
      item needs further editing.
	 */
	public void testAddFlag() {
		// create an expense 
		ItemList expenseList_ = new ItemList();
		String expenseName = "BMW";
		Item testExpenseFlag = new Item(expenseName);
		expenseList_.addItem(testExpenseFlag);
		testExpenseFlag.setDate("2015-01-05");
		testExpenseFlag.setCategory("vehicle rental");
		testExpenseFlag.setAmount(3000);
		testExpenseFlag.setCurrency("CNY");
		testExpenseFlag.setDescription("travel purpose");
		Item expense = new Item(expenseName);
		assertTrue("no flag",testExpenseFlag.getFlag());
		testExpenseFlag.addFlag();
		assertTrue("has flag", testExpenseFlag.getFlag());		
	}
	
	public void testDeleteFlag() {
		String ExpenseName = "bus ticket";
		Item expense = new Item(ExpenseName);
		expense.deleteFlag();
	}
	
	/*
	  US04.05.01
      As a claimant, I want to view an expense item and its details.
	 */
	public void testItemDetail() {
		Item expenseInfo = new Item("BMW");
		expenseInfo.setDate("2015-01-05");
		expenseInfo.setCategory("vehicle rental");
		expenseInfo.setAmount(3000);
		expenseInfo.setCurrency("CNY");
		expenseInfo.setDescription("travel purpose");
		System.out.println(expenseInfo.getDate()+expenseInfo.getCategory()+expenseInfo.getAmount()+
				expenseInfo.getCurrency()+expenseInfo.getDescription());	
	}
	
	
	// US05.01.01
	public void testComplete() {
		String ExpenseName = "bus ticket";
		Item expenseC = new Item(ExpenseName);
		assertFalse("Expense information is not complete", expenseC.infoComplete());
		expenseC.addDate();
		expenseC.addCategory();
		expenseC.addAmount();
		expenseC.addCurrency();
		expenseC.addDescription();
		assertTrue("Expense information is complete", expenseC.infoComplete());
	}
	
	// US05.01.01
	public void testMarkIncomplete() {
		String ExpenseName = "bus ticket";
		Item expenseI = new Item(ExpenseName);
		expenseI.setIncomplete();
		assertFalse("Expense information is not complete", expenseI.infoComplete());
	}

}