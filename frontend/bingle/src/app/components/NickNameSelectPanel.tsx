import React from 'react';

type useButton = {
  useThisButton: Boolean;
  onClick: ((data: any) => void) | (() => void) | undefined;
};

type props = {
  useNextButton: useButton;
  useSaveButton: useButton;
};

export default function NickNameSelectPanel({
  useNextButton,
  useSaveButton,
}: props) {
  return <div></div>;
}
