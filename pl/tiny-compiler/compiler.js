const tokenizer = require("./tokenizer");
const parser = require("./parser");
const codeGenerator = require("./code-generator");

const compiler = input => {
  const tokens = tokenizer(input);
  const ast = parser(tokens);
  const output = codeGenerator(ast);

  return output;
};
