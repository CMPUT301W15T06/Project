package ca.ualberta.CMPUT301W15T06;

public class AppSingleton {
	private static AppSingleton appsingleton;
	private ClaimList claimList;
	private Claim currentClaim;
	private Item currentItem;
	
    private AppSingleton() {  
    	claimList=ClaimListManager.getInstance().load();
    }  
    
    public static AppSingleton getInstance() {  
        if (appsingleton == null) {  
        	appsingleton = new AppSingleton();  
        }  
        return appsingleton;  
    }
    

  	public ClaimList getClaimList(){
  		return claimList;
  	}

	public void setCurrentClaim(Claim claim) {
		currentClaim=claim;
	}

	public Claim getCurrentClaim() {
		// TODO Auto-generated method stub
		return currentClaim;
	}
	
	public void setCurrentItem(Item item) {
		currentItem=item;
	}
	
	public Item getCurrentItem() {
		return currentItem;	
	}
	
    
}
