import json

def avg(l):
    return sum(l) / len(l)

in_fname = input("Ime datoteke z rezultati: ")
out_prefix = input("Predpona za izhodne datoteke: ")

with open(in_fname, "r") as f:
    results = json.loads(f.read())

# časi zahtev
times = [i["reqTime"] for i in results]
times.sort()

with open("1_" + out_prefix + "_total_times.dat", "w") as f:
    for i in range(len(times)):
        f.write(str(i+1) + " " + str(times[i]) + "\n")

# časi na mreži
times = [(i["reqTime"] - i["proc_time"]) for i in results]
times.sort()

with open("1_" + out_prefix + "_transfer_times.dat", "w") as f:
    for i in range(len(times)):
        f.write(str(i+1) + " " + str(times[i]) + "\n")

# za čas, ki ga porabi nalaganje slik
times = [i["image_fetch_time"] for i in results]
times.sort()

with open("1_" + out_prefix + "_loading_time_absolute.dat", "w") as f:
    for i in range(len(times)):
        f.write(str(i+1) + " " + str(times[i]) + "\n")

# za delež časa, ki ga porabi nalaganje slik
times = [(100 * i["image_fetch_time"] / i["proc_time"]) for i in results]
times.sort()

with open("1_" + out_prefix + "_loading_time_percentage.dat", "w") as f:
    for i in range(len(times)):
        f.write(str(i+1) + " " + str(times[i]) + "\n")

# za povprečen čas nalaganja 1 slike
times = [i["image_fetch_time"] for i in results]
pics = [i["imageId"] if "imageId" in i else 20 for i in results] # ni zadetka => naloženih je bilo 20 slik
for i in range(len(times)):
    times[i] = times[i] / pics[i]

with open("1_" + out_prefix + "_pic_loading_time.dat", "w") as f:
    for i in range(len(times)):
        f.write(str(i+1) + " " + str(times[i]) + "\n")
