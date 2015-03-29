package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;

public class UserList {
	private ArrayList<String> userList;

	private boolean needSynList;

	
	public UserList(){
		userList=new ArrayList<String>();
		needSynList=false;
	}
	
	public ArrayList<String> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}

	public boolean isNeedSyn() {
		boolean result=false;
		for (String name:userList){
			if(ClaimListManager.getInstance().load(name).isNeedSyn()){
				result=true;
			}
		}
		return result;
	}


	public boolean isNeedSynList() {
		return needSynList;
	}

	public void setNeedSynList(boolean needSynList) {
		this.needSynList = needSynList;
	}

	
}
