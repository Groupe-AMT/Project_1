package ch.heigvd.amt.projet1.application.apimanagement;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

@Getter
@Setter
public final class ApiManagementFacade { //class made to manage the gamification API
    private static String x_api_key = ""; //apiKey for the current application
    private static ArrayList<String> eventList = new ArrayList<String>(); //contains the list of event ids created by this application
    private static String projet2_url = "http://172.25.0.1:8080/";
    public static Rule CreateRule(String action,String attribute,String pointscale,int amount,String name,String badge){ //crée une rule
        Rule result ;
        RuleIf _if = new RuleIf(action, attribute);
        RuleThen then = new RuleThen(pointscale, amount);
        result = new Rule(name, badge, _if, then);
        return result;
    }
    public static Event CreateEvent(String user_id, String user_name,String action, String attribute){ //instantiates an event
        return new Event(user_id, user_name, action, attribute, OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)  );
    }
    public static String objectToJsonString(Object o) throws IllegalAccessException { //turns objects into JSON strings
        String result = "{";
        String temp_value = "";
        Class c = o.getClass();
        Field[] fieldlist = c.getDeclaredFields(); //get fields of the parameter
        for(int i = 0; i < fieldlist.length ; i++){
            String temp_name = fieldlist[i].getName(); //get name of the currently processed field
            Object temp_obj = fieldlist[i].get(o); //get content of the field
            String temp_type = fieldlist[i].getType().getTypeName(); //get name of the field's type
            switch(temp_type){ //handle different types
                case "java.lang.String" :
                    temp_value = (String) temp_obj;
                    break;
                case "java.lang.int" :
                    int temp_int = ((int) temp_obj);
                    temp_value = Integer.toString(temp_int);
                    break;
                case "java.lang.double" :
                    double temp_double = (double) temp_obj;
                    temp_value = Double.toString(temp_double);
                    break;
                case  "java.time.OffsetDateTime" :
                    OffsetDateTime temp_offsetdate = (OffsetDateTime) temp_obj;
                    temp_value = temp_offsetdate.toString();
                    break;
                default:
                    temp_value = temp_obj.toString(); //takes care of any non handled types
            }
            result +=  "\"" + temp_name + "\":" + "\"" + temp_value + "\"" ;
            if(i != fieldlist.length - 1){result += ",";}
        }
        result += "}";
        System.out.println(result);
        return result;
    }

    public static String HttpPost(String address, String payload) throws IOException, IllegalAccessException {
        String result = "";
        URL url = new URL(address);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        byte[] out = payload.getBytes();
        http.setRequestProperty("XApiKey", x_api_key);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        InputStreamReader in = new InputStreamReader(http.getInputStream());
        result = IOUtils.toString(in);
        in.close();
        http.disconnect();
        return result;
    }
    public static String HttpPostFromObject(String address, Object o) throws IOException, IllegalAccessException { //use this to post to API
        String result = "";
        result = HttpPost(address, objectToJsonString(o));
        return result;
    }
    public static String HttpGet(String address, String argument) throws IOException, IllegalAccessException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpUriRequest request = new HttpGet(address + argument);
        request.addHeader("XApiKey", x_api_key);
        HttpResponse response = client.execute(request);
        String result = "{\"failure\" : \"true\"}";
        result = EntityUtils.toString(response.getEntity());
        return result;
    }
    public static JSONObject ConvertToJSON(String json){ //for processing GET responses
        JSONObject result;
        System.out.println(json);
        result = new JSONObject(json);
        return result;
    }
    public static String[] JSONTOStringArray(JSONObject json) {//for processing GET responses
        JSONArray names = json.names();
        String[] result = new String[names.length()];
        for(int i = 0; i < names.length(); i++){
            String property_name = names.getString(i);
            result[i] = json.get(property_name).toString();
        }
        return result;
    }
    public static String[] GetStrings(String address, String argument) throws IOException, IllegalAccessException { //query l'endpoint et convertit en array de string la réponse
        return JSONTOStringArray(ConvertToJSON(HttpGet(address,argument)));
    }
    public static String GetAllUserEventDigest(String username) throws IOException, IllegalAccessException {
        String digest = "";
        for (Iterator<String> i = eventList.iterator(); i.hasNext();
             ) {
            String item = i.next();
            String[] event = GetStrings(projet2_url + "events/",item);
            if(username.equals(event[1])){
                digest += "type : "+event[2] + " value : " +event[3] +"\n";
            }
        }
        return digest;
    }

    /**
     * Returns a list of event values for a specific user and type of event
     */
    public static String[] GetUserEventDigest(String username, String type) throws IOException, IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        for (Iterator<String> i = eventList.iterator(); i.hasNext();
        ) {
            String item = i.next();
            String[] event = GetStrings("http://172.25.0.1:8080/events/",item);
            if(username.equals(event[1])){
                if(type.equals(event[2])){
                    list.add(event[3]);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }
    public static void SendVoteEvent(HttpServletRequest req) throws IOException, IllegalAccessException {
        if(x_api_key == ""){x_api_key = RegisterApplication();}
        Boolean vote = Boolean.parseBoolean(req.getParameter("vote"));
        HttpSession session = req.getSession(true);
        String s = "";
        String id = UUID.randomUUID().toString();
        eventList.add(id);
        if (session.getAttribute("currentUser")!=null) {
            CurrentUserDTO currentUserDTO = (CurrentUserDTO) session.getAttribute("currentUser");
            s = currentUserDTO.getUsername();
        }
        if(vote == true){ //Gamification API call
            Event ev = ApiManagementFacade.CreateEvent(id, s, "vote","up" );
            System.out.println(ApiManagementFacade.HttpPostFromObject(projet2_url + "events",ev));
        }else{
            Event ev = ApiManagementFacade.CreateEvent(id, s, "vote","down" );
            System.out.println(ApiManagementFacade.HttpPostFromObject(projet2_url + "events",ev));
        }
    }
    public static String RegisterApplication() throws IOException, IllegalAccessException {
        Registration registration = new Registration("Project_1", "BufferOverflow", "bufferoverflow@heig-vd.ch");
        return JSONTOStringArray(ConvertToJSON(HttpPostFromObject(projet2_url + "applications", registration)))[0]; //IP of swagger should be used to avoid error 500
    }
}
