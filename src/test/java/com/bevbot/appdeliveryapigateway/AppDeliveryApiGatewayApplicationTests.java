package com.bevbot.appdeliveryapigateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"browse.drink.api=http://localhost:${wiremock.server.port}",
                "browse.drink.ui=http://localhost:${wiremock.server.port}",
                "content=http://localhost:${wiremock.server.port}",
                "order.drink.api=http://localhost:${wiremock.server.port}",
                "order.drink.ui=http://localhost:${wiremock.server.port}",
                "container.ui=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
public class AppDeliveryApiGatewayApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testBrowseApiRouteWhenBrowseApiUriExpectOriginalPath() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/browse/api/restaurants.json"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                        .withHeader("Content-Type", "application/json")));
        webClient
                .get().uri("/browse/api/restaurants.json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");
    }

    @Test
    public void testBrowseUiRouteWhenBrowseUiUriExpectRootPath() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/static/js/bundle.js"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                        .withHeader("Content-Type", "application/json")));
        webClient
                .get().uri("/browse/static/js/bundle.js")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");
    }

    @Test
    public void testContentRouteWhenContentUriExpectRootPath() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/images/1-Moksa.jpg"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                        .withHeader("Content-Type", "application/json")));
        webClient
                .get().uri("/content/images/1-Moksa.jpg")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");
    }

    @Test
    public void testOrderApiRouteWhenOrderApiUriExpectOriginalPath() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/order/api/drink-orders"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                        .withHeader("Content-Type", "application/json")));
        webClient
                .get().uri("/order/api/drink-orders")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");
    }

    @Test
    public void testOrderUiRouteWhenOrderUiUriExpectRootPath() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/static/js/bundle.js"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                        .withHeader("Content-Type", "application/json")));
        webClient
                .get().uri("/order/static/js/bundle.js")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");
    }

    @Test
    public void testContainerUiRouteWhenContainerUiUriExpectOriginalPath() throws Exception {
        //Stubs
        stubFor(get(urlEqualTo("/static/main.js"))
                .willReturn(aResponse()
                        .withBody("{\"headers\":{\"Hello\":\"World\"}}")
                        .withHeader("Content-Type", "application/json")));
        webClient
                .get().uri("/static/main.js")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");
    }

}
