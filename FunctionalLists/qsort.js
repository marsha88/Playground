const qsort = (list) => {
	if(list.length <= 1) {
		return list;
	}
	else {
		const pivot = list.shift();	
		return list.filter((x) => x < pivot).push(pivot).push(list.filter((x) => x >= pivot));
	}
};

let test = [5,4,3,2,1];
console.log(qsort(test));
