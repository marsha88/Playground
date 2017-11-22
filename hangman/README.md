## Idea for my solution
1.) Store a dictionary of common words
- I was able to find a large set of words separated by lengths online. I later found out that this contained duplicates,
  so if I were to redo this the first thing I would do is generate a better dictionary.

2.) Given a word with no letters guessed, guess a vowel that has not been guessed yet.

3.) If a vowel has been guessed then I generate a letter frequency for each letter that appears
    in words of the same length as the mystery word. I also filter this dictionary list out by
    generating a regular expression and matching it on each word of the same length as the mystery word.
    This way I am only testing the frequency of characters for words that the mystery word could actually be.
    I then return this character with the highest frequency to guess if it hasn't been guessed before.

## Program Setup:
#### Install Node Modules using `npm install` within the project directory.
* Node Version: v8.5.0

## Program Usage:
`node hangman <Number of games to play>`
