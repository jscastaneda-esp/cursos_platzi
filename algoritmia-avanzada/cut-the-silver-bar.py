n = int(input())
while n:
    count = 0
    while n > 1:
        n = n//2
        count += 1

    print(count)
    n = int(input())
