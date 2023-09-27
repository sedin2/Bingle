import { Button, Modal, Box, Typography } from '@mui/material';
import { useCallback, useState } from 'react';

type props = {
  buttonText: string;
  modalTitle: string;
  modalDescription: string;
  children: React.ReactNode;
};

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  height: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function ModalButton({
  buttonText,
  modalTitle,
  modalDescription,
  children,
}: props) {
  const [open, setOpen] = useState(false);
  const handleOpen = useCallback(() => {
    setOpen(true);
  }, []);
  const handleClose = useCallback(() => {
    setOpen(false);
  }, []);
  return (
    <div>
      <Button onClick={handleOpen}>{buttonText}</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby='child-modal-title'
        aria-describedby='child-modal-description'
      >
        <Box sx={{ ...style }}>
          <Typography id='modal-modal-title' variant='h6' component='h2'>
            {modalTitle}
          </Typography>
          <Typography id='modal-modal-description' sx={{ mt: 2 }}>
            {modalDescription}
          </Typography>
          {children}
        </Box>
      </Modal>
    </div>
  );
}
