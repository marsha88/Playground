import React from "react";

const RadioInput = ({ checked, onToggle, children }) => {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "space-between",
        paddingRight: 10,
      }}
    >
      <label>{children}</label>

      <div
        onClick={onToggle}
        style={{
          border: 3,
          borderStyle: "solid",
          borderRadius: 3,
          borderColor: "#222",
          width: 10,
          height: 10,
          marginLeft: 10,
          padding: 1,
          backgroundColor: checked ? "#00ae86" : "",
        }}
      ></div>
    </div>
  );
};

export default RadioInput;
