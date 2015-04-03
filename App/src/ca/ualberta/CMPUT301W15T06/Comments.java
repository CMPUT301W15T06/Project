package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;

public class Comments extends AppModel{

	ArrayList<Comment> commentList;
	
	
	
	
	
	public Comments(){
		commentList=new ArrayList<Comment>();
	}
	
	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		return false;
	}


	public ArrayList<Comment> getCommentList(){
		return commentList;
	}

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
