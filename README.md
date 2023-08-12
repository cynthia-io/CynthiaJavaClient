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

Cynthia uses [Lombok](https://projectlombok.org/) annotations, and we use a standard `lombok.config` file:

```properties
lombok.addLombokGeneratedAnnotation = true
lombok.anyConstructor.addConstructorProperties = true
```

## Example: Natural Language Search

Here's an example of how to connect to Cynthia in a Java application. In this example, 
we search in the Lacoste product catalog for a `warm men's jacket` using a model called the `crafty-crocodile-1.1` .

```java
package io.cynthia.client.example;

import io.cynthia.client.CynthiaClient;
import io.cynthia.client.search.CynthiaSearchOptions;
import io.cynthia.client.search.CynthiaSearchQuery;
import io.cynthia.client.search.CynthiaSearchRequest;
import io.cynthia.client.search.CynthiaSearchResponse;
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
        final CynthiaSearchResponse response = client.search(
                "Lacoste",
                "crafty-crocodile-1.1",
                CynthiaSearchRequest.of(
                        List.of(CynthiaSearchQuery.of("warm men's jacket")),
                        CynthiaSearchOptions.of(autoLimit, top)));
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
    "correlationId": "0ca39d69-72f0-420e-9fc8-a6f335d8ee68",
    "data": [
        [
            {
                "resultId": "d14f2b7a-18b1-4573-970f-541c23a91d9f",
                "score": 0.16635426878929138,
                "properties": {
                    "title": "Men's Quilted Color-Block Water-Repellent Jacket",
                    "description": "Stay warm in this thermal jacket. A color-block design that combines sustainable eiderdown with crocodile style.",
                    "image": "5466f9228d2a996b258fc78071c1d5a3.png"
                }
            },
            {
                "resultId": "8793502a-af12-4008-a28b-1ea713891813",
                "score": 0.12331419438123703,
                "properties": {
                    "title": "Men's Fold Away Hood Vest",
                    "description": "Keep warm all season long in this responsible down vest jacket. A warm and water-repellent design with its own fold away hood.",
                    "image": "23651cfdd6c142e138c4a6eac4654195.png"
                }
            },
            {
                "resultId": "f4285e26-5642-4d92-b975-3f2d917e8f3d",
                "score": 0.08255892992019653,
                "properties": {
                    "title": "Men's Oversized Branded Water-Repellent Jacket",
                    "description": "Comfortable, responsible, functional. Rain or shine, stay warm in this bold Lacoste jacket.",
                    "image": "e430e6c7a9f6275d60256a35038c5048.png"
                }
            },
            {
                "resultId": "d32e046d-dbb2-4330-bd4a-91541d3176aa",
                "score": 0.07648279517889023,
                "properties": {
                    "title": "Men's Quilted Hooded Jacket",
                    "description": "Sustainable. Water-repellent. Durable. With additional details for extra functionality. The Lacoste accessory for daily life.",
                    "image": "63f6fafe15b46eced44516d25f09fae7.png"
                }
            },
            {
                "resultId": "9f6b131d-d27b-4993-8c10-bc1c8f4b3f95",
                "score": 0.07484214752912521,
                "properties": {
                    "title": "Men's Sherpa Fleece Vest",
                    "description": "Warm. Bold. Comfortable. A functional vest with signature Lacoste high-tech details.",
                    "image": "97492e333c9cd6eccaf4c194ea1c9110.png"
                }
            },
            {
                "resultId": "8c15c2fe-327b-4c6e-923a-1bec53545844",
                "score": 0.057507604360580444,
                "properties": {
                    "title": "Men's Water-Resistant Cotton Blend Short Hooded Parka",
                    "description": "Minimalism meets elegance in this short parka crafted in a water-resistant cotton blend. With convenient pockets, an adjustable hood and a concealed zip fastening, it has fitted sleeve ends featuring an inner adjustment system. Don't miss this functional, contemporary piece, which takes you effortlessly through the city, no matter what the weather.",
                    "image": "3b65a53dbb7d25c44dc4a2ede5a9ba46.png"
                }
            },
            {
                "resultId": "527ac95d-2bed-4531-9f44-b12325dd5100",
                "score": 0.049184732139110565,
                "properties": {
                    "title": "Men's Checked Responsible Wool Chesterfield Jacket",
                    "description": "Elegant, essential and iconic sum up this warm, long Chesterfield coat. A comfortable must-have made from a recycled wool blend and featuring a checkered design. Whether you wear it with a velvet suit or go for a more offbeat look with a hoody, this timeless piece adapts to your every whim.",
                    "image": "6502b80fa49e127766cc1942e2f25679.png"
                }
            },
            {
                "resultId": "b7916c05-2b59-4932-8456-d07e8cdc9534",
                "score": 0.03910645470023155,
                "properties": {
                    "title": "Men's Insulated Padded Bomber Jacket",
                    "description": "Flawless comfort and Lacoste elegance come with this water-resistant bomber jacket. Promises a perfect silhouette at all times.",
                    "image": "074b72cdd2f738afe92648f7523ed85c.png"
                }
            },
            {
                "resultId": "7d19d29b-9c70-48a8-9edb-c7f2cdc934af",
                "score": 0.03582192212343216,
                "properties": {
                    "title": "Men's Quilted Water-Repellent Jacket",
                    "description": "Combine warmth and comfort in this quilted jacket. Iconic, water-repellent, responsible. Clearly essential.",
                    "image": "84221c8c481f036f58ad4a5b0a73a862.png"
                }
            },
            {
                "resultId": "45a5d071-5465-4fb9-9c64-049bff6dcebe",
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
the attention sums to `1.0`. 

## Example: Providing Search Feedback

Cynthia accepts feedback based on user behavior, such as clicks and conversions. In this way, Cynthia is able
to self-improve through usage and over time. Submitting feedback is easy and uses identifiers to tell Cynthia 
what was engaged and how valuable that engagement was to the customer.

For example, let's imaging in the above search, the user clicks on the first result, for the `Men's Quilted Color-Block Water-Repellent Jacket`
and let's further imagine that this click is worth `$1.75` to Lacoste. Then the corresponding feedback would be sent to 
Cynthia with the following request:

```java
final String correlationId = "0ca39d69-72f0-420e-9fc8-a6f335d8ee68";
final String resultId = "d14f2b7a-18b1-4573-970f-541c23a91d9f";
final String type = "Click";
final float value = 1.75f;
final CynthiaSearchFeedbackRequestItem requestItem = CynthiaSearchFeedbackRequestItem.of(correlationId, resultId, type, value);
final CynthiaSearchFeedbackRequest request = CynthiaSearchFeedbackRequest.of(List.of(requestItem));
final boolean submitted = client.feedback(request);
```

The larger the feedback value, the more Cynthia will learn to associate that product with that search, and thus
self-improve through organic participation.

## License

This project is licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.txt).

## Contributing

We welcome contributions from the community. Please read our Contributing Guidelines to learn more about how you can get involved.


## About Us

Cynthia.io Inc. is a C-Corp in the State of Delaware. The name Cynthia is an anthropomorphized contraction 
of our trademark, the `Synthetic Intelligent Agent™`. Please [visit our website](https://cynthia.io) to 
learn more about us and what we do.


