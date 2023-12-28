import i18n from "i18next";
import en_US from "./languages/en_US/str.json";
import { initReactI18next } from "react-i18next";

i18n.use(initReactI18next).init({
  resources: {
    "en-US": { translation: en_US },
  },
  fallbackLng: "en-US",
  debug: import.meta.env.DEV,
});

export default { i18n };
