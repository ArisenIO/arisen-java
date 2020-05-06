package one.block.arisenjava.error.utilities;

import one.block.arisenjava.error.ArisenError;
import org.jetbrains.annotations.NotNull;

/**
 * Error is thrown for exceptions that occur during Base58
 * encoding or decoding operations.
 */
public class Base58ManipulationError extends ArisenError {
    public Base58ManipulationError() {
    }

    public Base58ManipulationError(@NotNull String message) {
        super(message);
    }

    public Base58ManipulationError(@NotNull String message,
            @NotNull Exception exception) {
        super(message, exception);
    }

    public Base58ManipulationError(@NotNull Exception exception) {
        super(exception);
    }
}
