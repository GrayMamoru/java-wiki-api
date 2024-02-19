package dev.masterflomaster1.jwa.model.action;

import com.google.gson.Gson;
import dev.masterflomaster1.jwa.Response;
import dev.masterflomaster1.jwa.WikiApi;
import dev.masterflomaster1.jwa.WikiApiRequest;
import dev.masterflomaster1.jwa.WikiApiSyntaxException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ShortenUrlActionTest {

    private static WikiApi api;
    private static Gson gson;

    @BeforeAll
    static void before() {
        api = new WikiApi();
        gson = new Gson();
    }

    @Test
    @DisplayName("Get the short URL for https://en.wikipedia.org/wiki/Arctica.")
    void testExample1() throws WikiApiSyntaxException, IOException, InterruptedException {
        var a = new WikiApiRequest.Builder()
                .action(
                        new ShortenUrlAction.Builder()
                                .url("https://en.wikipedia.org/wiki/Arctica")
                                .build()
                )
                .build();

        System.out.println(api.execute(a));
        System.out.println(a.getUrl());

        Response r = gson.fromJson(api.execute(a), Response.class);
        System.out.println(r);
    }

    @Test
    void getUrl() throws WikiApiSyntaxException {
        var a = new ShortenUrlAction.Builder()
                .url("https://en.wikipedia.org/wiki/Arctica")
                .build();

        assertEquals("https://en.wikipedia.org/wiki/Arctica", a.getUrl());
    }

    @Test
    void isQrCode() throws WikiApiSyntaxException {
        var a = new ShortenUrlAction.Builder()
                .url("https://en.wikipedia.org/wiki/Arctica")
                .qrCode()
                .build();

        assertTrue(a.isQrCode());
    }
}