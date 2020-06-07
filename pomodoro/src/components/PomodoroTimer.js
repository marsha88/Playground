import React, { useState, useEffect } from "react";
import useInterval from "use-interval";
import useSound from "use-sound";

import { Box, Button, RadioInput } from "./common";
import PhaseSelection from "./PhaseSelection";
import Timer from "./Timer";
import { formatSecondsToMinutes } from "../utils";

import bellSfx from "../resources/mp3/bell.mp3";

export const POMODORO = "POMODORO";
export const SHORT_BREAK = "SHORT_BREAK";
export const LONG_BREAK = "LONG_BREAK";

/** Time intervals for each phase in seconds */
const TIME_INTERVALS = {
  [SHORT_BREAK]: 5 * 60,
  [LONG_BREAK]: 20 * 60,
  [POMODORO]: 25 * 60,
};

const divisibleByFour = x => x !== 0 && x % 4 === 0;

const PomodoroTimer = ({ ...props }) => {
  const [remainingTime, setRemainingTime] = useState(TIME_INTERVALS[POMODORO]);
  const [phase, setPhase] = useState(POMODORO);
  const [pomodoros, setPomodoros] = useState(0);
  const [active, setActive] = useState(false);
  const [phaseComplete, setPhaseComplete] = useState(false);
  const [settings, setSettings] = useState({
    soundNotifications: true,
    autoCycle: false,
  });
  const [ring] = useSound(bellSfx);

  const updatePhase = phase => {
    setActive(false);
    setPhase(phase);
    setRemainingTime(TIME_INTERVALS[phase]);
  };

  useInterval(() => setRemainingTime(t => t - 1), active && 1000);

  useEffect(() => {
    document.title = formatSecondsToMinutes(remainingTime);

    if (remainingTime === 0) {
      setActive(false);
      setPhaseComplete(true);
      if (settings.soundNotifications) {
        ring();
      }
    }
  }, [remainingTime, ring, settings]);

  useEffect(() => {
    const handlePhaseComplete = phase => {
      if (phase === POMODORO) {
        const breakPhase = divisibleByFour(pomodoros + 1) ? LONG_BREAK : SHORT_BREAK;
        updatePhase(breakPhase);
        setPomodoros(p => p + 1);
      } else {
        updatePhase(POMODORO);
      }

      if (settings.autoCycle) {
        setActive(true);
      }
      setPhaseComplete(false);
    };

    /** Added delay for smoother transition between phases */
    const timeout = setTimeout(() => {
      if (phaseComplete) {
        handlePhaseComplete(phase);
      }
    }, 2000);

    return () => clearTimeout(timeout);
  }, [phaseComplete, phase, pomodoros, settings]);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
      {...props}
    >
      <PhaseSelection activePhase={phase} setActivePhase={updatePhase} />
      <Box style={{ width: "100%", fontSize: 20 }}>
        <Box style={{ marginBottom: 20 }}>pomodoros completed: {pomodoros}</Box>

        <Timer seconds={remainingTime} />

        <div
          style={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-around",
            fontSize: 25,
          }}
        >
          <Button onClick={() => setActive(true)}>start</Button>
          <Button onClick={() => setActive(false)}>stop</Button>
          <Button
            onClick={() => {
              /** Resetting is equivalent to updating to the current phase */
              updatePhase(phase);
            }}
          >
            reset
          </Button>
        </div>

        <Box style={{ marginTop: 20, fontSize: 20 }}>
          settings
          <hr />
          <RadioInput
            checked={settings.soundNotifications}
            onToggle={() =>
              setSettings({
                ...settings,
                soundNotifications: !settings.soundNotifications,
              })
            }
          >
            sound notifications
          </RadioInput>
          <RadioInput
            checked={settings.autoCycle}
            onToggle={() =>
              setSettings({
                ...settings,
                autoCycle: !settings.autoCycle,
              })
            }
          >
            auto cycle
          </RadioInput>
        </Box>
      </Box>
    </div>
  );
};

export default PomodoroTimer;
