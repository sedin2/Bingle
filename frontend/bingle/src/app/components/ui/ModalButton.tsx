import { Button, Modal } from '@mui/material';
import { useCallback, useState } from 'react';

type props = {
  buttonText: string;
  children: React.ReactNode;
};

export default function ModalButton({ buttonText, children }: props) {
  const [open, setOpen] = useState(false);
  const handleOpen = useCallback(() => {
    setOpen(true);
  }, []);
  return (
    <div>
      <Button onClick={handleOpen}>{buttonText}</Button>
      <Modal open={open}>{children}</Modal>
    </div>
  );
}
