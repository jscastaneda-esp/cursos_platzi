def run():
    # All cubics of natural numbers
    # my_dict = {i: i**3 for i in range(1, 101)}

    # All cubics of natural number not divisible by three
    # my_dict = {i: i**3 for i in range(1, 101) if i % 3 != 0}

    # All square root of natural numbers
    my_dict = {i: i**(1/2) for i in range(1, 1001)}

    print(my_dict)


if __name__ == '__main__':
    run()
