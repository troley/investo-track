import Attribution from "../model/attribution";

/**
 * @returns retrieves the data provider brand attribution, if any. Null otherwise.
 */
const getAttribution = async (): Promise<Attribution | null> => {
  const response = await fetch(import.meta.env.VITE_API_GET_ATTRIBUTION_URL);
  return await response.json();
};

export { getAttribution };
