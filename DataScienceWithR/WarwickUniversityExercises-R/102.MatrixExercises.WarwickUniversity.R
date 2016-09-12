# Warwick University R Programming Exercises
# Source of Exercises: 
# http://www2.warwick.ac.uk/fac/sci/statistics/staff/academic-research/reed/rexercises.pdf
# Author: Jeevan Kumar R
# 10 Aug 2014
# Matrices

# 1a. 
A = matrix(c(1, 5, -2, 1, 2, -1, 3, 6, -3), nrow = 3, ncol = 3)
A %*% A %*% A

# 1b.
A[,3] = A[,2] + A[,3]
A

# 2. 
tens = rep(10,15)
B = matrix (c(tens, -tens, tens), nrow = 15, ncol = 3)
t(B) %*% B

# 3. 
matE = matrix( rep ( 0 , 36 ) , nrow = 6, ncol = 6)
matE[which( (row(matE) == col(matE) + 1) )] = 1
matE[which( (row(matE) == col(matE) - 1) )] = 1
matE

# 4. 
tmp = seq(from = 0, to = 5, by = 1)
outer ( tmp, tmp, "+" )

# 5a. 
tmp = seq(from = 0, to = 4, by = 1)
outer ( tmp, tmp, "+" )%%5

# 5b.
tmp = seq(from = 0, to = 9, by = 1)
outer ( tmp, tmp, "+" )%%10

# 5c.
tmp = seq(from = 0, to = 8, by = 1)
outer ( tmp, tmp + 0, "-" )%%9

# 6.
tmp = seq(from = 0, to = 4, by = 1)
A = (outer ( tmp, tmp + 0, "-" )%%5) +1
y = matrix(c(7, -1, -3, 5, 17), nrow = 5, ncol = 1)
x = solve(A) %*% y
x
# solution is ok, validation code below
# sum(A %*% x - y)

# 7. 
set.seed(75)
aMat <- matrix (sample(10, size = 60, replace = T), nr = 6)
aMat
# 7a.
rowSums(aMat>4)

# 7b.
tmp = rowSums(aMat==7)
tmp
which(tmp==2)
#aMat[which(tmp==2),]

colS = colSums(aMat)
outer(colS, colS, "+")
pos = which(outer(colS, colS, "+")>75)+10
pos
series = c(floor(pos/10), pos%%10)
series
len = length(series)
matrix(series, nrow = len/2, ncol = 2)

#8. 
