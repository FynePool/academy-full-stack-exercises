let nameChangeHandler = function(e) {
	let errors = document.querySelector("#errors")
	if (e.target.value.length > 20) {
		errors.textContent="La lunghezza del nome deve essere inferiore a 20"
	}
}

let clickAlertHandler = function(e) {
	alert("E' stato premuto l'elemento "+e.target)
	let inputName = document.querySelector("input[name='nome']");
	if (inputName.value.length > 20) {
		e.preventDefault()
		let errors = document.querySelector("#errors")
		errors.textContent="La lunghezza del nome deve essere inferiore a 20"
	}
	e.stopPropagation()
}

let clearForm = function(e) {
	e.preventDefault()
	let textInputs = document.querySelectorAll("form input[type='text']")
	textInputs.forEach((item) => { item.value = "" })
	let checkboxInputs = document.querySelectorAll("form input[type='checkbox']")
	checkboxInputs.forEach((item) => { item.checked = false })
//	let inputName = document.querySelector("input[name='nome']")
//	inputName.value = '123456789012345678901';
}

let sendForm = function(e) {
	e.preventDefault()
	let fd = new FormData(e.target)
	
	let XHR = new XMLHttpRequest()
	
	XHR.addEventListener("load", function(xe) {
		if (xe.target.status === 200) {
			alert(xe.target.responseText)
			console.log("Dopo la ricezione della risposta")
		} else {
			console.error(xe.target)
		}
	})

	XHR.addEventListener("error", function(xe) {
		console.error("Errore invocando il servizio 	")
	})
	
	XHR.open("POST", "http://localhost:8081/v1/echo")
	
	
	XHR.send(fd)
	
	console.log("Form inviata!")
}

window.addEventListener("load", function() {	
	let inputName = document.querySelector("input[name='nome']")
	inputName.addEventListener("change", nameChangeHandler)
//	inputName.addEventListener("blur", nameChangeHandler)
	document.querySelectorAll("input[type='text']").forEach((element) => {
		element.addEventListener("keyup", function(e) {
			let errors = document.querySelector("#errors")
			errors.textContent = e.target.value.length
		})
	});
//	inputName.addEventListener("focus", function(e) {
//		alert("Compila il campo " + e.target.name)
//	})
	let labelName = document.querySelector("label[for='nome']")
//	labelName.addEventListener("click", clickAlertHandler)
//	document.querySelector("body").addEventListener("click", clickAlertHandler)
	
	document.querySelector("input[type='submit']").addEventListener("click", clickAlertHandler)
	// document.querySelector("form").addEventListener("click", clickAlertHandler)
	
	document.querySelector("#cleaner").addEventListener("click", clearForm)
	
	document.querySelector("form").addEventListener("submit", sendForm)
})