'use client';

import Link from 'next/link';
import { queryString } from '../service/oAuth';

export default function LoginButton() {
  return (
    <Link href={queryString} className='font-bold text-2xl'>
      로그인
    </Link>
  );
}
