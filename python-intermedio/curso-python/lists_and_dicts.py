def run():
    my_list = [1, "Hello", True, 4.5]
    my_dict = {"firstname": "Jonathan", "lastname": "Castañeda"}

    super_list = [
        {"firstname": "Jonathan", "lastname": "Castañeda"},
        {"firstname": "Stiven", "lastname": "Espinosa"},
        {"firstname": "Laura", "lastname": "Garrido"},
        {"firstname": "Yulied", "lastname": "Calderòn"}
    ]

    super_dict = {
        "natural_nums": [1, 2, 3, 4, 5],
        "integer_nums": [-1, -2, 0, 1, 2],
        "floating_nums": [1.1, 4.5, 6.43]
    }

    print("Super Dict")
    for key, value in super_dict.items():
        print(key, "-", value)

    print()
    print("Super List")
    for value in super_list:
        print(value)


if __name__ == '__main__':
    run()
