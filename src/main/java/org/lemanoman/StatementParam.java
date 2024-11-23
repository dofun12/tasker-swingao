package org.lemanoman;

public record StatementParam<T>(Integer index, T value, Class<T> type) {
    public StatementParam {
        if (index == null || value == null || type == null) {
            throw new IllegalArgumentException("All parameters must be non-null");
        }

    }

}
