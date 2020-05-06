package one.block.arisenjava.models;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Class holds block chain account name.
 */
public class ARISENName {

    /**
     * arisen account name in String format.
     */
    @NotNull private String accountName;

    /**
     * Initialize arisenName object with arisen account name in String format
     *
     * @param accountName - input arisen account name in String format.
     */
    public ARISENName(@NotNull String accountName) {
        this.accountName = accountName;
    }

    /**
     * Get arisen account name in String format.
     *
     * @return arisen account name in String format.
     */
    @NotNull
    public String getAccountName() {
        return accountName;
    }

    /**
     * Get arisen account name
     *
     * @param accountName input arisen account name in string format.
     */
    public void setAccountName(@NotNull String accountName) {
        this.accountName = accountName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ARISENName arisenName = (ARISENName) o;
        return getAccountName().equals(arisenName.getAccountName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountName());
    }
}
