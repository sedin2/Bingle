'use client';

import HighLightPanel from './components/HighLightPanel';
import TeamList from './components/TeamList';
import useToken from './hooks/useToken';

export default function Home() {
  const accessToken = useToken()[0];
  return (
    <section>
      {!accessToken && (
        <>
          <div>
            <h1 className='text-bold text-3xl'>LCK 순위</h1>
          </div>
          <div>
            <h1 className='text-bold text-3xl'>경기 하이라이트</h1>
            <HighLightPanel></HighLightPanel>
          </div>
        </>
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
