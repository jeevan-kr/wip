def fizzBuzz():
	opt = ""
	for i in range(301):
		opt = ""
		flag = 0
		if i%3 == 0:
			flag = 1
			opt +="Fizz"
		if i%5 == 0:
			flag = 1
			opt += "Buzz"
		if flag == 0:
			opt = str(i)
		print opt

fizzBuzz()

	