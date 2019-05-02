# Zapiski

Datoteka za dumpanje informacij o nalogi.

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
