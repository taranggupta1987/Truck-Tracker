/**
 * 
 */
package com.dream.filler.tracker.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Kumar Rohit
 *
 */
@Slf4j
public class TrackerUtils {

	
	public static String firstLetterCapital(String message) {
		// stores each characters to a char array
		char[] charArray = message.toCharArray();
		boolean foundSpace = true;

		for(int i = 0; i < charArray.length; i++) {

			// if the array element is a letter
			if(Character.isLetter(charArray[i])) {

				// check space is present before the letter
				if(foundSpace) {

					// change the letter into uppercase
					charArray[i] = Character.toUpperCase(charArray[i]);
					foundSpace = false;
				}
			}

			else {
				// if the new character is not character
				foundSpace = true;
			}
		}

		// convert the char array to the string
		message = String.valueOf(charArray);
		return message;
	}


	public static List<JsonObject> getDataFromJsonPacket(String obj) throws JsonParseException, JsonMappingException, IOException{

		JsonParser parser = new JsonParser();
		List<JsonObject> jsonObjs= new ArrayList<JsonObject>();
		com.google.gson.JsonObject payload = parser.parse(obj).getAsJsonObject();
		JsonArray contentString = payload.get("rows").getAsJsonArray();
		for (JsonElement pa : contentString) {
			//JsonObject obj= new Json
			jsonObjs.add(pa.getAsJsonObject());

		}
		return jsonObjs;		
	}

	public static <T> T jsonStringToObject(String text, Class<T> valueType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		//mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		//mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		return mapper.readValue(text.getBytes(), valueType);
	}

	public static String ObjecttojsonString(Object valueType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		//mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		return mapper.writeValueAsString(valueType);
	}

	public static String ObjecttojsonStringWithOutRoot(Object valueType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		//mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		return mapper.writeValueAsString(valueType);
	}
	
	public static String encodeValue(String value) {
	    try {
	    	String base64encodedString = Base64.getEncoder().encodeToString(
	    			value.getBytes("utf-8"));
	    	return base64encodedString;
	        //return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
	    } catch (UnsupportedEncodingException ex) {
	        throw new RuntimeException(ex.getCause());
	    }
	}
	
	public static boolean getStatus(String jsonString) {
		JSONObject jsonObject = new JSONObject(jsonString);
		return Boolean.parseBoolean(jsonObject.optString("Status"));
	}
	
	public static String getMessage(String jsonString) {
		JSONObject jsonObject = new JSONObject(jsonString);
		return jsonObject.optString("Message");
	}
	
	public static <T> T jsonStringToObjectToken(String text, Class<T> valueType) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		//mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		//mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		return mapper.readValue(text.getBytes(StandardCharsets.UTF_8), valueType);
	}
}
