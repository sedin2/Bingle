import './globals.css';
import { Noto_Sans } from 'next/font/google';

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
    <html lang='en'>
      <body className={notoSans.className}>{children}</body>
    </html>
  );
}
