package ca.ualberta.CMPUT301W15T06.test;

import java.util.Date;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Expense;

// https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3  2015-02-08

public class ExpenseTest extends TestCase {
	public void testExpenseName () {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		assertTrue("Expense Name is not equal", ExpenseName.equals(expense.getExpenseName()));
	}
	
	public void testExpenseDate () {
		String ExpenseName = "bus ticket";
		Date ExpenseDate = new Date(2015-02-11);
		Expense.addExpenseDate();
		Expense expense = new Expense(ExpenseName);
		assertTrue("Date is ", ExpenseDate.equals(expense.getDate()));
	}
	
	public void testCategory () {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		String ExpenseCategory = "vechile fee";
		Expense.addCategory();
		assertTrue("Category", ExpenseCategory.equals(expense.getCategory()));
	}
	
	public void testAmount () {
		double ExpenseAmount = 3.00;
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		Expense.addAmount();
		assertTrue("Amount",ExpenseAmount.equals(expense.getAmount()));		
	}
	
	public void testCurrency() {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		String ExpenseCurrency = "CNY";
		Expense.addCurrency();
		assertTrue("Currency", ExpenseCurrency.equals(expense.getCurrenct()));
	}
	
	public void testDescription() {
		String ExpenseDes = "lalala";
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		Expense.addExpenseDes();
		assertTrue("Description",ExpenseDes.equals(expense.getDes()));
	}
	
	public void testAddFlag() {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		expense.addFlag();
	}
	
	public void testDeleteFlag() {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		expense.deleteFlag();
	}
	
	public void testComplete() {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		assertFalse("Expense information is not complete", expense.infoComplete());
		Expense.addExpenseDate();
		Expense.addCategory();
		Expense.addAmount();
		Expense.addCurrency();
		Expense.addExpenseDes();
		assertTrue("Expense information is complete", expense.infoComplete());
	}
	
	public void testMarkIncomplete() {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		Expense.markIncomplete();
		assertFalse("Expense information is not complete", expense.infoComplete());
	}

}
