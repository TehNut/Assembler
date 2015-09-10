package tehnut.assembler;

import java.util.Locale;

public enum Side {

    COMMON,
    CLIENT,
    SERVER;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}
