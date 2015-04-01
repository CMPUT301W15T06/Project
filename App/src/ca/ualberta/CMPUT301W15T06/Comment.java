package ca.ualberta.CMPUT301W15T06;

import java.util.Date;

public class Comment extends AppModel {

	private String approverName;
	private String comment;
	private Date date;
	private boolean finish;
	
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



	public String getApproverName() {
		return approverName;
	}



	public String getComment() {
		return comment;
	}


	public Date getDate() {
		return date;
	}


	public boolean isFinish() {
		return finish;
	}


	public void setFinish(boolean finish) {
		this.finish = finish;
	}


}
