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
public class CynthiaNLUSearchResponse {
    List<List<CynthiaNLUSearchResponseItem>> data;
    CynthiaNLUSearchOptions options;

    public static CynthiaNLUSearchResponse of(@NonNull final List<List<CynthiaNLUSearchResponseItem>> data,
                                              @NonNull final CynthiaNLUSearchOptions options) {
        return CynthiaNLUSearchResponse.builder().data(data).options(options).build();
    }
}
