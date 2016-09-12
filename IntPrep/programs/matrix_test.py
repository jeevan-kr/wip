def genSquareMat(n):
    outer = []
    ctr = 10
    for i in range(0,n):
        inner = []
        for j in range(0,n):
            inner.append(ctr)
            ctr += 1
        outer.append(inner)
    return outer

def printMatrixNow(a):
    for i in range(0,len(a)):
        for j in range(0,len(a[0])):
            print a[i][j],
        print

def copyMat(n):
    cop = [[0]*len(n[0]) for y in range(len(n))]
    for i in range(0, len(n)):
        for j in range(0, len(n[0])):
            cop[i][j] =  n[i][j]

    return cop
def rotMatPrac(n):
    j = 0
    for st in range(0,len(n[0])/2):
        end = len(n[0]) - st
        jcomp = len(n) - j
        #print st, end, j, jcomp
        top = n[j][st:end]
        bot = n[jcomp-1][st:end]
        rit = [ row [end-1] for row in n][j:jcomp]
        lft = [row[st] for row in n][j:jcomp]
        #print t,r,b,l
        n[j][st:end] = reversed(lft) # t <- l
        n[jcomp-1][st:end] = reversed(rit) # b <- r

        i = 0
        for k in range(j,jcomp):
            #print st,end,j,jcomp
            n[k][end-1] = top[i]
            n[k][st] = bot[i]
            i+=1
        j += 1
    return n
x = [
    [0,1,0],
    [1,0,1],
    [0,1,0]
    ]

#printMatrixNow(x)
print "xxxxx"
#printMatrixNow(copyMat(x))
x = [
    [1,2,3,4],
    [5,6,7,8],
    [9,10,11,12],
    [13,14,15,16]
]
print "*****"
#printMatrixNow(x)
print "rrrrr"

f = genSquareMat(7)
#printMatrixNow(f)
print 'xcxc'
c = rotMat(f)
#printMatrixNow(c)

f = genSquareMat(4)
printMatrixNow(f)
d = rotMatPrac(f)
print "*****"
printMatrixNow(d)

