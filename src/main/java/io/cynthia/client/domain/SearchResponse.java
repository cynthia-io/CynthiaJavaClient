package io.cynthia.client.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true)
@Builder
@Value
public class SearchResponse {
    String correlationId;
    List<List<SearchResult>> data;
}
