package one.block.arisenjava.error.rpcProvider;

import one.block.arisenjava.error.ArisenError;
import org.jetbrains.annotations.NotNull;

/**
 * Error class is used when there is an exception while attempting to use the RPC call, getRawAbi().
 */
public class GetRawAbiRpcError extends ArisenError {

    public GetRawAbiRpcError() {
    }

    public GetRawAbiRpcError(@NotNull String message) {
        super(message);
    }

    public GetRawAbiRpcError(@NotNull String message,
            @NotNull Exception exception) {
        super(message, exception);
    }

    public GetRawAbiRpcError(@NotNull Exception exception) {
        super(exception);
    }
}
