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
public class CynthiaNLUSearchRequest {
    List<CynthiaNLUSearchQuery> data;
    CynthiaNLUSearchOptions options;

    public static CynthiaNLUSearchRequest of(@NonNull final List<CynthiaNLUSearchQuery> data,
                                             @NonNull final CynthiaNLUSearchOptions options) {
        return CynthiaNLUSearchRequest.builder().data(data).options(options).build();
    }
}
