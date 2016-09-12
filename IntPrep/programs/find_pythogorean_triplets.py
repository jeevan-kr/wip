import random
a = [1,2,3,4,5]

def safeCopy (a):
    retVal = []
    for item in a:
        retVal.append(item)
    return retVal

def bf_hasTriplets(a):
    """
    This method checks if the array has Pythogorean Triplets
    :param a: list
    :return: boolean
    """
    retVal = False
    a = safeCopy(a)
    for i in range(0, len(a)):
        a[i] = a[i] * a[i]

    for i in range(0,len(a)):
        for j in range(0, len(a)):
            for k in range(0, len(a)):
                if a[i] == a[j] + a[k]:
                    print a[i], " = ", a[j], " + ", a[k]
                    retVal = True

    return retVal

def hasTriplets(a):
    retVal = False
    a = safeCopy(a)

    a.sort()
    # print a

    for i in range(0,len(a)):
        a[i] = a[i] * a[i]
    # print a

    for i in range(len(a)-1, 0, -1):
        l = 0
        r = i-1

        while l < r:
            if a[l] + a[r] == a[i]:
                print a[i], " = ", a[l], " + ", a[r]
                retVal = True

            if a[l] + a[r] < a[i]:
                l += 1
            else:
                r -= 1

    return retVal


random.shuffle(a)

print a
print bf_hasTriplets(a)
print a
print hasTriplets(a)