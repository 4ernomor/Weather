import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//I decided to do the task through the jackson library. While I was setting up maven, I almost went crazy.
// But it's a good experience.
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

//Create the class "Weather"
public class Weather {
    public static void main(String[] args) {
        // Create HTTP request with headers/URL/GET/build
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                //It's my key for Yandex API, you must enter you key
                .headers("X-Yandex-Weather-Key", "80526107-7407-4b32-b0da-b02776e8d3ee")
                 .uri(URI.create("https://api.weather.yandex.ru/v2/forecast?lat=51.32&lon=46.00&limit=2"))
                .GET()
                .build();
        //Create the construction try/catch and if/else
        try {
            //Send the HTTP response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            //Checking answer code the request
            if (response.statusCode() == 200) {
                //The response body is retrieved
                String body = response.body();
                //Create the new object from jackson library
                ObjectMapper objectMapper = new ObjectMapper();
                //Parsing body answer
                JsonNode rootNode = objectMapper.readTree(body);
                //Extracting the forecasts node in answer body
                JsonNode forecastsNode = rootNode.path("forecasts");
                //Variables to the counting day's avg temp
                double sumTempAvg = 0;
                int count = 0;
                //Checking the "forecasts" array or not
                if (forecastsNode.isArray()) {
                    //Type conversion to ArrayNode
                    ArrayNode forecastsArray = (ArrayNode) forecastsNode;
                    //Iteration in array
                    for (JsonNode forecast : forecastsArray) {
                        //Checking parts,day, and temp_avg in array
                        JsonNode parts = forecast.path("parts").path("day");
                        //We calculate the average value when we have the corresponding parameters above
                        if (parts.has("temp_avg")) {
                            sumTempAvg += parts.path("temp_avg").asDouble();
                            count++;
                        //Error handling
                        } else {
                            System.out.println("temp_avg не найдено");
                        }
                    }
                    //Error handling
                } else {
                    System.out.println("forecasts не является массивом.");
                }
                //Formula the calculating avg
                if (count > 0) {
                    double averageTemp = sumTempAvg / count;
                    System.out.println("Средняя температура за 2 дня: " + averageTemp + "°C");
                //Error handling
                } else {
                    System.out.println("Не удалось получить данные о температуре.");
                }
                //Outputing the fact temp in the Saratov city
                int temperature = rootNode.path("fact").path("temp").asInt();
                System.out.println("Температура (фактическая) в моём любимом городе Саратов: " + temperature + "°C");
                //Outputing the all data
                System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
                //Error handling
            } else {
                System.err.println("Ошибка запроса: " + response.statusCode() + " " + response.body());
            }
            //Exception handling
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
        //Have a nice day to you, inspector