import './globals.css';
import { Noto_Sans } from 'next/font/google';
import Navbar from './components/Navbar';
import TokenContext from './context/tokenContext';

const notoSans = Noto_Sans({ weight: '300', subsets: ['latin'] });

export const metadata = {
  title: 'Bingle',
  description: 'LCK Schedule Alert WebApp',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang='en' className={notoSans.className}>
      <TokenContext>
        <body className='flex flex-col w-full h-screen max-w-screen-2xl mx-auto overflow-auto bg-gray-100'>
          <Navbar></Navbar>
          {children}
        </body>
      </TokenContext>
    </html>
  );
}
