def read():
    numbers = []
    with open("./files/numbers.txt", "r", encoding="utf-8") as f:
        for line in f:
            try:
                numbers.append(int(line))
            except TypeError:
                pass

    print(numbers)


def write():
    names = ["Jonathan", "Laura", "Stiven", "Yulied", "Ñoño", "Rocío"]
    with open("./files/names.txt", "w", encoding="utf-8") as f:
        for name in names:
            f.write(name)
            f.write("\n")


def append():
    names = ["Calderòn", "Castañeda"]
    with open("./files/names.txt", "a", encoding="utf-8") as f:
        for name in names:
            f.write(name)
            f.write("\n")


def run():
    read()
    write()
    append()


if __name__ == "__main__":
    run()
