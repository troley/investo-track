package com.example.investotrack.coingeckodataprovider.client.model;

import java.io.Serializable;

/**
 * Represents a cryptocurrency data model as provided by the CoinGecko API.
 *
 * @param id     the id of the cryptocurrency
 * @param symbol the symbol of the cryptocurrency (e.g. BTC, ETH etc.)
 * @param name   the name of the cryptocurrency (e.g. Bitcoin, Ethereum etc.)
 */
public record CryptoCurrency(String id, String symbol, String name) implements Serializable {}
