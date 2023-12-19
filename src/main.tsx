import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Root from "./routes/Root.tsx";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import App from "./App.tsx";

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
    <RouterProvider router={router} />
  </React.StrictMode>
);
