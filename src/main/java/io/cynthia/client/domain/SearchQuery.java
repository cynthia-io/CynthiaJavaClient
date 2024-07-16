package io.cynthia.client.domain;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Builder
@Data
public class SearchQuery {
    String query;
}
