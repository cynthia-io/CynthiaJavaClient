package io.cynthia.client.domain;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true)
@Builder
@Value
public class SearchRequest {
    List<SearchQuery> data;
    SearchOptions options;
}
