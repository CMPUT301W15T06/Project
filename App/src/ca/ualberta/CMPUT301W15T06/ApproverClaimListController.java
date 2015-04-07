/*
UA CMPUT 301 Project Group: CMPUT301W15T06
Copyright {2015} {Jingjiao Ni
              Tianqi Xiao
              Jiafeng Wu
              Xinyi Pan 
              Xinyi Wu
              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.
 */

package ca.ualberta.CMPUT301W15T06;

import android.util.Log;
/**
 * <p>
 * This <code>ApproverClaimListController</code> class controls the action of 
 * approver for claimant. This controller class will displays a 
 * list of <code>Claim</code>, add new comment to the <code>Claim</code>, 
 * approve or return the <code>Claim</code>. It will be used when the 
 * approver asks to view and change status to the <code>Claim</code>.
 * <p>
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see android.util.Log
 */
public class ApproverClaimListController {

	/**
	 * Set a <code>Claim</code> object claim which contains all the detail of a claim.
	 */
	private Claim claim;
	
	/**
	 * Set a <code>Claim</code> to the claim list for approver.
	 * 
	 * @param claim  a claim in the claim list waiting for approver to view
	 */
	public ApproverClaimListController(Claim claim) {
		// TODO Auto-generated constructor stub
		this.claim=claim;
	}

	/**
	 * Add a new String variable string as comment. Check network error and throws exceptions when necessary.
	 * 
	 * @param string  a String variable that approver entered as comment (like "Incomplete Claim Information")
	 * @throws NetWorkException
	 */
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

	/**
	 * This method will allow approver to reutrn a claim after finish reviewing. 
	 * Check for errors and throw exceptions when necessary. 
	 * 
	 * @throws NetWorkException
	 * @throws WrongApproverException
	 */
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

	/**
	 * This method will allow approver to approve a claim after finish reviewing. 
	 * Check for errors and throw exceptions when necessary. 
	 * 
	 * @throws NetWorkException
	 * @throws WrongApproverException
	 */
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
