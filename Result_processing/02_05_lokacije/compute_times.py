import json

def avg(l):
    return sum(l) / len(l)

with open("avgs.dat", "w") as f_out:

    with open("vic.out", "r") as f_in:
        results = json.loads(f_in.read())

    times = [i["reqTime"] for i in results]

    f_out.write("vic " + str(min(times)) + " " + str(max(times)) + " " + str(avg(times)) + "\n")

    with open("planina.txt", "r") as f_in:
        results = json.loads(f_in.read())

    times = [i["reqTime"] for i in results]

    f_out.write("planina " + str(min(times)) + " " + str(max(times)) + " " + str(avg(times)) + "\n")

    with open("vrtojba.out", "r") as f_in:
        results = json.loads(f_in.read())

    times = [i["reqTime"] for i in results]

    f_out.write("vrtojba " + str(min(times)) + " " + str(max(times)) + " " + str(avg(times)) + "\n")
