package io.cynthia.client.http;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.Accessors;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.cynthia.client.utils.JsonUtils.toJson;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

@Accessors(fluent = true)
@Builder(access = AccessLevel.PRIVATE)
@Value
public class CynthiaHttpDriver {

    public static CynthiaHttpDriver of() {
        return CynthiaHttpDriver.builder().build();
    }

    private void setHeaders(@NonNull final Request request,
                            @NonNull final Map<String, String> headers) {
        for (final String key : headers.keySet()) {
            final String value = headers.get(key);
            request.setHeader(key, value);
        }
    }

    @SneakyThrows
    public String getGetResponseContent(@NonNull final String url,
                                        @NonNull final Map<String, String> headers) {
        return getGetResponse(url, headers).content();
    }

    @SneakyThrows
    private CynthiaHttpResponse getCynthiaHttpResponse(@NonNull final Request request) {
        final Response response = request.execute();
        final HttpResponse httpResponse = response.returnResponse();
        final Content returnContent = response.returnContent();
        final int status = httpResponse.getCode();
        final String content = returnContent.asString(StandardCharsets.UTF_8);
        return CynthiaHttpResponse.builder().status(status).content(content).build();
    }

    @SneakyThrows
    public CynthiaHttpResponse getGetResponse(@NonNull final String url,
                                              @NonNull final Map<String, String> headers) {
        final Request request = Request.get(url);
        setHeaders(request, headers);
        return getCynthiaHttpResponse(request);
    }

    @SneakyThrows
    public CynthiaHttpResponse getPostResponse(@NonNull final String url,
                                               @NonNull final Map<String, String> headers,
                                               @NonNull final String body,
                                               @NonNull final ContentType type) {
        final Request request = Request.post(url).bodyString(body, type);
        setHeaders(request, headers);
        return getCynthiaHttpResponse(request);
    }

    @SneakyThrows
    public CynthiaHttpResponse getPostResponse(@NonNull final String url,
                                               @NonNull final Map<String, String> headers,
                                               @NonNull final Object body) {
        return getPostResponse(url, headers, toJson(body), APPLICATION_JSON);
    }

    @SneakyThrows
    public CynthiaHttpResponse getPostResponse(@NonNull final String url,
                                               @NonNull final Map<String, String> headers,
                                               @NonNull final String body) {
        return getPostResponse(url, headers, body, APPLICATION_JSON);
    }

    @SneakyThrows
    public String getPostResponseContent(@NonNull final String url,
                                         @NonNull final Map<String, String> headers,
                                         @NonNull final String body) {
        return getPostResponse(url, headers, body).content();
    }
}
