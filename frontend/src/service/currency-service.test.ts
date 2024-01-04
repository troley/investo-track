import { describe, expect, it, vi } from "vitest";
import * as CurrencyService from "./currency-service";
import Currency from "../model/currency";

describe("currency-service", () => {
  it("should getAll currencies", async () => {
    // Arrange
    const coins: Currency[] = [
      {
        id: "bitcoin",
        symbol: "btc",
        name: "Bitcoin",
      },
      {
        id: "ethereum",
        symbol: "eth",
        name: "Ethereum",
      },
      {
        id: "stellar",
        symbol: "xlm",
        name: "Stellar",
      },
    ];

    const mockResponse = { json: async () => coins } as Response;

    vi.spyOn(global, "fetch").mockResolvedValue(mockResponse);
    const mockGetAll = vi.fn().mockImplementation(CurrencyService.getAll);

    // Act + Assert
    expect(await mockGetAll()).toEqual(coins);
  });

  it("should only get currencies that match the search query", async () => {
    // Arrange
    const coins: Currency[] = [
      {
        id: "bitcoin",
        symbol: "btc",
        name: "Bitcoin",
      },
      {
        id: "bitcoinlite",
        symbol: "btcl",
        name: "Bitcoin Lite",
      },
    ];

    const mockResponse = { json: async () => coins } as Response;

    vi.spyOn(global, "fetch").mockResolvedValue(mockResponse);
    const mockGetAll = vi.fn().mockImplementation(CurrencyService.getAll);

    const searchQuery = "btc";

    // Act + Assert
    expect(await mockGetAll(searchQuery)).toEqual([
      expect.objectContaining({ symbol: expect.stringContaining(searchQuery) }),
      expect.objectContaining({ symbol: expect.stringContaining(searchQuery) }),
    ]);
  });
});
