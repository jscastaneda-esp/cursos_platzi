from os import system, name
from random import choice

from constants import *
from notices import *


def select_word():
    with open('./files/data.txt', 'r', encoding="utf-8") as f:
        words = [word for word in f]

    return choice(words).upper()


def clear():
    # for windows
    if name == 'nt':
        system('cls')

    # for mac and linux
    else:
        system('clear')


def equals_normalize_letter(origin, src):
    if origin in ESPECIAL_LETTERS.keys():
        return src == ESPECIAL_LETTERS.get(origin)

    return src == origin


def run():
    welcome()
    word = select_word()
    hits = ["___" for _ in range(1, len(word))]
    fails = 0

    while "___" in hits:
        print(HANGMANPICS[fails])

        if fails < 6:
            print("Palabra: ", ' '.join(hits))
        else:
            game_over()
            print("La palabra era: ", '   '.join(word))
            break

        letter = input("Ingresa una letra: ").upper()

        try:
            assert len(letter) == 1, "Debe ser una sola letra"

            found = False
            for k, v in enumerate(word):
                if equals_normalize_letter(v, letter):
                    hits[k] = f' {v} '
                    found = True

            if not found and fails < 6:
                fails += 1
        except AssertionError:
            pass

        clear()

    if "___" not in hits:
        print("Palabra: ", ' '.join(hits))
        winner()


if __name__ == "__main__":
    run()
