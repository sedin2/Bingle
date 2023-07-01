import { useContext, Dispatch, SetStateAction } from 'react';
import { tokenContext } from '../context/tokenContext';

export default function useToken(): [string, Dispatch<SetStateAction<string>>] {
  const { accessToken, setAccessToken } = useContext(tokenContext);
  return [
    accessToken as string,
    setAccessToken as Dispatch<SetStateAction<string>>,
  ];
}
