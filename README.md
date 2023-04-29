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

## Example: Connecting to Cynthia using HTTP Fluent HC Client API

Here's an example of how to connect to Cynthia using the HTTP fluent HC client API in a Java application:

```java
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class CynthiaClientExample {

    private static final String CYNTHIA_API_URL = "https://api.cynthia.io/api/v1.0/search";

    public static void main(String[] args) {
        // Your input text for inference
        final String inputText = "This is an example input text for Cynthia.";

        // Your Cynthia API Key
        String apiKey = "your-cynthia-api-key";

        try {
            String response = Request.Post(CYNTHIA_API_URL)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .bodyString("{\"input_text\": \"" + inputText + "\"}", ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString();

            System.out.println("Cynthia response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

Replace `your-cynthia-api-key` with your actual Cynthia API key, and you're ready to make search requests using the Cynthia Java Client in your Java applications.

## License

This project is licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Contributing

We welcome contributions from the community. Please read our Contributing Guidelines to learn more about how you can get involved.
