package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.Claim;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimTest extends ActivityInstrumentationTestCase2<Claim> {

	public ClaimTest() {
		super(Claim.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testName()
	{
		String name = "Name";
		Claim claimantName = new Claim(name);
		Claim.setName(name);
		assertNotSame("The Name is not equal", name, claimantName.getName());
	}
	
	public void testBeginDate()
	{
		String beginDate = "2015-02-18";
		Claim BeginDate = new Claim(beginDate);
		Claim.setBeginDate(beginDate);
		assertEquals("The begin date is", beginDate, BeginDate.getBeginDate());
	}

	public void testEndDate()
	{
		String endDate = "2015-03-08";
		Claim EndDate = new Claim(endDate);
		Claim.setEndDate(endDate);
		assertEquals("The begin date is", endDate, EndDate.getBeginDate());
	}
	

}
