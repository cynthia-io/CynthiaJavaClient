package io.cynthia.client.search;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaSearchQuery {
    String query;

    public static CynthiaSearchQuery of(@NonNull final String query) {
        return CynthiaSearchQuery.builder().query(query).build();
    }
}
