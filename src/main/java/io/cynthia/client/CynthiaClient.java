package io.cynthia.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.cynthia.client.feedback.CynthiaSearchFeedbackRequest;
import io.cynthia.client.http.CynthiaHttpClient;
import io.cynthia.client.http.CynthiaHttpResponse;
import io.cynthia.client.search.CynthiaSearchRequest;
import io.cynthia.client.search.CynthiaSearchResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.hc.core5.http.HttpStatus;

import java.util.Map;

import static io.cynthia.client.utils.JsonUtils.toJson;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaClient {
    public static final String DEFAULT_API_URL = "https://api.cynthia.io/api/v1.0/";
    public static final String CYNTHIA_API_KEY = "CYNTHIA-API-KEY";
    public static final String SEARCH = "search";
    public static final String FEEDBACK = "feedback";
    CynthiaHttpClient httpClient;
    String apiKey;
    String baseUrl;

    public static CynthiaClient of(@NonNull final String apiKey) {
        return CynthiaClient.builder()
                .apiKey(apiKey)
                .baseUrl(DEFAULT_API_URL)
                .httpClient(CynthiaHttpClient.of())
                .build();
    }

    public static CynthiaClient of(@NonNull final String apiKey,
                                   @NonNull final String baseUrl) {
        return CynthiaClient.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .httpClient(CynthiaHttpClient.of())
                .build();
    }

    @SneakyThrows
    public CynthiaSearchResponse search(@NonNull final String modelName,
                                        @NonNull final String modelVersion,
                                        @NonNull final CynthiaSearchRequest request) {
        final String apiUrl = String.format("%s/%s/%s/%s", baseUrl(), SEARCH, modelName, modelVersion);
        return httpClient().makePostRequest(apiUrl, Map.of(CYNTHIA_API_KEY, apiKey()), toJson(request), new TypeReference<>() {
        });
    }

    @SneakyThrows
    public boolean feedback(@NonNull final CynthiaSearchFeedbackRequest request) {
        try {
            final String apiUrl = String.format("%s/%s", baseUrl(), FEEDBACK);
            final CynthiaHttpResponse response = httpClient().makePostRequest(apiUrl, Map.of(CYNTHIA_API_KEY, apiKey()), request);
            final int code = response.status();
            if (code == HttpStatus.SC_OK) {
                return true;
            }
        } catch (final Throwable t) {
            return false;
        }
        return false;
    }
}
