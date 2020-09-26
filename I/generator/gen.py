from random import randint, shuffle

for i in range(100):
  print('Generating {}...'.format(i))
  f = open('./data/{}.in'.format(i), 'wt')
  N = randint(10, 200)
  print('N =', N)
  f.write('{}\n'.format(N))
  mans = ['man' + str(i) for i in range(N)]
  womans = ['woman' + str(i) for i in range(N)]

  for man in mans:
    f.write(man + ' ')
  f.write('\n')

  for woman in womans:
    f.write(woman + ' ')
  f.write('\n')
  
  for man in mans:
    shuffle(womans)
    f.write(man + ' ')
    for woman in womans:
      f.write(woman + ' ')
    f.write('\n')


  for woman in womans:
    shuffle(mans)
    f.write(woman + ' ')
    for man in mans:
      f.write(man + ' ')
    f.write('\n')
  f.close()
