import json

def avg(l):
    return sum(l) / len(l)

with open("avgs.dat", "w") as f_out:
    
    for i in range(0, 700, 100):
        
        with open(str(i) + ".txt", "r") as f_in:
            results = json.loads(f_in.read())
        
        times = [i["reqTime"] for i in results]
        
        f_out.write(str(i) + " " + str(avg(times)) + "\n")
