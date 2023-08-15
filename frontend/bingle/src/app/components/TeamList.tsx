import { List, ListItem, ListItemAvatar, ListItemText } from '@mui/material';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import IconButton from '@mui/material/IconButton';
import Avatar from '@mui/material/Avatar';
import React, { useState } from 'react';
import useTeams from '../hooks/useTeams';
import { PacmanLoader } from 'react-spinners';

export default function TeamList() {
  const [dense, setDense] = useState(false);
  const [secondary, setSecondary] = useState(true);
  const { teams, error, isLoading } = useTeams();
  return (
    <div>
      {isLoading && <PacmanLoader size='120px'></PacmanLoader>}
      {error && 'something error'}
      {teams && (
        <List dense={dense}>
          {teams.map(({ name, colorImageUrl }) => (
            <ListItem
              secondaryAction={
                <IconButton edge='end' aria-label='Add'>
                  <AddCircleIcon fontSize='large' />
                </IconButton>
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
