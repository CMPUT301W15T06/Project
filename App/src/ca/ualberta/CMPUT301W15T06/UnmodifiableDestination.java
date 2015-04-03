package ca.ualberta.CMPUT301W15T06;

public class UnmodifiableDestination extends Destination {

	public UnmodifiableDestination(Destination dest) {
		// TODO Auto-generated constructor stub
		super(dest);
	}

	public void setName(String name) throws StatusException{
		throw new StatusException();
	}


	public void setReason(String reason) throws StatusException{
		throw new StatusException();
	}

}
