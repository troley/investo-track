import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Root from "./routes/Root.tsx";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import App from "./routes/App.tsx";
import "./i18n.ts";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        index: true,
        element: <App />,
      },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  </React.StrictMode>,
);
