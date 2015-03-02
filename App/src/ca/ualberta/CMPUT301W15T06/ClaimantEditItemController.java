package ca.ualberta.CMPUT301W15T06;

public class ClaimantEditItemController {
	
	private Item item;

	public ClaimantEditItemController(Item item){
		this.item=item;
	}
	
	public void editItem(String date, String category, String description, double amount, String currency){	
	item.setDate(date);
	item.setCategory(category);
	item.setDescription(description);
	item.setAmount(amount);
	item.setCurrency(currency);

}
}
