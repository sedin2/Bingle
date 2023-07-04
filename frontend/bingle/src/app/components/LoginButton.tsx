'use client';

import Link from 'next/link';
import useToken from '../hooks/useToken';
import { fetcher } from '../service/fetcher';
import { queryString } from '../service/oAuth';

export default function LoginButton() {
  const [accessToken, setAccessToken] = useToken();
  const requestLogOut = () => {
    fetcher('http://localhost:8080/logout', 'POST', accessToken, undefined);
  };
  return (
    <div>
      {accessToken && (
        <button
          onClick={() => {
            requestLogOut();
          }}
          className='font-bold text-2xl'
        >
          로그아웃
        </button>
      )}
      {!accessToken && (
        <Link href={queryString} className='font-bold text-2xl'>
          로그인
        </Link>
      )}
    </div>
  );
}
