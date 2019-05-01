import matplotlib.pyplot as plt
import json

def avg(l):
    return sum(l) / len(l)

with open("../projekt/test.out", "r") as f:
    results = json.loads(f.read())

plt.rc("text", usetex = True)
plt.rc("font", family = "serif")

# časi zahtev
times = [i["reqTime"] for i in results]
times.sort()

plt.plot(times)
plt.ylabel("t₆ - t₁ [ms, naraščajoče]")
plt.xlabel("število zahtev, kumulativno")
plt.savefig("total_times.svg")
plt.clf()

# časi na mreži
times = [(i["reqTime"] - i["proc_time"]) for i in results]
times.sort()

plt.plot(times)
plt.ylabel("(t₆ - t₁) - (t₅ - t₂) [ms, naraščajoče]")
plt.xlabel("število zahtev, kumulativno")
plt.savefig("transfer_times.svg")
plt.clf()

# za število zadetkov na posamezno sliko
pics = [i["imageId"] if "imageId" in i else 0 for i in results]
picNum = [0] * 21
for i in pics:
    picNum[i] += 1
print("Št. zadetkov:", picNum)

# za čas, ki ga porabi nalaganje slik
times = [i["image_fetch_time"] for i in results]
times.sort()

plt.plot(times)
plt.ylabel("t₄ - t₃ [ms, naraščajoče]")
plt.xlabel("število zahtev, kumulativno")
plt.savefig("loading_time_absolute.svg")
plt.clf()

# za delež časa, ki ga porabi nalaganje slik
times = [(100 * i["image_fetch_time"] / i["proc_time"]) for i in results]
times.sort()

plt.plot(times)
plt.ylabel("Delež časa [odstotek]")
plt.xlabel("število zahtev, kumulativno")
plt.savefig("loading_time_percentage.svg")
plt.clf()

# za povprečen čas nalaganja 1 slike
times = [i["image_fetch_time"] for i in results]
pics = [i["imageId"] if "imageId" in i else 20 for i in results] # ni zadetka => naloženih je bilo 20 slik
for i in range(len(times)):
    times[i] = times[i] / pics[i]

plt.plot(times)
plt.ylabel("Čas nalaganja 1 slike [ms]")
plt.xlabel("zaporedno število zahteve")
plt.savefig("pic_loading_time.svg")
plt.clf()
