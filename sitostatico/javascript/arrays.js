let print = require('./printVariable').print

let testArrays = function(a) {
	let arr = [ "Primo", a, "Terzo" ];
	print("arr[0]", arr[0])
	arr[0] = "Primo modificato"
	print("arr[0]", arr[0])
	print("arr[3]", arr[3])
	print("arr.length", arr.length)
	arr.push("Quarto")
	print("arr", arr)
	arr[4] = "Quinto"
	print("arr", arr)
	arr[10] = "Noooo"
	print("arr", arr)
	print("arr.length", arr.length)
	if (arr instanceof Array) {
		console.log("sì, è un Array")
	}
	if (Array.isArray(arr)) {
		console.log("sì, è un Array")
	}
	arr['pippo'] = "Ancora"
	print("arr", arr)
	print("arr['pippo']", arr.pippo)
	if (Array.isArray(arr)) {
		console.log("sì, è un Array")
	}
	arr.forEach((item, i, a) => {
		print("item "+i, item)
		print("a", a)
	})
	arr.forEach(function(item, i, a) {
		print("item "+i, item)
		print("a", a)
	})
	for(let i in arr) {
		print("i", i)
	}
	for(let v of arr) {
		print("v", v)
	}
	for(let i in arr) {
		print("arr["+i+"]", arr[i])
	}
	
	print("arr.pop()", arr.pop())
	print("arr", arr)
	
	print("arr.shift()", arr.shift())
	print("arr", arr)

	print("arr.unshift('Nuovo primo')", arr.unshift('Nuovo Primo'))
	print("arr", arr)
	
	print("arr.slice(1,3)", arr.slice(1,3))
	print("arr", arr)
	
	arr.splice(2, 1, "Nuovo1", "Nuovo2")
	print("arr", arr)

	arr.splice(0, 0, "Nuovo primo")
	print("arr", arr)
	
	print("arr.join()", arr.join())
	
	let a1 = [1, 2, 3, 4, 5]
	let a2 = a1.map((v) => {
		return v + 1
	})
	print("a1", a1)
	print("a2", a2)
	print("tutti sqrt", a1.map(Math.sqrt))
	
	print("somma di tutti", a1.reduce((acc, v) => {
		return acc + v;
	}))
}

testArrays(10, 20 , 40)