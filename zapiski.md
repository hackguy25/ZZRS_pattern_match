# Zapiski

Datoteka za dumpanje informacij o nalogi.

## Mnenja 2. skupine

Ideja za test: Se vam zdi smiselno, da bi obremenili še bazo? Zaenkrat ste imeli vedno 540 slik? Resnici na ljubo je težko zapolniti 30 GB prostora, ampak glede na opis se mi zdi, da bi to ogromno vplivalo na čas iskanja po bazi.
 - Ne: iz slike 1.9 je razvidno, da v večini primerov dostop do slike zavzame do 7 milisekund, torej bi časovna zahtevnost iskanja rasla sorazmerno z velikostjo baze. Za tak zaključek ne potrebujemo nujno zapolniti celotnih 30 GB prostora.

Povečajte oz. na drugačen način prikažite sliko 1.3, ker se zelo slabo vidi
 - DONE

Testiranje 1.4.1: ali je baza na istem strežniku ali je ločena, kakšni so recimo časi (ping) iz strežnika do baze
 - Ločena je, kot je opisano v 1.2.4. Ker se čas dostopa do baze meri na strežniku in so minimalni dostopi krajši od milisekunde, sklepamo, da je tudi ping veliko krajši od milisekunde. AWS nam ne omogoča eksplicitnih merjenj pinga do EBS, zato bi bile takšne meritve težko izvedljive.

Kdaj so bili izvedeni testi od 1.4.2 do 1.4.5? Ali je bil upoštevan faktor lokacije in časa?
 - V 1.4.3 smo ugotovili, da čas ne vpliva občutno na čas izvajanja, v 1.4.2 pa, da lokacija tudi ne. Torej je faktor časa in lokacije v vseh testiranjih irelevanten, če le upoštevamo ping do storitve in nazaj.

Poglavje 1.4.4 - pravopisna napaka: vnos namesto vnost ctrl+c
 - DONE

Malo večja analiza pri 1.4.4 ali pa da celo ponovite ta test, zakaj je pri 400 MB odvzetega pomilnika manjši čas odgovora kot pri 100 MB?
 - Problem pošiljanja naključnih zahtev je, da pride do variacij med testi, na katere ne moremo vplivati. Če bi testirali znova, bi se morda situacija popolnoma obrnila.

Zaključek: ali bi mi proporočili to storitev in za kakšen tip aplikacije (glede na 1.4.5 vidimo, da se časi ogromno povečajo, če želimo imeti več uporabnikov). Kaj pa recimo za hranjenje? 30 GB se mi zdi ogromno razpoložljivega prostora, mogoče za hrambo slik? Zato me zanima, kako bi se baza obnašala pod obremenitvijo.
 - Priporočili bi za storitve, ki so računsko razmeroma nezahtevne. Baza je del večjega "bazena" pomnjenja, je v primerjavi s strežnikom veliko težje preobremeniti, ima določene omejitve prenosa, ki niso natančno opisane na spletni strani.

Medzmes kadar je možno razbrati časi iz slike 1.1 bi jih kljub temu spremenil. Odstranil bi puščice časov in bi zapise, na malo večje kakor zdaj, napisal ob točkah nastanka.
 - TODO redesign oznak

V poglavju 1.2.3 se uporablja beseda pika pri čemer se v poglavju 1.2.2 uporablja pixel. Če nisem jaz zgrešil česar in besedi imata različni pomen bi izbral eno in le njo uporabljal.
 - dobra pripomba, to discuss

Če bi bilo možno bi pri tabeli 1.2 točno navedel kateri čas. Razumem, da je t_zahteva, a bi raje mel konkretno opredeljeno kakor le "časi zahtev", da sem brez dvoma prepičan kaj prikazuje.
 - TODO

Enako za tabelo 1.3.
 - TODO

