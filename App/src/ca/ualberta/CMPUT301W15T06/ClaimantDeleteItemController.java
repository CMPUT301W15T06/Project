package ca.ualberta.CMPUT301W15T06;

public class ClaimantDeleteItemController {
	
	private Claim claim=null;
	
	public ClaimantDeleteItemController(Claim claim){
		this.claim=claim;
	}
	
	public void removeItem(Item item) throws StatusException{
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		claim.getItemList().remove(item);
		claim.notifyListeners();
	}
}
