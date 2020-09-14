def gcd(a,b):
    """Compute the greatest common divisor of a and b"""
    while b > 0:
        a, b = b, a % b
    return a
    
def lcm(a, b):
    """Compute the lowest common multiple of a and b"""
    return a * b / gcd(a, b)

def get_GCD(nums):
    _gcd = nums[0]
    for n in nums[1:]:
        _gcd = gcd(_gcd, n)
    return _gcd

def get_LCM(nums):
    _lcm = nums[0]
    for n in nums[1:]:
        _lcm = int(_lcm*n / gcd(_lcm, n))
    return _lcm

length = input()
bunja = []
bunmo = []
for i in range(int(length)):
    num = input().split(" ") 
    bunja.append(int(num[0]))
    bunmo.append(int(num[1]))

numerator = get_LCM(bunmo)
denominator = get_GCD(bunja)
print(str(int(denominator))+" "+str(int(numerator)))
