def getFirstElementWithEvenOccurence(A):
	retVal = -1
	for element in A:
		count = 0
		for searchEle in A:
			if element == searchEle:
				count += 1

		if count % 2 ==0:
			retVal = element
			break
	return retVal
inp = [1,2,3,4,5,3,4,4,4]
res = getFirstElementWithEvenOccurence(inp)
print "input", inp, "result", res, "correct:", (res==3)

inp = [1,2,3,4,5,2,3,4,4,4]
res = getFirstElementWithEvenOccurence(inp)
print "input", inp, "result", res, "correct:", (res==2)