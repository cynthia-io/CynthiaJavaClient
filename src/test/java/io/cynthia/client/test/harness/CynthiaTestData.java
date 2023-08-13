package io.cynthia.client.test.harness;

import io.cynthia.client.search.CynthiaSearchOptions;
import io.cynthia.client.search.CynthiaSearchQuery;
import io.cynthia.client.search.CynthiaSearchRequest;
import io.cynthia.client.search.CynthiaSearchResponse;
import io.cynthia.client.search.CynthiaSearchResponseItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Accessors(fluent = true)
@Builder(access = AccessLevel.PRIVATE)
@Value
public class CynthiaTestData {

    public static CynthiaTestData of() {
        return CynthiaTestData.builder().build();
    }

    public CynthiaSearchRequest getTestSearchRequest() {
        return CynthiaSearchRequest.of(List.of(CynthiaSearchQuery.of("test query")), CynthiaSearchOptions.of(true, 10));
    }

    public CynthiaSearchResponse getTestSearchResponse() {
        return CynthiaSearchResponse.of(UUID.randomUUID().toString(), List.of(List.of(
                CynthiaSearchResponseItem.builder()
                        .resultId(UUID.randomUUID().toString())
                        .score(0.75)
                        .properties(Map.of())
                        .build(),
                CynthiaSearchResponseItem.builder()
                        .resultId(UUID.randomUUID().toString())
                        .score(0.15)
                        .properties(Map.of())
                        .build(),
                CynthiaSearchResponseItem.builder()
                        .resultId(UUID.randomUUID().toString())
                        .score(0.10)
                        .properties(Map.of())
                        .build())));
    }
}
