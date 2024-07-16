package io.cynthia.client.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Map;

@Accessors(fluent = true)
@Builder
@Value
public class SearchResult {
    String resultId;
    Double score;
    Map<String, Object> properties;
}
