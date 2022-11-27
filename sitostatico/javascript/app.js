console.info("Caricamento app.js")

let appendContent = function(element, content) {
	if (content) {
		if (content instanceof HTMLElement) {
			element.appendChild(content)
		} else {
			element.appendChild(document.createTextNode(content))
		}
	}	
}

let createDiv = function(classNames, content) {
	let result = document.createElement("div")
	if (classNames) {
		if (Array.isArray(classNames)) {
			result.className = classNames.join(" ")
		} else {
			result.className = classNames
		}
	}
	appendContent(result, content)
	return result
}

let createA = function(url, content) {
	let a = document.createElement("a")
	a.href = url
	appendContent(a, content)
	return a
}

let createHeader = function() {
	let header = document.createElement("header")
	let titleZone = createDiv("titleZone")
	let logo = createDiv("logo", createA("/","TL"))
	let title = createDiv("title", "TheLibrary - Negozio")
	let menuZone = createDiv("menuZone")
	let mainMenu = createDiv("mainMenu")
	let helpMenu = createDiv("helpMenu")
	header.appendChild(titleZone)
	header.appendChild(menuZone)
	titleZone.appendChild(logo)
	titleZone.appendChild(title)
	menuZone.appendChild(mainMenu)
	menuZone.appendChild(helpMenu)
	return header
}

let createPage = function() {
	let header = createHeader()
	let mainContent = document.querySelector("#mainContent")
	mainContent.parentElement.insertBefore(header, mainContent)
}

window.addEventListener("load", createPage)

/*
 * window.addEventListener("load", function(e) { console.info("Pagina caricata")
 * let d =document.getElementById("mainContent") let p1 =
 * document.createElement("p") p1.className="title" p1.style.color='red' let t1 =
 * document.createTextNode("Questo è stato aggiunto dinamicamente 2")
 * p1.appendChild(t1) d.appendChild(p1) setTimeout(() => { p1.style.display =
 * 'none' }, 10000) })
 */
