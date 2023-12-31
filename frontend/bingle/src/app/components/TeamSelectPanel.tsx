import { List, ListItem, ListItemAvatar, ListItemText } from '@mui/material';
import Avatar from '@mui/material/Avatar';
import { useCallback, useState } from 'react';
import useTeams from '../hooks/useTeams';
import { PacmanLoader } from 'react-spinners';
import CheckIcon from '@mui/icons-material/Check';
import ToggleButton from '@mui/material/ToggleButton';
import { Button } from '@mui/material';
import useUser from '../hooks/useUser';

type useButton = {
  useThisButton: Boolean;
  onClick: ((data: any) => void) | (() => void) | undefined;
};

type props = {
  useNextButton: useButton;
  useSaveButton: useButton;
};

export default function TeamSelectPanel({
  useNextButton,
  useSaveButton,
}: props) {
  const [dense, setDense] = useState(false);
  const [secondary, setSecondary] = useState(true);
  const { teams, error, isLoading } = useTeams();
  const [selectedTeams, setSelectedTeams] = useState<string[]>([]);
  const [user, setUser, isValidUser, setIsValidUser] = useUser();
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
  const handleNext = useCallback(() => {
    if (selectedTeams.length === 0) {
      // Error popup (구독할 팀을 한 개 이상 선택하세요!)
      return;
    }
    setUser((user) => {
      return { ...user, teamId: [...selectedTeams] };
    });
    if (useNextButton.onClick) {
      const onClickFunc = useNextButton.onClick as () => void;
      onClickFunc();
    }
  }, [selectedTeams]);
  return (
    <div>
      <div>
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
                      selected={selectedTeams.includes(name)}
                      onChange={handleSelected}
                      color='success'
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
      </div>
      <div>
        {useNextButton.useThisButton && (
          <Button onClick={handleNext} variant='outlined'>
            다음으로
          </Button>
        )}
        {useSaveButton.useThisButton && (
          <Button onClick={useSaveButton.onClick} variant='outlined'>
            저장하기
          </Button>
        )}
      </div>
    </div>
  );
}
