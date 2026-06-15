import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "MoneySubscriptions",
  description: "Controle pessoal de assinaturas e custos recorrentes",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body>{children}</body>
    </html>
  );
}
