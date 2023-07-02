'use client';

import LoginButton from './components/LoginButton';
import TeamList from './components/TeamList';
import useToken from './hooks/useToken';

export default function Home() {
  const accessToken = useToken()[0];
  return (
    <section>
      {!accessToken && (
        <div className='surface-0 text-700 text-center mt-10'>
          <div className='text-blue-600 font-bold mb-3'>
            <i className='pi pi-discord'></i>&nbsp;POWERED BY BINGLE
          </div>
          <div className='text-900 font-bold text-5xl mb-3'>Join Bingle!</div>
          <div className='text-700 text-2xl mb-5'>
            LCK Schedule Alert WebApp. <br /> T1 <br /> GEN G <br /> HANHWA{' '}
            <br />
            KT <br /> DRX <br /> KAWNGDONG
          </div>
          <LoginButton></LoginButton>
        </div>
      )}

      {accessToken && (
        <div className='text-3xl font-bold text-center mt-10 mx-auto w-3/5'>
          좋아하는 팀을 구독해 보세요!
          <br />
          <TeamList></TeamList>
        </div>
      )}
    </section>
  );
}
