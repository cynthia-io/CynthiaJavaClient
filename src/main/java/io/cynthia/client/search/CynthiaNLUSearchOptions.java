package io.cynthia.client.search;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaNLUSearchOptions {
    private static final int DEFAULT_TOP = 30;
    private static final boolean DEFAULT_AUTO_LIMIT = true;
    boolean autoLimit;
    int top;

    public static CynthiaNLUSearchOptions of() {
        return CynthiaNLUSearchOptions.builder().autoLimit(DEFAULT_AUTO_LIMIT).top(DEFAULT_TOP).build();
    }

    public static CynthiaNLUSearchOptions of(final int top) {
        return CynthiaNLUSearchOptions.builder().autoLimit(DEFAULT_AUTO_LIMIT).top(top).build();
    }

    public static CynthiaNLUSearchOptions of(final boolean autoLimit) {
        return CynthiaNLUSearchOptions.builder().autoLimit(autoLimit).top(DEFAULT_TOP).build();
    }

    public static CynthiaNLUSearchOptions of(final boolean autoLimit, final int top) {
        return CynthiaNLUSearchOptions.builder().autoLimit(autoLimit).top(top).build();
    }
}
