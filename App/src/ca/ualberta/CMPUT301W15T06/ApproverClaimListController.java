package ca.ualberta.CMPUT301W15T06;

import android.util.Log;

public class ApproverClaimListController {

	private Claim claim;
	
	public ApproverClaimListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}

	public void addComment(String string) throws NetWorkException {
		// TODO Auto-generated method stub
		Comment comment=new Comment(string);
		
	
		claim.getComments().getCommentList().add(comment);
		try {
			claim.notifyListeners();
		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			claim.getComments().getCommentList().remove(comment);
			throw new NetWorkException();
		}
		
	}

	public void returnClaim() throws NetWorkException, WrongApproverException {
		// TODO Auto-generated method stub

		if(claim.getComments().isFinish()){
			addComment("The Approver didn't give comments.");
		}else if(!claim.getComments().getUnFinishedComment().getApproverName().equals(AppSingleton.getInstance().getUserName())){
			throw new WrongApproverException();
		}
		User user =AppSingleton.getInstance().getTempUser();

		user.getClaimList().remove(claim);
		ModifiableClaim modifiableClaim=new ModifiableClaim(claim,"Returned");
		user.getClaimList().add(modifiableClaim);
		modifiableClaim.getComments().getUnFinishedComment().setFinish(true);
		try {			
			claim.notifyListeners();
			AppSingleton.getInstance().getNeedApproveList().remove(claim);
			claim=modifiableClaim;
			AppSingleton.getInstance().setCurrentClaim(modifiableClaim);

		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			user.getClaimList().add(claim);
			user.getClaimList().remove(modifiableClaim);
			throw new NetWorkException();
		}
		
	
	}

	public void approveClaim() throws NetWorkException, WrongApproverException {
		// TODO Auto-generated method stub

		if(claim.getComments().isFinish()){
			addComment("The Approver didn't give comments.");
		}else if(!claim.getComments().getUnFinishedComment().getApproverName().equals(AppSingleton.getInstance().getUserName())){
			throw new WrongApproverException();
		}
		
	
		
		try {
			claim.getComments().getUnFinishedComment().setFinish(true);
			claim.setStatus("Approved");
			AppSingleton.getInstance().getNeedApproveList().remove(claim);
		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			claim.getComments().getUnFinishedComment().setFinish(false);
			claim.setStatusSimple("Submitted");
			throw new NetWorkException();
		}
		
	}

}
