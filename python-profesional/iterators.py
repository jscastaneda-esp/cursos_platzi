import time


class FibonacciIter:
    def __init__(self, max=None):
        self.max = max

    def __iter__(self):
        self.num1 = 0
        self.num2 = 1
        return self

    def __next__(self):
        result = self.num1
        self.num1, self.num2 = self.num2, (self.num1 + self.num2)

        if not self.max or result < self.max:
            return result

        raise StopIteration


if __name__ == "__main__":
    fibonacci = FibonacciIter(10000000)

    for element in fibonacci:
        print(element)
        time.sleep(0.05)
