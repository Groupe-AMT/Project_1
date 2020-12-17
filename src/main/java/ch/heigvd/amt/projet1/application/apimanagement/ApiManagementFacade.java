package ch.heigvd.amt.projet1.application.apimanagement;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import ch.heigvd.amt.projet1.application.identitymanagement.authentificate.CurrentUserDTO;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;


public final class ApiManagementFacade { //class made to manage the gamification API
    public static Rule CreateRule(String action,String attribute,String pointscale,int amount,String name,String badge){ //crée une rule
        Rule result ;
        RuleIf _if = new RuleIf(action, attribute);
        RuleThen then = new RuleThen(pointscale, amount);
        result = new Rule(name, badge, _if, then);
        return result;
    }
    public static String objectToJsonString(Object o) throws IllegalAccessException {
        String result = "{";
        String temp_value = "";
        Class c = o.getClass();
        Field[] fieldlist = c.getDeclaredFields(); //get fields of the parameter
        for(int i = 0; i < fieldlist.length ; i++){
            String temp_name = fieldlist[i].getName(); //get name of the currently processed field
            Object temp_obj = fieldlist[i].get(o); //get content of the field
            String temp_type = fieldlist[i].getType().getTypeName(); //get name of the field's type
            System.out.println(temp_type);
            if(temp_type == "java.lang.String"){ //handle different types
                temp_value = (String) temp_obj;
            }
            if(temp_type == "java.lang.int"){
                int temp_int = ((int) temp_obj);
                temp_value = Integer.toString(temp_int);
            }
            if(temp_type == "java.lang.double"){
                double temp_double = (double) temp_obj;
                temp_value = Double.toString(temp_double);
            }
            if(temp_type == "java.time.OffsetDateTime"){
                OffsetDateTime temp_offsetdate = (OffsetDateTime) temp_obj;
                temp_value = temp_offsetdate.toString();
            }
            result +=  "\"" + temp_name + "\":" + "\"" + temp_value + "\"" ;
            if(i != fieldlist.length - 1){result += ",";}
        }
        result += "}";
        System.out.println(result);
        return result;
    }
    public static String stringifyEvent(Event obj){
        String result = "{";
        result += "\"" + "user_id" + "\":" + "\"" + obj.getIdUser() + "\"" + ",";
        result += "\"" + "user_ame" + "\":" + "\"" + obj.getUserName() + "\"" + ",";
        result += "\"" + "action" + "\":" + "\"" + obj.getAction() + "\"" + ",";
        result += "\"" + "attribute" + "\":" + "\"" + obj.getAttribute() + "\"" + ",";
        result += "\"" + "timestamp" + "\":" + "\"" + obj.getTimestamp().toString() + "\"";
        result += "}";
        return result;
    }
    public static String stringifyPointScale(PointScale obj){
        String result = "{";
        result += "\"" + "name" + "\":" + "\"" + obj.getName() + "\"" + ",";
        result += "\"" + "scale" + "\":" + "\"" + obj.getScale() + "\"" + ",";
        result += "}";
        return result;
    }
    public static String stringifiedObject(Object o){ //gère les conversions objet-String
        String result = "";
        if(o instanceof Event) {
            Event obj = (Event) o;
            result = stringifyEvent(obj);
        }
        if(o instanceof PointScale) {
            PointScale obj = (PointScale) o;
            result = stringifyPointScale(obj);
        }
        return result;
    }
    public static String HttpPost(String address, String payload) throws IOException {
        String result = "";
        URL url = new URL(address);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
        byte[] out = payload.getBytes();
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        InputStreamReader in = new InputStreamReader(http.getInputStream());
        result = IOUtils.toString(in);
        in.close();
        http.disconnect();
        return result;
    }
    public static String HttpPostFromObject(String address, Event o) throws IOException, IllegalAccessException { //use this to post to API
        String result = "";
        result = HttpPost(address, objectToJsonString(o));
        return result;
    }
    public static String HttpGet(String address, String argument) throws IOException {
        String result = "";
        URL url = new URL(address);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("GET"); // PUT is another valid option
        InputStreamReader in = new InputStreamReader(http.getInputStream());
        result = IOUtils.toString(in);
        in.close();
        http.disconnect();
        return result;
    }
    public static Event CreateEvent(String user_id, String user_name,String action, String attribute){
        return new Event(user_id, user_name, action, attribute, OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)  );
    }
    public static void SendVoteEvent(HttpServletRequest req) throws IOException, IllegalAccessException {
        Boolean vote = Boolean.parseBoolean(req.getParameter("vote"));
        HttpSession session = req.getSession(true);
        String s = "";
        if (session.getAttribute("currentUser")!=null) {
            CurrentUserDTO currentUserDTO = (CurrentUserDTO) session.getAttribute("currentUser");
            s = currentUserDTO.getUsername();
        }
        if(vote == true){ //Gamification API call
            Event ev = ApiManagementFacade.CreateEvent((req.getParameter("vid")), s, "vote","up" );
            System.out.println(ApiManagementFacade.HttpPostFromObject("http://172.18.0.4:8080/events",ev));
        }else{
            Event ev = ApiManagementFacade.CreateEvent((req.getParameter("vid")), s, "vote","up" );
            System.out.println(ApiManagementFacade.HttpPostFromObject("http://sym.iict.ch/rest/json",ev));
        }
    }
}
