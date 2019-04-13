import matplotlib.pyplot as plt
import json

with open("../projekt/test.out", "r") as f:
    results = json.loads(f.read())

# časi zahtev
times = [i["reqTime"] for i in results]
times.sort()

plt.plot(times)
plt.ylabel("Čas posamezne zahteve [ms, naraščajoče]")
plt.savefig("total_times.svg")
plt.clf()

# časi na mreži
times = [(i["reqTime"] - i["proc_time"]) for i in results]
times.sort()

plt.plot(times)
plt.ylabel("Čas paketa na mreži [ms, naraščajoče]")
plt.savefig("transfer_times.svg")
plt.clf()

# za število zadetkov na posamezno sliko
pics = [i["imageId"] if "imageId" in i else 0 for i in results]
picNum = [0] * 21
for i in pics:
    picNum[i] += 1
print("Št. zadetkov:", picNum)

# za delež časa, ki ga porabi nalaganje slik
times = [(100 * i["image_fetch_time"] / i["proc_time"]) for i in results]
times.sort()

plt.plot(times)
plt.ylabel("Delež časa [odstotek]")
plt.savefig("loading_time_percentage.svg")
plt.clf()

# za povprečen čas nalaganja 1 slike
times = [i["image_fetch_time"] for i in results]
pics = [i["imageId"] if "imageId" in i else 20 for i in results] # ni zadetka => naloženih je bilo 20 slik
for i in range(len(times)):
    times[i] = times[i] / pics[i]

plt.plot(times)
plt.ylabel("Čas nalaganja 1 slike [ms]")
plt.savefig("pic_loading_time.svg")
plt.clf()
