package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimList;
import android.test.ActivityInstrumentationTestCase2;

public class ClaimListTest extends ActivityInstrumentationTestCase2<ClaimList> {

	public ClaimListTest() {
		super(ClaimList.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	//US01.04.01 test if a new claim can be successfully added
	public void testAddClaim()
	{
		String newClaim = "new claim";
		Claim claim = new Claim(newClaim);
		assertEquals("new claim is not added", newClaim.equals(claim.getClaim()));	
	}
	
	// test if the claim can be saved and push online
	public void testPushOnline(){
		String name = "Travel";
		Claim claim = new Claim(name);
		ClaimList claimList = new ClaimList();
		claimList.addClaim(claim);
		claimList.pushOnline();
		assertTrue("Push Online unsuccessfully",claim.equals(claimList.pullOnline()));
	}
	
}
