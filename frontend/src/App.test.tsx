import { describe, expect, it } from "vitest";
import { waitFor, screen, render } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import "@testing-library/jest-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import i18n from "./i18n";
import App, { debounceTime } from "./App";
import { attribution } from "./mocks/handlers";

describe("<App />", async () => {
  const queryClient = new QueryClient({
    defaultOptions: { queries: { retry: false } },
  });

  const fieldPlaceholderText = i18n.i18n.t("Home.SearchField.PlaceholderText");

  describe("Get all currencies", () => {
    it("should not trigger Fetch when <SearchField /> is clicked without providing any input", async () => {
      // Arrange
      render(
        <QueryClientProvider client={queryClient}>
          <App />
        </QueryClientProvider>
      );

      const field = screen.getByPlaceholderText(fieldPlaceholderText);

      // Act
      await userEvent.click(field);

      // Assert
      expect(field).toHaveValue("");
      expect(await screen.queryByText("Bitcoin")).not.toBeInTheDocument();
    });

    it("should fetch only currencies that contain the <SearchField /> input text", async () => {
      // Arrange
      render(
        <QueryClientProvider client={queryClient}>
          <App />
        </QueryClientProvider>
      );

      const field = screen.getByPlaceholderText(fieldPlaceholderText);

      // Act
      await userEvent.type(field, "btc");

      // Assert
      expect(field).toHaveValue("btc");
      await waitFor(
        async () => {
          expect(screen.getByText("Bitcoin")).toBeInTheDocument();
          expect(await screen.queryByText("Ethereum")).not.toBeInTheDocument();
        },
        {
          timeout: debounceTime + 100, // Timeout a bit longer
        }
      );
    });
  });

  it("should fetch and display <AttributionLabel> when available and <App /> is rendered", async () => {
    // Arrange
    render(
      <QueryClientProvider client={queryClient}>
        <App />
      </QueryClientProvider>
    );

    // Assert
    await waitFor(() =>
      expect(
        screen.getByText(`Powered by ${attribution.brand}`)
      ).toBeInTheDocument()
    );
  });
});
