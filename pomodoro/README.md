# Pomodoro Timer 🍅

> Author: Clayton Marshall (https://claytn.dev)

## Build Instructions

I'll provide instructions on building the project on your local machine, but if you just want to test out the demo, you can try it out at the link below!

https://pomodoro.claytn.now.sh/

### Building Locally:

Versions Required (aka: "The versions I used"):

```
yarn - v1.16.0
npm - v6.14.4
node - v13.5.0
```

From inside the project directory:

`$ yarn # Pull in dependencies`

`$ yarn start # Serves project on localhost:3000`

For quicker testing, you can update the `TIME_INTERVALS` inside `src/components/PomodoroTimer.js` so you don't have to wait a full 25 minute pomodoro cycle 🙂

## User Stories

### Provided User Stories

- As a User, I can start a timer for a pomodoro session
- As a User, I should know how much time is left in my session
- As a User, I should be notified when my session ends, even if the tab isn’t active
- As a User, I should see how many pomodoros I’ve done
- As a User, I should be prompted to take the appropriate break time

### Additional User Stories

- As a User, I should be able to start and stop the active timer
- As a User, I should be able to reset the timer for the current phase
- As a User, I should be able to see what phase I'm currently in (pomodoro, short break, long break)
- As a User, I should be able to manually select the active phase
- As a User, I should be able to toggle sound notifications
- As a User, I should be able to toggle auto-cycling through phases
- As a User, I should be able to see the remaining time in the tab title
