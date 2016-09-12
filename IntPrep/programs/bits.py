def getBits(n):
    lim = pow(2,n)
    opt = {}
    for i in range(0,lim):
        temp = bin(i)
        #print temp[temp.index('b')+ 1:]
        temp_int =  int(temp[temp.index('b') + 1:])
        #print y
        y = temp_int
        sum = 0
        while y > 0:
            sum += y % 10
            y = y / 10

        #print temp_int, sum
        val = []
        if sum in opt.keys():
            val = opt[sum]

        val.append(temp_int)
        opt[sum] = val

    for key in opt.keys():
        print key,
        for item in opt[key]:
            print item,
        print

getBits(5)