import Link from 'next/link';

export default function Navbar() {
  return (
    <div className='sticky flex justify-between p-2 bg-white'>
      <h1 className='text-3xl font-bold'>
        <Link href='/'>Bingle</Link>
      </h1>
    </div>
  );
}
