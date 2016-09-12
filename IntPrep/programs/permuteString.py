
def permute(pre, a):
    if len(a) == 0:
        print pre
    else:
        for i in range(0, len(a)):
            permute(pre + a[i], a[0:i] + a[i + 1:len(a)])

permute("", "abc")

