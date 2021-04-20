try:
    cin = input()
    while cin.strip() != '':
        k, n = [int(i) for i in cin.split()]

        if k == 1 or n == 1:
            print('A'*n, end='')

            if k > 1 and n == 1:
                for i in range(1, k-1):
                    print(chr(65 + i), end='')

                print(chr(65 + (k-1))*n, end='')

            print()
        else:
            m = k**n
            groups = m//k

            afters = [0]*m

            for i in range(groups):
                for j in range(k):
                    afters[j * groups + i] = i * k + j

            selected = [0]*m
            for i in range(m):
                if selected[i]: continue

                j = i
                while not selected[j]:
                    selected[j] = 1
                    print(chr(65 + j // groups), end='')
                    j = afters[j]
            
            print()

        cin = input()
except EOFError:
    pass
