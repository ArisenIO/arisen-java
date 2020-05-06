package one.block.arisenjava.models.rpcProvider.request;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

/**
 * The request class of getRawAbi() RPC call {@link one.block.arisenjava.interfaces.IRPCProvider#getRawAbi(GetRawAbiRequest)}
 */
public class GetRawAbiRequest {

    /**
     * Instantiates a new GetRawAbiRequest.
     *
     * @param accountName the String representation of arisen name type
     */
    public GetRawAbiRequest(@NotNull String accountName) {
        this.accountName = accountName;
    }

    /**
     * The string representation of arisen name type
     */
    @SerializedName("account_name")
    @NotNull
    private String accountName;

    /**
     * Gets the string representation of arisen name type
     *
     * @return the string representation of arisen name type
     */
    @NotNull
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the string representation of arisen name type
     *
     * @param accountName the string representation of arisen name type
     */
    public void setAccountName(@NotNull String accountName) {
        this.accountName = accountName;
    }
}
