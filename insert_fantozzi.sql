-- Exercise 1

insert into actor(actor_id, first_name, last_name)
  values (500, 'Paolo', 'Villaggio'),
		 (501, 'Anna', 'Mazzamauro'),
         (502, 'Gigi', 'Reder'),
         (503, 'Giuseppe', 'Anatrelli'),
         (504, 'Liù', 'Bosisio');
         


insert into film(film_id, title, description, release_year, language_id, length)
  values (5000, 'Fantozzi',
  'Il ragionier Ugo Fantozzi, dimenticato da molti giorni nei gabinetti murati della società ItalPetrolCemeTermoTessilFarmoMetalChimica viene ritrovato grazie a una telefonata della moglie Pina che ha osato finalmente chiedere sue notizie. Da quel momento veniamo a conoscenza della sua vita familiare (ha una figlia, Mariangela, dall aspetto decisamente poco invitante), del suo segreto amore (la collega signorina Silvani) e soprattutto delle vessazioni a cui è sottoposto (e a cui talvolta si auto sottopone preventivamente) al lavoro.',
  1975, 2, 100);

insert into film_actor(film_id, actor_id)
  values (5000, 500),
         (5000, 501),
         (5000, 502),
         (5000, 503),
         (5000, 504);

insert into inventory(inventory_id, film_id, store_id)
  values (10000, 5000, 1),
         (10001, 5000, 1),
         (10002, 5000, 1),
         (10003, 5000, 2),
         (10004, 5000, 2);

-- Exercise 2

insert into city(city_id, city, country_id) values (2000, 'San Bonifacio', 49);

insert into address(address_id, address, district, city_id, postal_code, phone, location)
  values(3000, 'via dei Cosacchi, 23', 'VR', 2000, '37047', '111-222-333', ST_GeomFromText('POINT(1 1)'));

insert into customer(customer_id, store_id, first_name, last_name, 	email, address_id)
  values (4000, 1, 'Maria', 'Bianchi', 'MARIA.BIANCHI@sakilacustomer.org', 3000);
  
-- insert into rental(rental_id, inventory_id, customer_id, staff_id)
--  values (50000, 10001, 4000, 1);

insert into rental(rental_id, inventory_id, customer_id, staff_id, rental_date)
  values (50000, 10001, 4000, 1, '2015-05-23 00:00:00.000');
  
-- Cleaning

delete from rental where rental_id = 50000;

delete from customer where customer_id = 4000;

delete from address where address_id = 3000;

delete from city where city_id = 2000;

delete from film_actor where film_id = 5000;

delete from actor where actor_id in (500, 501, 502, 503, 504);

delete from inventory where film_id = 5000;

delete from film where film_id = 5000;
