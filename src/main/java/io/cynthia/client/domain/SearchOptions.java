package io.cynthia.client.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Builder
@Value
public class SearchOptions {
    boolean autoLimit;
    int top;
}
