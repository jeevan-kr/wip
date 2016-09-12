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
\! echo 'Same as above but any state with less than 500 orders needs to be bucketed as OTHER';

SELECT (CASE WHEN counts >= 500 THEN state ELSE 'OTHER' END) as state_new, SUM(counts) as newCount
FROM (SELECT state, count(orderid) as counts
      FROM orders
      GROUP BY state) a
GROUP BY (CASE WHEN counts >=500 THEN state ELSE 'OTHER' END)
ORDER BY 2 DESC;

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

\! echo 'Display the state that has the 5th largest no. of orders';

SELECT state
FROM (SELECT a.state, COUNT(DISTINCT (b.state)) as rank
            FROM (SELECT state, count(orderid) as num_ord
                  FROM orders
                  GROUP BY state
                  ) as a,
                  (SELECT state, count(orderid) as num_ord
                  FROM orders
                  GROUP BY state
                  ) as b
            WHERE a.num_ord <= b.num_ord
            GROUP BY a.state ) c
WHERE rank = 5;
GROUP BY state;

\! echo 'What is the number of zip codes that have a given number of orders?';

SELECT ordersperzip, COUNT(*) as occur_ordersperzip, MIN(zipcode), MAX(zipcode)
FROM (  SELECT o.zipcode, COUNT(*) as ordersperzip
        FROM orders o
        GROUP BY o.zipcode
     ) bystate
GROUP BY ordersperzip
ORDER BY 1;

\! echo 'What is the number of order lines where the product occurs once (overall), twice, and so on?';

SELECT  ordersperprod, count(*) AS freq_ordersperprod, MAX(productid), MIN(productid)
FROM (  SELECT productid, count(orderid) AS ordersperprod
        FROM orderline
        GROUP BY productid) o
GROUP BY ordersperprod;

\! echo 'What is the proportion of products that account for half of all order lines? - no solution; LAG is not a function in mysql';


\! echo 'How many different values does NUMUNITS take on?';

SELECT COUNT(DISTINCT numunits) 
FROM orderline;
\! echo 'Histogram of prices by no. of digits in the price';

SELECT numdigits, count(totalprice), MIN(totalprice), MAX(totalprice)
FROM (SELECT (CASE WHEN totalprice >= 1                   THEN FLOOR(1+LOG10(totalprice))
             WHEN totalprice > -1 AND totalprice <1 THEN 0
             WHEN totalprice <=-1                   THEN -1*FLOOR(1+ LOG10(-1*totalprice)) 
             END) as numdigits, totalprice
      FROM orders) a
GROUP BY numdigits;

\! echo 'mode of price';

SELECT totalprice, count(totalprice)
FROM orders
GROUP BY totalprice;

\! echo 'How many different order lines within an order contain the same product?';

SELECT cnt_ord_prod, count(*) 
FROM (  SELECT orderid, productid, count(*) as cnt_ord_prod
        FROM orderline
        GROUP BY orderid, productid
     ) a
GROUP BY cnt_ord_prod
ORDER BY 1 DESC 
LIMIT 10;
    
\! echo 'Distribution of AE payers';

SELECT  state, 
        count(*),  
        SUM(CASE WHEN paymenttype='AE' THEN 1 ELSE 0 END),
        AVG(CASE WHEN paymenttype='AE' THEN 1.0 ELSE 0.0 END)
FROM orders 
GROUP BY state 
HAVING count(*) >= 100
ORDER BY 2 DESC LIMIT 10;

\! echo 'summarizing a colum - just copy past from the book';

SELECT  COUNT(*) as numvalues,
        MAX(freqnull) as freqnull,
        MIN(minval) as minval,
        SUM(CASE WHEN state = minval THEN freq ELSE 0 END) as numminvals,
        MAX(maxval) as maxval,
        SUM(CASE WHEN state = maxval THEN freq ELSE 0 END) as nummaxvals,
        MIN(CASE WHEN freq = maxfreq THEN state END) as mode,
        SUM(CASE WHEN freq = maxfreq THEN 1 ELSE 0 END) as nummodes,
        MAX(maxfreq) as modefreq,
        MIN(CASE WHEN freq = minfreq THEN state END) as antimode,
        SUM(CASE WHEN freq = minfreq THEN 1 ELSE 0 END) as numantimodes,
        MAX(minfreq) as antimodefreq,
        SUM(CASE WHEN freq = 1 THEN freq ELSE 0 END) as numuniques
FROM   (SELECT state, COUNT(*) as freq
        FROM orders
        GROUP BY state
        ) osum CROSS JOIN
        (SELECT MIN(freq) as minfreq, MAX(freq) as maxfreq, MIN(state) as minval, MAX(state) as maxval, SUM(CASE WHEN state IS NULL THEN freq ELSE 0 END) as freqnull
         FROM ( SELECT state, COUNT(*) as freq
                FROM orders
                GROUP BY state) osum) summary;
                
\! echo 'get all column names in a table';
SELECT table_name, column_name, ordinal_position
FROM information_schema.columns
WHERE table_name = 'orders';
