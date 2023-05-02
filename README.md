# Cynthia Java Client

Cynthia Java Client is a lightweight and easy-to-use Java API that simplifies the process of connecting to the Cynthia AI model for inference requests in your Java applications.

## Getting Started

To get started with the Cynthia Java Client, clone this repository and install the artifact:

```bash
git clone https://github.com/cynthia-io/JavaClient.git
cd JavaClient
mvn clean install
```

Now, add the following Maven dependency to your project's `pom.xml` file:

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

This query will return the following 10 products from [Lacoste](https://www.lacoste.com/us/#query=warm%20men's%20jacket).

```json
{
    "data": [
        [
            {
                "id": "bf65f20c4a48dcb01f770fbb73d3dd05",
                "score": 0.16635426878929138,
                "properties": {
                    "title": "Men's Quilted Color-Block Water-Repellent Jacket",
                    "description": "Stay warm in this thermal jacket. A color-block design that combines sustainable eiderdown with crocodile style.",
                    "image": "5466f9228d2a996b258fc78071c1d5a3.png"
                }
            },
            {
                "id": "e098c951e59f139859e0809b1f9aea6a",
                "score": 0.12331419438123703,
                "properties": {
                    "title": "Men's Fold Away Hood Vest",
                    "description": "Keep warm all season long in this responsible down vest jacket. A warm and water-repellent design with its own fold away hood.",
                    "image": "23651cfdd6c142e138c4a6eac4654195.png"
                }
            },
            {
                "id": "aac87d755fdd9b1ffa696cb0b6a10241",
                "score": 0.08255892992019653,
                "properties": {
                    "title": "Men's Oversized Branded Water-Repellent Jacket",
                    "description": "Comfortable, responsible, functional. Rain or shine, stay warm in this bold Lacoste jacket.",
                    "image": "e430e6c7a9f6275d60256a35038c5048.png"
                }
            },
            {
                "id": "3f26d09d1b533014f1c2f334d663c991",
                "score": 0.07648279517889023,
                "properties": {
                    "title": "Men's Quilted Hooded Jacket",
                    "description": "Sustainable. Water-repellent. Durable. With additional details for extra functionality. The Lacoste accessory for daily life.",
                    "image": "63f6fafe15b46eced44516d25f09fae7.png"
                }
            },
            {
                "id": "289ad3577f1593b1bae087bfd5bc5d4d",
                "score": 0.07484214752912521,
                "properties": {
                    "title": "Men's Sherpa Fleece Vest",
                    "description": "Warm. Bold. Comfortable. A functional vest with signature Lacoste high-tech details.",
                    "image": "97492e333c9cd6eccaf4c194ea1c9110.png"
                }
            },
            {
                "id": "450efb871faa13de1bd30844faa32db8",
                "score": 0.057507604360580444,
                "properties": {
                    "title": "Men's Water-Resistant Cotton Blend Short Hooded Parka",
                    "description": "Minimalism meets elegance in this short parka crafted in a water-resistant cotton blend. With convenient pockets, an adjustable hood and a concealed zip fastening, it has fitted sleeve ends featuring an inner adjustment system. Don't miss this functional, contemporary piece, which takes you effortlessly through the city, no matter what the weather.",
                    "image": "3b65a53dbb7d25c44dc4a2ede5a9ba46.png"
                }
            },
            {
                "id": "f1d74c8a86e3b384240583f05c54d10a",
                "score": 0.049184732139110565,
                "properties": {
                    "title": "Men's Checked Responsible Wool Chesterfield Jacket",
                    "description": "Elegant, essential and iconic sum up this warm, long Chesterfield coat. A comfortable must-have made from a recycled wool blend and featuring a checkered design. Whether you wear it with a velvet suit or go for a more offbeat look with a hoody, this timeless piece adapts to your every whim.",
                    "image": "6502b80fa49e127766cc1942e2f25679.png"
                }
            },
            {
                "id": "434bcc7c9eeb5b61029eb23ba6bf0bc5",
                "score": 0.03910645470023155,
                "properties": {
                    "title": "Men's Insulated Padded Bomber Jacket",
                    "description": "Flawless comfort and Lacoste elegance come with this water-resistant bomber jacket. Promises a perfect silhouette at all times.",
                    "image": "074b72cdd2f738afe92648f7523ed85c.png"
                }
            },
            {
                "id": "83af71f3ba67f6bf0f672ee30ebf0c12",
                "score": 0.03582192212343216,
                "properties": {
                    "title": "Men's Quilted Water-Repellent Jacket",
                    "description": "Combine warmth and comfort in this quilted jacket. Iconic, water-repellent, responsible. Clearly essential.",
                    "image": "84221c8c481f036f58ad4a5b0a73a862.png"
                }
            },
            {
                "id": "c0cd625dbff100d4d9f42c62af5906bd",
                "score": 0.023045210167765617,
                "properties": {
                    "title": "Men's Water-Repellent Parka",
                    "description": "Nautical style accompanies this functional jacket. Inspired by the sailing world, its ergonomic design makes all the difference.",
                    "image": "100071d3b9083bafba250b95555149e4.png"
                }
            }
        ]
    ]
}
```

For NLU Search, the Cynthia `score` property associated with each search result represents the fraction of contribution
of the model's attention on this result as it pertaining to relevancy. Imagine the results are a pie chart 
where each product gets a slice of the pie. The size of the slice represents relevancy, and over all of the products, 
the attention sums to `1.0`. Scores may be either 32-bit or 64-bit floating point numbers, depending on 
the model. Cynthia prefers computations using 32-bit floating point numbers while performing in-memory computations
and in neural network parameters.

## License

This project is licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Contributing

We welcome contributions from the community. Please read our Contributing Guidelines to learn more about how you can get involved.


## About Us

Cynthia.io Inc. is a C-Corp in the State of Delaware. The name Cynthia is an anthropomorphized contraction 
of our trademark, the `Synthetic Intelligent Agent™`. Please [visit our website](https://cynthia.io) to 
learn more about us and what we do.


