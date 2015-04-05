package ca.ualberta.CMPUT301W15T06;

import android.location.Location;

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

	public void setLocation(Location location) throws NetWorkException {
		this.location = location;
		notifyListeners();
	}

}
