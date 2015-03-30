package ca.ualberta.CMPUT301W15T06;

public class ClaimantEditDestinationController {

	private Destination dest;
	
	public ClaimantEditDestinationController(Destination currentDestination) {
		// TODO Auto-generated constructor stub
		dest=currentDestination;
	}

	public void editDestination(String name,String reason) throws StatusException, NetWorkException{
		dest.setName(name);
		dest.setReason(reason);
	}


}
