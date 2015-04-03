package ca.ualberta.CMPUT301W15T06;

public class ModifiableDestination extends Destination {
	

	public ModifiableDestination(Destination dest){
		super(dest);
	}

	public ModifiableDestination(){
		super();
	}
	public void setName(String name) throws NetWorkException {
		this.name=name;
		notifyListeners();
	}


	public void setReason(String reason) throws NetWorkException {
		this.reason=reason;
		notifyListeners();
	}

}
