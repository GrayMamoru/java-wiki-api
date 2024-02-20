package dev.masterflomaster1.jwa.request.action;

import dev.masterflomaster1.jwa.Response;
import dev.masterflomaster1.jwa.WikiApi;
import dev.masterflomaster1.jwa.WikiApiRequest;
import dev.masterflomaster1.jwa.WikiApiSyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShortenUrlActionTest {

    private static WikiApi api;

    @BeforeAll
    static void before() {
        api = new WikiApi();
    }

    @Test
    @DisplayName("Get the short URL for https://en.wikipedia.org/wiki/Arctica.")
    void testExample1() throws WikiApiSyntaxException, IOException {
        var a = new WikiApiRequest.Builder()
                .action(new ShortenUrlAction.Builder()
                        .url("https://en.wikipedia.org/wiki/Arctica")
                        .build()
                )
                .build();

        Response r = api.execute(a);
        System.out.println(r);
    }

    @Test
    void testBuilder() throws WikiApiSyntaxException {
        var a = new ShortenUrlAction.Builder()
                .url("https://en.wikipedia.org/wiki/Arctica")
                .qrCode()
                .build();

        assertEquals("https://en.wikipedia.org/wiki/Arctica", a.getUrl());
        assertTrue(a.isQrCode());
    }

}