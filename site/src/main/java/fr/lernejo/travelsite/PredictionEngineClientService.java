package fr.lernejo.travelsite;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Service
public class PredictionEngineClientService {

    @Autowired
    public PredictionEngineClient client ;

    public JsonNode getDestination(User user) throws IOException {
        String[] lines = LoadCountryResources();

        ObjectMapper mapperArray = new ObjectMapper();
        ArrayNode jsonDestination = mapperArray.createArrayNode();

        Call<JsonNode> request = client.getPrediction(user.getUserCountry());
        Response<JsonNode> response = request.execute();

        float userTemperature = MeanTemperature(response.body());

        for (String line : lines) {
            Call<JsonNode> myRequest = client.getPrediction(line);
            Response<JsonNode> myResponse = myRequest.execute();
            if (Diff(user, userTemperature, MeanTemperature(myResponse.body()))) {
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode jsonNode = mapper.createObjectNode();
                jsonNode.put("country", line);
                jsonNode.put("temperature", MeanTemperature(myResponse.body()));
                jsonDestination.add(jsonNode);
            }
        }
        return  jsonDestination;
    }

    private float MeanTemperature (JsonNode node) {
        float x = node.get("temperatures").get(0).get("temperature").asInt();
        float y = node.get("temperatures").get(1).get("temperature").asInt();
        return (x+y)/2;
    }
    private String[] LoadCountryResources() throws IOException {
        InputStream listCountry = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        String content = new String(listCountry.readAllBytes(), StandardCharsets.UTF_8);
        Stream<String> line = content.lines();
        return line.toArray(String[]::new);
    }
    private Boolean Diff(User user, float userTemperature, float temperature) {
        Boolean flag = false;
        if(user.getWeatherExpectation()){
            float userMinimum = userTemperature + user.getMinimumTemperatureDistance();
            if(temperature > userMinimum ){
                flag = true;
            }
        }else {
            float userMinimum = userTemperature - user.getMinimumTemperatureDistance();
            if(temperature < userMinimum ){
                flag = true;
            }
        }
        return flag;
    }
}
