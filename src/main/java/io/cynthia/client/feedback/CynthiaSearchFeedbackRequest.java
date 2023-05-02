package io.cynthia.client.feedback;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaSearchFeedbackRequest {
    List<CynthiaSearchFeedbackRequestItem> data;

    public static CynthiaSearchFeedbackRequest of(@NonNull final List<CynthiaSearchFeedbackRequestItem> data) {
        return CynthiaSearchFeedbackRequest.builder().data(data).build();
    }
}
