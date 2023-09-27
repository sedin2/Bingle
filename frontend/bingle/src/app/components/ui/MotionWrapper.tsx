'use client';
import { AnimatePresence, motion } from 'framer-motion';

const pageEffect = {
  initial: {
    transform: `translateX(50px)`,
    opacity: 0,
    transition: `transform 0.6s ease`,
  },
  animate: {
    transform: `translateX(0px)`,
    opacity: 1,
    transition: `transform 0.6s ease`,
  },
  exit: {
    transform: `translateX(50px)`,
    opacity: 0,
    transition: `transform 0.6s ease`,
  },
};

export default function MotionWrapper({
  children,
  key,
}: {
  children: React.ReactNode;
  key: any;
}) {
  return (
    <AnimatePresence>
      <motion.div
        key={key}
        initial={pageEffect.initial}
        animate={pageEffect.animate}
        exit={pageEffect.exit}
        transition={{ duration: 0.5 }}
      >
        {children}
      </motion.div>
    </AnimatePresence>
  );
}
