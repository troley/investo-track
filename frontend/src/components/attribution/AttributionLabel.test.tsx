import { describe, expect, it } from "vitest";
import AttributionLabel from "./AttributionLabel";
import { render, waitFor } from "@testing-library/react";
import "@testing-library/jest-dom";
import i18n from "../../i18n";

describe("<AttributionLabel />", () => {
  const brand = "testbrand";
  const url = "http://textexample.org";

  it("should not be a link element when url is not present", async () => {
    // Arrange
    const attributionLabel = render(
      <AttributionLabel data={{ brand: brand }} />
    );

    const attributionLabelText = i18n.i18n.t("Attribution.LabelText", {
      brand: brand,
    });

    // Assert
    await waitFor(() => {
      const element = attributionLabel.getByText(attributionLabelText);
      expect(element).toBeInTheDocument();
      expect(element.getAttribute("href")).toBeNull();
    });
  });

  it("should be a link element when url is present", async () => {
    // Arrange
    const attributionLabel = render(
      <AttributionLabel data={{ brand: brand, url: url }} />
    );

    const attributionLabelText = i18n.i18n.t("Attribution.LabelText", {
      brand: brand,
    });

    // Assert
    await waitFor(() => {
      const element = attributionLabel.getByText(attributionLabelText);
      expect(element).toBeInTheDocument();
      expect(element.getAttribute("href")).toEqual(url);
    });
  });
});
