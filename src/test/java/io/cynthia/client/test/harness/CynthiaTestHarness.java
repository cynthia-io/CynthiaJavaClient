package io.cynthia.client.test.harness;

import io.cynthia.client.CynthiaClient;
import io.cynthia.client.feedback.CynthiaSearchFeedbackRequest;
import io.cynthia.client.http.CynthiaHttpClient;
import io.cynthia.client.http.CynthiaHttpDriver;
import io.cynthia.client.http.CynthiaHttpResponse;
import io.cynthia.client.search.CynthiaSearchRequest;
import io.cynthia.client.search.CynthiaSearchResponse;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

import static io.cynthia.client.CynthiaClient.CYNTHIA_API_KEY;
import static io.cynthia.client.CynthiaClient.FEEDBACK;
import static io.cynthia.client.CynthiaClient.SEARCH;
import static io.cynthia.client.utils.JsonUtils.toJson;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Accessors(fluent = true, chain = true)
@Data
@Slf4j
public class CynthiaTestHarness {
    CynthiaClient client;
    CynthiaTestData testData;

    @BeforeEach
    public void setupTest() {
        final CynthiaHttpDriver httpDriver = mock(CynthiaHttpDriver.class);
        final CynthiaHttpClient httpClient = CynthiaHttpClient.of(httpDriver);
        final CynthiaClient client = CynthiaClient.builder().apiKey("").baseUrl("").httpClient(httpClient).build();
        client(client);
        testData(CynthiaTestData.of());
    }

    public void mockSearchResponse(@NonNull final String modelName,
                                   @NonNull final String modelVersion,
                                   @NonNull final CynthiaSearchRequest searchRequest,
                                   @NonNull final CynthiaSearchResponse searchResponse) {
        final String apiUrl = String.format("%s/%s/%s/%s", client().baseUrl(), SEARCH, modelName, modelVersion);
        when(client().httpClient().httpDriver().getPostResponseContent(
                apiUrl,
                Map.of(CYNTHIA_API_KEY, client.apiKey()),
                toJson(searchRequest))
        ).thenReturn(toJson(searchResponse));
    }

    public void mockFeedbackResponse(@NonNull final CynthiaSearchFeedbackRequest feedbackRequest,
                                     final int status) {
        final String apiUrl = String.format("%s/%s", client().baseUrl(), FEEDBACK);
        when(client().httpClient().httpDriver().getPostResponse(
                apiUrl,
                Map.of(CYNTHIA_API_KEY, client().apiKey()),
                feedbackRequest)
        ).thenReturn(CynthiaHttpResponse.builder().content("").status(status).build());
    }
}
