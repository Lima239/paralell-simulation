import matplotlib.pyplot as plt

FILE = 'data/output2.txt'

x, y1, y2, y3 = [], [], [], []

with open(FILE, 'r+') as f:
    for line in f.readlines():
        l = line.split(' ')
        x.append(float(l[0]))
        y1.append(float(l[1]))
        y2.append(float(l[2]))
        y3.append(float(l[3]))

x = x[0:5]
y1 = y1[0:5]
y2 = y2[0:5]
y3 = y3[0:5]

fig = plt.figure()
sub = fig.add_subplot(211)
sub.plot(x, y1, label = "Chunking")
sub.plot(x, y2, label = "Work Stealing")
sub.plot(x, y3, label = "Factoring")
sub.set_xlabel('N')
sub.set_ylabel('speedup')
sub.legend()
plt.show()