package ca.ualberta.CMPUT301W15T06.test;

import junit.framework.TestCase;
import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;

public class ClaimListTest extends TestCase {

	// US02.01.01 test the display of claim list
	public void testDisplayList()
	{
		Claim claim = new Claim("new claim");
		ClaimList claimList = new ClaimList();
		claimList.addClaim(claim);
		assertTrue("correctly dispalyed?", claimList.getClaimList()!=null);
	}

	// US02.02.01 test if the list is ordered
	public void testOrderedList()
	{
		
	}
	
}
