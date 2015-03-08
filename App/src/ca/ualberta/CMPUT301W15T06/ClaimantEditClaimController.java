package ca.ualberta.CMPUT301W15T06;

public class ClaimantEditClaimController {

	private Claim claim=null;
	
	public ClaimantEditClaimController(Claim currentClaim) {
		// TODO Auto-generated constructor stub
		claim=currentClaim;
	}

	public void editClaim(String name, String begin, String end) throws StatusException {
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		claim.setName(name);
		claim.setBeginDate(begin);
		claim.setEndDate(end);
		
		if (name.equals("")||begin.equals("")||end.equals("")){
			claim.setMissValue(true);
		}else{
			claim.setMissValue(false);
		}
		
	}
	
	

}
