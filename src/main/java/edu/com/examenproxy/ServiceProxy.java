package edu.com.examenproxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceProxy {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final List<String> GET_URL = new ArrayList<>();
    private static int conturrentServer = 0;

    public String conection(String path, String valueList, String value) throws IOException {
        int ready = 0;
        if (ready != 1){
            ready += 1;
            GET_URL.add("http://localhost:8080/");
            GET_URL.add("http://localhost:8081/");
        }
        URL obj = new URL(GET_URL.get(conturrentServer) + path + "?list=" + valueList + "&value=" + value);
        System.out.println(obj.toString());
        roundRobinServer();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        StringBuffer response = new StringBuffer();
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response.toString();
    }

    private static void roundRobinServer() {
        conturrentServer = (conturrentServer + 1) % GET_URL.size();
    }



}
