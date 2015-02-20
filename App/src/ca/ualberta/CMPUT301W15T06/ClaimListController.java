package ca.ualberta.CMPUT301W15T06;

import java.io.IOException;

import com.google.gson.JsonElement;



public class ClaimListController {
	private static ClaimList claimList=null;

	
	//try to get the unique claimlist, if not exist,load from data
	public static ClaimList getClaimList(){
		claimList=ClaimListManager.load();
		return claimList;
	}
	

	public static void addClaim(String claimName, String begin, String end){
		Claim claim=new Claim(claimName);
		claim.setBeginDate(begin);
		claim.setEndDate(end);
		claimList.addClaim(claim);
		ClaimListManager.save();
	}


	public static ClaimList getClaimListForSave() {
		// TODO Auto-generated method stub
		return claimList;
	}



	
//	public static void changeClaim(String name,String begin, String end, String descript) throws StatusException, Exception{
//		// use to check if the input of begin date is correct format because we need to compare them
//		String [] l=begin.trim().split("-");
//		@SuppressWarnings("unused")
//		int lh=Integer.valueOf(l[0])*10000+Integer.valueOf(l[1])*100+Integer.valueOf(l[2]);
//		claimList.changeClaim(name, begin, end,descript);
//		claimList.update();
//	}
//
//	public static void deleteClaim() throws StatusException {
//		claimList.deleteClaim(claimList.getCurrentClaim());
//		claimList.update();
//	}
//
	public static void setCurrentClaim(Claim claim) {
		claimList.setCurrentClaim(claim);
	}
	
	public static Claim getCurrentClaim() {
		return claimList.getCurrentClaim();
	}

	public static void setCurrentItem(Item item) {
		claimList.getCurrentClaim().setCurrentItem(item);
	}

	public static Item getCurrentItem() {
		return getCurrentClaim().getCurrentItem();
	}
	
	
	public static void addDestination(String dest, String reason) {
		Destination destination=new Destination(dest);
		destination.setReason(reason);
		claimList.getCurrentClaim().addDestination(destination);
		ClaimListManager.save();	
	}
	


	public static void addItem(String date, String category, String description, double amount, String currency){
		Item item=new Item();
		item.setDate(date);
		item.setCategory(category);
		item.setDescription(description);
		item.setAmount(amount);
		item.setCurrency(currency);
		claimList.getCurrentClaim().addItem(item);
		ClaimListManager.save();
	}
//
//
//	public static ArrayList<Item> getItemList() {
//		return claimList.getCurrentClaim().getItemList();
//	}
//
//


	public static void removeItem(){
		claimList.getCurrentClaim().removeItem(claimList.getCurrentClaim().getCurrentItem());
		ClaimListManager.save();
	}




	public static void editItem(String date, String category, String description, double amount, String currency){	
		getCurrentItem().setDate(date);
		getCurrentItem().setCategory(category);
		getCurrentItem().setDescription(description);
		getCurrentItem().setAmount(amount);
		getCurrentItem().setCurrency(currency);
		ClaimListManager.save();
	}

	

}
