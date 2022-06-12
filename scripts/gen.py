import numpy as np

def gen(t_min, t_max, target_avg, n, tolerance=0.000001):
    r = np.array([t_min, t_max])
    l = np.random.default_rng().uniform(t_min, t_max, n)
    i = 0
    while True:
        i = i+1
        concat = np.concatenate((l, r))
        avg = np.average(concat)
        diff = abs(target_avg - avg) 
        if diff <= tolerance:
            print(f'Number of iterations: {i}')
            return concat
        l = l + (target_avg - avg)
        l = np.extract(l >= t_min, l)
        l = np.extract(l <= t_max, l)
        if target_avg < avg:
            l_new = np.random.default_rng().uniform(target_avg - diff, avg, n - 2 - len(l))
        else:
            l_new = np.random.default_rng().uniform(avg, target_avg + diff, n - 2 - len(l))
        l = np.concatenate((l, l_new))

MIN = 0.08
MAX = 0.16
AVG = 0.1
N = 10000
FILENAME = '/Users/KlaudiaLichmanova/Desktop/paralell-simulation/input6.txt'

result = gen(MIN, MAX, AVG, N)

print('Generated result with min: {0}, max: {1}, avg: {2} and length: {3}'.format(np.min(result), np.max(result), np.average(result), len(result)))

with open(FILENAME, 'w+') as f:
    f.write('\n'.join([str(MIN), str(MAX), ' '.join(map(str, result))]))