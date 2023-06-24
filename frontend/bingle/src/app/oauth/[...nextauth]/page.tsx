'use client';
import { redirect, useSearchParams } from 'next/navigation';
import { PacmanLoader } from 'react-spinners';
import useSWR from 'swr';

export default function OAuthPage() {
  const searchParams = useSearchParams();
  const code = searchParams.get('code');
  const getString = `http://localhost:8080/oauth/callback/kakao?code=${code}`;
  const { data, error, isLoading } = useSWR(getString, fetch);

  return (
    <div>
      {isLoading && (
        <div className='flex h-screen items-center justify-center'>
          <PacmanLoader size={40} />
        </div>
      )}
      {error && <h1>error!</h1>}
      {data && redirect('/')}
    </div>
  );
}
