import { format, addSeconds } from "date-fns";

export const formatSecondsToMinutes = seconds => {
  return format(addSeconds(new Date(0), seconds), "mm:ss");
};
