from functools import reduce


def lambdas(msg, arg, function):
    """
    Function high order
    """
    print(msg, function(arg))


lambdas("Palindromo", "ana", lambda text: text == text[::-1])
lambdas("NO palindromo", "otro", lambda text: text == text[::-1])
lambdas("Es adulto", 19, lambda age: age >= 18)
lambdas("NO es adulto", 10, lambda age: age >= 18)

# Filter
my_list = [1, 4, 5, 6, 9, 13, 19, 21]
odd = list(filter(lambda x: x % 2 != 0, my_list))
print(odd)

# Map
my_list = [1, 2, 3, 4, 5]
squares = list(map(lambda x: x**2, my_list))
print(squares)

# Reduce
my_list = [2, 2, 2, 2, 2]
all_multiplied = reduce(lambda a, b: a * b, my_list)
print(all_multiplied)
