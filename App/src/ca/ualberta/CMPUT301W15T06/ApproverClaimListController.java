package ca.ualberta.CMPUT301W15T06;

public class ApproverClaimListController {

	private Claim claim;
	
	public ApproverClaimListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}

	public void addComment(String string) throws NetWorkException {
		// TODO Auto-generated method stub
		Comment comment=new Comment(string);
		
	
		claim.getCommentList().add(comment);
		try {
			claim.notifyListeners();
		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			claim.getCommentList().remove(comment);
			throw new NetWorkException();
		}
		
	}

	public void returnClaim() throws NetWorkException, WrongApproverException {
		// TODO Auto-generated method stub
		if(!claim.getCommentList().get(claim.getCommentList().size()-1).getApproverName().equals(AppSingleton.getInstance().getUserName())){
			throw new WrongApproverException();
		}
		if(claim.getCommentList().size()==0||claim.getCommentList().get(claim.getCommentList().size()-1).isFinish()){
			addComment("The Approver didn't give comments.");
		}
		try {
			claim.getCommentList().get(claim.getCommentList().size()-1).setFinish(true);
			claim.setStatus("Returned");
		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			claim.getCommentList().get(claim.getCommentList().size()-1).setFinish(false);
			claim.setStatusSimple("Submitted");
			throw new NetWorkException();
		}
	
	}

	public void approveClaim() throws NetWorkException, WrongApproverException {
		// TODO Auto-generated method stub
		if(!claim.getCommentList().get(claim.getCommentList().size()-1).getApproverName().equals(AppSingleton.getInstance().getUserName())){
			throw new WrongApproverException();
		}
		if(claim.getCommentList().size()==0||claim.getCommentList().get(claim.getCommentList().size()-1).isFinish()){
			addComment("The Approver didn't give comments.");
		}
		try {
			claim.getCommentList().get(claim.getCommentList().size()-1).setFinish(true);
			claim.setStatus("Approved");
		} catch (NetWorkException e) {
			// TODO Auto-generated catch block
			claim.getCommentList().get(claim.getCommentList().size()-1).setFinish(false);
			claim.setStatusSimple("Submitted");
			throw new NetWorkException();
		}
		
	}

}
