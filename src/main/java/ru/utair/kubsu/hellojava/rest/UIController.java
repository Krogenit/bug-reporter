package ru.utair.kubsu.hellojava.rest;

import com.google.common.cache.Cache;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.utair.kubsu.hellojava.HasLogger;
import ru.utair.kubsu.hellojava.dao.JokeDao;
import ru.utair.kubsu.hellojava.model.Joke;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class UIController implements HasLogger {

    public UIController() {

    }

    @Autowired
    private JokeDao jokeDao;

    @Autowired
    private RestTemplate rest;

    @Autowired
    private Cache<ObjectId, Joke> cache;

    @GetMapping(path = "joke")
    public ResponseEntity<Joke> getRandomJoke() {

        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        String url = "http://umorili.herokuapp.com/api/random?num=100";

        try{
            result = rest.getForObject(url, ArrayList.class);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Joke>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(result.size());

        String site = result.get(randomIndex).get("site");
        String name = result.get(randomIndex).get("name");
        String desc = result.get(randomIndex).get("desc");
        String elementPureHtml = result.get(randomIndex).get("elementPureHtml");

        /*elementPureHtml = elementPureHtml.replaceAll("<br />", "")
                .replaceAll("&lt;","")
                .replaceAll("&gt;","")
                .replaceAll("<p>","")
                .replaceAll("</p>","")
                .replaceAll("</a>","")
                .replaceAll("<b>","")
                .replaceAll("</b>","")
                .replaceAll("&quot","\"")
                .replaceAll("&laquo;","<<")
                .replaceAll("&raquo;",">>")
                .replaceAll("&nbsp;","\n");*/

        Joke joke = new Joke(site, desc, elementPureHtml);
        ObjectId id = new ObjectId();
        joke.setId(id);

        cache.put(id, joke);

        return new ResponseEntity<Joke>(joke, HttpStatus.OK);
    }

    @PostMapping(path = "add")
    public void add(@RequestBody String id) {
        ObjectId objId = new ObjectId(id);
        Joke joke = cache.getIfPresent(objId);
        jokeDao.addJoke(joke);
    }

    @GetMapping(path = "getall")
    public List<Joke> getAllJokes() {
        getLogger().info("Start getAllJokes");
        return jokeDao.getAllJokes();
    }

    @GetMapping(path = "hello")
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
