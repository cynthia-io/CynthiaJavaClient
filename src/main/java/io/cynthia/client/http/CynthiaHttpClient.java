package io.cynthia.client.http;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Map;

import static io.cynthia.client.utils.JsonUtils.toJson;
import static io.cynthia.client.utils.JsonUtils.toObject;

@Accessors(fluent = true)
@Builder(access = AccessLevel.PRIVATE)
@Value
public class CynthiaHttpClient {
    CynthiaHttpDriver httpDriver;

    public static CynthiaHttpClient of() {
        return of(CynthiaHttpDriver.of());
    }

    public static CynthiaHttpClient of(@NonNull final CynthiaHttpDriver httpDriver) {
        return CynthiaHttpClient.builder().httpDriver(httpDriver).build();
    }

    public <T> T makePostRequest(@NonNull final String url,
                                 @NonNull final Map<String, String> headers,
                                 @NonNull final String body,
                                 @NonNull final TypeReference<T> typeReference) {
        return toObject(httpDriver().getPostResponseContent(url, headers, body), typeReference);
    }

    public CynthiaHttpResponse makePostRequest(@NonNull final String url,
                                               @NonNull final Map<String, String> headers,
                                               @NonNull final Object data) {
        return httpDriver().getPostResponse(url, headers, data);
    }
}
