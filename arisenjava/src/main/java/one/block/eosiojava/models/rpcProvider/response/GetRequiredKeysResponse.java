package one.block.arisenjava.models.rpcProvider.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import one.block.arisenjava.models.rpcProvider.request.GetRequiredKeysRequest;

/**
 * The response of getRequiredKeys() RPC call {@link one.block.arisenjava.interfaces.IRPCProvider#getRequiredKeys(GetRequiredKeysRequest)}
 */
public class GetRequiredKeysResponse {

    /**
     * The required public arisen keys to sign the transaction. It gets assigned to {@link
     * one.block.arisenjava.models.signatureProvider.arisenTransactionSignatureRequest#setSigningPublicKeys(List)},
     * which is passed to a Signature Provider to sign a transaction.
     */
    @SerializedName("required_keys")
    private List<String> requiredKeys;

    /**
     * Gets the required public arisen keys to sign the transaction. It gets assigned to {@link
     * one.block.arisenjava.models.signatureProvider.arisenTransactionSignatureRequest#setSigningPublicKeys(List)},
     * which is passed to a Signature Provider to sign a transaction.
     * @return The required public arisen keys.
     */
    public List<String> getRequiredKeys() {
        return requiredKeys;
    }
}
