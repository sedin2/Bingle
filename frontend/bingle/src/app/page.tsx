'use client';

import { PacmanLoader } from 'react-spinners';
import HighLightPanel from './components/HighLightPanel';
import RankTable from './components/RankTable';
import TeamList from './components/TeamList';
import useFetcher from './hooks/useFetcher';
import useToken from './hooks/useToken';

export default function Home() {
  const accessToken = useToken()[0];
  const getTeamListURL = 'http://localhost:8080/teams';
  const { data, error, isLoading } = useFetcher(getTeamListURL, 'GET');
  return (
    <section>
      {!accessToken && (
        <>
          <div className='m-10'>
            <h1 className='text-bold text-3xl m-5'>LCK 순위</h1>
            {isLoading && <PacmanLoader size={40} />}
            {data && data.code === 'OK' ? (
              <RankTable data={data.data}></RankTable>
            ) : undefined}
          </div>
          <div className='m-10'>
            <h1 className='text-bold text-3xl m-5'>경기 하이라이트</h1>
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
