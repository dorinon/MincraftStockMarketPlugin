package com.dorinon.market;

public enum OfferType {
    Sell, Buy, Futures, Options;

    public int toInt() {
        return switch (this) {
            case Sell -> 1;
            case Buy -> 2;
            case Futures -> 3;
            default -> -1;
        };
    }
}
