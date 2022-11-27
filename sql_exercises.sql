-- List of movies rented in certain year in certain store [TO USE WITH JAVA]
SELECT distinct f.film_id, f.title, f.release_year, f.length
FROM film as f JOIN inventory as i ON i.film_id = f.film_id
			   JOIN rental as r ON r.inventory_id = i.inventory_id
			   JOIN payment as p ON p.rental_id = r.rental_id
               join staff as st on st.staff_id = p.staff_id
               join store as sto on sto.store_id = st.store_id
where sto.store_id = 2 AND f.release_year = '2006';

-- List of cities where customers spend more than 100 counting for each city ho many clients (395)
SELECT cy.city AS City, cy.city_id AS CityID, SUM(p.amount) AS Revenue
FROM payment AS p JOIN customer AS c ON c.customer_id = p.customer_id 
				  JOIN address AS a ON a.address_id = c.address_id
                  JOIN city AS cy ON cy.city_id = a.city_id
WHERE 100 < ( SELECT SUM(p2.amount)
			  FROM payment as p2
              WHERE p2.customer_id = c.customer_id )
GROUP BY cy.city_id, cy.city
ORDER BY Revenue DESC;

-- Highest gross movie ( TELEGRAPH VOYAGE 231,73 )
SELECT f.film_id, f.title, SUM(p.amount)
FROM film as f JOIN inventory as i ON i.film_id = f.film_id
			   JOIN rental as r ON r.inventory_id = i.inventory_id
			   JOIN payment as p ON p.rental_id = r.rental_id
GROUP BY f.film_id, f.title
HAVING SUM(p.amount) = ( SELECT MAX(Amounts.SUMS)
						 FROM ( SELECT SUM(p2.amount) as SUMS
								FROM inventory as i2 JOIN rental as r2 ON r2.inventory_id = i2.inventory_id
													 JOIN payment as p2 ON p2.rental_id = r2.rental_id
								GROUP BY i2.film_id) AS Amounts);
                         
-- Total spent from JESSIE BANKS
SELECT c.customer_id, c.last_name, c.first_name, SUM(p.amount) AS Spending
FROM customer AS c JOIN payment AS p ON c.customer_id = p.customer_id
WHERE c.first_name = 'JESSIE' AND c.last_name = 'BANKS';

-- Films with box office greater than 200 ordered in DESC (6)
SELECT f.title, SUM(p.amount) AS Spending
FROM payment AS p JOIN rental AS r ON r.rental_id = p.rental_id
				  JOIN inventory AS i ON r.inventory_id = i.inventory_id
                  JOIN film AS f ON f.film_id = i.film_id
GROUP BY f.title
HAVING SUM(p.amount) > 200
ORDER BY SUM(p.amount) DESC;

SELECT *
FROM film AS f
WHERE EXISTS (SELECT SUM(p.amount) 
			  FROM payment AS p JOIN rental AS r ON r.rental_id = p.rental_id
								JOIN inventory AS i ON i.inventory_id = r.inventory_id
			  WHERE i.film_id = f.film_id
			  HAVING SUM(p.amount) > 200
              ORDER BY SUM(p.amount) DESC);

-- Customers with spending greater than 100 (395)
SELECT c.customer_id, c.last_name, c.first_name, SUM(p.amount) AS Spending
FROM customer AS c JOIN payment AS p ON c.customer_id = p.customer_id
GROUP BY c.customer_id
HAVING SUM(p.amount) > 100;

SELECT * 
FROM customer c
WHERE 100 < ( SELECT SUM(amount) 
			  FROM payment p
			  WHERE p.customer_id = c.customer_id );

-- Total revenue of Woodridge stores ( 33927,04 | 33726,77 | 30414,99 )
SELECT st.store_id AS Store, SUM(p.amount) AS Revenue
FROM payment AS p JOIN staff AS st ON st.staff_id = p.staff_id 
				  JOIN store AS s ON s.store_id = st.store_id 
                  JOIN address AS a ON s.address_id = a.address_id
                  JOIN city AS cy ON cy.city_id = a.city_id
WHERE cy.city = 'Woodridge'
GROUP BY st.store_id;

-- Stores in Woodridge (only one with ID: 2)
SELECT * 
FROM store AS s JOIN address AS a ON s.address_id = a.address_id
				JOIN city AS cy ON cy.city_id = a.city_id
WHERE cy.city = 'Woodridge';

-- Total amount of purchases made by JESSIE BANKS (91,74)
SELECT SUM(p.amount)
FROM payment AS p JOIN customer AS c ON c.customer_id = p.customer_id
WHERE c.first_name = 'JESSIE' AND c.last_name = 'BANKS';

-- Amount of movies rented by JESSIE BANKS (26)
SELECT COUNT(*)
FROM rental AS r JOIN customer AS c ON c.customer_id=r.customer_id
WHERE c.first_name = 'JESSIE' AND c.last_name = 'BANKS';

-- Amount of movies with JOHNNY LOLLOBRIGIDA (29)
SELECT DISTINCT COUNT(*) 
FROM film AS f JOIN film_actor AS fa ON f.film_id=fa.film_id
WHERE fa.actor_id IN (	SELECT a.actor_id 
						FROM actor AS a 
                        WHERE a.first_name = 'JOHNNY' AND a.last_name = 'LOLLOBRIGIDA');
                        
-- Amount of movies in sakila DB (1000)
SELECT COUNT(*) AS amount FROM film;

-- Rentals that overshoot film.rental_duration (7452)
SELECT DATEDIFF(r.return_date, r.rental_date) - f.rental_duration AS overshoot, c.last_name, c.first_name, f.title
FROM customer AS c  JOIN rental AS r ON c.customer_id=r.customer_id 
                    JOIN inventory AS i ON i.inventory_id=r.inventory_id 
                    JOIN film AS f ON f.film_id=i.film_id
WHERE ( DATEDIFF(r.return_date, r.rental_date) > f.rental_duration ) OR ( r.return_date IS NULL AND DATEDIFF(NOW(), r.rental_date) > f.rental_duration )
ORDER BY overshoot DESC, c.last_name;

-- Movies longer than 2 hours (457)
SELECT * FROM film WHERE length>120;

-- Customer with same first name (16)
SELECT * 
FROM customer AS c1
WHERE c1.first_name IN (SELECT c2.first_name 
						FROM customer AS c2 
						WHERE c2.customer_id != c1.customer_id)
ORDER BY c1.last_name;

-- Movies with SPENCER DEPP using view (24)
SELECT * FROM film_list WHERE actors LIKE '%SPENCER DEPP%';

-- Movies with SPENCER DEPP (24)
SELECT *
FROM film AS f JOIN film_actor AS fa ON f.film_id=fa.film_id
WHERE fa.actor_id IN (	SELECT a.actor_id 
						FROM actor AS a 
                        WHERE a.first_name = 'SPENCER' AND a.last_name = 'DEPP');