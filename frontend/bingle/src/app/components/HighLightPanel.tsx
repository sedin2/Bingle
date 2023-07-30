'use client';
import { useState } from 'react';
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
  const [selectedTeam, setSelectedTeam] = useState(LCKTeamList[0]);
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
    </div>
  );
}
