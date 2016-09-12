import random

def sample(k,a):
    res = []

    for i in range(0,k):
        res.append(a[i])
        print "  ,",

    for i in range(k, len(a)):
        temp = random.randint(0,i)

        if temp < k:
            #print "replacing", res[temp], "with", a[i], "at", i
            res[temp] = a[i]
    print
    return res

ipt = []
for i in range(0,15):
    ipt.append(random.randint(0,100))

print ipt
print sample(5,ipt)
