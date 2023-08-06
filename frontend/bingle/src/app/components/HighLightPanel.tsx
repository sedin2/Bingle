'use client';
import { useEffect, useState } from 'react';
import TeamChip from './TeamChip';

const LCKTeamList: string[] = [
  'KT',
  '젠지',
  '한화생명',
  'DK',
  'T1',
  '리브 샌박',
  '농심',
  '광동',
  'DRX',
  'OK저축은행',
];

export default function HighLightPanel() {
  const [selectedTeam, setSelectedTeam] = useState<string>(LCKTeamList[0]);
  const [videoList, setVideoList] = useState<string[]>([]);
  const queryString = `https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=${selectedTeam}&key=${process.env.NEXT_PUBLIC_YOUTUBE_API_KEY}`;
  useEffect(() => {
    fetch(queryString)
      .then((response: any) => response.json())
      .then((data: any) => {
        console.log(data);
        const items = data.items as any[];
        setVideoList(items.map((item) => item.id.videoId));
      });
    /* TODO : check CORS Error and change it to using header */
    // fetcher(queryString, 'GET').then((data: any) => {
    //   console.log(data);
    //   const items = data.items as any[];
    //   setVideoList(items.map((item) => item.id.videoId));
    // });
  }, [selectedTeam]);
  return (
    <div className='mx-20'>
      <ul className='grid grid-cols-3 sm:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6 2xl:grid-cols-7 gap-y-4'>
        {LCKTeamList.map((name) => (
          <TeamChip
            key={name}
            setSelected={setSelectedTeam}
            label={name}
            selected={name === selectedTeam}
          />
        ))}
      </ul>
      <ul className='grid grid-cols-1 xl:grid-cols-2 gap-3 mt-10'>
        {videoList.map((id) => (
          <iframe
            key={id}
            id='player'
            width='100%'
            height='320'
            src={`https://www.youtube.com/embed/${id}`}
            frameBorder='0'
          />
        ))}
      </ul>
    </div>
  );
}
