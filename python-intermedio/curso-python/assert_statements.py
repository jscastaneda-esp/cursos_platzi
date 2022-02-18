def divisors(num):
    assert num > 0, "Debes ingresar un nùmero positivo"

    divisors = []
    for i in range(1, num + 1):
        if num % i == 0:
            divisors.append(i)

    return divisors


def run():
    try:
        num = input("Ingresa un nùmero: ")
        assert num.isnumeric(), "Debes ingresar un nùmero"

        print(divisors(int(num)))
        print("Termino ejecuciòn")
    except AssertionError as ae:
        print(ae)


if __name__ == "__main__":
    run()
