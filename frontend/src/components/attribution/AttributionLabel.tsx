import { useTranslation } from "react-i18next";
import Attribution from "../../model/attribution";

interface AttributionLabelProps {
  data: Attribution;
}

export default function AttributionLabel({ data }: AttributionLabelProps) {
  const { t } = useTranslation();

  if (!data.url) {
    return (
      <div className="text-end">
        <p className="text-sm">
          {t("Attribution.LabelText", {
            brand: data.brand,
          })}
        </p>
      </div>
    );
  }

  return (
    <div className="text-end">
      <a
        className="link link-info items-end text-sm"
        href={data.url}
        target="_blank"
      >
        {t("Attribution.LabelText", {
          brand: data.brand,
        })}
      </a>
    </div>
  );
}
