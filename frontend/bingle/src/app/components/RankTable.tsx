import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Image from 'next/image';
import useTeams from '../hooks/useTeams';
import { PacmanLoader } from 'react-spinners';

export default function orderPointTable() {
  const { teams, error, isLoading } = useTeams();
  return (
    <>
      {isLoading && <PacmanLoader size='120px'></PacmanLoader>}
      {error && 'Something error'}
      {teams && (
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
      )}
    </>
  );
}
