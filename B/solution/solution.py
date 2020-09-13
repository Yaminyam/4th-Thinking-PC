import sys

start = sys.stdin.readline()
stack = []
answer = ['고무오리야 사랑해', '힝구']

while True:
    command = sys.stdin.readline()
    if command == '고무오리 디버깅 끝':
        break
    if command == '문제':
        stack.append(0)
    elif command == '고무오리':
        if len(stack):
            stack.pop()
        else:
            stack += [0, 0]

print(answer[1 if len(stack) else 0])
