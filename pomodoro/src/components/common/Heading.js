import React from "react";

const Heading = ({ children }) => (
  <h1
    style={{
      fontFamily: "serif",
      fontSize: 50,
      fontWeight: "normal",
      padding: 0,
      margin: 0,
    }}
  >
    {children}
    <hr />
  </h1>
);

export default Heading;
