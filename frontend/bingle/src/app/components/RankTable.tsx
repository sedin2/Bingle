import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useEffect, useState } from 'react';
import Image from 'next/image';

type Team = {
  name: string;
  orderPoint: number;
  colorImageUrl: string;
};

function createData({ name, orderPoint, colorImageUrl }: Team) {
  return { name, orderPoint, colorImageUrl };
}
let rows: any[] = [];

const parseAndSetTeams = (setTeams: any, data: any) => {
  if (!data.teams) return undefined;
  console.log(data);
  const teams = data.teams as Team[];
  teams.forEach((team: Team) => {
    rows.push(createData(team));
  });

  rows.sort((teamA: any, teamB: any) => {
    if (teamA.orderPoint > teamB.orderPoint) return 1;
    else return 0;
  });

  setTeams(rows);
};

export default function orderPointTable({ data }: any) {
  const [teams, setTeams] = useState<Team[]>([
    { name: '', orderPoint: 0, colorImageUrl: '' },
  ]);
  useEffect(() => {
    if (data) {
      parseAndSetTeams(setTeams, data);
    }
  }, [data]);
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size='small' aria-label='a dense table'>
        <TableHead>
          <TableRow>
            <TableCell>TEAM</TableCell>
            <TableCell align='right'>포인트</TableCell>
            <TableCell align='right'>로고</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {teams.map((team) => (
            <TableRow
              key={team?.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component='th' scope='row'>
                {team?.name}
              </TableCell>
              <TableCell align='right'>{team?.orderPoint}</TableCell>
              <TableCell align='right'>
                {/* Todo : Change to next/image */}
                <img
                  alt='logo'
                  src={team?.colorImageUrl || ''}
                  height='150'
                  width='150'
                />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
