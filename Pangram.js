
const isPangram = (counts) => {
	for prop in counts {
		if(counts[prop] < 1) {
			return false;
		}
	}
	return true;
};

const sp_rec = (text, dictionary, dictionaryCount, lo, hi) => {

	while(!isPangram(dict_count) && hi < text.length) {
		dict_count[dict[hi]] += 1;
		hi += 1;	
	}
	if(!isPangram(dict_count)) {
		return -1;
	}
	dictCount[dict[lo]] -= 1;
	return Math.min(hi - lo, sp_rec(text, dict, dict_count, lo + 1, hi);	
};

const smallestPangram = (text, dict) => {
	return sp_rec(text, dict, {}, 0, 0);
};


const dictionary = ['abcde'].split('');

console.log(smallestPangram("abdddeeabcde", dictionary));

