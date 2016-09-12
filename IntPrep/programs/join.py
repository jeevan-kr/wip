
def join (l,r):
    for item in l:
        for i in range(0,len(r)):
            if item == r[i]:
                print item,r[i]

def createDict(l):
    lDict = {}
    for item in l:
        if item in lDict.keys():
            lDict[item] += 1
        else:
            lDict[item] = 1
    return lDict

def eff_join(l,r):
    lDict = createDict(l) #O(m)
    rDict = createDict(r) #O(n)

    for item in lDict.keys(): #O(m)
        if item in rDict.keys():
            vals = rDict[item]
            while vals > 0:
                print item, item
                vals -= 1

def eff_left_outer_join(l,r):
    lDict = createDict(l) #O(n)
    rDict = createDict(r) #O(n)

    for item in lDict.keys(): #O(n)
        if item in rDict.keys():
            vals = rDict[item]
            while vals > 0:
                print item, item
                vals -= 1
        else:
            print item

def left_outer_join (l,r):
    for item in l:
        presentFlag = False
        for i in range(0,len(r)):
            if item == r[i]:
                print item,r[i]
                presentFlag = True
        if presentFlag == False:
            print item

def right_outer_join(l,r):
    left_outer_join(r,l)

def cross_join(l,r):
    for l_item in l:
        for r_item in r:
            print l_item, r_item

l = ['a','b','c','x','y','z']
r = ['d','c','a','c','a','e','c']

print "***Join***"
join(l,r)
print "***Eff Join***"
eff_join(l,r)
print "***Left Outer Join***"
left_outer_join(l,r)
print "***Eff Left Outer Join***"
eff_left_outer_join(l,r)

print "***Right Outer Join***"
right_outer_join(l,r)
#print "***Cross Join***"
#cross_join(l,r)