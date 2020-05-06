package one.block.arisenjava.interfaces;

import java.util.List;
import java.util.Map;
import one.block.arisenjava.error.abiProvider.GetAbiError;
import one.block.arisenjava.models.ARISENName;
import org.jetbrains.annotations.NotNull;

/**
 * Interface of ABI Provider
 */
public interface IABIProvider {

    /**
     * Gets multiple ABI by list of ARISENName.
     * <br>
     * Check ABIProviderImpl.getABIs() flow in "complete workflow"
     * doc for more detail about the implementation
     *
     * @param chainId the chain id
     * @param accounts the accounts
     * @return the abis
     * @throws GetAbiError thrown if there are any exceptions during the getAbi process.
     */
    @NotNull
    Map<String, String> getAbis(@NotNull String chainId, @NotNull List<ARISENName> accounts) throws GetAbiError;

    /**
     * Gets abi by ARISENName.
     * <br>
     * Check ABIProviderImpl.getABI() flow in "complete workflow"
     * doc for more detail about the implementation
     *
     * @param chainId the chain id
     * @param account the account
     * @return the abi
     * @throws GetAbiError thrown if there are any exceptions during the getAbis process.
     */
    @NotNull
    String getAbi(@NotNull String chainId, @NotNull arisenName account) throws GetAbiError;
}
