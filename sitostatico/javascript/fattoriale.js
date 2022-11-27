let fattIterative = function(n) {
	let result = 1
	let i = 2
	while (i <= n) {
		result *= i
		i++
	}
	return result;
}

let fattIterative2 = function(n) {
	let result = 1
	let i = 1
	do {
		result *= i
		i++
	} while (i <= n)
	return result;
}

let fattRecursive = function(n) {
	if (n > 1) {
		return n * fattRecursive(n - 1)
	}
	return 1
}

exports.fattIter = fattIterative
exports.fattIter2 = fattIterative2
exports.fattRec = fattRecursive