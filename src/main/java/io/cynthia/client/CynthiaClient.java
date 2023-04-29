package io.cynthia.client;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaClient {
    String apiKey;

    public static CynthiaClient of(@NonNull final String apiKey) {
        return CynthiaClient.builder().apiKey(apiKey).build();
    }


}
