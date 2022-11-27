let print = require('./printVariable').print

let testVariables = function() {
	var a = 10 + b + c
	// print("d", d) // No, d not defined
	b = 3
	print("b", b)
	{
		// print("d", d) // No, d not defined
		let  d = 34
		var b = 11
		const e = 65
		print("b", b)
		print("d", d)
		print("e", e)
		// e = 66 // No, è consts
		// const e = 67 // No, già definita
		{
			// print("d", d) // No, d not defined
			print("b", b)
			let d = 56
			var b = 12
			const e = 66
			print("d", d)
			print("b", b)
			print("e", e)
		}
		print("d", d)
		print("b", b)
		print("e", e)
	}
	// print("d", d) // No, d not defined
	print("b", b)
	var c = 12
	print("a", a)
	print("b", b)
	print("c", c)
	print("f", f)
} 

var f = 23
print("f", f)
// print("b", b) // No, b not defined
testVariables()