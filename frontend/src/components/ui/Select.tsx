import type { PropsWithChildren, SelectHTMLAttributes } from "react";

type SelectProps = PropsWithChildren<SelectHTMLAttributes<HTMLSelectElement>> & {
  label: string;
  error?: string;
};

export function Select({ children, label, error, id, className, ...props }: SelectProps) {
  const selectId = id ?? props.name ?? label.toLowerCase().replace(/\s+/g, "-");
  const classes = ["ui-input", className].filter(Boolean).join(" ");

  return (
    <label className="ui-field" htmlFor={selectId}>
      <span className="ui-label">{label}</span>
      <select {...props} id={selectId} className={classes}>
        {children}
      </select>
      {error ? <span className="ui-error">{error}</span> : null}
    </label>
  );
}
