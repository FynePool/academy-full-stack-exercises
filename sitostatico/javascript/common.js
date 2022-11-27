console.log("This is my first js!");

let testFor = function () {
    for (let i = 0; i < 10; i++) {
        console.log("i classic:", i)
    }

    for (let i in [1, 2, 3, 4]) console.log("i in: ", i)
    for (let i of [1, 2, 3, 4]) console.log("i of: ", i)

    let arr = new Array;
    arr = ["john", "bishop"];
    console.log("Array content: ", arr)
}

class Person {
    constructor(firstName, lastName) {
        var _firstName = firstName;
        var _lastName = lastName;
        this.getFirstName = function () {
            return _firstName;
        };
        this.getLastName = function () {
            return _lastName;
        };
        this.toString = function () {
            return _firstName + " " + _lastName;
        };
    }
}

let testPersona = function () {
    let per = new Person("Pippo", "Pluto");
    console.log("Nome persona: ", per.getFirstName());
    console.log("Cognome persona: ", per.getLastName());
}

testFor();
testPersona();