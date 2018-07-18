package ru.utair.kubsu.hellojava.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping(path = "/rest/test", produces = "application/json")
public class UIController {

    public UIController() {

    }

    @GetMapping
    public String getHelloWorldString() {

        String url = "http://umorili.herokuapp.com/api/random?num=1";
        StringBuffer response = null;

        try
        {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(response != null) {
            return response.toString();
        } else {
            return "null";
        }
        //System.out.println(response.toString());
    }
}
