package io.cynthia.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.cynthia.client.search.CynthiaNLUSearchRequest;
import io.cynthia.client.search.CynthiaNLUSearchResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

import static io.cynthia.client.utils.JsonUtils.toJson;
import static io.cynthia.client.utils.JsonUtils.toObject;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaClient {
    private static final String DEFAULT_BASE_URL = "https://api.cynthia.io/api/v1.0/";
    private static final String CYNTHIA_API_KEY = "CYNTHIA-API-KEY";
    String apiKey;
    String baseUrl;

    public static CynthiaClient of(@NonNull final String apiKey) {
        return CynthiaClient.builder().apiKey(apiKey).baseUrl(DEFAULT_BASE_URL).build();
    }

    public static CynthiaClient of(@NonNull final String apiKey,
                                   @NonNull final String baseUrl) {
        return CynthiaClient.builder().apiKey(apiKey).baseUrl(baseUrl).build();
    }

    @SneakyThrows
    public CynthiaNLUSearchResponse nluSearch(@NonNull final String modelName,
                                              @NonNull final String modelVersion,
                                              @NonNull final CynthiaNLUSearchRequest nluSearchRequest) {
        final String apiUrl = String.format("%s/%s/%s", baseUrl(), modelName, modelVersion);
        final String response = Request.post(apiUrl)
                .addHeader(CYNTHIA_API_KEY, apiKey())
                .bodyString(toJson(nluSearchRequest), ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
        return toObject(response, new TypeReference<>() {});
    }
}
