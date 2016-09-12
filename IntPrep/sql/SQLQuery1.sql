-- Popular stories by month, year
-- Stories {storyid, userid, parent_story_id, create_date}
-- Likes/Loves/Wow/Sad/Haha {storyid, userid, date}
-- Friends {f_userid, t_userid}
-- requests {from, to, time}
-- accepted {acceptor_id, requestor_id, time}

--

CREATE TABLE TestDB.dbo.emp 
(	id INT, 
	name varchar(50),
	man_id INT,
	sal INT
);

INSERT INTO TestDB.dbo.emp VALUES 
(1,'Jeevan', NULL, 5000)
,(2,'Shah', 1, 6000)
,(3,'Ali', 2, 9000);

SELECT * FROM TestDB.dbo.emp;

DROP TABLE #t1

SELECT *, DENSE_RANK() OVER (ORDER BY sal DESC) AS sal_rank, RANK() OVER (ORDER BY sal DESC) AS sal_rank2
INTO #t1
FROM TestDB.dbo.emp;

SELECT * FROM #t1

SELECT id, name, man_id, sal
FROM #t1
WHERE sal_rank = 2;

--Given a custom table (id, ...), an order table (id, order_date, custom_fk, ...) 
-- and an order item table (id, order_fk, ...), 
--write a SQL query statement to find out all customers 
--who haven't placed any order in the past six months

SELECT customer_fk, MAX(order_date) AS latest_ord_date
INTO #LatestOrd
FROM order_tab

SELECT A.id AS order_id
		, A.customer_fk
		, A.order_date
		, (DATEDIFF(day, TODAY(), A.order_date) * 1.0 / 7.0 / 26.0) AS latest_ord_bi_ann

INTO #t2
FROM order_tab AS A JOIN #LatestOrd AS B ON (A.customer_fk == B.customer_fk AND A.order_date = B.latest_ord_date);

SELECT *
FROM #t2
WHERE latest_ord_bi_ann > 0;


--
--If in a relation there are multiple duplicate rows . 
--Your task is to delete one duplicate row. 

--a1 a2 
--1 3 
--1 3 
--2 4 
--3 5 
--3 5 
--3 5 
--after deletion 
---a1 a2 
--1 3 
--2 4 
--3 5 
--3 5 

create table A (a1 int,a2 int) 
insert into A values(1,3),(1,3),(2,4),(3,5),(3,5),(3,5); 
DROP TABLE #t5
SELECT *,ROW_NUMBER() OVER(PARTITION BY A1 ORDER BY A2 DESC) AS TAB_ROW 
INTO #t5
FROM A

DELETE FROM #t5 WHERE TAB_ROW>=2

SELECT * FROM #t5
