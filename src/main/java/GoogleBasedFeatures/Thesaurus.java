package GoogleBasedFeatures;

// Not a Google-based feature!!! Wondering where to put it into???

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Thesaurus {

    private List<String> synonyms = new ArrayList<>();
    private List<String> antonyms = new ArrayList<>();

    public List<String> getSynonyms() {
        return synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void lookUp(String word) {
        synonyms.clear();
        antonyms.clear();
        word = word.replace(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dictionaryapi.com/api/v3/references/thesaurus/json/"
                        + word + "?key=5214f54f-235c-4414-b8de-0dcab17200d8"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = mapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode synsNode = null;
        JsonNode antsNode = null;
        try {
            synsNode = root.get(0).get("meta").get("syns");
            antsNode = root.get(0).get("meta").get("ants");
        } catch (NullPointerException e) {
            return;
        }


        for (JsonNode node : synsNode) {
            for (JsonNode syn : node) {
                synonyms.add(syn.asText());
            }
        }

        for (JsonNode node : antsNode) {
            for (JsonNode ant : node) {
                antonyms.add(ant.asText());
            }
        }
    }
}