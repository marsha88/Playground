import React from "react";

const Button = ({ children, classNames = [], ...props }) => {
  const classNameMerge = ["block", ...classNames];
  return (
    <button className={classNameMerge.join(" ")} style={{ fontSize: 25 }} {...props}>
      {children}
    </button>
  );
};

export default Button;
