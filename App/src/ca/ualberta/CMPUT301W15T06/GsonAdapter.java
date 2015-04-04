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