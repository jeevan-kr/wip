def arrUnion(a,b):
    retVal = {}
    merged = a + b

    for i in range(0,len(merged)):
        retVal[merged[i]] = 1

    return sorted(retVal.keys())

a = [3,1,4,345,5,7,2,4]
b = [9,2,1,13]

def arrUnion2(a,b):
    return sorted(list(set(a + b)))

print arrUnion2(a,b)