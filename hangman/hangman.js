const request = require('request');
const whilst = require('async/whilst');
const guessGenerator = require('./guessGenerator.js');

const API_ENDPOINT = "http://gallows.hulu.com/play?code=marsha88@purdue.edu";

let num_requested = 0;
if(process.argv.length !== 3 || !parseInt(process.argv[2])){
  console.log("Usage: node hangman <Number of games>");
  process.exit();
}
else {
  num_requested = parseInt(process.argv[2]);
}

let guesses = []; // contains previously guessed letters. resets for each game
let count = 0;
whilst(
  () => { return count < num_requested; },
  (next_game) => {
    request(API_ENDPOINT, function(err, resp, body){
        if(!err) {
          let {status, token, remaining_guesses, state} = JSON.parse(body);
          let current_word = 0;
          whilst(
              () => { return status === 'ALIVE'; },
              (continue_game) => {
                  let word = state.split(' ')[current_word];
                  let charToGuess = guessGenerator(word, guesses);
                  guesses.push(charToGuess);
                  const GUESS_ENDPOINT = `${API_ENDPOINT}&token=${token}&guess=${charToGuess}`
                  request(GUESS_ENDPOINT, function(err, resp, body) {
                      status = JSON.parse(body).status;
                      state = JSON.parse(body).state;
                      console.log(body);
                      if(!JSON.parse(body).state.split(' ')[current_word].includes('_')) {
                        current_word += 1;
                      }
                      continue_game();
                  });
              },
              (err, n) => {
                if(!err) {
                  count += 1;
                  next_game();
                }
                else {
                  console.log("Reveived Error: ", err);
                }
              }
          );
        }
        else {
          console.log("Received Error: ", err);
        }
      });
  },
  (err, n) => {
      if(!err) {
        guesses = [];
        console.log("Thanks for Playing!");
      }
      else {
        console.log("Received Error: ", err)
      }
  }
);
