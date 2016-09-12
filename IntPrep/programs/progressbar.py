import math
import time
def prog(i, limit):
    modval = 10
    while i < limit:

        val = math.floor((i * 100.0 / limit))

        if val > 0.0 and val == modval:
            print "*", i, val, i *1.0/limit
            time.sleep(0.5)
            modval += 10
        i +=1

prog(0,1334)

