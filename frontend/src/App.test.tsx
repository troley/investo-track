import { describe, expect, test } from "vitest";
import { waitFor, screen, render } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import "@testing-library/jest-dom";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { I18nextProvider } from "react-i18next";
import i18n from "./i18n";
import App from "./App";

describe("<App />", async () => {
  const queryClient = new QueryClient({
    defaultOptions: { queries: { retry: false } },
  });

  const fieldPlaceholderText = i18n.i18n.getResource(
    "en-US",
    "translation",
    "Home.SearchField.PlaceholderText"
  );

  describe("Get all currencies", () => {
    test("clicks on <SearchField /> and provides no input, does not trigger fetch", async () => {
      // Arrange
      render(
        <I18nextProvider i18n={i18n.i18n}>
          <QueryClientProvider client={queryClient}>
            <App />
          </QueryClientProvider>
        </I18nextProvider>
      );

      const field = screen.getByPlaceholderText(fieldPlaceholderText);

      // Act
      await userEvent.click(field);

      // Assert
      expect(field).toHaveValue("");
      expect(await screen.queryByText("Bitcoin")).not.toBeInTheDocument();
    });

    test("Provides 'btc' as input to <SearchField />, fetches expected coin", async () => {
      // Arrange
      render(
        <I18nextProvider i18n={i18n.i18n}>
          <QueryClientProvider client={queryClient}>
            <App />
          </QueryClientProvider>
        </I18nextProvider>
      );

      const field = screen.getByPlaceholderText(fieldPlaceholderText);

      // Act
      await userEvent.type(field, "btc");

      // Assert
      expect(field).toHaveValue("btc");
      await waitFor(
        () => expect(screen.getByText("Bitcoin")).toBeInTheDocument(),
        {
          timeout: 1600,
        }
      );
    });
  });
});
