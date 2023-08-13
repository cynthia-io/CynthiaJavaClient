package io.cynthia.client.search;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaSearchOptions {
    private static final int DEFAULT_TOP = 30;
    private static final boolean DEFAULT_AUTO_LIMIT = true;
    boolean autoLimit;
    int top;

    public static CynthiaSearchOptions of(final boolean autoLimit, final int top) {
        return CynthiaSearchOptions.builder().autoLimit(autoLimit).top(top).build();
    }
}
