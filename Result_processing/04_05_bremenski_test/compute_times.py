import json

def avg(l):
    return sum(l) / len(l)

with open("avgs.dat", "w") as f_out:

    with open("1.txt", "r") as f_in:
        results = json.loads(f_in.read())

    times = [i["reqTime"] for i in results]

    f_out.write("1 " + str(min(times)) + " " + str(max(times)) + " " + str(avg(times)) + "\n")

    with open("3.txt", "r") as f_in:
        results = json.loads(f_in.read())

    times = [i["reqTime"] for i in results]

    f_out.write("3 " + str(min(times)) + " " + str(max(times)) + " " + str(avg(times)) + "\n")
