from timeit import default_timer as timer

def countStepJump(stepCount):
    count = 0
    remStep = 0
    count = hop(stepCount)
    return count

def hop(sc):
    mySteps = 0

    if sc == 0:
        return 1

    for hopStep in hops:
        if sc >= hopStep:
            mySteps += hop(sc - hopStep)
    return mySteps

hops =[1,2,3]

print hop(3)


def hopDyn(sc,mapx):
    temp = 0
    if sc == 0:
        return 1

    if sc in mapx:
        return mapx[sc]

    for hopStep in hops:
        if sc >= hopStep and sc not in mapx:
            temp += hopDyn(sc - hopStep, mapx)

    mapx[sc] = temp

    return mapx[sc]

#print hopDyn(3,{})

def fibDyn(n,mapx):

    if n == 1:
        return 1
    elif n == 2:
        return 1
    elif n in mapx:
        return mapx[n]

    mapx[n] = fibDyn (n-1,mapx) + fibDyn (n-2,mapx)

    return mapx[n]

def fib(n):

    if n ==1:
        return 1
    if n == 2:
        return 1
    print n-1, n-2
    return fib(n-1) + fib(n-2)

#1,1,2,3,5,8...
val = 5
#fibout = fibDyn(val,{})
#print "Dyn:", fibout

#fibout = fib(val)
#print "Rec:", fibout






