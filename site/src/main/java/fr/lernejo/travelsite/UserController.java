package fr.lernejo.travelsite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private PredictionEngineClientService clientService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/inscription")
    public void RegisterNewUser (@RequestBody String body) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(body);
            String email = node.get("userEmail").asText();
            String name = node.get("userName").asText();
            String country = node.get("userCountry").asText();
            String weatherExpectation = node.get("weatherExpectation").asText();

            int temperature = node.get("minimumTemperatureDistance").asInt();

            User user = new User(email,name,country,weatherExpectation,temperature );
            userService.addNewUser(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/api/travels")
    public String DestinationList (@RequestParam String userName) throws IOException {
        JsonNode destination = clientService.getDestination(userService.userByName(userName));
        return destination.toString();
    }
 }
