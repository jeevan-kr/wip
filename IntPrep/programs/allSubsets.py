
def quickAllSubsets(a):
    limit = pow(2,len(a))

    for i in range(0,limit):
        bRep = str(format(i,'0'+str(len(a)) +'b'))
        for j in range(0, len(a)):
            if bRep[j] == '1':
                print a[j],
        print


x = ['a','b','c','d']

y = ['a','b','c']

#print allSubsets(x)
quickAllSubsets(x)