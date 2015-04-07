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

import java.util.ArrayList;

/**
 * This class gathers all the comment of the claim created by the approver to
 * a big list. It is associate with <code>Claim</code> and <code>Comment</code>. 
 * By using this class, approver view all the comments.
 * 
 * @author CMPUT301W15T06
 * @version 04/07/2015
 * @see java.util.ArrayList
 */
public class Comments extends AppModel{

	/**
	 * Set a new ArrayList commnetList to contain all the comment of Claim.
	 */
	ArrayList<Comment> commentList;

	/**
	 * General Constructor. Create a new array list.
	 */
	public Comments(){
		commentList=new ArrayList<Comment>();
	}
	
	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * This method will return the commentList for further use or display purpose.
	 * 
	 * @return the commentList with contains all the comment of the Claim
	 */
	public ArrayList<Comment> getCommentList(){
		return commentList;
	}

	/**
	 * This method will return a boolean variable isFinish to check if the action of the 
	 * commentList is finished.
	 * 
	 * @return a boolean variable isFinish which tells the program whether the action is finished ("True" or "False")
	 */
	public boolean isFinish() {
		for (Comment comment:commentList){
			if(!comment.isFinish()){
				return false;
			}
		}
		return true;
	}



	

	public Comment getUnFinishedComment() {
		// TODO Auto-generated method stub
		for (Comment comment:commentList){
			if(!comment.isFinish()){
				return comment;
			}
		}
		return null;
	}

}
