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

export const attribution = {
  brand: "testbrand",
  url: "http://testbrandexample.org",
};

export const handlers = [
  http.get(`${import.meta.env.VITE_API_FIND_CURRENCIES_URL}`, (resolver) => {
    const searchParam = new URL(resolver.request.url).searchParams.get("q");

    if (searchParam === null) {
      return HttpResponse.json();
    }

    return HttpResponse.json(
      currencies.filter(
        (c) => c.symbol.includes(searchParam) || c.name.includes(searchParam)
      )
    );
  }),
  http.get(`${import.meta.env.VITE_API_GET_ATTRIBUTION_URL}`, () => {
    return HttpResponse.json(attribution);
  }),
];
