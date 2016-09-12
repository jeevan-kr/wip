def liss(a, seq_up, map):
    print a,seq_up,map

    if a == None or len(a) == 1:
        #print 'j', seq
        map.append(seq_up)
        return
    for i in range(0, len(a)):
        if seq_up is None or len(seq_up) == 0:
            t_up = list(seq_up)
            t_up.append(a[i])

            liss(a[i+1:], t_up, map)

        else:
            if a[i] > seq_up[len(seq_up)-1]:
                t_up = list(seq_up)
                t_up.append(a[i])
                liss(a[i+1:], t_up, map)



def printMat(a, l):
    for row in a:
        if len(row) == l:
            print row


def findMaxLen(a):
    max = -1
    for i in range(0,len(a)):
        if len(a[i]) > max:
            max = len(a[i])

    return max

x = []
liss([1,-1,-3,2,-7,3,4,-8,5,1],[], x)

printMat(x, findMaxLen(x))