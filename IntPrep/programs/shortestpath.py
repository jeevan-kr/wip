def allPairsShortestPath(a):
    '''
    Floyd Warshall Algorithm
    :param a:
    :return:
    '''
    for i in range(0,len(a)):
        for j in range(0,len(a[0])):
            for k in range(0, len(a[0])):
                t = a[i][k] + a[k][j]
                if a[i][j] > t:
                    a[i][j] = t
    return a

def printMat(x):
    for i in range(len(x)):
        for j in range(len(x[0])):
            print x[i][j],
        print


y = [[0,1,1],[2,0,4],[1,3,0]]
printMat(y)
print "*****"
printMat(allPairsShortestPath(y))