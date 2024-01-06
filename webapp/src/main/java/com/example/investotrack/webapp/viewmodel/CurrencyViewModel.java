package com.example.investotrack.webapp.viewmodel;

import java.io.Serializable;

public record CurrencyViewModel(String id, String symbol, String name) implements Serializable {}
