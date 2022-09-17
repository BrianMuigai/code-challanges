def solution(n):
    n = int(n)
    i = 0
    while n > 1:
        if (n&1) == 0:
            n >>= 1
        elif (n&3) == 1 or n == 3:
            n -= 1
        else:
            n += 1
        i += 1
    return i

print(solution(4))
print(solution(15))
print(solution(12345))
print(solution(123456789098))
print(solution(12345678909898765678987659876543456789876543))
