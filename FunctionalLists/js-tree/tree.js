const isNull = (node) => {
	node((value, left, right) => {
		return (value === null && left === null && right === null);
	});
};

const nullNode = () => {
	return (selector) => {
		selector(null, null, null);
	};	
};

const tree = (value, left, right) => {
	return (selector) => {
		selector(value, left, right);
	};	
};

const left = (tree) => {
	return tree((value, left, right) => {
		return left;
	});
};

const right = (tree) => {
	return tree((value, left, right) => {
		return right;
	});
};

const nodeVal = (tree) => {
	return tree((value, left, right) => {
		return value;
	});
};

const printTree = tree => {
	if(!isNull(tree)){
		tree((value, left, right) => {
			printTree(left);
			printTree(right);
			console.log(value);
		});		
	}
};



const testTree = tree(5, tree(3, nullNode, nullNode), tree(8, nullNode, nullNode));

printTree(testTree);

