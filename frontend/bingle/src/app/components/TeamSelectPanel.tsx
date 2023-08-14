import { Button } from '@mui/material';
import TeamList from './TeamList';

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
  return (
    <div>
      <div>
        <TeamList></TeamList>
      </div>
      <div>
        {useNextButton.useThisButton && (
          <Button onClick={useNextButton.onClick} variant='outlined'>
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
