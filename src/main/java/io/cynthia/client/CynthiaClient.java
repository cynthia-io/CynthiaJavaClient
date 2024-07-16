package io.cynthia.client;

import io.cynthia.client.domain.SearchRequest;
import io.cynthia.client.domain.SearchResponse;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static io.cynthia.client.utils.JsonUtils.toJson;
import static io.cynthia.client.utils.JsonUtils.toObject;

@Accessors(fluent = true)
@Builder
@Slf4j
@Value
public class CynthiaClient {
    String cynthiaHost;
    long timeout;

    @SneakyThrows
    public SearchResponse getSearchResults(@NonNull final String apiKey,
                                           @NonNull final String apiVersion,
                                           @NonNull final String modelName,
                                           @NonNull final String modelVersion,
                                           @NonNull final SearchRequest searchRequest) {
        final String apiURL = String.format("%s/api/%s/search/%s/%s", cynthiaHost(), apiVersion, modelName, modelVersion);

        final String requestBody = toJson(searchRequest);

        final HttpClient httpClient = HttpClient.newHttpClient();

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .timeout(Duration.ofMillis(timeout()))
                .header("cynthia-api-key", apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            log.error("Error response from server: {} {}", response.statusCode(), response.body());
            throw new RuntimeException("Failed to get search results: HTTP " + response.statusCode());
        }

        return toObject(response.body(), SearchResponse.class);
    }
}