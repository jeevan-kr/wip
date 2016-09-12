import sys

def printOptions():
	print "\n"
	for k, v in options.iteritems():
		print k, v.__name__

def binSum(n):
	st = str(bin(n))
	sum = 0;
	i = 2
	while i < len(st):
		sum += int(st[i])
		i += 1
	return sum

def assignmentVectors(num):
	n = int(num)
	for i in range(0,pow(2,n)-1):
		if binSum(i) == n/2:
			print ('{:0'+str(n)+ 'b}').format(i)
def getTags(tweet):
	tagList = []
	if tweet == None or len(tweet) ==0:
		return []
	flag = True
	i = 0
	while(flag):
		st = tweet.find('#',i)
		if st > -1:
			sp = tweet.find(' ',st+1)
			if sp > -1:
				tagList.append(tweet[st:sp])
				i = sp + 1
			else:
				tagList.append(tweet[st:len(tweet)-1])
				flag = False
				i = len(tweet)
		else:
			flag = False
	return tagList
def knapsack():
	return 
def travellingSalesman():
	return
def squareRoot():
	return
def returnOutliers():
	return
def join():
	
	return

def insertionSort(A):
	#print len(A)
	for i in range(1,len(A)):
		#insert(A,i,A[i])
		print A
		j = i - 1
		val = A[i]
		while (j >= 0 and A[j] >= val):
			A[j+1] = A[j]
			j = j - 1
			
		A[j+1] = val
	return A

def insertionSortRunner(input):
	#print input 
	inSArr = input.split(",")
	inArr = []
	for i in range(0,len(inSArr)):
		inArr.append(int(inSArr[i]))
	opt = insertionSort(inArr)
	return opt

def hashtagPopularity(tweetList):
	hashtagCount = {}
	for tweet in tweetList:
		tagList = getTags(tweet)
		for tag in tagList:
			if tag in hashtagCount:
				hashtagCount[tag] += 1
			else:
				hashtagCount[tag] = 1
	d = hashtagCount
	for w in sorted(d, key=d.get, reverse=True):
		print w, d[w]

def hashtagPopularityRunner(fileName):
	lines = []
	with open(fileName, 'r') as f:
		for line in f.readlines():
			lines.append(line)
	#for line in lines:
	#	print getTags(line)
	hashtagPopularity(lines)	
def sqr():
	return ''

def hasDuplicateChar(input):
	chars = input.split(",")
	retVal = False
	inpDict = {}
	for char in chars:
		if char in inpDict:
			retVal = True
			break
		else:
			inpDict[char] = 1
	return retVal

def isPermutation(input):
	retVal = True
	#Split the input into two strings
	inputs = input.split(":")
	
	if len(inputs[0]) != len(inputs[1]):
		return False

	#Split each of the strings into arrays, with elements with a comma
	aCharArray = list(inputs[0])
	bCharArray = list(inputs[1])
	
	#sort each string
	aCharArray = sortIt(aCharArray)
	bCharArray = sortIt(bCharArray)
	
	#return sort(input.split(","))

	for i in range (0,len(aCharArray)):
		if aCharArray[i] != bCharArray[i]:
			retVal = False
			break
	return retVal

def sorting(input):
	return sortIt(input.split(","))

def sortIt(inputArray):
	for i in range(0,len(inputArray)):
		for j in range(0,len(inputArray)):
			if inputArray[i] < inputArray[j]:
				temp = inputArray[i]
				inputArray[i] = inputArray[j]
				inputArray[j] = temp
	return inputArray

def quickSortRunner(input):
	inSArr = input.split(",")
	inArr = []
	for i in range(0,len(inSArr)):
		inArr.append(int(inSArr[i]))
	opt = quickSort(inArr)
	return opt
def quickSort(alist):
	quickSortHelper(alist,0,len(alist)-1)
	return alist

def quickSortHelper(alist,first,last):
	if first<last:
		splitpoint = partition(alist,first,last)
		print splitpoint
		print alist
		quickSortHelper(alist,first,splitpoint-1)
		quickSortHelper(alist,splitpoint+1,last)

def partition(alist, start, end):
	mid = (start + end)/2 
	#mid = start
	pivot = alist[mid]
	print "pivot", pivot, start, end
	done = False
	while not done:
		if alist[start] <= pivot:
			start += 1
		if alist[end] >=pivot:
			end -= 1
		if alist[start] > pivot and alist[end] < pivot:
			print "swapping", start, end
			temp = alist[start]
			alist[start] = alist[end]
			alist[end] = temp
			start += 1
			end -= 1
		if end <= mid and start < mid:
			
		print alist, start, end
	return mid

options = {
	0 : assignmentVectors,
	1 : hashtagPopularityRunner,
	2 : knapsack,
	3 : travellingSalesman,
	4 : squareRoot,
	5 : returnOutliers,
	6 : hasDuplicateChar,
	7 : sorting,
	8 : isPermutation,
	9 : insertionSortRunner,
	10: quickSortRunner
}

if __name__ == '__main__':
    if len(sys.argv) > 1:
        opIndex = int(sys.argv[1])
        print options[opIndex](sys.argv[2])

    else:
        print "Whoops, need program selection!"
        print "usage: python runner.py [Option]"
        printOptions()
        sys.exit()
