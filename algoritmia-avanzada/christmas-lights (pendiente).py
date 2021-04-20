class Tree(object):

    def _buildTree(self, index, l, r):
        if l == r:
            self.nodes[index] = self.array[l]
            return
        
        left = 2 * index
        right = left + 1
        mid = (l + r) // 2
        self._buildTree(left, l, mid)
        self._buildTree(right, mid + 1, r)
        self.nodes[index] = min(self.nodes[left], self.nodes[right])

    def _getTreeSize(self, n):
        size = 1
        while size < n:
            size <<= 1
        return size << 1

    def __init__(self, array):
        self.size = self._getTreeSize(len(array))
        self.nodes = [None for i in range(self.size)]
        self.array = array
        # The tree is built
        self._buildTree(array, 1, 0, len(array)-1)


# lights = []

# def find_light_on(n, start, is_left):
#     if lights[start] == '1': return start

#     if is_left:
#         while start >= 0:
#             if lights[start] == '1': return start
#             start -= 1
#     else:
#         while start < n:
#             if lights[start] == '1': return start
#             start += 1


# def update_lights(start, end):
#     while start <= end:
#         value = lights[start]
#         lights[start] = '0' if value == '1' else '1'
#         start += 1


# Se obtienen la cantidad de casos
testcases = int(input())

# Se solicita la informacion de cada caso
while testcases > 0:
    k, m = [int(i) for i in input().split()]
    hex_value = bin(int(input(), 16))[2:].zfill(int(k))
    lights = [int(c) for c in hex_value]

    tree = Tree(lights)

    # while m > 0:
    #     start, end = [int(i)-1 for i in input().split()]

    #     if 0 < start < k and 0 < end < k-1:
    #         if lights[start] == '0':
    #             l_i = find_light_on(k, start-1, True)
    #             if l_i != None: start = l_i
            
    #         if lights[end] == '0':
    #             r_i = find_light_on(k, end+1, False)
    #             if r_i != None: end = r_i

    #     update_lights(start, end)
    #     m -= 1

    # print(hex(int(''.join(lights), 2))[2:].upper())
    testcases -= 1
