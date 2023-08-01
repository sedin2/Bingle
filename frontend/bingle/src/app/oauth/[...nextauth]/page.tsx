'use client';
import useFetcher from '@/app/hooks/useFetcher';
import useToken from '@/app/hooks/useToken';
import { redirect, useSearchParams } from 'next/navigation';
import { SetStateAction, Dispatch, useEffect } from 'react';
import { PacmanLoader } from 'react-spinners';

export default function OAuthPage() {
  const searchParams = useSearchParams();
  const code = searchParams.get('code');
  const getString = `http://localhost:8080/oauth/callback/kakao?code=${code}`;
  const setAccessToken = useToken()[1] as Dispatch<SetStateAction<string>>;
  const { data, error, isLoading } = useFetcher(getString, 'GET');
  useEffect(() => {
    if (data?.code == 'OK') {
      setAccessToken(data.data.accessToken);
      redirect('/');
    }
  }, [data]);
  return (
    <div>
      {isLoading && (
        <div className='flex h-screen items-center justify-center'>
          <PacmanLoader size={40} />
        </div>
      )}
      {error && <h1>error!</h1>}
      {data && <h1>`${data.message}`</h1>}
    </div>
  );
}
