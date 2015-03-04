package ca.ualberta.CMPUT301W15T06;

public class ClaimantAddItemController {
	
	private Claim claim=null;
	
	public ClaimantAddItemController(Claim claim){
		this.claim=claim;
	}
	
	public void addItem(String date, String category, String description, double amount, String currency){
		Item item=new Item();
		item.setDate(date);
		item.setCategory(category);
		item.setDescription(description);
		item.setAmount(amount);
		item.setCurrency(currency);
		claim.addItem(item);

	}
}
