import Currency from "../model/currency";

/**
 * Retrieves all currencies. When a query string is provided, then the
 * currencies matching or containing the query string will be retrieved.
 *
 * @param query the optional filter string to filter the currencies by
 * @returns     all currencies, or currencies matching or containing the query string
 */
const getAll = async (query?: string): Promise<Currency[]> => {
  let url;
  if (query) {
    url = import.meta.env.VITE_API_FIND_CURRENCIES_URL + `?q=${query}`;
  } else {
    url = import.meta.env.VITE_API_FIND_CURRENCIES_URL;
  }
  const response = await fetch(url);
  return await response.json();
};

export { getAll };
