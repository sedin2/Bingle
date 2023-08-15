import { Button, List, ListItem, ListItemText } from '@mui/material';
import { useCallback, useState } from 'react';
import useUser from '../hooks/useUser';
import FormControlLabel from '@mui/material/FormControlLabel';
import Switch from '@mui/material/Switch';

type useButton = {
  useThisButton: Boolean;
  onClick: ((data: any) => void) | (() => void) | undefined;
};

type props = {
  useNextButton: useButton;
  useSaveButton: useButton;
};

type notifications = {
  isScheduleNotification: Boolean;
  isLiveMatchLink: Boolean;
  isMatchHighlight: Boolean;
  isMatchWeeklyReport: Boolean;
  isSpoiler: Boolean;
};

const notificationOptions = [
  {
    english: 'isScheduleNotification',
    korean: '경기 전 일정 알림',
  },
  {
    english: 'isLiveMatchLink',
    korean: '경기 라이브 영상',
  },
  {
    english: 'isMatchHighlight',
    korean: '경기 하이라이트',
  },
  {
    english: 'isMatchWeeklyReport',
    korean: '경기 주간 리포트',
  },
  {
    english: 'isSpoiler',
    korean: '* 스포일러 방지',
  },
];
const initialNotifications = [false, false, false, false, false];

export default function NotificationSelectPanel({
  useNextButton,
  useSaveButton,
}: props) {
  const [notifications, setNotifications] =
    useState<Boolean[]>(initialNotifications);
  const [user, setUser, isValidUser, setIsValidUser] = useUser();
  const [dense, setDense] = useState(false);
  const [secondary, setSecondary] = useState(false);
  const handleSave = useCallback(() => {
    setUser((user) => {
      return {
        ...user,
        notifications: {
          isScheduleNotification: notifications[0],
          isLiveMatchLink: notifications[1],
          isMatchHighlight: notifications[2],
          isMatchWeeklyReport: notifications[3],
          isSpoiler: notifications[4],
        },
      };
    });
    // Success popup ('회원가입이 완료되었습니다 축하드려요 {닉네임}님 홈으로 가기 버튼')
  }, [notifications]);
  return (
    <div>
      <h1 className='font-bold text-3xl text-center'>알림톡 정보 선택</h1>
      {
        <List dense={dense}>
          {notificationOptions.map(({ english, korean }, index) => (
            <ListItem
              key={english}
              secondaryAction={
                <FormControlLabel
                  sx={{
                    display: 'block',
                  }}
                  control={
                    <Switch
                      checked={notifications[index]}
                      onChange={() =>
                        setNotifications((prev) => {
                          return prev.map((option, idx) => {
                            if (idx === index) {
                              return !option;
                            } else return option;
                          });
                        })
                      }
                      name='loading'
                      color='primary'
                    />
                  }
                />
              }
            >
              <ListItemText
                primary={korean}
                secondary={secondary ? 'secondary text' : null}
              ></ListItemText>
            </ListItem>
          ))}
        </List>
      }
      {useNextButton.useThisButton && (
        <Button onClick={useNextButton.onClick} variant='outlined'>
          다음으로
        </Button>
      )}
      {useSaveButton.useThisButton && (
        <Button onClick={handleSave} variant='outlined'>
          회원가입 완료
        </Button>
      )}
    </div>
  );
}
