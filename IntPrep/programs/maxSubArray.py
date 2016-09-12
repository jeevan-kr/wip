# Function to find the maximum contiguous subarray and return the indices of the subarray
def maxSubArraySum(a, size):
    max_so_far = 0
    max_ending_here = 0
    allZero = True
    start = -1
    startSet = False
    end = -1
    for i in range(0, size):

        if allZero == True and a[i] > 0:
            allZero = False

        print i, max_so_far, max_ending_here, start, end
        max_ending_here = max_ending_here + a[i]

        if max_ending_here < 0:
            max_ending_here = 0
            start = -1
            startSet = False
        elif startSet == False:
            start = i
            startSet = True
        if (max_so_far < max_ending_here):
            max_so_far = max_ending_here
            end = i

    if allZero == True:
        maxVal = max(a)
        start = a.index(maxVal)
        end = start
        return (max(a), start, end)

    return (max_so_far, start, end)

# Driver function to check the above function
#a = [-2, -3, 4, -1, -2, 1, 5, -3]
#a = [-1,-2,-3]
a = [-3,1,-2,1,-2]
print"Maximum contiguous sum is", maxSubArraySum(a,len(a))

#This code is contributed by _Devesh Agrawal_