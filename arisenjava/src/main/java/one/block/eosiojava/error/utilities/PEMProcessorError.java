/*
 * Copyright (c) 2017-2019 block.one all rights reserved.
 */

package one.block.arisenjava.error.utilities;

import one.block.arisenjava.error.ArisenError;
import org.jetbrains.annotations.NotNull;

/**
 * Error that originates from the {@link one.block.arisenjava.utilities.PEMProcessor} class.
 */
public class PEMProcessorError extends ArisenError {

    public PEMProcessorError() {
    }

    public PEMProcessorError(@NotNull String message) {
        super(message);
    }

    public PEMProcessorError(@NotNull String message,
            @NotNull Exception exception) {
        super(message, exception);
    }

    public PEMProcessorError(@NotNull Exception exception) {
        super(exception);
    }
}
