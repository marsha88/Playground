import React from "react";

import { Box } from "./common";
import { formatSecondsToMinutes } from "../utils";

const Timer = ({ seconds }) => {
  const formattedTime = formatSecondsToMinutes(seconds);

  return (
    <Box
      style={{
        fontSize: 60,
        fontFamily: "serif",
        display: "flex",
        justifyContent: "center",
      }}
    >
      {formattedTime}
    </Box>
  );
};

export default Timer;
