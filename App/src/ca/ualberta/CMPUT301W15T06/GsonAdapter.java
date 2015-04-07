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

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * This is an adapter class for <code>Gson</code>, as a subclass of <code>JsonSerializer</code> 
 * and <code>JsonDeserializer</code>. This class is design to let the application use the Gson library.
 * 
 * @author CMPUT301W15T06
 * @see com.google.gson.JsonDeserializationContext
 * @see com.google.gson.JsonDeserializer
 * @see com.google.gson.JsonElement
 * @see com.google.gson.JsonObject
 * @see com.google.gson.JsonParseException
 * @see com.google.gson.JsonPrimitive
 * @see com.google.gson.JsonSerializationContext
 * @see com.google.gson.JsonSerializer
 * @param <T>
 */
public class GsonAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T>{

	private static final String CLASSNAME = "CLASSNAME";
	private static final String INSTANCE  = "INSTANCE";


	

	@Override
	public JsonElement serialize(T src, Type type,
			JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject retValue = new JsonObject();
	    String className = src.getClass().getCanonicalName();
	    retValue.addProperty(CLASSNAME, className);
	    JsonElement elem = context.serialize(src); 
	    retValue.add(INSTANCE, elem);
	    return retValue;
	}


	@Override
	public T deserialize(JsonElement json, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		JsonObject jsonObject =  json.getAsJsonObject();
	    JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
	    String className = prim.getAsString();

	    Class<?> klass = null;
	    try {
	        klass = Class.forName(className);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        throw new JsonParseException(e.getMessage());
	    }
	    return context.deserialize(jsonObject.get(INSTANCE), klass);
	}

}