package exploreJSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class ExploreJson {
	
	public static void main(String[] args) {
		
     try {
    	 
    	 boolean append = true;
         FileHandler handler = new FileHandler("default.log", append);
  
         Logger logger = Logger.getLogger("exploreJSON.core");
         logger.addHandler(handler);
    	 
    	 
    	 URL obj = new URL("https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22");
         HttpURLConnection con = (HttpURLConnection) obj.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("Accept", "application/json");
         
         BufferedReader in = new BufferedReader(
                 new InputStreamReader(con.getInputStream()));
         String inputLine;
         StringBuffer response = new StringBuffer();
         while ((inputLine = in.readLine()) != null) {
        	
         	response.append(inputLine);
         }
         in.close();
         //print in String
        // System.out.println(response.toString());
         //Read JSON response and print
         JSONObject myResponse = new JSONObject(response.toString());
         JSONArray jArray =  (JSONArray) myResponse.get("list");
        int count=0;
         try {
    	         for (int i=0, l=jArray.length(); i<l; i++){
    	        	 JSONObject m1 =  jArray.getJSONObject(i);
    	        	 char result = (jArray.getJSONObject(i).getString("name")).charAt(0);
    	        	 if((result=='T') || (result=='t'))
    	        	 {
    	        		 count++;
    	        		
    	        	 }
    	         }
    	         System.out.println("The count is"+count); 
    	         logger.info("The number of cities with parameter name begins with the letter T are"+count);
         } catch (JSONException e) {}
         //System.out.println("map------ "+jArray.toString());
         
       
        } catch (Exception e) {
         e.printStackTrace();
       }
     }
}
	   

