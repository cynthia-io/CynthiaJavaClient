package io.cynthia.client.http;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Builder
@EqualsAndHashCode
@Value
public class CynthiaHttpResponse {
    String content;
    int status;
}
