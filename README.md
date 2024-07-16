# Cynthia Java Client

The Cynthia Java Client is a lightweight and easy-to-use Java API that simplifies the process of connecting to the Cynthia AI model for search requests in your Java applications.

## Getting Started

To get started with the Cynthia Java Client, clone this repository and install the artifact:

```bash
git clone https://github.com/cynthiasystems/JavaClient.git
cd JavaClient
mvn clean install
```
Now, add the following Maven dependency to your project's `pom.xml` file:
```xml
<dependency>
    <groupId>io.cynthia.client</groupId>
    <artifactId>JavaClient</artifactId>
    <version>1.0.0</version>
</dependency>
```
Cynthia uses Lombok annotations, and we use a standard lombok.config file:
```properties
lombok.addLombokGeneratedAnnotation = true
lombok.anyConstructor.addConstructorProperties = true
```
### Example: Natural Language Search
Here's an example of how to connect to Cynthia in a Java application. In this example,
we search in the [CrystalVaults](https://www.crystalvaults.com/) catalog for a `Red slab` using 
a model called the `miraculous-moonstone-1.0`.

```java
package io.cynthia.client.example;

import io.cynthia.client.CynthiaClient;
import io.cynthia.client.domain.SearchOptions;
import io.cynthia.client.domain.SearchQuery;
import io.cynthia.client.domain.SearchRequest;
import io.cynthia.client.domain.SearchResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.cynthia.client.utils.JsonUtils.toJson;

@Slf4j
public class Main {

    public static void main(final String[] args) {
        final String apiKey = "your-api-key-here";
        final boolean autoLimit = true;
        final int top = 3;
        final CynthiaClient client = CynthiaClient.builder()
                .cynthiaHost("https://demo-api.cynthia.io")
                .timeout(5000)
                .build();
        final SearchResponse response = client.getSearchResults(
                apiKey,                       // Your unique Cynthia API key
                "v1.0",                       // API version
                "CrystalVaults",              // Data source or catalog name
                "miraculous-moonstone-1.0",   // Specific model version for this data source
                SearchRequest.builder()
                        .data(List.of(SearchQuery.builder().query("Red slab").build()))
                        .options(SearchOptions.builder().autoLimit(autoLimit).top(top).build())
                        .build());
        log.info(toJson(response));
    }
}
```
Replace `your-api-key-here` with your actual Cynthia API key, and you're ready to make search requests using the Cynthia Java Client in your Java applications.

The parameter `autoLimit = true` means that Cynthia may truncate the results to increase relevancy and precision.

The parameter `top = 3` controls the maximum size of the retrieval, such as the top-3 results.

### Testing

The project includes unit tests using JUnit 5 and MockWebServer. To run the tests, use:

```bash
mvn clean test
```

## License
This project is licensed under the MIT License.

## Contributing
We welcome contributions from the community. Please read our Contributing Guidelines to learn more about how you can get involved.

## About Us
Cynthia Systems is a corporation within Companies House of the United Kingdom. The name Cynthia is an anthropomorphized contraction
of our trademark, the `Synthetic Intelligent Agent™`. Please visit [our website](https://cynthiasystems.com/) to
learn more about us and what we do.