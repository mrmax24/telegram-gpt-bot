package app.telegramgptbot.telegrambot.service;

import app.telegramgptbot.telegrambot.config.OpenAiConfig;
import app.telegramgptbot.telegrambot.exception.OpenAiResponseException;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGptService {
    private final RestTemplate restTemplate;
    private final OpenAiConfig openAiConfig;

    public String getChatGptResponse(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(openAiConfig.getKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = String.format("{\"model\": \"%s\", \"messages\": "
                        + "[{\"role\": \"user\", \"content\": \"%s\"}]}",
                openAiConfig.getModel(), message);

        try {
            String response = restTemplate.postForEntity(openAiConfig.getUrl(),
                    new HttpEntity<>(body, headers), String.class).getBody();
            return extractContentFromResponse(response);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error processing JSON response: "
                    + e.getStatusCode().value() + " " + e.getStatusText());
        } catch (Exception e) {
            throw new RuntimeException("Unknown error processing response", e);
        }
    }

    private String extractContentFromResponse(String response) {
        try {
            JSONObject responseObject = new JSONObject(response);
            JSONArray choices = responseObject.getJSONArray("choices");

            if (!choices.isEmpty()) {
                JSONObject firstChoice = choices.getJSONObject(0);

                if (firstChoice.has("message") && firstChoice
                        .getJSONObject("message").has("content")) {
                    return firstChoice.getJSONObject("message").getString("content");
                }
            }
            throw new OpenAiResponseException("Invalid JSON response structure: missing adminResponse");
        } catch (JSONException jsonException) {
            throw new OpenAiResponseException("Error processing JSON response" + jsonException);
        } catch (Exception e) {
            throw new OpenAiResponseException("Unknown error processing response" + e);
        }
    }
}
