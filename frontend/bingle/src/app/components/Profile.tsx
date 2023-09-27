'use client';

import useUser from '../hooks/useUser';

export default function Profile() {
  const [user, setUser, isValidUser] = useUser();
  return (
    <div>
      {isValidUser && <h1 className='text-bold text-3xl'>{user.nickname}</h1>}
    </div>
  );
}
