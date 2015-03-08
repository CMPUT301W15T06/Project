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
		
		if (claimName.equals("")||begin.equals("")||end.equals("")){
			claim.setMissValue(true);
		}else{
			claim.setMissValue(false);
		}

		claim.addModelListener(new Listener() {
			
			@Override
			public void update() {
				// TODO Auto-generated method stub
				claimList.notifyListeners();
			}
		});
		claimList.getClaimList().add(claim);
		claimList.notifyListeners();
	}

}
