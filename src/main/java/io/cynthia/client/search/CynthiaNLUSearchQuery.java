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
public class CynthiaNLUSearchQuery {
    String query;

    public static CynthiaNLUSearchQuery of(@NonNull final String query) {
        return CynthiaNLUSearchQuery.builder().query(query).build();
    }
}
