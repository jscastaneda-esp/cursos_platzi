empty_set = set()

my_set = {1, 2, 3, 4, 5}
print(my_set)

my_set_2 = {True, "Hola", False, 1, 20.4, (1, 2)}
print(my_set_2)

my_set_3 = {1, 1, 3, 5, 5, 100}
print(my_set_3)

try:
    my_set_4 = {[1, 2], 1}  # list is mutable
    print(my_set_4)
except TypeError as te:
    print(te)

my_set_from_list = set([1, 10, 5, 1, 1, 2])
print(my_set_from_list)

my_set_from_tuple = set((1, 10, 5, 10))
print(my_set_from_tuple)

my_set.add(10)
print("add", my_set)

my_set.update((1, 5), "Hola", [60, 10])
print("update", my_set)

my_set.discard(10)
print("discard", my_set)

my_set.discard(100)
print("discard [No exists]", my_set)

my_set.remove("H")
print("remove", my_set)

try:
    my_set.remove("z")  # Key no exists
except KeyError as ke:
    print("remove [No exists]", ke)

my_set_union = my_set | my_set_2  # my_set.union(my_set_2)
print("Set 1", my_set, "Set 2", my_set_2, "Union", my_set_union)

my_set_intersec = my_set & my_set_3  # my_set.intersection(my_set_3)
print("Set 1", my_set, "Set 2", my_set_3, "Intersection", my_set_intersec)

my_set_diff = my_set - my_set_3  # my_set.difference(my_set_3)
print("Set 1", my_set, "Set 2", my_set_3, "Difference", my_set_diff)

my_set_diff_2 = my_set_3 - my_set  # my_set_3.difference(my_set)
print("Set 1", my_set_3, "Set 2", my_set, "Difference", my_set_diff_2)

my_set_symmetric_diff = my_set ^ my_set_3  # my_set.symmetric_difference(my_set_3)
print("Set 1", my_set, "Set 2", my_set_3, "Symmetric difference", my_set_symmetric_diff)


def remove_duplicates(some_list):
    without_duplicates = []
    for element in some_list:
        if element not in without_duplicates:
            without_duplicates.append(element)

    return without_duplicates


def remove_duplicates_with_set(some_list):
    without_duplicates = set(some_list)
    return list(without_duplicates)


def run():
    random_list = [1, 1, 2, 2, 4]
    print("Remove duplicates elements of one list manual", random_list, "->", remove_duplicates(random_list))
    print("Remove duplicates elements of one list with sets", random_list, "->", remove_duplicates_with_set(random_list))


if __name__ == "__main__":
    run()
