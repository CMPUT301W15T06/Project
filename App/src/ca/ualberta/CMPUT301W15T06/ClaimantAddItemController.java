package ca.ualberta.CMPUT301W15T06;

public class ClaimantAddItemController {
	
	private Claim claim=null;
	
	public ClaimantAddItemController(Claim claim){
		this.claim=claim;
	}
	
	public void addItem(String date, String category, String description, Double amount, String currency){
		Item item=new Item();
		item.setDate(date);
		item.setCategory(category);
		item.setDescription(description);
		item.setAmount(amount);
		item.setCurrency(currency);
		claim.getItemList().add(item);
		claim.notifyListeners();

	}
}
