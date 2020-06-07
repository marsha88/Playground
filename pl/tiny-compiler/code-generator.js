const codeGenerator = node => {
  switch (node.type) {
    case "Program":
      return node.body.map(codeGenerator).join("\n");

    case "CallExpression":
      return `${node.name}(${node.params.map(codeGenerator).join(", ")})`;

    case "NumberLiteral":
      return parseInt(node.value);

    case "StringLiteral":
      return `"${node.value}"`;

    default:
      throw new TypeError(node.type);
  }
};

module.exports = codeGenerator;
