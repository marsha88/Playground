import React from "react";

import { Box, Button } from "./common";
import { POMODORO, SHORT_BREAK, LONG_BREAK } from "./PomodoroTimer";

const PhaseButton = ({ active, children, ...props }) => (
  <Button style={{ fontSize: 20 }} classNames={[active ? "accent" : ""]} {...props}>
    {children}
  </Button>
);

const PhaseSelection = ({ activePhase, setActivePhase }) => {
  return (
    <Box
      style={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-around",
        width: "100%",
      }}
    >
      <PhaseButton
        active={activePhase === POMODORO}
        onClick={() => {
          setActivePhase(POMODORO);
        }}
      >
        pomodoro
      </PhaseButton>
      <PhaseButton
        active={activePhase === SHORT_BREAK}
        onClick={() => {
          setActivePhase(SHORT_BREAK);
        }}
      >
        short break
      </PhaseButton>
      <PhaseButton
        active={activePhase === LONG_BREAK}
        onClick={() => {
          setActivePhase(LONG_BREAK);
        }}
      >
        long break
      </PhaseButton>
    </Box>
  );
};

export default PhaseSelection;
