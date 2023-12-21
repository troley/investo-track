import { ChangeEvent } from "react";

interface SearchFieldProps {
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  fieldLabelText?: string;
  fieldPlaceholderText?: string;
}

export default function SearchField({
  onChange,
  fieldLabelText,
  fieldPlaceholderText,
}: SearchFieldProps) {
  const searchField = (
    <input
      type="text"
      placeholder={fieldPlaceholderText}
      className="input input-bordered w-full"
      onChange={onChange}
    />
  );

  if (fieldLabelText !== undefined) {
    return (
      <label className="form-control w-full">
        <div className="label">
          <span className="label-text">{fieldLabelText}</span>
        </div>
        {searchField}
      </label>
    );
  } else {
    return searchField;
  }
}
