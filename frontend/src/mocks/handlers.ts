import { http, HttpResponse } from "msw";

export const currencies = [
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
];

export const handlers = [
  http.get(`${import.meta.env.VITE_API_FIND_CURRENCIES_URL}?q=btc`, () => {
    return HttpResponse.json(currencies);
  }),
];
