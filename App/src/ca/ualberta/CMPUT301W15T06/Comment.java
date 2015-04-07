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

import java.util.Date;

/**
 * This class controls the comment of the claim created by the approver. 
 * It is associate with <code>Claim</code>. By using this class, approver 
 * can add comment to the Claim with the date and approver's name. 
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.Date
 */
public class Comment extends AppModel {

	/**
	 * Set a String variable approverName to record the full name of the approver.
	 * Set a String variable comment to record the comment that approver entered.
	 * Set a Date variable date to record the date that the comment is added.
	 * Set a Boolean variable finish to check if the adding action is finished.
	 */
	private String approverName;
	private String comment;
	private Date date;
	private boolean finish;
	
	/**
	 * General constructor. This will set an empty new date, get the name of the approver by calling 
	 * <code>AppSingleton</code>. Set the comment to a string, set the finish default value of false.
	 * 
	 * @param str  a String that is the comment of the Claim
	 */
	public Comment(String str){
		date=new Date();
		approverName=AppSingleton.getInstance().getUserName();
		comment=str;
		finish=false;
	}
	
	
	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * This method will return the approver's name for further use or display.
	 * 
	 * @return the full name of the approver (like "Tom Smith")
	 */
	public String getApproverName() {
		return approverName;
	}

	/**
	 * This method will return the comment of the Claim for further use or display.
	 * 
	 * @return the comment of the Claim (like "Incomplete Information")
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * This method will return the date of the comment for further use or display.
	 * 
	 * @return the date when the comment is created
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * This method will return a Boolean variable finish to check if the adding action is finished.
	 * 
	 * @return a boolean variable that indicate whether the comment is finish adding ("True" or "False")
	 */
	public boolean isFinish() {
		return finish;
	}

	/**
	 * Set the Boolean variable finish the application.
	 * 
	 * @param finish  a boolean variable that indicate whether the comment is finish adding ("True" or "False")
	 */
	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	/**
	 * Return all the information collect for comment as a string to display.
	 * 
	 * @return a string variable which combined all the information for the comment
	 */
	public String toString(){
		return "Approver: "+approverName+"\n Comment: "+comment;
	}

}
