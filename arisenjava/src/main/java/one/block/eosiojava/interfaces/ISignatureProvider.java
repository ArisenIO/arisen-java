package one.block.arisenjava.interfaces;

import java.util.List;
import one.block.arisenjava.error.signatureProvider.GetAvailableKeysError;
import one.block.arisenjava.error.signatureProvider.SignTransactionError;
import one.block.arisenjava.models.signatureProvider.ArisenTransactionSignatureRequest;
import one.block.arisenjava.models.signatureProvider.ArisenTransactionSignatureResponse;
import org.jetbrains.annotations.NotNull;

/**
 * The interface of Signature provider.
 */
public interface ISignatureProvider {

    /**
     * Sign a transaction in Signature Provider <br> Check signTransaction flow() in "complete
     * workflow" for more detail
     *
     * @param arisenTransactionSignatureRequest the request
     * @return the response
     * @throws SignTransactionError thrown if there are any exceptions during the signing process.
     */
    @NotNull
    ArisenTransactionSignatureResponse signTransaction(
            @NotNull ArisenTransactionSignatureRequest arisenTransactionSignatureRequest)
            throws SignTransactionError;

    /**
     * Gets available keys from signature provider <br> Check createSignatureRequest() flow in
     * "complete workflow" for more detail of how the method is used
     *
     * @return the available keys of signature provider in Rix format
     * @throws GetAvailableKeysError thrown if there are any exceptions during the get available keys process.
     */
    @NotNull
    List<String> getAvailableKeys() throws GetAvailableKeysError;
}
