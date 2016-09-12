/*

   This is a sql file with queries from chapter 1 of 'Data Analysis Using SQL and Excel'

   */



\! echo '********************************';
\! echo '\nWrong way to count NULLs';  
SELECT count(*)
FROM zipcensus
WHERE state <> NULL;


\! echo '\nRight way to count NULLs\n';  
SELECT count(*)
FROM zipcensus
WHERE state IS NOT NULL;


\! echo '\nGetting SCFs for all states';

SELECT zc.zipcode, SUBSTRING(zc.zipcode, 1, 3) as scf
FROM zipcensus zc
LIMIT 10;

\! echo '\nGetting SCFs for MN';

SELECT zc.zipcode, SUBSTRING(zc.zipcode, 1, 3) as scf
FROM zipcensus zc
WHERE state ='MN'
ORDER BY 1;

\! echo '\nCount of zipcodes*';

SELECT count(*)
FROM zipcensus;

\! echo '\nSTATES with descending no. of zip';

SELECT state, count(*)
FROM zipcensus
GROUP BY state 
ORDER BY 2 DESC;

\! echo '\nSTATES with descending no. of SCF';

SELECT state, COUNT( DISTINCT (SUBSTRING(zipcode,1,3)))
FROM zipcensus
GROUP BY state 
ORDER BY 2 DESC
LIMIT 10;

\! echo '\nJOIN orders and zipcodes';

SELECT o.orderid, zc.zipcode, zc.population
FROM orders o JOIN zipcensus zc ON (o.zipcode = zc.zipcode);

\! echo '\nHow many zipcodes have zipcodes within the same state that have a larger population - Jeevan Answer';

SELECT a.zipcode, count(b.zipcode)
FROM zipcensus a, zipcensus b  
WHERE a.population < b.population AND a.state = b.state
GROUP BY a.zipcode;

\! echo '\nHow many zipcodes have zipcodes within the same state that have a larger population - Book Answer';
SELECT a.zipcode, SUM(CASE WHEN a.population < b.population THEN 1 ELSE 0 END) as numzip
FROM zipcensus a JOIN zipcensus b ON a.state = b.state
GROUP BY a.zipcode;

/* Verification Logic
DROP IF EXISTS temp1; 
CREATE TABLE temp1 (zipcode INT, numzip INT);

INSERT INTO temp1 (
	SELECT a.zipcode, count(b.zipcode)
	FROM zipcensus a, zipcensus b  
	WHERE a.population < b.population AND a.state = b.state
	GROUP BY a.zipcode;
);

DROP IF EXISTS temp1; 
CREATE TABLE temp2 LIKE temp1;

INSERT INTO temp2 )
	SELECT a.zipcode, SUM(CASE WHEN a.population < b.population THEN 1 ELSE 0 END) as numzip
	FROM zipcensus a JOIN zipcensus b ON a.state = b.state
	GROUP BY a.zipcode
);

SELECT SUM(temp.numzip-temp2.numzip) FROM temp, temp2 WHERE temp.zipcode =temp2.zipcode;   

*/

\! echo '\nHow many orders are greater than the median rent where the customer resides';

SELECT zipcensus.zipcode, count(orderid) 
FROM orders JOIN zipcensus ON orders.zipcode=zipcensus.zipcode 
WHERE totalprice > hhumediancashrent 
GROUP BY zipcensus.zipcode;

\! echo '\nHow many orders are greater than the median rent where the customer resides - statewise';

SELECT zipcensus.state, count(orderid) 
FROM orders JOIN zipcensus ON orders.zipcode=zipcensus.zipcode 
WHERE totalprice > fammedincome 
GROUP BY zipcensus.state

\! echo '\n How many zip codes in each state have a population of
more than 10,000 and what is the total population of these?';

SELECT state, count(zipcode), sum(population)
FROM zipcensus 
WHERE population > 10000
GROUP BY state;

\! echo '\nHow many zip codes in each state have a population of more than 10,000, how many have a population of more than 1,000, and what is the total population of each of these sets?';

SELECT state, 
	SUM( CASE WHEN population > 10000 THEN 1 ELSE 0 END) as num_10000, 
    SUM( CASE WHEN population > 1000 THEN 1 ELSE 0 END) as num_1000, 
	SUM( CASE WHEN population > 10000 THEN population ELSE 0 END) as pop_10000, 
    SUM( CASE WHEN population > 1000 THEN population ELSE 0 END) as pop_1000
FROM zipcensus 
GROUP BY state;

\! echo '\nHow many zip codes in each state have a population of more than 10,000, how many have a population of more than 1,000, and what is the total population of each of these sets? - Sub Query Variant';

