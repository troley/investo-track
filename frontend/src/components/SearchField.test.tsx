import { describe, expect, test } from "vitest";
import renderer from "react-test-renderer";
import SearchField from "./SearchField";

describe("<SearchField />", () => {
  test("SearchField is an <input> when fieldLabelText is missing", () => {
    // Arrange
    const tree = renderer
      .create(<SearchField onChange={() => {}} />)
      .toJSON() as renderer.ReactTestRendererJSON;

    // Assert
    expect(tree.type).toBe("input");
  });

  test("SearchField is <label> with a child <input> when fieldLabelText is present", () => {
    // Arrange
    const tree = renderer
      .create(<SearchField onChange={() => {}} fieldLabelText="test" />)
      .toJSON() as renderer.ReactTestRendererJSON;

    // Assert
    expect(tree.type).toBe("label");
  });
});
