const compose = (...funcs) => {
  if (funcs.length === 0) {
    return arg => arg;
  }

  if (funcs.length === 1) {
    return funcs[0];
  }
 // folds left to right 
  return funcs.reduce((ac, cur) => (comp) => ac(cur(comp)));
};

/* example */
const withTheme = comp => 
	(props) => comp({theme: 'dark', ...props});

const withMobileProp = comp => 
	(props) => comp({mobile: true, ...props});

const withValueProp = comp => 
	(props) => comp({value: 78, ...props});


const MyComponent = ({ mobile, theme, value }) => {
  console.log(`Mobile = ${mobile}, Theme = ${theme}, Value = ${value}`);
};

const EnhancedComponent = compose(
  withTheme,
  withMobileProp,
  withValueProp
)(MyComponent);

EnhancedComponent();

