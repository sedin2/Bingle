'use client';
import React, {
  createContext,
  Dispatch,
  SetStateAction,
  useState,
} from 'react';

interface ItokenContext {
  accessToken?: string;
  setAccessToken?: Dispatch<SetStateAction<string>>;
}

export const tokenContext = createContext<ItokenContext>({});

export default function TokenContext({
  children,
}: {
  children: React.ReactNode;
}) {
  const [accessToken, setAccessToken] = useState('');
  const value = {
    accessToken,
    setAccessToken,
  };
  return (
    <tokenContext.Provider value={value}>{children}</tokenContext.Provider>
  );
}
