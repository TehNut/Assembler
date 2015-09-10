package tehnut.assembler;

import java.util.Locale;

/**
 * Used to determine which folder(s) each file goes into.
 */
public enum Side {

    COMMON,
    CLIENT,
    SERVER;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
