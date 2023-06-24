'use client';
import { useSearchParams } from 'next/navigation';

export default function OAuthPage() {
  const searchParams = useSearchParams();
  const code = searchParams.get('code');
  /* TODO : request to Backend with "code", and handling response from Backend */
  return <div>redirect!!! {`${code}`}</div>;
}
