import sys

class Node:
	#dat
	def __init__(self, d):
		self.dat = d
		self.next = None

	def appendToTail(self, d):
		n = self
		while n.next != None:
			n = n.next
		temp = Node(d)
		n.next = temp

	def printNode(self):
		n = self
		while n is not None:
			print n.dat,"->",
			n = n.next
		print
		#print "data", self.dat

def printOptions():
	print 

	for k, v in options.iteritems():
		print k, v.__name__

def createList(input):
	items = input.split(",")
	#print items
	head = None

	for i in range(0,len(items)):
		
		temp = Node(items[i])
		
		if head is None:
			head = temp
		else: 
			prev.next = temp
		prev = temp
	return head

def delNodeRunner(input):
	delIndex = int(sys.argv[3])
	head = createList(input)
	n = head
	i = 1
	while i < delIndex:
		n = n.next
		i += 1
	delNode(n)
	return head

def delNode(n):
	while n.next.next is not None:
		n.dat = n.next.dat
		n = n.next
	n.dat = n.next.dat
	n.next = None

def partition(input):
	p = sys.argv[3]
	head = createList(input)
	count = 1
	before = []
	after = []
	n = head
	
	while n is not None:
		if n.dat < p :
			before.append(n.dat)
		elif n.dat > p:
			after.append(n.dat)
		else:
			count += 1

	outHead = None
	first = True

	for item in before:
		if first == True:
			outHead = Node(item.dat)
		else:
			outHead.appendToTail(item.dat)

	while count > 0
		outHead.appendToTail(p)
		count -= 1

	for item in after:
		if first == True:
			outHead = Node(item.dat)
		else:
			outHead.appendToTail(item.dat)
	return outHead


def removeDuplicates(input):
	head = createList(input)
	nodeList = {}
	n = head

	while n is not None:
		#print 'l'
		if n.dat in nodeList:
			if prev is None:
				head = n.next
			else:
				#print 'found', n.dat
				prev.next = n.next
		else:
			#print 'ad'
			nodeList[n.dat] = 1
		prev = n
		n = n.next
	return head
options = {
	0 : createList,
	1 : removeDuplicates,
	2 : delNodeRunner
}

if __name__ == '__main__':
    if len(sys.argv) > 1:
        opIndex = int(sys.argv[1])
        options[opIndex](sys.argv[2]).printNode()

    else:
        print "Whoops, need program selection!"
        print "usage: python runner.py [Option]"
        printOptions()
        sys.exit()
'''
c = Node(3)
c.printNode()
c.appendToTail(5)
c.appendToTail(11)
c.appendToTail(0)
c.appendToTail(2)
c.printNode()
'''