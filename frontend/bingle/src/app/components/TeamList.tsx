import { List, ListItem, ListItemAvatar, ListItemText } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import { useCallback, useState } from 'react';
import useTeams from '../hooks/useTeams';
import { PacmanLoader } from 'react-spinners';
import CheckIcon from '@mui/icons-material/Check';
import ToggleButton from '@mui/material/ToggleButton';

export default function TeamList() {
  const [dense, setDense] = useState(false);
  const [secondary, setSecondary] = useState(true);
  const { teams, error, isLoading } = useTeams();
  const [selectedTeams, setSelectedTeams] = useState<string[]>([]);
  const handleSelected = useCallback(
    (event: React.MouseEvent<HTMLElement>) => {
      let tempTarget = event.target as any;
      if (tempTarget.tagName != 'BUTTON') {
        for (
          ;
          tempTarget.tagName != 'BUTTON';
          tempTarget = tempTarget.parentElement
        );
        if (tempTarget.tagName != 'BUTTON') return;
      }
      if (selectedTeams.includes(tempTarget.id)) {
        setSelectedTeams((prev) => {
          return prev.filter((team) => {
            if (team !== tempTarget.id) return team;
          });
        });
      } else {
        setSelectedTeams((prev) => {
          return [...prev, tempTarget.id];
        });
      }
    },
    [selectedTeams]
  );
  return (
    <div>
      <div>
        <h1 className='font-bold text-3xl text-center'>
          구독할 팀을 선택하세요!
        </h1>
      </div>
      {isLoading && <PacmanLoader size='120px'></PacmanLoader>}
      {error && 'something error'}
      {teams && (
        <List dense={dense}>
          {teams.map(({ name, colorImageUrl }) => (
            <ListItem
              key={name}
              secondaryAction={
                <ToggleButton
                  id={name}
                  value='check'
                  selected={
                    !!selectedTeams.find((team) => {
                      if (team === name) return true;
                    })
                  }
                  onChange={handleSelected}
                >
                  <CheckIcon />
                </ToggleButton>
              }
            >
              <ListItemAvatar>
                <Avatar alt='temp' src={colorImageUrl}></Avatar>
              </ListItemAvatar>
              <ListItemText
                primary={name}
                secondary={secondary ? 'secondary text' : null}
              ></ListItemText>
            </ListItem>
          ))}
        </List>
      )}
    </div>
  );
}
