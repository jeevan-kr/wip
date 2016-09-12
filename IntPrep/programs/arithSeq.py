from itertools import repeat

def arithmeticSequence(A):
	retVal = True
	A.sort()
	diff = -1
	
	if len(A) < 2:
		return False
	else:
		diff = A[1] - A[0]
	
	for j in range (1,len(A)):
		if diff != A[j] - A[j-1]:
			retVal = False
			break

	return retVal
def arSpl(A):
	mn = min(A)
	mx = max(A)
	delta, rem = divmod((mx - mn) , (len(A) - 1))
	flgList = list(repeat(0,len(A)))
	if rem:
		return False
	else:
		for e in A:
			t,rem = divmod((e-mn), delta)
			if rem:
				return False
			else:
				flgList[e] += 1
				
inp = [1,2,3,4,5,6,7]
res = arithmeticSequence(inp)
print "input:", inp, "result:", res, "correct:", (res==True)
	
for i in range(0,len(inp)):
	inp[i] = inp[i] + 4

res = arithmeticSequence(inp)
print "input:", inp, "result:", res, "correct:", (res==True)
	
inp = [1,3,5,7,10]
res = arithmeticSequence(inp)
print "input:", inp, "result:", res, "correct:", (res==False)

inp = [2,5,7,10,12]
res = arithmeticSequence(inp)
print "input:", inp, "result:", res, "correct:", (res==False)

inp = [1,1,1,1]
res = arithmeticSequence(inp)
print "input:", inp, "result:", res, "correct:", (res==True)
