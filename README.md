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
## Example Maven Project

Here's an example of a Maven `pom.xml` file that imports the Cynthia Java Client:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>io.cynthia</groupId>
    <artifactId>java-client-example</artifactId>
    <version>1.0.0</version>
    <properties>
        <java.version>17</java.version>
        <junit.version>5.9.2</junit.version>
        <lombok.version>1.18.26</lombok.version>
        <maven.compiler.plugin.version>3.8.1</maven.compiler.plugin.version>
        <maven.resources.plugin.version>3.2.0</maven.resources.plugin.version>
        <maven.shade.plugin.version>3.2.4</maven.shade.plugin.version>
        <maven.surefire.version>2.19.1</maven.surefire.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>
    <dependencies>
        <dependency>
            <groupId>io.cynthia</groupId>
            <artifactId>java-client</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>
    </dependencies>
    <build>
        <finalName>Cynthia-Java-Client-Example-${project.version}</finalName>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>false</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.plugin.version}</version>
                <configuration>
                    <release>${java.version}</release>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven.surefire.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>org.junit.platform</groupId>
                        <artifactId>junit-platform-surefire-provider</artifactId>
                        <version>1.0.2</version>
                    </dependency>
                </dependencies>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>${maven.resources.plugin.version}</version>
                <configuration>
                    <nonFilteredFileExtensions>
                        <nonFilteredFileExtension>gz</nonFilteredFileExtension>
                    </nonFilteredFileExtensions>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>${maven.shade.plugin.version}</version>
                <configuration>
                    <createDependencyReducedPom>true</createDependencyReducedPom>
                    <filters>
                        <filter>
                            <artifact>*:*</artifact>
                            <excludes>
                                <exclude>META-INF/*.DSA</exclude>
                                <exclude>META-INF/*.RSA</exclude>
                                <exclude>META-INF/*.SF</exclude>
                                <exclude>META-INF/LICENSE*</exclude>
                                <exclude>META-INF/NOTICE*</exclude>
                                <exclude>module-info.class</exclude>
                            </excludes>
                        </filter>
                    </filters>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <manifestEntries>
                                        <Main-Class>io.cynthia.client.example.Main</Main-Class>
                                    </manifestEntries>
                                </transformer>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer" />
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

Cynthia favors Lombok annotations, and we use a standard `lombok.config` file:

```properties
lombok.addLombokGeneratedAnnotation = true
lombok.anyConstructor.addConstructorProperties = true
```

## Example: Searching with Cynthia using the fluent API.

Here's an example of how to connect to Cynthia using the HTTP fluent HC client API in a Java application. In this example, 
we search in the Lacoste product catalog for a `warm men's jacket` using a model called the `crafty-crocodile-1.1` .

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
        final CynthiaNLUSearchResponse response = client.nluSearch(
                "Lacoste", 
                "crafty-crocodile-1.1",
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
