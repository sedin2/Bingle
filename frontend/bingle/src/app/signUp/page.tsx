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
  const [signUpStep, setSignUpStep] = useState(signUpSteps.IDLE_STEP);
  const stepString = `(${signUpStep + 1}/${Object.keys(signUpSteps).length})`;
  const setUser = useUser()[1];
  const goToNextStep = useCallback((userData: any) => {
    setUser(userData);
    setSignUpStep((prevStep) => prevStep + 1);
  }, []);
  const signUpRequest = useCallback((userData: any) => {
    fetcher('http://localhost:8080/signup', 'POST', undefined, userData);
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
    <div>
      {/* TODO : Handle react sliding when step changing */}
      <>{<header>회원가입 ${stepString}</header>}</>
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
