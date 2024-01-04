import { beforeAll, afterEach, afterAll, vi } from "vitest";
import { server } from "./src/mocks/node";

beforeAll(() => server.listen());

afterEach(() => {
  vi.restoreAllMocks();
  server.resetHandlers();
});

afterAll(() => server.close());
