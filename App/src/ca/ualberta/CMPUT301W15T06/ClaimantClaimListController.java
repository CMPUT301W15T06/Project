package ca.ualberta.CMPUT301W15T06;

import java.io.IOException;

import com.google.gson.JsonElement;




public class ClaimantClaimListController {
	
	private ClaimList claimList=null;
	private Claim claim=null;
	
	public ClaimantClaimListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}

	public ClaimantClaimListController(ClaimList claimList) {
		// TODO Auto-generated constructor stub
		this.claimList=claimList;
	}

	public void submit() throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		claim.setStatus("Submitted");
	}

	public void delete(Claim claim) throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		claimList.getClaimList().remove(claim);
		claimList.notifyListeners();
		
	}
	

}
