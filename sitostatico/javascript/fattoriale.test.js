const {fattIter, fattIter2, fattRec} = require('./fattoriale')

test('Iterative fact of 4 equals 24', () => {
	  expect(fattIter(4)).toBe(24)
	})

test('Iterative fact of 0 equals 1', () => {
	  expect(fattIter(0)).toBe(1)
	})

test('Iterative2 fact of 4 equals 24', () => {
	  expect(fattIter2(4)).toBe(24)
	})

test('Iterative2 fact of 0 equals 1', () => {
	  expect(fattIter2(0)).toBe(1)
	})
	
test('Ricorsive fact of 4 equals 24', () => {
	  expect(fattRec(4)).toBe(24)
	})
	
test('Ricorsive fact of 0 equals 1', () => {
	  expect(fattRec(0)).toBe(1)
	})
