try:
    cin = input()
    while cin.strip() != '':
        l, n = [int(i) for i in cin.split()]

        if not l: break

        mod = l % 2
        l_2 = l**2
    
        better = (l_2 + 1) // 2

        if n <= better:
            print(4 * n)
        else:
            leftover = l_2 - n
            l_3 = (l - 2)**2

            center = (l_3 + 1) // 2

            if leftover <= center:
                print(4 * (l + leftover))
            else:
                res = 4 * (l + center) + 2 * min(leftover - center, 4 * (l // 2 - 1))
                other = l_3 - center

                if l % 2:
                    res = max(res, 4 * (l + other) + 2 * (leftover - other))

                print(res)

        cin = input()
except EOFError:
    pass
