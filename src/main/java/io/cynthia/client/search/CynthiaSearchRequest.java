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
public class CynthiaSearchRequest {
    List<CynthiaSearchQuery> data;
    CynthiaSearchOptions options;

    public static CynthiaSearchRequest of(@NonNull final List<CynthiaSearchQuery> data,
                                          @NonNull final CynthiaSearchOptions options) {
        return CynthiaSearchRequest.builder().data(data).options(options).build();
    }
}
