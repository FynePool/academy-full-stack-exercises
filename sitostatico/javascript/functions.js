function myfunc(a, b, c) {
	return a+b+c;
}


console.log(myfunc(2));

var helloWorld = function () {
	console.log("Hello World  function")
} 

var print = function(message) {
	console.log(message)
}

var somma = function(a, b) {
	return a + b;
}

// helloWorld = 10;

helloWorld()
print("Hello World message")
print("2 + 3 = "+somma(2, 3));

let f = (a, b) => a+b;
