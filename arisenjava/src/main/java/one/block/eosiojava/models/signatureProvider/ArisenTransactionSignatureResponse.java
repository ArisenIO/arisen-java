package one.block.arisenjava.models.signatureProvider;

import java.util.List;
import one.block.arisenjava.error.signatureProvider.SignatureProviderError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The response object returned from the SignatureProvider after the transaction has been signed.
 */
public class ArisenTransactionSignatureResponse {

    /**
     * The serialized (Hex) version of {@link one.block.arisenjava.models.rpcProvider.Transaction}.
     * <br>
     * It is the result of {@link one.block.arisenjava.interfaces.ISerializationProvider#serializeTransaction(String)}
     * <br>
     * The transaction could have been modified by the signature provider.
     * <br>
     * If signature provider modifies the serialized transaction returned in the response {@link
     * arisenTransactionSignatureResponse#getSerializeTransaction()} but {@link
     * arisenTransactionSignatureRequest#isModifiable()} is false then {@link
     * one.block.arisenjava.error.session.TransactionGetSignatureNotAllowModifyTransactionError} will
     * be thrown
     */
    @NotNull
    private String serializeTransaction;

    /**
     * The signatures that are signed by private keys of {@link ArisenTransactionSignatureRequest#getSigningPublicKeys()}
     */
    @NotNull
    private List<String> signatures;

    /**
     * The error that occurred during signing.
     */
    @Nullable
    private SignatureProviderError error;

    public ArisenTransactionSignatureResponse(@NotNull String serializeTransaction,
            @NotNull List<String> signatures, @Nullable SignatureProviderError error) {
        this.serializeTransaction = serializeTransaction;
        this.signatures = signatures;
        this.error = error;
    }

    /**
     * Gets the serialized transaction.
     *
     * @return the serialize transaction
     */
    @NotNull
    public String getSerializeTransaction() {
        return serializeTransaction;
    }

    /**
     * Gets signatures.
     *
     * @return the signatures
     */
    @NotNull
    public List<String> getSignatures() {
        return signatures;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    @Nullable
    public SignatureProviderError getError() {
        return error;
    }
}
