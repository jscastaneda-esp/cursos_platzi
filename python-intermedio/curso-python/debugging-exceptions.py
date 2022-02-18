def divisors(num):
    divisors = []
    for i in range(1, num + 1):
        if num % i == 0:
            divisors.append(i)

    return divisors


def run():
    try:
        num = int(input("Ingresa un nùmero: "))

        try:
            if num < 1:
                raise ValueError("Debes ingresar un nùmero positivo")

            print(divisors(num))
            print("Termino ejecuciòn")
        except ValueError as ve:
            print(ve)
    except ValueError as ve:
        print("Debes ingresar un nùmero")


if __name__ == "__main__":
    run()
