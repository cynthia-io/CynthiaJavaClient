package io.cynthia.client.test;

import io.cynthia.client.feedback.CynthiaSearchFeedbackRequest;
import io.cynthia.client.feedback.CynthiaSearchFeedbackRequestItem;
import io.cynthia.client.search.CynthiaSearchRequest;
import io.cynthia.client.search.CynthiaSearchResponse;
import io.cynthia.client.test.harness.CynthiaTestHarness;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CynthiaClientTest extends CynthiaTestHarness {

    @Test
    public void testSearchRequest() {
        final String modelName = "Test";
        final String modelVersion = "test-model-1.0";
        final CynthiaSearchRequest searchRequest = testData().getTestSearchRequest();
        final CynthiaSearchResponse searchResponse = testData().getTestSearchResponse();
        mockSearchResponse(modelName, modelVersion, searchRequest, searchResponse);
        final CynthiaSearchResponse searchResponseA = client().search(modelName, modelVersion, searchRequest);
        assertEquals(searchResponse, searchResponseA);
    }

    @Test
    public void testSearchFeedback() {
        final CynthiaSearchResponse searchResponse = testData().getTestSearchResponse();
        final String correlationId = searchResponse.correlationId();
        final String resultId = searchResponse.data().get(0).get(0).resultId();
        final CynthiaSearchFeedbackRequest feedbackRequest = CynthiaSearchFeedbackRequest.of(List.of(
                CynthiaSearchFeedbackRequestItem.of(correlationId, resultId, "CLICK", 1.0f)));
        mockFeedbackResponse(feedbackRequest, 200);
        final boolean submitted = client().feedback(feedbackRequest);
        assertTrue(submitted);
    }

}
