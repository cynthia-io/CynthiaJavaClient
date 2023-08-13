package io.cynthia.client.search;

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
public class CynthiaSearchResponse {
    String correlationId;
    List<List<CynthiaSearchResponseItem>> data;

    public static CynthiaSearchResponse of(@NonNull final String correlationId,
                                           @NonNull final List<List<CynthiaSearchResponseItem>> data) {
        return CynthiaSearchResponse.builder().correlationId(correlationId).data(data).build();
    }
}
