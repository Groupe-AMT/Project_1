package ch.heigvd.amt.projet1.application.apimanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@Getter
@Setter
public final class ApiManagementFacade { //class made to manage the gamification API
    private static ArrayList<String> eventList = new ArrayList<>(); //contains the list of event ids created by this application
    private static String projet2_url = "http://172.25.0.1:8080/";
    private static String x_api_key = ""; //apiKey for the current application

    public static String HttpPost(String address, String payload) throws IOException {
        String result;
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
    public static String HttpGet(String address) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10 * 1000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpUriRequest request = new HttpGet(address);
        request.addHeader("XApiKey", x_api_key);
        HttpResponse response = client.execute(request);
        String result = "{\"failure\" : \"true\"}";
        result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static EntityAPIEvent CreateEvent(String user_id, String user_name, String action, String attribute){ //instantiates an event
        return new EntityAPIEvent(user_id, user_name, action, attribute, OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)  );
    }
    public static void sendEvent(EntityAPIEvent event) throws IOException {
        HttpPost(projet2_url+"events", event.jsonFormat());
    }

    public static List<EntityAPIBadge> jsonToBadges(String jsonBadges) throws JsonProcessingException {
        // A partir d'un JSON contenant un ensemble de badge, on créait un tableau d'objet EntityAPIBadge
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonBadges, mapper.getTypeFactory().constructCollectionType(List.class, EntityAPIBadge.class));
    }
    public static List<EntityAPIBadge> getBadgesForUser(String UserId) throws IOException {
        return jsonToBadges(HttpGet(projet2_url+"BadgeRewards/"+UserId));
    }

    public static List<EntityAPIPointScale> jsonToPointScales(String jsonPointScales) throws JsonProcessingException {
        // A partir d'un JSON contenant un ensemble de points cale, on créait un tableau d'objet EntityAPIBadge
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonPointScales, mapper.getTypeFactory().constructCollectionType(List.class, EntityAPIPointScale.class));
    }
    public static List<EntityAPIPointScale> getPointScalesForUser(String UserId) throws IOException {
        return jsonToPointScales(HttpGet(projet2_url+"PointScaleRewards/"+UserId));
    }

    public static List<EntityAPIResultsByUser> jsonToResultsByUser(String jsonRanking) throws JsonProcessingException {
        // A partir d'un JSON contenant un ensemble d'utilisateur, on créait un tableau d'objet EntityAPIResultsByUser
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonRanking, mapper.getTypeFactory().constructCollectionType(List.class, EntityAPIResultsByUser.class));
    }
    public static List<EntityAPIResultsByUser> getRanking() throws IOException {
        return jsonToResultsByUser(HttpGet(projet2_url+"rankings"));
    }
}
