# Zapiski

Datoteka za dumpanje informacij o nalogi.

## Predlog "fizične" strukture (Blaž)

Shema: https://docs.google.com/drawings/d/1NxHEyr6ILuLKZGfZOJZOe72QnYa3tz8xQYlqSyKkOrU

### Front-end
#### enostavna HTML stran
- naslov
- kratek opis uporabe
- polje za vnos vzorca (npr. color picker, file upload, ...)
- gumb "Pošlji" (skripta, ki v JSON obliki pošlje serverju zahtevo)
- polje z rezultati

### Top-level back-end
#### server, ki sprejme zahtevo, jo razpošlje processing node-om, zbere rezultate in jih vrne uporabniku
- osnova: https://hub.docker.com/_/openjdk?tab=description
- Java/OpenJDK server

### Processing node
#### server, ki hrani del slik, sprejema navodila top-level serverja, izvaja iskanje nad njimi
- ista osnova kot top-level
- osnova skalabilnosti
- za začetek dovolj 1

## Predlog strukture zahtevkov (Blaž)

Zahtevek uporabnika: [string tip, [?? vzorec, ?? maska]]
- za začetek dovolj 1 piksel: ["pixel", [int r, int g, int b]]
Rezultati: [[ime slike, y, x], ...]
