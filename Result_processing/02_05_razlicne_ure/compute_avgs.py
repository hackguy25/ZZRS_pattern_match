import json

def avg(l):
    return sum(l) / len(l)

with open("avgs.dat", "w") as f_out:
    
    for i in range(1, 25):
        
        with open(str(i).zfill(2) + ".txt", "r") as f_in:
            results = json.loads(f_in.read())
        
        times = [i["reqTime"] for i in results]
        
        f_out.write(str(i) + " " + str(avg(times)) + "\n")
