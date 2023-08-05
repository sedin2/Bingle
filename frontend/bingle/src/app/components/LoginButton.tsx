'use client';

import Link from 'next/link';
import useToken from '../hooks/useToken';
import { fetcher } from '../service/fetcher';

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
        <Link href={'oauth/login/kakao'} className='font-bold text-2xl'>
          로그인
        </Link>
      )}
    </div>
  );
}
