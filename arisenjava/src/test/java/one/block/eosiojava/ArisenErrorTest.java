

package one.block.arisenjava;

import one.block.arisenjava.error.ArisenError;
import one.block.arisenjava.error.serializationProvider.SerializationProviderError;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ArisenErrorTest {

    @Test
    public void errroMessage() {
        ArisenError err = new ArisenError("Parsing Error!");
        String description = err.getLocalizedMessage();
        assertEquals("Parsing Error!", description);

        Exception origError = new IOException("File Not Found");
        ArisenError err2 = new ArisenError(
                "Could not find resource.",
                origError);
        assertEquals("Could not find resource.", err2.getLocalizedMessage());

        assertEquals("File Not Found", err2.getCause().getMessage());
    }

    @Test
    public void asJsonString() {
        String jsonResult = "{\n" +
                "  \"errorType\": \"ArisenError\",\n" +
                "  \"errorInfo\": {\n" +
                "    \"errorCode\": \"SerializationProviderError\",\n" +
                "    \"reason\": \"Serialization Provider Failure\"\n" +
                "  }\n" +
                "}";
        SerializationProviderError err2 = new SerializationProviderError("Serialization Provider Failure");
        String errJsonString = err2.asJsonString();
        assertEquals(jsonResult, errJsonString);
    }

}