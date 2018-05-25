/* functional conditionals */
const btrue  = (t, f) => t
const bfalse = (t, f) => f
const band   = (b1, b2) => b1(b2, bfalse)
const bor    = (b1, b2) => b1(btrue, b2)
const ifelse = (b, t, e) => b(t, e)
//const bwhile = (b, e1, e2) => ifelse(b, bwhile(, e2)


/* example programs */
const p = ifelse(bor(btrue, bfalse), 5, "clayton")
console.log("p = ", p);

const p1 = ifelse(bor(bfalse, bfalse), 5, "clayton")
console.log("p1 = ", p1);
