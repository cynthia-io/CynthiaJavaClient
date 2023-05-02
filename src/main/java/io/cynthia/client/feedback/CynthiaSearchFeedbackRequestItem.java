package io.cynthia.client.feedback;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaSearchFeedbackRequestItem {
    String correlationId;
    String resultId;
    String type;
    float value;

    public static CynthiaSearchFeedbackRequestItem of(@NonNull final String correlationId,
                                                      @NonNull final String resultId,
                                                      @NonNull final String type,
                                                      final float value) {
        return CynthiaSearchFeedbackRequestItem.builder()
                .correlationId(correlationId)
                .resultId(resultId)
                .type(type)
                .value(value)
                .build();
    }
}
