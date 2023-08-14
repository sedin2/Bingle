import { useContext, Dispatch, SetStateAction } from 'react';
import { userContext, User } from '../context/UserContext';

export default function useUser(): [
  User,
  Dispatch<SetStateAction<User>>,
  Boolean,
  Dispatch<SetStateAction<Boolean>>
] {
  const { user, setUser, isValidUser, setIsValidUser } =
    useContext(userContext);
  return [
    user as User,
    setUser as Dispatch<SetStateAction<User>>,
    isValidUser as Boolean,
    setIsValidUser as Dispatch<SetStateAction<Boolean>>,
  ];
}
