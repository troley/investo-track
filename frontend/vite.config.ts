/// <reference types="vitest" />
import { defineConfig, loadEnv } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default ({ mode }) => {
  // https://stackoverflow.com/a/70711383 solution to load the env variables
  // outside of the app scope.
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) };

  return defineConfig({
    plugins: [
      react({
        include: "**/*.tsx",
      }),
    ],
    server: {
      proxy: {
        "/api": process.env.VITE_BACK_END_BASE_URL,
      },
    },
    test: {
      globals: true,
      root: __dirname,
      environment: "jsdom",
      setupFiles: ["./vitest.setup.ts"],
    },
  });
};