SELECT  zc.state, 
        SUM(is_pop_10000) as count_pop_10000,
        SUM(is_pop_1000) as count_pop_1000,
        SUM(is_pop_10000*zc.population) as pop_10000,
        SUM(is_pop_1000*zc.population) as pop_1000
FROM    (SELECT state, 
                (CASE WHEN population > 10000 THEN 1 ELSE 0 END
                ) as is_pop_10000, 
                (CASE WHEN population > 1000 THEN 1 ELSE 0 END
                ) as is_pop_1000,
                population 
        FROM zipcensus
        ) as zc
GROUP BY zc.state
ORDER BY 2 DESC
LIMIT 10;

\! echo 'How many zip codes in each state have a population density
greater than the average zip code population density in the state?';

SELECT zc.state, count(zc.zipcode)
FROM (  SELECT state, AVG(population*1000000/landareameters) as density
        FROM zipcensus
        GROUP BY state ) AS zd,
     (  SELECT state, zipcode, (population)*1000000/(landareameters) as density
        FROM zipcensus ) AS  zc
WHERE zd.state = zc.state AND zc.density > zd.density
GROUP BY zc.state
ORDER BY 2 DESC 
LIMIT 10;

\! echo 'How many zip codes in each state have a population density greater than its state\'s population density?';

SELECT zc.state, count(zc.zipcode)
FROM (  SELECT state, SUM(population)*1000000/SUM(landareameters) as density
        FROM zipcensus
        GROUP BY state ) AS zd,
     (  SELECT state, zipcode, (population)*1000000/(landareameters) as density
        FROM zipcensus ) AS  zc
WHERE zd.state = zc.state AND zc.density > zd.density
GROUP BY zc.state
ORDER BY 2 DESC
LIMIT 10;

\! echo 'list of all zip codes in states with fewer than 100 zip codes';

SELECT zipcode 
FROM zipcensus
WHERE state IN (SELECT state
                FROM zipcensus
                GROUP BY state
                HAVING count(zipcode) <100
                );

\! echo 'list of all zip codes in states with fewer than 100 zip codes - JOIN';

SELECT  zipcode 
FROM    zipcensus za 
        JOIN (
            SELECT state 
            FROM zipcensus 
            GROUP BY state 
            HAVING count(zipcode) < 100
        ) zb 
        ON (za.state=zb.state);

\! echo 'Which zip code in each state has the maximum population and what is
the population? - join using where';

SELECT za.state, za.zipcode
FROM zipcensus za, (
                    SELECT state, MAX(population) as maxpop
                    FROM zipcensus
                    GROUP BY state
                    ) zb
WHERE za.state = zb.state AND za.population = zb.maxpop LIMIT 10;


\! echo 'Which zip code in each state has the maximum population and what is
the population? - join ';

SELECT za.state, za.zipcode
FROM zipcensus za JOIN (
                    SELECT state, MAX(population) as maxpop
                    FROM zipcensus
                    GROUP BY state
                    ) zb ON (za.state = zb.state AND za.population = zb.maxpop) LIMIT 10;
                    
\! echo 'Which zip code in each state has the maximum population and what is
the population? - inner join correalted ';

SELECT za.state, za.zipcode
FROM zipcensus za
WHERE za.population IN (SELECT MAX(population) as maxpop
                        FROM zipcensus zb
                        WHERE za.state = zb.state 
                        GROUP BY state)
LIMIT 10;

\! echo 'UNION ALL EXample';

SELECT count(*) 
FROM    ((SELECT latitude as latlong
         FROM zipcensus) UNION ALL (SELECT longitude as latlong
         FROM zipcensus) ) a LIMIT 10;
         
\! echo 'What is the distribution of orders by state and how is this related to the state’s population?';

SELECT orders.state, count(orderid), zd.statepop
FROM    orders, 
        (SELECT state, SUM(population) as statepop 
         FROM zipcensus 
         GROUP BY state) zd
WHERE orders.state = zd.state
GROUP BY orders.state;

\! echo 'What is the distribution of orders among states that have 100 or more orders?';


SELECT za.state, count(za.orderid)
FROM orders za
WHERE za.state in ( SELECT state
                    FROM orders
                    GROUP BY state
                    HAVING count(orderid)>100);

\! echo 'What is the distribution of orders by state, for states that have more than 2% of the orders?';

SELECT state, count(orderid) 
FROM orders
GROUP BY state
HAVING count(orderid) > (SELECT count(orderid)*0.02 as total FROM orders);

\! echo 'What is the distribution of the number of orders in the 20 states that have the largest number of orders?';

SELECT state, count(orderid) 
FROM orders 
GROUP BY state 
ORDER BY 2 DESC
LIMIT 20;

