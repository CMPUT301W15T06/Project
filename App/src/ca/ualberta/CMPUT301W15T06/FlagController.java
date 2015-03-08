package ca.ualberta.CMPUT301W15T06;

public class FlagController {

	private Item item=null;
	
	public FlagController(Item currentItem) {
		// TODO Auto-generated constructor stub
		item=currentItem;
	}
	
	public void changeFlag() throws StatusException{
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		if (item.getFlag()){
			item.setFlag(false);
		}else{
			item.setFlag(true);
		}
	}

}
