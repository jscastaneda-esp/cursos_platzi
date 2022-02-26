from ast import Pass


def run():
    # All squares of natural numbers
    # squares = [i**2 for i in range(1, 101)]

    # All squares of natural numbers not divisible by three
    # squares = [i**2 for i in range(1, 101) if i % 3 != 0]

    # All numbers that multiples of four, six and nine
    squares = [i for i in range(1, 100000) if i % 36 == 0]

    print(squares)


if __name__ == '__main__':
    run()
