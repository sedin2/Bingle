import { useContext } from 'react';
import { tokenContext } from '../context/tokenContext';

export default function useToken() {
  const { accessToken, setAccessToken } = useContext(tokenContext);
  return [accessToken, setAccessToken];
}
