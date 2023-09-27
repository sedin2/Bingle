import { Button, List, ListItem, ListItemText } from '@mui/material';
import { useCallback, useState } from 'react';
import useUser from '../hooks/useUser';
import FormControlLabel from '@mui/material/FormControlLabel';
import Switch from '@mui/material/Switch';
import ModalButton from './ui/ModalButton';
import Link from 'next/link';

type useButton = {
  useThisButton: Boolean;
  onClick: ((data: any) => void) | (() => void) | undefined;
};

type props = {
  useNextButton: useButton;
  useSaveButton: useButton;
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
  const modalDescription = `${user.nickname}님 가입 축하드려요 :D`;
  const handleSave = useCallback(
    (signUpRequest) => {
      const newUser: User = {
        ...user,
        notifications: {
          isScheduleNotification: notifications[0],
          isLiveMatchLink: notifications[1],
          isMatchHighlight: notifications[2],
          isMatchWeeklyReport: notifications[3],
          isSpoiler: notifications[4],
        },
      };
      setUser((user) => newUser);
      signUpRequest(newUser);
      // Success popup ('회원가입이 완료되었습니다 축하드려요 {닉네임}님 홈으로 가기 버튼')
    },
    [notifications]
  );
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
        <ModalButton
          buttonText='회원가입 완료'
          modalTitle='회원가입 완료!'
          modalDescription={modalDescription}
        >
          <Link
            className='bg-lime-600 text-bold text-black text-3xl text-center'
            href='/'
            onClick={() => handleSave(useSaveButton.onClick)}
          >
            홈으로!
          </Link>
        </ModalButton>
      )}
    </div>
  );
}
