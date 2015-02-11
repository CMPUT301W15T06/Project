package ca.ualberta.CMPUT301W15T06.test;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;

public class approval_test extends TestCase{
	
	public void testSubmittedClaimList() {

		ClaimList cList = new ClaimList();
		ArrayList<Claim> cl = cList.getClaimList();
		assertTrue("null_list should be null", cl.equals(null));
		@SuppressWarnings("deprecation")
		String d1 = "2012-03-27";
//		Date d1 = new Date (2012, 3, 27);
		Claim test = new Claim("A");
		test.setBeginDate(d1)
		cl.addSubmittedExpenseClaim(test);
		ExpenseClaim ec = SubmittedClaimList.getSubmittedClaim(0);
		assertFalse("expenseClaim should not be null", ec.equals(null));
	}
	
	@SuppressWarnings("deprecation")
	public void testSortClaim() {
		Date d1 = new Date (2012, 3, 27);
		Date d2 = new Date (2012, 3, 27);
		Date d3 = new Date (2014, 5, 12);
		Date d4 = new Date (2012, 9, 3);
		ExpenseClaim test1 = new ExpenseClaim("A", d1, "Edmonton");
		SubmittedClaimList.addSubmittedExpenseClaim(test1);
		ExpenseClaim test2 = new ExpenseClaim("B", d2, "Calgory");
		SubmittedClaimList.addSubmittedExpenseClaim(test2);
		ExpenseClaim test3 = new ExpenseClaim("C", d3, "UA");
		SubmittedClaimList.addSubmittedExpenseClaim(test3);
		ExpenseClaim test4 = new ExpenseClaim("D", d4, "Others");
		SubmittedClaimList.addSubmittedExpenseClaim(test4);
		int length = SubmittedClaimList.getExpenseClaimList().size();
		Date last_date = SubmittedClaimList.getSubmittedClaim(0).getStartDate();
		Date new_date = last_date;
		int i = 0;
		while (i < length){
			new_date = SubmittedClaimList.getSubmittedClaim(i).getStartDate();
			assertTrue("new date is larger than or equal to old date", new_date.compareTo(last_date)>=0);
			i++;
		}
	}
	
	public void testSubmittedClaim() {
		ArrayList<ExpenseClaim> null_list = SubmittedClaimList.getExpenseClaimList();
		assertTrue("null_list should be null", null_list.equals(null));
		
		@SuppressWarnings("deprecation")
		Date d1 = new Date (2012, 3, 27);
		ExpenseClaim test = new ExpenseClaim("A", d1, "Edmonton");
		SubmittedClaimList.addSubmittedExpenseClaim(test);
		ExpenseClaim ec = SubmittedClaimList.getSubmittedClaim(0);
		assertTrue("claiment name should be A", ec.getClaiment().equals("A"));
		assertTrue("starting date should be 2012.3.27", ec.getStartDate().equals(d1));
		assertTrue("Destination should be Edmonton", ec.getDestination().equals("Edmonton"));
		assertTrue("Status should be submitted", ec.getState().equals("submitted"));
		assertTrue("Total currency amount should be zero", ec.getTotalCurrency()==0);
		assertTrue("Approver name should be null", ec.getApproverName().equals(null));
		
	}
	
	public void testAddComment() {
		// add a claim 
		testSubmittedClaim();
		
		// test case
		SubmittedClaimList.getSubmittedClaim(0).addComment("New Comment");
		assertTrue("Comment should be same with 'New Comment'", SubmittedClaimList.getSubmittedClaim(0).getComment().equals("New comment"));
	}
	
	
}
