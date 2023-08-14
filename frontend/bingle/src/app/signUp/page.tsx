'use client';
import { useCallback, useMemo, useState } from 'react';
import NickNameSelectPanel from '../components/NickNameSelectPanel';
import NotificationSelectPanel from '../components/NotificationSelectPanel';
import TeamSelectPanel from '../components/TeamSelectPanel';
import useUser from '../hooks/useUser';
import { fetcher } from '../service/fetcher';

const signUpSteps = {
  IDLE_STEP: 0,
  NICKNAME_STEP: 1,
  TEAM_SELECT_STEP: 2,
  NOTIFICATION_SELECT_STEP: 3,
};
Object.freeze(signUpSteps);

// type signUpState = {
//   step: number;
//   data: Object;
//   isComplete: boolean;
// };

export default function signUp() {
  //   const [signUpState, setSignUpState] = useState<signUpState[]>([]);
  const [signUpStep, setSignUpStep] = useState(signUpSteps.NICKNAME_STEP);
  const stepString = `(${signUpStep + 1}/${Object.keys(signUpSteps).length})`;
  const [user, setUser, isValidUser, setIsValidUser] = useUser();
  const goToNextStep = useCallback((userData: any) => {
    setUser(userData);
    setSignUpStep((prevStep) => prevStep + 1);
  }, []);
  const signUpRequest = useCallback((userData: any) => {
    fetcher('http://localhost:8080/signup', 'POST', undefined, userData).then(
      (response) => {
        if (response.ok) {
          setIsValidUser(true);
          // Success Popup with redirect home button
        } else {
          // error popup, refresh signUp page (beginning from first step)
        }
      }
    );
  }, []);
  const nextButtonObject = useMemo(() => {
    if (signUpStep < Object.keys(signUpSteps).length - 1) {
      return { useThisButton: true, onClick: goToNextStep };
    } else {
      return { useThisButton: false, onClick: undefined };
    }
  }, []);
  const saveButtonObject = useMemo(() => {
    if (signUpStep < Object.keys(signUpSteps).length - 1) {
      return { useThisButton: false, onClick: undefined };
    } else {
      return { useThisButton: true, onClick: signUpRequest };
    }
  }, []);

  return (
    <div className='flex flex-col'>
      {/* TODO : Handle react sliding when step changing */}
      {/* TODO : prevent to access signUp page directly */}
      {
        <header className='font-bold text-3xl text-center'>
          회원가입 {stepString}
        </header>
      }
      {/* TODO : Checking IDLE Step is necessary
            {
                (signUpStep === signUpSteps.IDLE_STEP) &&
                
            } */}
      {signUpStep === signUpSteps.NICKNAME_STEP && (
        <NickNameSelectPanel
          useNextButton={nextButtonObject}
          useSaveButton={saveButtonObject}
        />
      )}
      {signUpStep === signUpSteps.TEAM_SELECT_STEP && (
        <TeamSelectPanel
          useNextButton={nextButtonObject}
          useSaveButton={saveButtonObject}
        />
      )}
      {signUpStep === signUpSteps.NOTIFICATION_SELECT_STEP && (
        <NotificationSelectPanel
          useNextButton={nextButtonObject}
          useSaveButton={saveButtonObject}
        />
      )}
    </div>
  );
}
