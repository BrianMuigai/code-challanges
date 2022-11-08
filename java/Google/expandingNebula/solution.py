from itertools import cycle
from collections import defaultdict


def generate(c1, c2, bitlen):
    a = c1 & ~(1 << bitlen)
    b = c2 & ~(1 << bitlen)
    c = c1 >> 1
    d = c2 >> 1
    return (a & ~b & ~c & ~d) | (~a & b & ~c & ~d) | (~a & ~b & c & ~d) | (~a & ~b & ~c & d)


def build_map(n, nums):
    mapping = defaultdict(set)
    nums = set(nums)
    for i in range(1 << (n+1)):
        for j in range(1 << (n+1)):
            generation = generate(i, j, n)
            if generation in nums:
                mapping[(generation, i)].add(j)
    return mapping


def answer(g):
    g = list(zip(*g))  # transpose
    nrows = len(g)
    ncols = len(g[0])

    # turn map into numbers
    nums = [sum([1 << i if col else 0 for i, col in enumerate(row)])
            for row in g]
    mapping = build_map(ncols, nums)

    preimage = {i: 1 for i in range(1 << (ncols+1))}
    for row in nums:
        next_row = defaultdict(int)
        for c1 in preimage:
            for c2 in mapping[(row, c1)]:
                next_row[c2] += preimage[c1]
        preimage = next_row
    ret = sum(preimage.values())

    return ret


def decrypt():
    import base64
    message = """GVUaFA0OEBoURkkLQlUOEwsMAU5LQU5SDR4FBA8KAAxAQVMRRRcaFQsIGAwDRkURRRcPBwEfARpA QVMRRRsHAhwIEQAFDQwWTlJOAA0FHAwRBARUDAZOQVRNUhwJDQZSCRcNRkJNUhsGAwtYFgFOQVRN UhoGBwwWTlJOBwECUkldQU5GCxxIRhM="""
    key = bytes("brianmuigai1", "utf8")
    print(bytes(a ^ b for a, b in zip(base64.b64decode(message), cycle(key))))


if __name__ == '__main__':
    print(answer(
        [
            [True, True, False, True, False, True, False, True, True, False],
            [True, True, False, False, False, False, True, True, True, False],
            [True, True, False, False, False, False, False, False, False, True],
            [False, True, False, False, False, False, True, True, False, False]
        ]))
    decrypt()