Podobno velja za sliko 1.10 in 1.11. Tukaj bi še dodal, da medzmes v poglavju 1.3 povejo, da želijo videti kako se časi odgovorja razlikujejo glede na število uporabnik, nisem pa zmogel brez dvoma ugotoviti kateri konkretni čas je tale čas odgovorja.
 - TODO t_zahteva v grafe, poglavje 1.3

Razumem preglednost grafa, kadar imaš rezultate prikazane naraščajoče (preglednost nad odstopanjem določenih zahtev), a bi rekel, da bi bilo boljše imet graf z enakim vrstnim redom vsepovsod, najbolje bi bilo kar vrstni red zahtev. Tudi ne vem koliko doprinašajo tele grafi, predstavljam si, da bi npr. sliko 1.6 spremenili na ne razvrščen diagram in bi obdržali opis le tega (v 499 izmed 500 primerov ni presegel 78 ms) obdržal vse trenutne informacije.
 - Ne strinjam se. Iz 1.4 na primer je težko razvidno, kako se storitev obnaša v povprečju. Če so časi razvrščeni, je lažje razbrati, kolikšen del zahtev na primer presega 2 sekundi. Iz neurejenih grafov je potrebno obravnavati vsak vrh posebej.

Tole sprememba se mi zdi smiselna, saj če bi vsi grafi imeli v enaki vrstici enak primeru uporabe bi lahko bralec poskušal ugotoviti kakšne povezave med časi, a z trenutnim razporejanjem tole možnost odstraniš in ne vidim dobrega raloga zakaj.
 - isto kot prej

Lahko bi predlagal test enake zahteve, kjer se preverja konsistentnost strežnika ter omrežja. Po možnosti bi lahko bili trije testi, 500 zahtev z parametri zahteve z zelo dobrim čas, z povprečnim časom in z zelo visokim časom (predstavljal si bi, da bi lahko tele podatke pridobil iz prvega testa).
 - jup, tko bi mogli že z vsega začetka :D

Pri testiranjih boljši opis na kakšen način se pošilja 500 zahtev (Ali se pošlje ena zahteva in se počaka na odgovor, ter se nato pošlje naslednjo, ali pa se zahteve pošiljajo na določen časovni interval.)
 - TODO

Pri primerjavi odzivov na zahteve ob različnih časih (1.4.3), bolj točno napisati kateri čas ste gledali (ali je to Tzahteva, Tprocesiranja ali kateri izmed drugih časov, ki na navedete v 1.3)
 - TODO, glej 5 zahtev gor

Podobno tudi v razdelki 1.4.4 napisati namesto povprečen čas odgovora Tzahteva (oziroma kakšen drugi čas)
 - isto

## Za naredit do 6. 5.

V ponedeljek, 6. 5. si bomo med skupinami izmenjali poročila, da damo in dobimo feedback.
Poročilo more zato bit nujno oddano enkrat do nedelje dopoldan, da profesor pregleda.

Trenutna ocena je 8, strukturno in slovnično je pretty much OK.
ref: https://photos.app.goo.gl/95Y4aeHgKFUK6Dv56

### Za popravit

- slovnične/tipkarske napake (glej slike)
- slike/grafi morajo stilsko spadati k tekstu - oznake naj bodo v podobni pisavi, kot je dokument
- kjer v tekstu piše "listing" / "v listingu"... => "izpis" / "v izpisu" (konsistentno v celem dokumentu) => DONE

### Za dodat

- čim več izmed naštetih testiranj v 1.5, ni nujno vse
- nujno dodat:
  - **bremenski test** - povečanje bremena - v našem primeru to pomeni zagon več odjemalcev hkrati in spremljanje Tpaketa
  - **stresni test** - zmanjšanje resursov - na nek način umetno zmanjšati zmogljivost storitve in spremljat rezultate
  - zaključek - kaj smo ugotovili, vsaj približno

## Za naredit do 29. 4. (do 22. poslat vmesno poročilo!)

### Za popravit

(glej slike Discord)

