const dictionary = require('./dictionary.js');

const vowels = ['E', 'A', 'O', 'I', 'U', 'Y'];

const generateRegex = (word) => {
    let regex = "";
    let count = 0;
    for(i = 0; i < word.length; i++){
      if(word[i] === '_') {
        regex += "\\w{1}"
      }
      else {
        regex += word[i];
      }
    }
    return new RegExp(regex);
};

const guessRandom = (guesses) => {
  let alphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ".split('');
  return alphabet.filter((l) => !guesses.includes(l))[Math.floor(Math.random()*alphabet.length)];
};

// returns the letter than had the max number of occurences
const maxOf = (obj, guesses) => {
  let max = -Infinity;
  let maxLetter = guessRandom(guesses);
  Object.keys(obj).forEach(function(prop) {
    if(obj[prop] > max) {
      max = obj[prop];
      maxLetter = prop;
    }
  });
  return maxLetter;
};

// returns the letter of highest frequency for a given word length
const calculateFreq = (word, guesses) => {
  let alpha = {};
  const desired_expr = generateRegex(word);

  if(dictionary[word.length]) {
    const possible_words = dictionary[word.length].filter((w) => desired_expr.test(w.toUpperCase()));
    possible_words.forEach((pword) => {
      pword.toUpperCase().split('').forEach((pletter) => {
        if(!guesses.includes(pletter)){
          if(alpha[pletter]) {
            alpha[pletter] += 1;
          }
          else {
            alpha[pletter] = 1;
          }
        }
      });
    });

    return maxOf(alpha, guesses);
  }

  return guessRandom(guesses);  //returns random letter that hasn't been guessed.
};

module.exports = (word, guesses) => {
  if(!word.split('').find((letter) => (letter >= "A" && letter <= "Z"))) {
    const guess = vowels.find((v) => !guesses.includes(v));
    if(!guess) {
      return calculateFreq(word, guesses);
    }
    else {
      return guess;
    }
  }
  else {
    return calculateFreq(word, guesses);
  }
};
