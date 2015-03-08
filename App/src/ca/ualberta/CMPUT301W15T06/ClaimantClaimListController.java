package ca.ualberta.CMPUT301W15T06;

import java.io.IOException;

import com.google.gson.JsonElement;




public class ClaimantClaimListController {
	
	private ClaimList claimList=null;
	private Claim claim=null;
	
	public ClaimantClaimListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}

	public ClaimantClaimListController(ClaimList claimList) {
		// TODO Auto-generated constructor stub
		this.claimList=claimList;
	}

	public void submit() throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		claim.setStatus("Submitted");
	}

	public void delete(Claim claim) throws StatusException {
		// TODO Auto-generated method stub
		if (AppSingleton.getInstance().getStatus().equals("Submitted")||AppSingleton.getInstance().getStatus().equals("Approved")){
			throw new StatusException();					
		}
		
		claimList.getClaimList().remove(claim);
		claimList.notifyListeners();
		
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




	
	






	

}
