from random import randint, choice


def write_list(lst, num, c):
  f = open(str(num) + '.in', 'wt')
  for line in lst:
    f.write(line + '\n')
  f.close()

  f = open(str(num) + '.out', 'wt')
  if c == 0:
    f.write('고무오리야 사랑해\n')
  else:
    f.write('힝구\n')
  f.close()

N = 100

lst = ['문제', '고무오리']

# 결과가 고무오리 힝구인 경우
love_count = 0
case_amount = 50
while True:
  count = 0
  case = ['고무오리 디버깅 시작']
  for i in range(N):
    text = choice(lst)
    if text == '문제':
      count += 1
    elif text == '고무오리':
      if count == 0:
        count += 2
      else:
        count -= 1
    case.append(text)
  case.append('고무오리 디버깅 끝')
  if count != 0:
    write_list(case, love_count + (case_amount // 2), count)
    love_count += 1
  if love_count >= case_amount // 2:
    break

# 결과가 고무오리 사랑해인 경우
love_count = 0
case_amount = 50
while True:
  count = 0
  case = ['고무오리 디버깅 시작']
  for i in range(N):
    text = choice(lst)
    if text == '문제':
      count += 1
    elif text == '고무오리':
      if count == 0:
        count += 2
      else:
        count -= 1
    case.append(text)
  case.append('고무오리 디버깅 끝')
  if count == 0:
    write_list(case, love_count, count)
    love_count += 1
  if love_count >= case_amount // 2:
    break
