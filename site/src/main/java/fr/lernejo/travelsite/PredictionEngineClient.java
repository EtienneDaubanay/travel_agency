package fr.lernejo.travelsite;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("/api/temperature")
    Call<JsonNode> getPrediction(@Query("country") String country);
}
