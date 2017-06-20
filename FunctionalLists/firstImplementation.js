const emptyList = null;
const isEmpty = function(list){
	return list === emptyList;
};

function prepend(head, tail){
	return function(command){
		if( command === 'head' ){
			return head;
		}
		else if( command === 'tail' ){
			return tail;
		}
		return null;
	}
}

function head(list){
	if( !isEmpty(list)){
		return list('head');
	}
	return emptyList;
}

function tail(list){
	if( !isEmpty(list) ){
		return list('tail');
	}
	return emptyList;
}

function map(fn, list){
	if( isEmpty(list) ){
		return emptyList;
	}
	else{
		return prepend(fn(head(list)), map(fn, tail(list)));
	}
}

function filter(fn, list){
	if( !isEmpty(list) ){
		if( fn(head(list)) ){
			return prepend(head(list), filter(fn, tail(list)));
		}
		return filter(fn, tail(list));
	}
	return emptyList;
}

const printList = function(list){
	if( !isEmpty(list) ){
		console.log(head(list));
		printList(tail(list));
	}
};

let myList = prepend( 1, prepend( 2, prepend( 3 , prepend(201, emptyList))));
let myListTimesTwo = map((elm)=>(elm * 2), myList);
let filteredList = filter((elm)=>(elm > 200), myList);
printList(myListTimesTwo);
