import { describe, expect, it } from "vitest";
import renderer from "react-test-renderer";
import SearchField from "./SearchField";

describe("<SearchField />", () => {
  it("should be an <input> when SearchField's fieldLabelText is missing", () => {
    // Arrange
    const tree = renderer
      .create(<SearchField onChange={() => {}} />)
      .toJSON() as renderer.ReactTestRendererJSON;

    // Assert
    expect(tree.type).toBe("input");
  });

  it("should be a <label> with a child <input> when SearchField's fieldLabelText is present", () => {
    // Arrange
    const tree = renderer
      .create(<SearchField onChange={() => {}} fieldLabelText="test" />)
      .toJSON() as renderer.ReactTestRendererJSON;

    // Assert
    expect(tree.type).toBe("label");
  });
});
