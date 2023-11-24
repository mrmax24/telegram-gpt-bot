package app.telegramgptbot.telegrambot.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ResponseProcessingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ChatGptService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Value("${openai.api.url}")
    private String openaiApiUrl;

    @Value("${openai.api.model}")
    private String openaiApiModel;

    public String getChatGptResponse(String message) {
        try {
            URL obj = new URL(openaiApiUrl);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + openaiApiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + openaiApiModel + "\", \"messages\": "
                    + "[{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return extractContentFromResponse(response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractContentFromResponse(String response) {
        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray choices = responseObject.getJSONArray("choices");

            if (choices.length() > 0) {
                JSONObject firstChoice = choices.getJSONObject(0);

                if (firstChoice.has("message") && firstChoice
                        .getJSONObject("message").has("content")) {
                    return firstChoice.getJSONObject("message").getString("content");
                }
            }
            throw new RuntimeException("Invalid JSON response structure: missing adminResponse");
        } catch (JSONException jsonException) {
            throw new RuntimeException("Error processing JSON response", jsonException);
        } catch (Exception e) {
            throw new RuntimeException("Unknown error processing response", e);
        }
    }
}
