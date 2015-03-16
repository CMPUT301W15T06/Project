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
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantAddItemController;
import ca.ualberta.CMPUT301W15T06.FlagController;
import ca.ualberta.CMPUT301W15T06.Item;
import ca.ualberta.CMPUT301W15T06.R;
import ca.ualberta.CMPUT301W15T06.StatusException;

// https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3  2015-02-08

public class ExpenseTest extends TestCase {
	
    /*
      US04.02.01
      As a claimant, I want the category for an expense item to be one of air fare, 
      ground transport, vehicle rental, private automobile, fuel, parking, registration, 
      accommodation, meal, or supplies.
     */
	public void testCategory() throws StatusException {
		// the category list that claimant want
		ArrayList<String> categorylist = new ArrayList<String>(
				Arrays.asList("air fare","ground transport","vehicle rental","private automobile",
						"fuel","parking","registration","accommodation","meal","supplies"));
		// add expense
		Claim b = new Claim("b");
		ClaimantAddItemController claim = new ClaimantAddItemController(b);
		
		Item testExpenseCate = new Item();
		claim.addItem("2015-01-05", "vehicle rental", "travel purpose", 3000.0, "CNY");
		// true if category is in the list
		assertTrue("category is in the list",categorylist.contains(testExpenseCate.getCategory()));
		// false if category not in the list
		testExpenseCate.setCategory("eat");
		assertFalse("category not in the list",categorylist.contains(testExpenseCate.getCategory()));
		
	}
	
	public void testAmount () {
		double ExpenseAmount = 3.00;
		String ExpenseName = "bus ticket";
		Item expense = new Item();
		expense.setAmount(ExpenseAmount);
		assertTrue("Amount",ExpenseAmount == expense.getAmount());		
	}
	
	/*
	  US04.03.01
      As a claimant, I want the currency for an expense amount to be one of CAD, 
      USD, EUR, GBP, CHF, JPY, or CNY.
	 */	
	public void testCurrency() throws StatusException{
		// the currency list that claimant want
		ArrayList<String> currencylist = new ArrayList<String>(
				Arrays.asList("CAD","USD","EUR","GBP","CHF","JPY","CNY"));
		// add expense
		Claim b = new Claim("b");
		ClaimantAddItemController claim = new ClaimantAddItemController(b);
		
		Item testExpenseCurr = new Item();
		claim.addItem("2015-01-03", "fuel", "for businness trip", 56.88, "EUR");
		
		// true if currency in the list
		assertTrue("curreny is in the list",currencylist.contains(testExpenseCurr.getCurrency()));
		// false if currency not in the list
		testExpenseCurr.setCategory("AUD");
		assertFalse("currency not in the list",currencylist.contains(testExpenseCurr.getCurrency()));
	}
	
	public void testDescription() {
		String ExpenseDes = "lalala";
		String ExpenseName = "bus ticket";
		Item expense = new Item();
		expense.setDescription(ExpenseDes);
		assertTrue("Description",ExpenseDes.equals(expense.getDescription()));
	}
	
	/*
	  US04.04.01
      As a claimant, I want to manually flag an expense item with an incompleteness 
      indicator, even if all item fields have values, so that I am reminded that the 
      item needs further editing.
	 */
	public void testAddFlag() throws StatusException {
		// create an expense 
		Claim b = new Claim("b");
		ClaimantAddItemController claim = new ClaimantAddItemController(b);		
		Item testExpenseFlag = new Item();
		claim.addItem("2015-01-05","vehicle rental","travel purpose",3000.00,"CNY");

		assertTrue("no flag",testExpenseFlag.getFlag());		
		FlagController fc = new FlagController(testExpenseFlag);		
		fc.changeFlag();
		assertTrue("has flag", testExpenseFlag.getFlag());		
	}
	
	
	/*
	  US04.05.01
      As a claimant, I want to view an expense item and its details.
	 */
	public void testItemDetail() throws StatusException{
		Item expenseInfo = new Item();
		Claim b = new Claim("b");
		ClaimantAddItemController claim = new ClaimantAddItemController(b);		
		claim.addItem("2015-01-05","vehicle rental","travel purpose",3000.00,"CNY");

		System.out.println(expenseInfo.getDate()+expenseInfo.getCategory()+expenseInfo.getAmount()+
				expenseInfo.getCurrency()+expenseInfo.getDescription());	
	}
	
	
	// US05.01.01
	public void testComplete() {
		Item expenseC = new Item();
		assertFalse("Expense information is not complete", expenseC.infoComplete());
		expenseC.getDate();
		expenseC.getCategory();
		expenseC.getAmount();
		expenseC.getCurrency();
		expenseC.getDescription();
		assertTrue("Expense information is complete", expenseC.infoComplete());
	}
	
	// US05.01.01
	public void testMarkIncomplete() {
		Item expenseI = new Item();
		expenseI.setIncomplete();
		assertFalse("Expense information is not complete", expenseI.infoComplete());
	}

}