

package one.block.arisenjava.error.utilities;

import one.block.arisenjava.error.ArisenError;
import org.jetbrains.annotations.NotNull;

/**
 * Error class is used when there is an exception while attempting to call any method of RIXFormatter
 */
public class RIXFormatterError extends ArisenError {

    public RIXFormatterError() {
    }

    public RIXFormatterError(@NotNull String message) {
        super(message);
    }

    public RIXFormatterError(@NotNull String message,
            @NotNull Exception exception) {
        super(message, exception);
    }

    public RIXFormatterError(@NotNull Exception exception) {
        super(exception);
    }
}
