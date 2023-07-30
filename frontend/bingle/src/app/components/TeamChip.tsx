'use client';
import { Chip } from '@mui/material';

type props = {
  setSelected: (label: string) => void;
  label: string;
  selected: boolean;
};

export default function TeamChip({ setSelected, label, selected }: props) {
  const handleClick = () => {
    setSelected(label);
  };
  return (
    <div>
      {selected && (
        <Chip
          sx={{ width: '150px' }}
          label={label}
          onClick={() => handleClick()}
          color='primary'
        />
      )}
      {!selected && (
        <Chip
          sx={{ width: '150px' }}
          label={label}
          onClick={() => handleClick()}
          variant='outlined'
        />
      )}
    </div>
  );
}
