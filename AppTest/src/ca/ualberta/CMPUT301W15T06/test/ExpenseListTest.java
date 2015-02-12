package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;

import ca.ualberta.CMPUT301W15T06.Expense;
import ca.ualberta.CMPUT301W15T06.ExpenseList;

import java.util.Collection;

//https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3 2015-02-08


public class ExpenseListTest extends TestCase
{
	public void testExpenseList() {
		ExpenseList expenseList = new ExpenseList();
		Collection<Expense> expense = expenseList.getExpense();
		assertTrue("Empty Expense List", expense.size() == 0);
	}
	
	public void testAddExpenseList() {
		ExpenseList expenseList = new ExpenseList();
		String expenseName = "bus ticket";
		Expense testExpense = new Expense(expenseName);
		expenseList.addExpense(testExpense);
		Collection<Expense> expense = expenseList.getExpense();
		assertTrue("Expense List size", expense.size() == 1);
		assertTrue("Test expense not contained", expense.contains(testExpense));		
	}
	
	public void testDeleteExpenseList() {
		ExpenseList expenseList = new ExpenseList();
		String expenseName = "bus ticket";
		Expense testExpense = new Expense(expenseName);
		expenseList.addExpense(testExpense);
		Collection<Expense> expense = expenseList.getExpense();
		assertTrue("Expense List size", expense.size() == 1);
		assertTrue("", expense.contains(testExpense));
		expenseList.deleteExpense(testExpense);
		expense = expenseList.getExpense();
		assertTrue("Expense List size", expense.size() == 0);
		assertFalse("Test expense still contained?", expense.contains(testExpense));	
	}


}
