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

/**
 * The <code>Destination</code> class is an sub-class of <code>AppModel</code>.
 * This class can set up a <code>Destination</code> with a name and a reason. 
 * This method will be called when users need to add a <code>Destination</code>,
 * edit a <code>Destination</code> or access to <code>Destination</code> detail.
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 */
public class Destination extends AppModel{
	
	/**
	 * Set protected string name to record destination name. 
	 */
	protected String name="";
	/**
	 * Set protected string reason to record the reason of 
	 * the destination. 
	 */
	protected String reason="";
	
	
	/**
	 * General construction. This public method sets up a 
	 * Destination object with name.
	 * 
	 * @param name  a String variable
	 */
	public Destination(){
		super();
	}
	
	public Destination(Destination dest) {
		// TODO Auto-generated constructor stub
		super(dest);
		name=dest.getName();
		reason=dest.getReason();
	}

	/**
	 * Return the string variable name. This method will be used when 
	 * other class need to use or display the name. 
	 * 
	 * @return name  a String variable
	 */
	public String getName(){		
		return name;
	}
	
	/**
	 * Return the string variable reason. This method will be used when 
	 * other class need to use or display the reason. 
	 * 
	 * @return reason  a String variable
	 */
	public String getReason(){		
		return reason;
	}
	
	/**
	 * This method will combine the name and the reason of the destination to
	 * one big String.
	 * 
	 * @return ""  a String variable
	 */
	public String toString(){
		return name+'\n'+reason;
	}

	@Override
	public boolean getMissValue() {
		// TODO Auto-generated method stub
		if (name.equals("")||reason.equals("")){
			return true;
		}
		return false;
	}

}
