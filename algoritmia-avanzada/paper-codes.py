import random


def is_prime(p):
    """
    Se utiliza el test de primalidad
    Inputs:
    p -> numero natural a evaluar
    Outputs:
    bool -> True=Primo, False=No primo
    """

    if p == 2:
        return True
    if p < 2 or p % 2 == 0:
        return False
    for n in range(3, int(p**0.5)+2, 2):
        if p % n == 0:
            return False
    return True


def xmcd(a, b):
    """
    Algoritmo de Euclides extendido

    Inputs:
    a -> numero natural
    b -> numero natural
    Outputs:
    c -> maximo comun divisor
    """

    s0, t0, s1, t1 = 1, 0, 0, 1

    while b:
        q = a // b # Division exacta para obtener el cociente
        r = a - b * q # Ecuacion a = b * q + r se despeja r

        s = s0 - q * s1 # Se calcula s_{i+1}
        t = t0 - q * t1 # Se calcula t_{i+1}

        # Se actualizan las variables para la siguiente iteracion
        a, b = b, r
        s0, t0, s1, t1 = s1, t1, s, t

    return a, s0, t0


def inverse_mod(a, n):
    """
    Inputs:
    a -> numero natural
    b -> numero natural
    Outputs:
    invmod -> 1 / a (mod n)
    """

    if n < 2:
        raise ValueError("El modulo debe ser mayor a 1")

    g, s, _ = xmcd(a, n)

    if g != 1:
        raise ValueError(f'No existe modulo inverso {a} y {n}')
    else:
        return s % n


def generate_keys(p, q):
    """
    Generacion de claves (publica y privada)
    Inputs:
    p -> numero natural
    q -> numero natural
    Outputs:
    public -> clave publica
    private -> clave privada
    """

    if not (is_prime(p) and is_prime(q)):
        raise ValueError('Numeros no primos.')
    elif p == q:
        raise ValueError('p y q no deben ser iguales')
    
    n = p * q
    phi = (p - 1) * (q - 1)

    # Se selecciona un valor menor a phi
    e = random.randrange(1, phi)

    # Se usa el algoritmo euclidiano extendido para obtener el mcd
    g, _, _ = xmcd(e, phi)
    while g != 1:
        e = random.randrange(1, phi)
        g, _, _ = xmcd(e, phi)

    # Se usa el algoritmo euclidiano extendido para obtener la inversa del modulo
    d = inverse_mod(e, phi)
    
    return (e, n), (d, n)


def encrypt(pk, m):
    """
    Cifrado de un mensaje
    Inputs:
    pk -> clave publica
    m -> mensaje en formato numerico
    Outputs
    c -> mensaje cifrado
    """

    # Se obtine las dos partes de la llave
    e, n = pk

    # c = m^e (mod n)
    return pow(m, e, n)


def decrypt(pk, c):
    """
    Descifrado del mensaje
    Inputs
    pk -> clave privada
    c -> mensaje cifrado
    Outputs
    m -> mensaje en formato numerico
    """

    # Se obtine las dos partes de la llave
    d, n = pk

    # m = c^d (mod n)
    return pow(c, d, n)