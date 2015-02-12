package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.Item;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimTest extends ActivityInstrumentationTestCase2<Claim> {

	public ClaimTest() {
		super(Claim.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	// US01.01.01 test if the system records the claimant information correctly
	public void testRecordInfo()
	{
		// add name
		String name = "Name";
		Claim claimantName = new Claim(name);
		Claim.setName(name);
		assertNotSame("The Name is not equal", name, claimantName.getName());
		// add starting date
		String beginDate = "2015-02-18";
		Claim BeginDate = new Claim(beginDate);
		Claim.setBeginDate(beginDate);
		assertEquals("The begin date is", beginDate, BeginDate.getBeginDate());
		// add ending date
		String endDate = "2015-03-08";
		Claim EndDate = new Claim(endDate);
		Claim.setEndDate(endDate);
		assertEquals("The begin date is", endDate, EndDate.getBeginDate());
	}

}
