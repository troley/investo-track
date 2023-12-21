import { useTranslation } from "react-i18next";

export default function Navbar() {
  const { t } = useTranslation();

  return (
    <div className="drawer">
      <input
        id="nav-menu-side-drawer"
        type="checkbox"
        className="drawer-toggle"
      />
      {/* Desktop */}
      <div className="drawer-content flex flex-col">
        <div className="w-full navbar bg-base-200">
          <div className="flex-none lg:hidden">
            <label
              htmlFor="nav-menu-side-drawer"
              aria-label={t("Navbar.Sidebar.OpenText")}
              className="btn btn-square btn-ghost"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                className="inline-block w-6 h-6 stroke-current"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M4 6h16M4 12h16M4 18h16"
                ></path>
              </svg>
            </label>
          </div>
          <div className="px-2 mx-2 text-2xl">{t("Navbar.BrandText")}</div>
          <div className="hidden lg:block">
            <ul className="menu menu-horizontal">{/* TODO: Render items */}</ul>
          </div>
        </div>
      </div>
      {/* Tablet / mobile sidebar */}
      <div className="drawer-side">
        <label
          htmlFor="nav-menu-side-drawer"
          aria-label={t("Navbar.Sidebar.CloseText")}
          className="drawer-overlay"
        ></label>
        <ul className="menu p-4 w-80 min-h-full bg-base-200">
          {/* TODO: Render items */}
        </ul>
      </div>
    </div>
  );
}
