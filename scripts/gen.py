import numpy as np
import sys

# command line arguments MIN MAX AVG W
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
            # print(f'Number of iterations: {i}')
            return concat
        l = l + (target_avg - avg)
        l = np.extract(l >= t_min, l)
        l = np.extract(l <= t_max, l)
        if target_avg < avg:
            l_new = np.random.default_rng().uniform(target_avg - diff, avg, n - 2 - len(l))
        else:
            l_new = np.random.default_rng().uniform(avg, target_avg + diff, n - 2 - len(l))
        l = np.concatenate((l, l_new))

MIN = float(sys.argv[1])
MAX = float(sys.argv[2])
AVG = float(sys.argv[3])
W = int(sys.argv[4])

result = gen(MIN, MAX, AVG, W)

#print('Generated result with min: {0}, max: {1}, avg: {2} and length: {3}'.format(np.min(result), np.max(result), np.average(result), len(result)))

print('\n'.join([str(MIN), str(MAX), ' '.join(map(str, result))]))