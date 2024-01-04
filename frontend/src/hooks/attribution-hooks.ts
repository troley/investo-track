import { useQuery } from "@tanstack/react-query";
import Attribution from "../model/attribution";
import { getAttribution } from "../service/attribution-service";

export const useAttributionQuery = () => {
  return useQuery({
    queryKey: ["attribution"],
    queryFn: async (): Promise<Attribution | null> => getAttribution(),
  });
};

export default {
  useAttributionQuery,
};
