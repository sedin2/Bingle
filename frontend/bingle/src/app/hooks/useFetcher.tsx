'use client';
import useSWR from 'swr';
import useToken from './useToken';

type fetchArg = {
  url: string;
  options?: {
    method: string;
    header: string;
  };
};

export type responseData = {
  code: string;
  message: string;
  data: any;
};

const fetcher = ({ url, options }: fetchArg) =>
  fetch(url, options).then((r) => r.json());

export default function useFetcher(
  url: string,
  method: string
): { data: responseData; error: any; isLoading: Boolean } {
  const [accessToken, setAccessToken] = useToken();
  const header = accessToken
    ? `'Content-Type': 'application/json', 'Authorization': 'Bearer ${accessToken}`
    : undefined;
  const { data, error, isLoading } = useSWR(
    header ? { url, options: { method: method, header } } : { url },
    fetcher
  );
  return { data, error, isLoading };
}
