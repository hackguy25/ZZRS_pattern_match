# Zapiski

Datoteka za dumpanje informacij o nalogi.

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
  - časi v odstavku se ne ujemajo s časi na shemi 1.1 => za popravit
  - sinhronizacija ur?
- 1.4
  - kaj je "odzivni čas" in "čas obdelave"?
  - kdaj se je merilo, koliko meritev, povprečje časov, varianca med časi, ...
  - bolj natančna določitev časov

### Za dodat

- merimo tako na clientu kot na serverju
- sinhronizacija ur, RTT client -> server -> client
- merjenje _vseh_ časov med oddajo zahteve in prejetjem odziva (glej shemo na sliki strani 5)
- ko se meri več datotek:
  - pri vsaki meritvi: koliko datotek smo preiskali, preden smo našli vzorec?
  - pri rezultatih: povprečno število datotek, dostopanih med iskanjem
- simulacija "naključnih" zahtev
  - interval zaupanja: sprejemljiv piksel je n vrednosti stran od iskanega
    - npr. (105, 24, 21) je 7 vrednosti stran od (104, 21, 18)
- vpliv HDD
  - meritev časa, ki ga porabi server, da naloži sliko v RAM
  - odstranitev diska: 1 slika, predpomnjena v RAM-u, koliko hitreje?
- testiranje, testiranje, testiranje!!!!
