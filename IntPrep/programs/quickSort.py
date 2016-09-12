import sys

def quicksort(myList, start, end):
    if start < end:
        # partition the list
        pivot = partition(myList, start, end)
        # sort both halves
        quicksort(myList, start, pivot-1)
        quicksort(myList, pivot+1, end)
    return myList

def partition(myList, start, end):
    pivot = myList[start]
    left = start+1
    right = end
    done = False
    while not done:
    	print myList, pivot
        while left <= right and myList[left] <= pivot:
            left = left + 1
        while myList[right] >= pivot and right >=left:
            right = right -1
        if right < left:
            done= True
        else:
            # swap places
            temp=myList[left]
            myList[left]=myList[right]
            myList[right]=temp
    # swap start with myList[right]
    print "swapping", myList[start], myList[right]
    temp=myList[start]
    myList[start]=myList[right]
    myList[right]=temp
    return right

def toIntArr(strArr):
	intArr = []
	for i in range(0,len(strArr)):
		intArr.append(int(strArr[i]))
	return intArr
if __name__ == '__main__':
	inp = toIntArr(sys.argv[1].split(","))
	print quicksort(inp, 0, len(inp)-1)