from timeit import default_timer as timer

def makeChange(n, coins):
    if n == 0:
        return 1

    if coins is None or len(coins) == 0:
        return 0

    count = 0
    sum = 0

    while count * coins[0] <= n:
        sum += makeChange(n - count * coins[0],coins[1:])
        count += 1
    return sum



def makeChangeDyn (n, coins, map):
    if n == 0:
        return 1
    if coins is None or len(coins) == 0:
        return 0
    if (n, tuple(coins)) in map:
        return map[(n, tuple(coins))]
    count = 0
    sum = 0

    while count * coins[0] <= n:
        # print n, count, coins
        sum += makeChangeDyn(n - count * coins[0], coins[1:], map)
        count += 1
    map[(n, tuple(coins))] = sum
    return map[(n, tuple(coins))]

val = 1000
coinx = [25,10,5,1]
start = timer()
opt = makeChange(val,coinx)
end = timer()
rec = end-start
print rec, opt

start = timer()
opt = makeChangeDyn(val,coinx, {})
end = timer()
dyn = end - start
print dyn, opt

print rec/dyn
