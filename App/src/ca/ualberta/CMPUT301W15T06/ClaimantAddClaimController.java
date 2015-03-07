package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;

public class ClaimantAddClaimController {
	private ClaimList claimList=null;
	
	public ClaimantAddClaimController(ClaimList claimList) {
		this.claimList=claimList;
	}
	
	public void addClaim(String claimName, String begin, String end){
		Claim claim=new Claim(claimName);
		claim.setBeginDate(begin);
		claim.setEndDate(end);
		claimList.getClaimList().add(claim);
		claimList.notifyListeners();
	}

}
