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
