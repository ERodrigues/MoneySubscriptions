import type { ButtonHTMLAttributes, PropsWithChildren } from "react";

type ButtonProps = PropsWithChildren<ButtonHTMLAttributes<HTMLButtonElement>> & {
  variant?: "primary" | "secondary";
};

export function Button({
  children,
  className,
  variant = "primary",
  type = "button",
  ...props
}: ButtonProps) {
  const classes = ["ui-button", `ui-button--${variant}`, className].filter(Boolean).join(" ");

  return (
    <button {...props} type={type} className={classes}>
      {children}
    </button>
  );
}
