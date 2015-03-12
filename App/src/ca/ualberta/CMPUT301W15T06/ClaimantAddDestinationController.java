package ca.ualberta.CMPUT301W15T06;

public class ClaimantAddDestinationController {
	private Claim claim=null;
			
	public ClaimantAddDestinationController(Claim claim){
		this.claim=claim;
	}
	

	public void addDestination(String dest, String reason) throws StatusException {
		
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		Destination destination=new Destination(dest);
		destination.setParent(claim);
		
		destination.setReason(reason);
		if (dest.equals("")||reason.equals("")){
			destination.setMissValue(true);
		}else{
			destination.setMissValue(false);
		}
		
		
		claim.getDestinationList().add(destination);
		
		claim.notifyListeners();
	}
}
