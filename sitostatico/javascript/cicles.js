let print = require('./printVariable').print

let testFor = function() {
	for (let i = 0; i < 10; i++) {
		print("i", i)
	}
}


testFor()