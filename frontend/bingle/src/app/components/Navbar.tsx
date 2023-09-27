import Link from 'next/link';
import LoginButton from './LoginButton';
import Profile from './Profile';

export default function Navbar() {
  return (
    <div className='sticky flex justify-between p-2 bg-white mx-2'>
      <Link href='/' className='text-3xl font-bold'>
        Bingle
      </Link>
      <Profile />
      <LoginButton></LoginButton>
    </div>
  );
}
