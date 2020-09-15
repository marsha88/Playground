import React from "react";

const Box = ({ children, classNames = [], ...props }) => {
  const classNameMerge = ["block", "fixed", ...classNames];
  return (
    <div className={classNameMerge.join(" ")} {...props}>
      {children}
    </div>
  );
};

export default Box;
