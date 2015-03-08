package ca.ualberta.CMPUT301W15T06;


public class ClaimantEditItemController {
	
	private Item item;

	public ClaimantEditItemController(Item item){
		this.item=item;
	}
	

	public void editItem(String date, String category, String description, Double amount, String currency) throws StatusException{	
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		item.setDate(date);
		item.setCategory(category);
		item.setDescription(description);
		item.setAmount(amount);
		item.setCurrency(currency);
		
		if (date.equals("")||description.equals("")||amount==null){
			item.setMissValue(true);
		}else{
			item.setMissValue(false);
		}


	}
}
