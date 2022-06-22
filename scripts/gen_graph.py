import matplotlib.pyplot as plt
import sys

FILE1 = sys.argv[1]
FILE2 = sys.argv[2]
FILE3 = sys.argv[3]

x, y1, y2, y3 = [], [], [], []

with open(FILE1, 'r+') as f:
    w1 = f.readline()
    N1 = f.readline()
    avg1 = f.readline()
    min1 = f.readline()
    max1 = f.readline()
    latency = f.readline()
    firstTask = f.readline()
    l = firstTask.split(' ')
    x.append(float(l[0]))
    t1 = float(l[1])
    y1.append(1)
    for line in f.readlines():
        l = line.split(' ')
        x.append(float(l[0]))
        y1.append((t1/float(l[1]))/float(l[0]))

with open(FILE2, 'r+') as f:
    w2 = f.readline()
    N2 = f.readline()
    avg2 = f.readline()
    min2 = f.readline()
    max2 = f.readline()
    latency = f.readline()
    firstTask = f.readline()
    l = firstTask.split(' ')
    t1 = float(l[1])
    y2.append(1)
    for line in f.readlines():
        l = line.split(' ')
        y2.append((t1/float(l[1]))/float(l[0]))

with open(FILE3, 'r+') as f:
    w3 = f.readline()
    N3 = f.readline()
    avg3 = f.readline()
    min3 = f.readline()
    max3 = f.readline()
    latency = f.readline()
    firstTask = f.readline()
    l = firstTask.split(' ')
    t1 = float(l[1])
    y3.append(1)
    for line in f.readlines():
        l = line.split(' ')
        y3.append((t1/float(l[1]))/float(l[0]))

x = x[0:int(N1)]
y1 = y1[0:int(N1)]
y2 = y2[0:int(N1)]
y3 = y3[0:int(N1)]

title = 'N = %d W =  %d C = %.1f MIN = %.2f MAX = %.2f L = %.2f'%(int(N1), int(w1), float(avg1), float(min1), float(max1), float(latency))

fig = plt.figure()
sub = fig.add_subplot(211)
sub.plot(x, y1, label = "Chunking")
sub.plot(x, y2, label = "Work Stealing")
sub.plot(x, y3, label = "Factoring")
sub.set_xlabel('N')
sub.set_ylabel('efficiency')
sub.set_title(title)
sub.legend()
plt.show()