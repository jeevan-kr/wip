ipt = [  [1, 0, 1, 1, 1, 1, 0, 1, 1, 1],
         [1, 0, 1, 0, 1, 1, 1, 0, 1, 1],
         [1, 1, 1, 0, 1, 1, 0, 1, 0, 1],
         [0, 0, 0, 0, 1, 0, 0, 0, 0, 1],
         [1, 1, 1, 0, 1, 1, 1, 0, 1, 0],
         [1, 0, 1, 1, 1, 1, 0, 1, 0, 0],
         [1, 0, 0, 0, 0, 0, 0, 0, 0, 1],



         [1, 0, 1, 1, 1, 1, 0, 1, 1, 1],
         [1, 1, 0, 0, 0, 0, 1, 0, 0, 1]]

class Node:
    def __init__(self, xCord, yCord,data):
        self.x = xCord
        self.y = yCord
        self.dat = data
        self.children = []

def printMat(n):
    for i in range(0, len(n)):
        for j in range(0, len(n[0])):
            print n[i][j],
        print

def convert_Mat_NodeDict(a):

    nodes = [[None]*len(a[0]) for y in range(len(a))]
    #b = [[0]*len(a[0])]*len(a)
    printMat (a)

    for i in range(0,len(a)):
        for j in range(0,len(a[0])):
            #print i, j
            #b[i][j] = a[i][j]
            if a[i][j] == 1:
                nodes[i][j] = Node(i,j,a[i][j])

    for i in range(0, len(a)):
        for j in range(0, len(a[0])):
            if a[i][j] == 1:
                if j + 1 < len(a[0]) and a[i][j+1] == 1:
                    #print i,j, " -> ", i, (j+1)
                    nodes[i][j].children.append(nodes[i][j+1])

                if  i + 1 < len(a) and a[i+1][j] == 1:
                    #print i, j, " -> ", (i + 1), (j + 0)
                    nodes[i][j].children.append(nodes[i + 1][j])
                '''
                if j -1 > 0 and a[i][j - 1] == 1:
                    nodes[i][j].children.append(nodes[i][j - 1])

                if i - 1 > 0  and a[i - 1][j] == 1:
                    nodes[i][j].children.append(nodes[i - 1][j])
                '''
    return nodes

def pathBetween(x1,y1,x2,y2,nodes):
    que = []
    visit = [[None] * len(nodes[0]) for y in range(len(nodes))]
    startNode = nodes[x1][y1]
    for i in range(0,len(startNode.children)):
        que.append(startNode.children[i])
    contFlag = True
    #print "root", len(que)
    while len(que) > 0:
        print len(que)
        ele = que.pop(0)

        if nodes[x2][y2]!= None and ele.x == nodes[x2][y2].x and ele.y == nodes[x2][y2].y :
            #print ele.x, ele.y, nodes[x2][y2].x, nodes[x2][y2].y
            return True
        else:
            for i in range(0, len(ele.children)):
                que.append(ele.children[i])
                # print "adding", str(ele.children[i].x), str(ele.children[i].x)

    return False
x =[[1,1,0]
    ,[0,0,0]
    ,[0,0,0]]
print len(ipt[0]),len(ipt)

nodesx = convert_Mat_NodeDict(ipt)

print pathBetween(0,0,8,0,nodesx)
