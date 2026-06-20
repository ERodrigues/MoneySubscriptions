export type FieldErrors = Record<string, string>;

export class ApiError extends Error {
  code: string;
  status: number;
  fieldErrors: FieldErrors;

  constructor(code: string, message: string, status: number, fieldErrors: FieldErrors = {}) {
    super(message);
    this.name = "ApiError";
    this.code = code;
    this.status = status;
    this.fieldErrors = fieldErrors;
  }
}

type RequestOptions = Omit<RequestInit, "body"> & {
  body?: unknown;
};

const DEFAULT_API_BASE_URL =
  process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8080/api/v1";

export async function apiRequest<T>(path: string, options: RequestOptions = {}): Promise<T> {
  const response = await fetch(`${DEFAULT_API_BASE_URL}${path}`, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...(options.headers ?? {})
    },
    body: options.body === undefined ? undefined : JSON.stringify(options.body),
    cache: "no-store"
  });

  if (!response.ok) {
    const payload = await safeJson(response);
    throw new ApiError(
      payload?.code ?? "HTTP_ERROR",
      payload?.message ?? "Unexpected API error",
      response.status,
      payload?.fieldErrors ?? {}
    );
  }

  if (response.status === 204) {
    return undefined as T;
  }

  return (await response.json()) as T;
}

async function safeJson(response: Response) {
  try {
    return await response.json();
  } catch {
    return null;
  }
}
