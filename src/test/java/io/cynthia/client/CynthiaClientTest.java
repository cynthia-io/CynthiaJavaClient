package io.cynthia.client;

import io.cynthia.client.domain.SearchOptions;
import io.cynthia.client.domain.SearchQuery;
import io.cynthia.client.domain.SearchRequest;
import io.cynthia.client.domain.SearchResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static io.cynthia.client.utils.ResourceUtils.readResourceFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@FieldDefaults(level = AccessLevel.PRIVATE)
class CynthiaClientTest {
    MockWebServer mockWebServer;
    CynthiaClient cynthiaClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = mockWebServer.url("/").toString();
        cynthiaClient = CynthiaClient.builder().cynthiaHost(baseUrl).timeout(5000).build();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetSearchResults() throws IOException {
        // Read mock response from file
        final String mockResponseBody = readResourceFile("/test_response.json");

        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(mockResponseBody).addHeader("Content-Type", "application/json"));

        // Prepare request
        final SearchQuery query = SearchQuery.builder().query("Red slab").build();
        final SearchOptions options = SearchOptions.builder().autoLimit(true).top(3).build();
        final SearchRequest request = SearchRequest.builder().data(Collections.singletonList(query)).options(options).build();

        // Execute request
        final SearchResponse response = cynthiaClient.getSearchResults("test-api-key", "v1.0", "CrystalVaults", "miraculous-moonstone-1.0", request);

        // Verify response
        assertNotNull(response);
        assertEquals("d168c8be-4eac-45e5-af64-5672f91cd35a", response.correlationId());
        assertEquals(1, response.data().size());
        assertEquals(3, response.data().get(0).size());

        // Verify first result
        assertEquals("e2700f0b-532b-4149-8856-aa03b0c12c78", response.data().get(0).get(0).resultId());
        assertEquals(0.11531034111976624, response.data().get(0).get(0).score());
        assertEquals("Polished Rhodochrosite Slab", response.data().get(0).get(0).properties().get("title"));
        assertEquals("$23.95", response.data().get(0).get(0).properties().get("price"));
    }
}
