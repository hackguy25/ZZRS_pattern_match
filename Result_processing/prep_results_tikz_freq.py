import json

def avg(l):
    return sum(l) / len(l)

in_fname = input("Ime datoteke z rezultati: ")
out_prefix = input("Predpona za izhodne datoteke: ")

with open(in_fname, "r") as f:
    results = json.loads(f.read())

# časi zahtev
times = [i["reqTime"] for i in results]

with open("1_" + out_prefix + "_total_times_unordered.dat", "w") as f:
    for i in range(len(times)):
        f.write(str(i+1) + " " + str(times[i]) + "\n")

#casi razlik

delta = [i["deltaTime"] for i in results]
with open("1_" + out_prefix + "_delta.dat", "w") as f:
    for i in range(len(delta)):
        f.write(str(i+1) + " " + str(delta[i]) + "\n")
