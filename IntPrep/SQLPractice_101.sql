CREATE TABLE Employees (EmpName VARCHAR(20), Salary INT);

INSERT INTO Employees (EmpName, Salary) VALUES ('Dave', 120), ('Bill', 210), ('Nancy', 140), ('Tami', 220),('Jenny', 230);
INSERT INTO Employees (EmpName, Salary) VALUES ('Idol', 210), ('Steph', 210);
INSERT INTO Employees (EmpName, Salary) VALUES ('Dexter', 140);
INSERT INTO Employees (EmpName, Salary) VALUES ('Tom', 225);

SELECT * 
FROM Employees
ORDER BY 2 DESC;

SELECT COUNT(Salary) AS SalCount
INTO EmpSals
FROM Employees;

SELECT * FROM EmpSals;

--Selecting nth highest value
--n=3 

--DROP TABLE EmpRanks;
SELECT	A.EmpName, 
		A.Salary, 
		DENSE_RANK() OVER (ORDER BY Salary DESC) AS SalaryRank, 
		ROW_NUMBER() OVER (ORDER BY Salary DESC) AS RowNum
INTO EmpRanks
FROM Employees AS A

SELECT	B.EmpName, 
		B.Salary
FROM	Employees AS B 
		JOIN (SELECT Top 1 Salary FROM EmpRanks WHERE SalaryRank = 3) AS A ON (A.Salary = B.Salary)

--Selecting median
--Count no. of values
SELECT COUNT(Salary) AS SalCount 
INTO EmpSalCount
FROM Employees;

--Determine if it is even or odd
--DROP TABLE OddEven;
SELECT Top 1 (CASE WHEN ((SELECT SalCount FROM EmpSalCount)%2=1) THEN 1 ELSE 0 END ) AS Odd
INTO OddEven
FROM Employees;

--Take the average depending on if the count of values is odd / even
SELECT AVG(Salary)
FROM EmpRanks 
WHERE RowNum = (SELECT SalCount FROM EmpSalCount)/2
		OR RowNum = (SELECT SalCount FROM EmpSalCount)/2 + (SELECT Top 1 Odd FROM OddEven);


--Selecting Histogram
SELECT Salary AS Salary, COUNT(EmpName) AS EmpCount
INTO Histo
FROM Employees
GROUP BY Salary;

--Selecting Mode
SELECT Salary
FROM Histo
WHERE EmpCount = (SELECT MAX(EmpCount) FROM Histo)

--Show Duplicate values
SELECT Salary FROM Histo WHERE EmpCount > 1;

--Union vs. Union All
--Union is distinct, and Union All is not distinct. 

--JOINS
--------
--Inner Join - Records common to both tables
--LEFT Outer Join - All records from left table with rows that have join condition satified
--RIGHT Outer Join - Opposite of LEFT OUTER JOINS
--Cross-Join - For each row in left with every row in right table
--Full Join - Left outer join 
CREATE TABLE Customers (Id INT, Name VARCHAR(50), ReferredBy INT)
INSERT INTO Customers VALUES (1, 'John', NULL), (2,'Jane',NULL), (3, 'Anne', 2), (4, 'Eric', NULL), (5, 'Pat', 1), (6, 'Alice', 2);
SELECT * FROM Customers;
--Id	Name			ReferredBy
--1	John Doe		NULL
--2	Jane Smith		NULL
--3	Anne Jenkins		2
--4	Eric Branford		NULL
--5	Pat Richards		1
--6	Alice Barnes		2


SELECT Name FROM Customers WHERE ReferredBy <> 2;

--Although there are 4 customers not referred by Jane Smith (including Jane Smith herself), 
--the query will only return one: Pat Richards. All the customers who were referred by nobody 
--at all (and therefore have NULL in their ReferredBy column) don’t show up. 
--But certainly those customers weren’t referred by Jane Smith, and certainly NULL is not equal to 2, 
--so why didn’t they show up?

--SQL Server uses three-valued logic, which can be troublesome for programmers accustomed to 
--the more satisfying two-valued logic (TRUE or FALSE) most programming languages use. 
--In most languages, if you were presented with two predicates: ReferredBy = 2 and ReferredBy <> 2, 
--you would expect one of them to be true and one of them to be false, given the same value of ReferredBy. 
--In SQL Server, however, if ReferredBy is NULL, neither of them are true and neither of them are false. 
--Anything compared to NULL evaluates to the third value in three-valued logic: UNKNOWN.

--The query should be written:

SELECT Name 
FROM Customers 
WHERE ReferredBy IS NULL OR ReferredBy <> 2

--Tables
--
CREATE TABLE Invoice (id INT, BillingDate DATE, CustomerId INT)

SELECT A.id, A.BillingDate, B.Name AS CustomerName, C.Name AS ReferrerName
FROM Invoice AS A 
	 JOIN Customers AS B ON (A.CustomerId = B.id)
	 JOIN Customers AS C ON (B.ReferredBy = C.id)
ORDER BY 2 ;

--Date Difference
DECLARE @startdate datetime2 = '2007-05-05 12:10:09.3312722';
DECLARE @enddate datetime2 = '2007-05-04 12:10:09.3312722'; 
SELECT DATEDIFF(day, @startdate, @enddate);

--Date Add
SELECT DATEADD(month, 1, '2006-08-30');
SELECT DATEADD(month, 1, '2006-08-31');
SELECT DATEADD(day, 30, '2006-08-31');
SELECT DATEADD(day, 31, '2006-08-31');

--Write a query to add 2 where Nmbr is 0 and add 3 where Nmbr is 1.
UPDATE TBL 
SET Nmbr = 	CASE WHEN Nmbr > 0 THEN 
					Nmbr+3 
				ELSE Nmbr+2 
		 	END;

