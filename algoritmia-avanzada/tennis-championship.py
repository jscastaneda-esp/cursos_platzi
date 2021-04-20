try:
    players = input()
    while players.strip() != '':
        matches = int(players) - 1
        print(matches)
        players = input()
except EOFError:
    pass
