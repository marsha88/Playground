function lineByLine() {
	const name = "linexline"
	const authors = ["ben", "jon", "clayton"]
	function getBlogInfo() {
		return name + " by: " + authors.join()
	}
	
	return getBlogInfo
}

const getBlogInfo = lineByLine()

console.log(getBlogInfo())
