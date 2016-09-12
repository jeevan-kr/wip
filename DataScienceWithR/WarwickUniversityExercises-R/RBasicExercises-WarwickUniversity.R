# Warwick University R Programming Exercises
# http://www2.warwick.ac.uk/fac/sci/statistics/staff/academic-research/reed/rexercises.pdf
# Author: Jeevan Kumar R

# 1. Create vectors
# 1a. (1,2,3....,20)
c(1:20);

# 1b. (20,19,18....,1)
c(20:1)

# 1c. (1,2,3....19, 20, 19, 18, ..., 1)
c(1:20,19:1)


# 1d. (4, 6, 3) and assign it to the name tmp.
inputVector = c(4,6,3)
inputVector

# 1e. (4, 6, 3, 4, 6, 3, : : : , 4, 6, 3) where there are 10 occurrences of 4.
rep(inputVector,10)


# 1f. (4,6, 3, 4, 6, 3, : : : ; 4, 6, 3, 4) where there are 11 occurrences of 4, 
# 10 occurrences of 6 and 10 occurrences of 3.

c(rep(inputVector,10), 4)

# 1g. (4, 4, : : : ; 4, 6, 6, : : : ; 6, 3, 3, : : :  3) 
# where there are 10 occurrences of 4, 20 occurrences of 6 and 30 occurrences of 3.
c(rep( 4 , times = 10), rep(6, times = 10), rep(3,times = 30))

# More elegent solution below
inputVector = c(4,6,3)
countVector = c(10,10,30)
c(rep(inputVector, countVector))

# 2. Create a vector of the values of exp(X)*cos(x) at x = 3, 3.1, 3.2, ... , 6.
x = seq(from = 3.1, to = 6, by = 0.1)
exp(x)*cos(x)


# 3a. Create the following vectors: (0.1^3* 0.2^1,  0.1^6*0.2^4,,,,, 0.1^36* 0.2^34)
x = seq(from = 3, to = 36, by = 3);
y = x-2;
c((0.1^x) * (0.2^y))


# 3b. (2, 2^2 / 2, 2^3 / 3, .... 2^25 / 25)
# re state Q - (2^1 / 1, 2^2 / 2, 2^3 / 3, .... 2^25 / 25)
# Answer 1
tmp = c((2^(1:25) / (1:25)))
tmp

# Answer 2
x = 1:25;
tmp = c((2^(x) / (x)));
tmp

# 4a. Calculate the following: Summation of i = 10 to 100 of i^3 + 4 * i ^2
i = 10:100;
tmpVector = c(i^3 + 4 * i^2)
sum(tmpVector)

# 4b. Calculate the following: Summation of i = 1 to 25 of 2^i / i + 3^i/ i^2
i = 1:25;
tmpVector = c((2^i)/i + (3^i)/(i^2))
sum(tmpVector)

# 5. Use the function paste to create the following character vectors of length 30:
# 5a. ("label 1", "label 2", ....., "label 30").
x = 1:30;
c(paste("label", x, sep = " "))

# 5b. ("fn1", "fn2", ....., "fn30").
x = 1:30;
c(paste("fn", x, sep = ""));

# 6. Execute the following lines which create two vectors of random integers which are 
# chosen with replacement from the integers 0, 1, : : : , 999. Both vectors have length 250.
# set.seed(50)
# xVec <- sample(0:999, 250, replace=T)
# yVec <- sample(0:999, 250, replace=T)
# Suppose x = (x1; x2; : : : ; xn) denotes the vector xVec 
# and y = (y1; y2; : : : ; yn) denotes the vector yVec.
set.seed(50)
xVec = sample(0:999, 250, replace=T)
yVec = sample(0:999, 250, replace=T)

xVec
yVec

# 6a. Create the vector (y2 - x1,...., yn - xn???1).
index = 2:250
tmpVector = c(yVec[index] - xVec[index-1])
tmpVector

# stated answer
# c(yVec[-1] - xVec[-length(xVec)])

# verification that stated answer and the above is the same
# sum(c(yVec[-1] - xVec[-length(xVec)]) - tmpVector) 


# 6b. Create the vector( sin(y1)/cos(x2), ...sin(yn???1)/cos(xn))
index = 2:250
c(sin(yVec[index-1])/cos(xVec[index]))

# stated answer
# tmp = sin(yVec[-length(yVec)]) / cos(xVec[-1])
# sum(tmpVector - tmp);

# 6c. Create the vector (x1 + 2x2 ???? x3; x2 + 22x3 ???? ; : : : ; xn???2 + 2xn???1 ???? xn).
index = 3:250
tmpVector = c(xVec[index -2] + 2 * xVec[index -1] - xVec[index] )
tmpVector

# stated answer
# xVec[-c(249,250)] + 2*xVec[-c(1,250)]-xVec[-c(1,2)]
# tmp = xVec[-c(249,250)] + 2*xVec[-c(1,250)]-xVec[-c(1,2)]
# sum(tmpVector -tmp)

# 6d. exp(-x )...
index = 1:249
tmpVector = c(exp(-xVec[index+1])/(xVec[index]+10))
tmpVector
#  sum(exp(-xVec[-1])/(xVec[-length(xVec)]+10))
# need to understand how this sort of index matters 

# 7a.This question uses the vectors xVec and yVec created in the previous question 
# and the functions sort, order, mean, sqrt, sum and abs.
# (a) Pick out the values in yVec which are > 600.
tmpVector = yVec[yVec > 600]
tmpVector

# (b) What are the index positions in yVec of the values which are > 600?
yVec
tmpVector = which(yVec > 600)
tmpVector

# (c) What are the values in xVec which correspond to the values in yVec which are > 600? 
# (By correspond, we mean at the same index positions.)
xVec
yVec
tmpVector = which(yVec > 600)
result = xVec[tmpVector]
result

# 7d. Create the vector ( jx1 ???? ?xj1=2; jx2 ???? ?xj1=2; : : : ; jxn ???? ?xj1=2) where ?x denotes the mean of the vector
# x = (x1; x2; : : : ; xn).
xMean = mean(xVec);
sdVector = abs(xVec-xMean)^(1/2)
sdVector

# 7e. (e) How many values in yVec are within 200 of the maximum value of the terms in yVec?
length(yVec[yVec > max(yVec)-200])
# stated answer
# sum(yVec>max(yVec)-200 )

# 7f. How many numbers in xVec are divisible by 2? (Note that the modulo operator is denoted %%.)
length(xVec[xVec%%2==0])
# stated answer
# sum( xVec%%2==0)

# 7g. Sort the numbers in the vector xVec in the order of increasing values in yVec.
yVec
which(yVec==sort(yVec))
# incorrect Solution
# xVec[order(yVec)]

# 7h. Pick out the elements in yVec at index positions 1; 4; 7; 10; 13; : : : :
index = seq(from = 1, to = 250, by = 3)
yVec[index]
# stated answer
# yVec[c(T,F,F)]

# 8. use cumprod
vals = 1:39
index = seq(2,38,b=2)
retVal = cumprod(vals[index]/vals[index+1])
sum(retVal) + 1

# stated answer
# 1+sum(cumprod(seq(2,38,b=2)/seq(3,39,b=2)))
