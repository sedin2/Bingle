'use client';
import { Button, TextField } from '@mui/material';
import { useCallback, useState } from 'react';
import useUser from '../hooks/useUser';
import { fetcher } from '../service/fetcher';
import CheckIcon from '@mui/icons-material/Check';

type useButton = {
  useThisButton: Boolean;
  onClick: ((data: any) => void) | (() => void) | undefined;
};

type props = {
  useNextButton: useButton;
  useSaveButton: useButton;
};

const isValidNickName = (nickName: string) => {
  return nickName.length >= 2 && nickName.length <= 10;
};

export default function NickNameSelectPanel({
  useNextButton,
  useSaveButton,
}: props) {
  const [user, setUser, isValidUser, setIsValidUser] = useUser();
  const [nickNameText, setNickNameText] = useState<string>('');
  const [isNickNameChecked, setIsNickNameChecked] = useState<Boolean>(false);
  const handleChange = useCallback(
    (event: React.ChangeEvent<HTMLInputElement>) => {
      setNickNameText(event.target.value);
    },
    []
  );
  const repeatCheck = useCallback(() => {
    if (isValidNickName(nickNameText)) {
      fetcher(
        'http://localhost:8080/nickname/check',
        'POST',
        undefined,
        nickNameText
      ).then((response) => {
        if (response.ok) {
          setIsNickNameChecked(true);
        } else {
        }
      });
    } else {
      // Error popup (올바르지 않은 닉네임입니다.)
    }
  }, []);
  const handleNext = useCallback(() => {
    if (!isValidNickName(nickNameText) || !isNickNameChecked) {
      // Error popup (닉네임이 올바르지 않거나 중복체크 되지 않았습니다.)
      return;
    }
    console.log(nickNameText);
    setUser((user) => {
      return { ...user, nickname: nickNameText };
    });
    if (useNextButton.onClick) {
      const onClickFunc = useNextButton.onClick as () => void;
      onClickFunc();
    }
  }, [nickNameText]);
  return (
    <div className='mt-10 text-center'>
      <h1 className='text-3xl font-bold'>닉네임 설정 🥰</h1>
      <div className='p-3'>
        <span className='font-bold text-2xl'>2자 이상 10자 미만</span>
        <span className=''> 닉네임을 입력해주세요! 😄</span>
      </div>
      <span className='pt-20 mt-20 pr-3'>
        {isValidNickName(nickNameText) ? (
          <TextField
            label='nickname'
            value={nickNameText}
            onChange={handleChange}
            color='success'
          ></TextField>
        ) : (
          <TextField
            error
            label='nickname'
            helperText='2자 이상 10자 미만으로 입력해 주세요!'
            value={nickNameText}
            onChange={handleChange}
          ></TextField>
        )}
      </span>
      <span className='m-auto'>
        {!isNickNameChecked && (
          <Button onClick={repeatCheck} variant='outlined'>
            중복체크
          </Button>
        )}
        {isNickNameChecked && <CheckIcon />}
      </span>
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
