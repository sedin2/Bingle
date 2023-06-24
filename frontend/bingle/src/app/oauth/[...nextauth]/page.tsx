'use client';
import { useSearchParams } from 'next/navigation';
import { PacmanLoader } from 'react-spinners';

export default function OAuthPage() {
  const searchParams = useSearchParams();
  const code = searchParams.get('code');
  /* TODO : request to Backend with "code", and handling response from Backend */
  return (
    <div className='flex h-screen items-center justify-center'>
      <PacmanLoader size={40} />
    </div>
  );
}
