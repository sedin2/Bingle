'use client';
import React, {
  createContext,
  Dispatch,
  SetStateAction,
  useState,
} from 'react';

export type User = {
  nickname?: string;
  teams?: string[];
  notifications?: {
    isScheduleNotification: Boolean;
    isLiveMatchLink: Boolean;
    isMatchHighlight: Boolean;
    isMatchWeeklyReport: Boolean;
    isSpoiler: Boolean;
  };
};

interface IUserContext {
  user?: User;
  setUser?: Dispatch<SetStateAction<User>>;
  isValidUser?: Boolean;
  setIsValidUser?: Dispatch<SetStateAction<Boolean>>;
}

export const userContext = createContext<IUserContext>({});

export default function UserContext({
  children,
}: {
  children: React.ReactNode;
}) {
  const [user, setUser] = useState<User>({});
  const [isValidUser, setIsValidUser] = useState<Boolean>(false);
  const value = {
    user,
    setUser,
    isValidUser,
    setIsValidUser,
  };
  return <userContext.Provider value={value}>{children}</userContext.Provider>;
}
