package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
public class TemperatureController {

    @GetMapping("/api/temperature")
    public String predictionTemp(@RequestParam String country) {
        String predictionJson = "";
        String predictionDay = "";
        TemperatureService data = new TemperatureService();
        int nbrDays = 2;
        try {

            int i = 1;
            for ( ;i < nbrDays; i++) {
                double temperature = data.getTemperature(country);
                predictionDay += "{\"date\":\"" + DatePlus(i) + "\",\"temperature\":" + temperature + "},";
            }
            double temperature = data.getTemperature(country);
            predictionDay += "{\"date\":\"" + DatePlus(i+1) + "\",\"temperature\":" + temperature + "}";

        } catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"Pays non trouvé",e);
        }
        predictionJson = "{\"country\":\""+ country +"\",\"temperatures\":["+ predictionDay+"]}";
        return predictionJson;
    }

    private LocalDateTime DatePlus (int i ){
        Date date = new Date();
        LocalDateTime currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime currentDatePlus  = currentDate.plusDays(i);
        return currentDatePlus;
    }

}
