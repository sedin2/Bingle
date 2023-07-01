'use client';

import Link from 'next/link';
import useToken from '../hooks/useToken';
import { queryString } from '../service/oAuth';

export default function LoginButton() {
  const [accessToken, setAccessToken] = useToken();
  return (
    <div>
      {accessToken && (
        <button
          onClick={() => {
            setAccessToken('');
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
