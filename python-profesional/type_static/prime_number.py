def is_prime_number(number: int) -> bool:
    if number <= 1:
        return False

    for i in range(2, number):
        if number % i == 0:
            return False

    return True


def run():
    print(is_prime_number(20))


if __name__ == "__main__":
    run()
