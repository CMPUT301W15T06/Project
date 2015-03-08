package ca.ualberta.CMPUT301W15T06;

public class ClaimantAddItemController {
	
	private Claim claim=null;
	
	public ClaimantAddItemController(Claim claim){
		this.claim=claim;
	}
	
	public void addItem(String date, String category, String description, Double amount, String currency) throws StatusException{
		
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		Item item=new Item();
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
		
		item.addModelListener(new Listener() {
			
			@Override
			public void update() {
				claim.notifyListeners();				
			}
		});
		
		claim.getItemList().add(item);
		claim.notifyListeners();

	}
}
