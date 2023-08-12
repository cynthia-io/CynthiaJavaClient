package io.cynthia.client.search;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Map;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaSearchResponseItem {
    @NonNull String resultId;
    double score;
    @NonNull Map<String, Object> properties;

    public static CynthiaSearchResponseItem of(@NonNull final String resultId,
                                               final double score,
                                               @NonNull final Map<String, Object> properties) {
        return CynthiaSearchResponseItem.builder()
                .resultId(resultId)
                .score(score)
                .properties(properties)
                .build();
    }
}
