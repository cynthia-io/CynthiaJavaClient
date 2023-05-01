# Cynthia Java Client

Cynthia Java Client is a lightweight and easy-to-use Java API that simplifies the process of connecting to the Cynthia AI model for inference requests in your Java applications.

## Getting Started

To get started with the Cynthia Java Client, add the following Maven dependency to your project's `pom.xml` file:

```xml
<dependency>
  <groupId>io.cynthia</groupId>
  <artifactId>java-client</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Example: Searching with Cynthia using the fluent API.

Here's an example of how to connect to Cynthia using the HTTP fluent HC client API in a Java application:

```java
package io.cynthia.client.example;

import io.cynthia.client.CynthiaClient;
import io.cynthia.client.search.CynthiaNLUSearchOptions;
import io.cynthia.client.search.CynthiaNLUSearchQuery;
import io.cynthia.client.search.CynthiaNLUSearchRequest;
import io.cynthia.client.search.CynthiaNLUSearchResponse;
import io.cynthia.client.utils.JsonUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@UtilityClass
public class Main {

    public static void main(String[] args) {
        final String apiKey = "978ce5f9-5afa-41f4-8653-b1fb74fc7918";
        final boolean autoLimit = true;
        final int top = 10;
        final CynthiaClient client = CynthiaClient.of(apiKey);
        final CynthiaNLUSearchResponse response = client.nluSearch("Lacoste", "crafty-crocodile-1.1",
                CynthiaNLUSearchRequest.of(
                        List.of(CynthiaNLUSearchQuery.of("warm men's jacket")),
                        CynthiaNLUSearchOptions.of(autoLimit, top)));
        log.info(JsonUtils.toJson(response));
    }
}
```

Replace `apiKey` with your actual Cynthia API key, and you're ready to make search requests using the Cynthia Java Client in your Java applications.

The parameter `autoLimit = true` means that Cynthia may truncate the results to increase relevancy and precision.

The parameter `top = 10` controls the maximum size of the retrieval, such as the top-10 results.

## License

This project is licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Contributing

We welcome contributions from the community. Please read our Contributing Guidelines to learn more about how you can get involved.
