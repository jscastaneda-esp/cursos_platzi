# https://asecuritysite.com/encryption/rsa12
from Crypto.Util.number import bytes_to_long, long_to_bytes
from Crypto.Random import get_random_bytes
import Crypto
import libnum
import sys

bits = 500

p = Crypto.Util.number.getPrime(bits, randfunc=get_random_bytes)
q = Crypto.Util.number.getPrime(bits, randfunc=get_random_bytes)

n = p * q
PHI = (p - 1) * (q - 1)

e = Crypto.Util.number.getPrime(bits, randfunc=get_random_bytes)
d = libnum.invmod(e, PHI)

msg = input()
m = bytes_to_long(msg.encode('utf-8'))
print(f'Message Text={msg}, Message Decimal={m}')

c = pow(m, e, n)
m = pow(c, d, n)

print(f'p={p}')
print(f'q={q}\n')
print(f'N={n}')
print(f'PHI={PHI}')
print(f'e={e}')
print(f'd={d}')
print('\nPrivate key (d,n)')
print('Public key (e,n)\n')
print(f'Cipher={c}')
print(f'Decipher Decimal={m}, Decipher={long_to_bytes(m)}')
