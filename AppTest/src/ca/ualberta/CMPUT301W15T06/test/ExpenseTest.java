package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Expense;

// https://www.youtube.com/watch?v=k9ZNbsc0Qgo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=3  2015-02-08

public class ExpenseTest extends TestCase {
	public void testExpense () {
		String ExpenseName = "bus ticket";
		Expense expense = new Expense(ExpenseName);
		assertTrue("Expense Name is not equal", ExpenseName.equals(expense.getExpenseName()));
	}
	

}