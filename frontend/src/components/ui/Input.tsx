import type { InputHTMLAttributes } from "react";

type InputProps = InputHTMLAttributes<HTMLInputElement> & {
  label: string;
  error?: string;
};

export function Input({ label, error, id, className, ...props }: InputProps) {
  const inputId = id ?? props.name ?? label.toLowerCase().replace(/\s+/g, "-");
  const classes = ["ui-input", className].filter(Boolean).join(" ");

  return (
    <label className="ui-field" htmlFor={inputId}>
      <span className="ui-label">{label}</span>
      <input {...props} id={inputId} className={classes} />
      {error ? <span className="ui-error">{error}</span> : null}
    </label>
  );
}
