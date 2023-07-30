import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function createData(
  name: string,
  rank: number,
  wins: number,
  loses: number,
  diff: number
) {
  return { name, rank, wins, loses, diff };
}

const rows = [
  createData('KT', 1, 15, 1, 26),
  createData('젠지', 2, 15, 1, 25),
  createData('한화생명', 3, 11, 5, 13),
  createData('DK', 4, 10, 6, 8),
  createData('KT', 5, 7, 9, -3),
];

export default function RankTable() {
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size='small' aria-label='a dense table'>
        <TableHead>
          <TableRow>
            <TableCell>TEAM</TableCell>
            <TableCell align='right'>순위</TableCell>
            <TableCell align='right'>승</TableCell>
            <TableCell align='right'>패</TableCell>
            <TableCell align='right'>득실차</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component='th' scope='row'>
                {row.name}
              </TableCell>
              <TableCell align='right'>{row.rank}</TableCell>
              <TableCell align='right'>{row.wins}</TableCell>
              <TableCell align='right'>{row.loses}</TableCell>
              <TableCell align='right'>{row.diff}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