- slovnične/tipkarske napake (glej slike) => DONE
- navajanje v stilu " [x]." -> " \cite{vir}." => DONE
- 1.2.1
  - redesign slike 1.1 => DONE
    - odjemalci na levi, storitev na desni
    - vse povezave enosmerne, bolj pregledne
- 1.2.2
  - ločila na koncu alinej => DONE
  - dodati to, da ne preišče *vseh* slik, temveč le toliko, dokler ne najde ujemanja => DONE
  - k računanju razlike komentar, kakšna odstopanja se uporablja => DONE
  - k slikovnemu izseku dodat, da se ujemanje gleda glede na odstopanje => DONE
- 1.2.3
  - podrobnejši opis situacije hranjenja datotek (začasno/trajno, EBS) => DONE
  - opis situacije glede hitrosti interneta na free instancah (kje je omejitev, kako vidimo na AWS) => DONE
- 1.2.4
  - dodaj, da je interpretiranje Jave potencialno ozko grlo => DONE
  - vse izpise kode referencirat, tko ku slike => DONE
- 1.4
  - zakaj odstopanje 86? => DONE
  - pri vseh grafih: oznake na obeh oseh, kadar je prikazan čas s sheme, nujno dodat => DONE
  - zmanjšaj grafe za 15%, da boljše zapolnijo strani => DONE
  - pri vsakem testiranju: iz katere lokacije, hitrost interneta pri odjemalcu, ping, traceroute => DONE
  - bolje opiši pojem "nalaganje" slike => DONE

### Za dodat

- 1.3
  - opis baze slik => DONE
    - kakšne slike (velikost, vsebina, format, loh kak primer)
    - koliko slik
- 1.4
  - absolutni časi nalaganja slik => DONE

## Za naredit do 15. 4.

### Za popravit

ref: https://photos.app.goo.gl/AymBzg7LygFMqAsP9

- slovnične/tipkarske napake
- v vsakem razdelku kratek opis razdelka => DONE
- 1.2.1
  - "Aplikacija" => "Opazovano okolje" => DONE
  - slika 1.1 ni referencirana => DONE
  - "shema aplikacije" => "shema opazovane storitve" => DONE
- 1.2.2
  - naštevanje tipov iskanj z \begin{itemize}, \item ..., \end{itemize} => DONE
  - pri vsakem iskanju format zahteve (JSON code template?)
- 1.2.3
  - pojem "slika" (disk image) uporabljen nejasno => DONE
- 1.3
  - časi v odstavku se ne ujemajo s časi na shemi 1.1 => za popravit => DONE
  - sinhronizacija ur? => DONE
- 1.4
  - kaj je "odzivni čas" in "čas obdelave"? => DONE
  - kdaj se je merilo, koliko meritev, povprečje časov, varianca med časi, ... => DONE
  - bolj natančna določitev časov => DONE

### Za dodat

- merimo tako na clientu kot na serverju => DONE
- sinhronizacija ur, RTT client -> server -> client => DONE (glej 1.3)
- merjenje _vseh_ časov med oddajo zahteve in prejetjem odziva (glej shemo na sliki strani 5) => DONE
- ko se meri več datotek:
  - pri vsaki meritvi: koliko datotek smo preiskali, preden smo našli vzorec? => DONE
  - pri rezultatih: povprečno število datotek, dostopanih med iskanjem => DONE
- simulacija "naključnih" zahtev
  - interval zaupanja: sprejemljiv piksel je n vrednosti stran od iskanega => DONE
    - npr. (105, 24, 21) je 7 vrednosti stran od (104, 21, 18)
- vpliv HDD
  - meritev časa, ki ga porabi server, da naloži sliko v RAM => DONE
  - odstranitev diska: 1 slika, predpomnjena v RAM-u, koliko hitreje? => DONE (samo odštejemo čas nalaganja)
- testiranje, testiranje, testiranje!!!!
