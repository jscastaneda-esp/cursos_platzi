from time import sleep


def fibonacci_gen(max=None):
    num1 = 0
    num2 = 1

    while not max or num1 <= max:
        yield num1
        num1, num2 = num2, (num1 + num2)


if __name__ == "__main__":
    fibonacci = fibonacci_gen(10000000)

    for element in fibonacci:
        print(element)
        sleep(0.05)
