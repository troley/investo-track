package com.example.investotrack.dataprovidercore.model;

import java.util.Objects;

public abstract class AbstractCurrency {

    private final String id;

    private final String symbol;

    private final String name;

    public AbstractCurrency(String id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCurrency that = (AbstractCurrency) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(symbol, that.symbol) &&
               Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, name);
    }

    @Override
    public String toString() {
        return "AbstractCurrency{" +
               "id='" + id + '\'' +
               ", symbol='" + symbol + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}
