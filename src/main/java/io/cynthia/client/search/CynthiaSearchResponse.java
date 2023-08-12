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
    List<List<CynthiaSearchResponseItem>> data;
    CynthiaSearchOptions options;

    public static CynthiaSearchResponse of(@NonNull final List<List<CynthiaSearchResponseItem>> data,
                                           @NonNull final CynthiaSearchOptions options) {
        return CynthiaSearchResponse.builder().data(data).options(options).build();
    }
}
