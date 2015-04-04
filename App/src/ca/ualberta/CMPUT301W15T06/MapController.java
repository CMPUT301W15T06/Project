package ca.ualberta.CMPUT301W15T06;

import android.location.Location;

public interface MapController {

	public void setLocation(Location location) throws NetWorkException, StatusException;
}
