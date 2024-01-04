import { describe, expect, it, vi } from "vitest";
import Attribution from "../model/attribution";
import * as AttributionService from "./attribution-service";

describe("attribution-service", () => {
  it("should fetch available attribution", async () => {
    // Arrange
    const attribution: Attribution = {
      brand: "testbrand",
      url: "http://testexample.org",
    };

    vi.spyOn(global, "fetch").mockResolvedValue({
      json: async () => attribution,
    } as Response);

    const getAttributionMock = vi
      .fn()
      .mockImplementation(AttributionService.getAttribution);

    // Act + Assert
    expect(await getAttributionMock()).toEqual(attribution);
  });

  it("should return null when no attribution is available", async () => {
    // Arrange
    vi.spyOn(global, "fetch").mockResolvedValue({
      json: async () => null,
    } as Response);

    const getAttributionMock = vi
      .fn()
      .mockImplementation(AttributionService.getAttribution);

    // Act + Assert
    expect(await getAttributionMock()).toEqual(null);
  });
});
