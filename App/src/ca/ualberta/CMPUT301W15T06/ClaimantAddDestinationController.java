package ca.ualberta.CMPUT301W15T06;

public class ClaimantAddDestinationController {
	private Claim claim=null;
			
	public ClaimantAddDestinationController(Claim claim){
		this.claim=claim;
	}
	

	public void addDestination(String dest, String reason) {
		Destination destination=new Destination(dest);
		destination.setReason(reason);
		claim.addDestination(destination);	
	}
}
