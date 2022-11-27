let print = require('./printVariable').print

let testBaseObjects = function() {
	let o1 = {}
	let o2 = new Object()
	let o3 = Object.create(null)
	o1.i = 12
	o1.print = function() {
		console.log(this)
	}
	o1.print()
	let f = o1.print;
	f = f.bind(o2);
	f();
}

let testConstructObjects = function() {
	
	let Person = function(firstName, lastName) {
		this.firstName = firstName
		this.lastName = lastName
	}

	Person.prototype.minAge = 12
	
	// let p1 = Person("Lucio", "Benfante");
	
	let p1 = new Person("Lucio", "Benfante")
	print("p1.firstName", p1.firstName)
	print("p1.lastName", p1.lastName)
	print("p1.minAge", p1.minAge)
		
	let p2 = Object.create(Person.prototype)
	Person.call(p2, "Maria", "Rossi")
	print("p2.firstName", p2.firstName)
	print("p2.lastName", p2.lastName)
	print("p2.minAge", p2.minAge)
}

let testEs6Classes = function() {
	
	class Person {
		constructor(firstName, lastName) {
			this.firstName = firstName
			this.lastName = lastName
		}
	}
	
	let p1 = new Person("Lucio", "Benfante")
	
	print("p1", p1.firstName)
}

let testObjectLitteral = function() {
	let p1 = {
			firstName: "Lucio",
			lastName: "Benfante",
			minAge: 12,
			address: {
				street: "via Verdi, 23",
				cap: 12034,
				city: "Rozzano"
			}
	}
	print("p1.firstName", p1.firstName)
	print("p1.lastName", p1.lastName)
	print("p1.minAge", p1.minAge)
	for(let i in p1) {
		print("i", i)
	}

	for(let i in p1) {
		print("p1["+i+"]", p1[i])
	}
	
}

let testAccessibility = function() {

	let Person = function(firstName, lastName) {
		var _firstName = firstName
		var _lastName = lastName
		this.getFirstName = function() {
			return _firstName
		}
		this.getLastName = function() {
			return _lastName
		}
		this.toString = function() {
			return _firstName + " " + _lastName
		}
	}

	Person.prototype.minAge = 12
	
	let p1 = new Person("Lucio", "Benfante")
	
	p1._firstName = "pippo"
	print("p1.getFirstName()", p1.getFirstName())
	print("p1", p1)
	
	if (p1 instanceof Person) {
		console.log("sì, è un Person")
	}

}

let testAccessibilityWithClass = function() {

	// class Person {
	// 	constructor(firstName, lastName) {
	// 		const _firstName = firstName
	// 		const _lastName = lastName
	// 		this.getFirstName = function() {
	// 			return _firstName
	// 		}
	// 		this.getLastName = function() {
	// 			return _lastName
	// 		}
	// 		this.toString = function() {
	// 			return _firstName + " " + _lastName
	// 		}	
	// 	}
	// }
	let Person = function(firstName, lastName) {
		var _firstName = firstName
		var _lastName = lastName
		this.getFirstName = function() {
			return _firstName
		}
		this.getLastName = function() {
			return _lastName
		}
		this.toString = function() {
			return _firstName + " " + _lastName
		}
	}

	Person.prototype.minAge = 12
	
	let p1 = new Person("Lucio", "Benfante")
	
	p1._firstName = "pippo"
	print("p1.getFirstName()", p1.getFirstName())
	print("p1", p1)
	
	if (p1 instanceof Person) {
		console.log("sì, è un Person")
	}

}



// testBaseObjects()
//testConstructObjects()
//testObjectLitteral()
//testEs6Classes()
testAccessibility()

let o = {
	a: 1,
	b: 2,
	sum: function() {
		return this.a + this.b;
	}
}

o.sum();

o.c = 3;

