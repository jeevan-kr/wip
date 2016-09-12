def negate(x):
    temp = x
    retVal = 0
    if x < 0:
        d = 1
    else:
        d = -1
    #print "d is", d

    while temp != 0:
        retVal += d
        temp += d
    return retVal

def subtract(a,b):
    return a + negate(b)

def multiply(a,b):
    t_a = abs(a)
    t_b = abs(b)
    while t_b != 1:
        t_a += abs(a)
        t_b += -1
        #print t_a
    if (a < 0 and b > 0) or (a > 0 and b <0):
        t_a = negate(t_a)
    return t_a
def divide (a,b):
    if b ==0:
        return None

    absa = abs(a)
    absb = abs(b)
    prd = 0
    x = 0
    while prd + absb <= absa:
        prd += absb
        x += 1
    if (a < 0 and b > 0) or (a > 0 and b < 0):
        x = negate(x)

    return x


#print 4, negate(4)
#print -10, negate(-10)

#print "4 - 5 = ", subtract(4,5)
#print "4 - -5 = ", subtract(4,-5)
#print "-4 - -5 = ", subtract(-4,-5)


#print "4 * -5 = ", multiply(4,-5)
#print "-4 * -5 = ", multiply(-4,-5)
#print "-4 * 5 = ", multiply(-4,5)

print divide(-6,-3)