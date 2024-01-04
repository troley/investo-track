import { useQuery } from "@tanstack/react-query";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { getAll } from "./service/currency-service";
import Currency from "./model/currency";
import SearchField from "./components/SearchField";
import ItemsButtonList from "./components/data_display/ItemsButtonList";
import AttributionLabel from "./components/attribution/AttributionLabel";
import { useAttributionQuery } from "./hooks/attribution-hooks";

export const debounceTime = 1500;

function App() {
  const { t } = useTranslation();

  const [query, setQuery] = useState("");

  const { data, refetch, isFetching } = useQuery({
    queryKey: ["currencies"],
    queryFn: async (): Promise<Currency[]> => getAll(query),
    enabled: false,
    retry: false,
  });

  const attributionQuery = useAttributionQuery();

  useEffect(() => {
    if (query) {
      const timeoutId = setTimeout(() => refetch(), debounceTime);
      return () => clearTimeout(timeoutId);
    }
  }, [query]);

  const handleCurrencyClick = () => {
    // TODO: to be implemented
  };

  return (
    <div className="container mx-auto">
      <div className="row-auto">
        <div id="searchFieldArea" className="w-full sm:mx-auto sm:w-60 md:w-72">
          <SearchField
            onChange={(e) => setQuery(e.target.value)}
            fieldLabelText={t("Home.SearchField.LabelText")}
            fieldPlaceholderText={t("Home.SearchField.PlaceholderText")}
          />
          {attributionQuery.data && (
            <AttributionLabel data={attributionQuery.data} />
          )}
          {isFetching ? (
            <span className="loading loading-spinner loading-md"></span>
          ) : (
            data && (
              <ItemsButtonList
                data={data.map((d) => {
                  return { id: d.id, displayValue: d.name };
                })}
                itemClickHandler={handleCurrencyClick}
              />
            )
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
