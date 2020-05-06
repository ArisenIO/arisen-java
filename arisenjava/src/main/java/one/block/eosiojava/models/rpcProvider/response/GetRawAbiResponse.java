package one.block.arisenjava.models.rpcProvider.response;

import com.google.gson.annotations.SerializedName;
import one.block.arisenjava.models.rpcProvider.request.GetRawAbiRequest;

/**
 * The response of getRawAbi() RPC call {@link one.block.arisenjava.interfaces.IRPCProvider#getRawAbi(GetRawAbiRequest)}
 */
public class GetRawAbiResponse {

    /**
     * The account name (contract name) found in {@link one.block.arisenjava.models.arisenName}
     */
    @SerializedName("account_name")
    private String accountName;

    @SerializedName("code_hash")
    private String codeHash;

    @SerializedName("abi_hash")
    private String abiHash;

    /**
     * The ABI (raw ABI) of the account name (contract name)
     * <br>
     * This ABI is used to serialize a contract's action data.
     */
    @SerializedName("abi")
    private String abi;

    /**
     * Gets the account name (contract name) found in {@link one.block.arisenjava.models.arisenName}
     *
     * @return the account name.
     */
    public String getAccountName() {
        return accountName;
    }

    public String getCodeHash() {
        return codeHash;
    }

    public String getAbiHash() {
        return abiHash;
    }

    /**
     * Gets The ABI (raw ABI) of the account name (contract name).
     * <br>
     *      This ABI is used to serialize a contract's action data.
     *
     * @return the raw ABI
     */
    public String getAbi() {
        return abi;
    }
}
