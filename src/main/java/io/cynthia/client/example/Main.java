package io.cynthia.client.example;

import io.cynthia.client.CynthiaClient;
import io.cynthia.client.feedback.CynthiaSearchFeedbackRequest;
import io.cynthia.client.feedback.CynthiaSearchFeedbackRequestItem;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@UtilityClass
@Slf4j
public class Main {

    public static void main(final String[] args) {
        final String correlationId = "0ca39d69-72f0-420e-9fc8-a6f335d8ee68";
        final String resultId = "d14f2b7a-18b1-4573-970f-541c23a91d9f";
        final String type = "Click";
        final float value = 1.75f;
        final CynthiaSearchFeedbackRequestItem requestItem = CynthiaSearchFeedbackRequestItem.of(correlationId, resultId, type, value);
        final CynthiaSearchFeedbackRequest request = CynthiaSearchFeedbackRequest.of(List.of(requestItem));
        final CynthiaClient client = CynthiaClient.of("my-secret-api-key");
        final boolean submitted = client.feedback(request);
        log.info("Submitted: {}", submitted);
    }
}
