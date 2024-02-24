package dev.masterflomaster1.jwa;

import dev.masterflomaster1.jwa.request.action.QueryAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class WikiApiRequestTest {

    private static WikiApi api;

    @BeforeAll
    static void before() {
        api = new WikiApi();
    }

    @Test
    @DisplayName("Exception is thrown when the action is not specified")
    void shouldThrownExceptionOnEmptyAction() {
        assertThrows(WikiApiSyntaxException.class, () -> new WikiApiRequest.Builder().build());
    }

    @Test
    void requestWithAdditionalParams() throws IOException {
        var a = new WikiApiRequest.Builder()
                .action(new QueryAction.Builder()
                        .build()
                )
                .assertUser("Example")
                .requestId("custom")
                .servedBy()
                .curTimestamp()
                .responseLangInfo()
                .build();

        Response r = api.execute(a);

        assertEquals("assertnameduserfailed", r.getError().getCode());
        assertEquals("custom", r.getRequestId());
        assertNotNull(r.getServedBy());
        assertNotNull(r.getCurTimestamp());
        assertNotNull(r.getUserLang());
        assertNotNull(r.getErrorLang());
    }

    @Test
    void testBuilder() {
        var a = new WikiApiRequest.Builder()
                .action(new QueryAction.Builder()
                        .build()
                )
                .assertUser("Example")
                .requestId("custom")
                .servedBy()
                .curTimestamp()
                .responseLangInfo()
                .useLang("en")
                .variant("t")
                .build();

        assertNotNull(a.getFormat());
        assertEquals("Example", a.getAssertUser());
        assertEquals("custom", a.getRequestId());
        assertTrue(a.isServedBy());
        assertTrue(a.isCurTimestamp());
        assertTrue(a.isResponseLangInfo());
        assertEquals("en", a.getUseLang());
        assertEquals("t", a.getVariant());
    }
}