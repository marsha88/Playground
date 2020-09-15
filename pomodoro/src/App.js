import React from "react";

import { Heading } from "./components/common";
import PomodoroTimer from "./components/PomodoroTimer";

const App = () => {
  return (
    <>
      <div
        style={{
          display: "flex",
          flex: 1,
          flexDirection: "column",
          justifyContent: "flex-start",
          alignItems: "center",
          paddingTop: "10vh",
        }}
      >
        <Heading>pomodoro timer</Heading>
        <PomodoroTimer />
      </div>
    </>
  );
};

export default App;
