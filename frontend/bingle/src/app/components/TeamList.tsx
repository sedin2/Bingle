import { List, ListItem, ListItemAvatar, ListItemText } from '@mui/material';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import IconButton from '@mui/material/IconButton';
import Avatar from '@mui/material/Avatar';
import React, { useState } from 'react';

const tempTeamArray = [
  {
    name: 'T1',
    imgSrc: '',
    secondaryText: 'LCK 1황',
  },
  {
    name: 'DK',
    imgSrc: '',
    secondaryText: 'LCK 2황',
  },
  {
    name: 'GEN G',
    imgSrc: '',
    secondaryText: 'LCK 3황',
  },
];

export default function TeamList() {
  const [dense, setDense] = useState(false);
  const [secondary, setSecondary] = useState(true);
  return (
    <div>
      <List dense={dense}>
        {tempTeamArray.map(({ name, imgSrc, secondaryText }) => (
          <ListItem
            secondaryAction={
              <IconButton edge='end' aria-label='Add'>
                <AddCircleIcon fontSize='large' />
              </IconButton>
            }
          >
            <ListItemAvatar>
              <Avatar alt='temp' src={imgSrc}></Avatar>
            </ListItemAvatar>
            <ListItemText
              primary={name}
              secondary={secondary ? secondaryText : null}
            ></ListItemText>
          </ListItem>
        ))}
      </List>
    </div>
  );
}
