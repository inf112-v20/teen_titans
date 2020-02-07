# INF112 Maven template 
Simple skeleton with libgdx. 


## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.



# Assignment 1
## Oppgave 1: Teamorganisering

Kompetanse: Vi går alle de vanlige informatikklinjene, teknologi/vitenskap/sikkerhet, og har dermed en grunnleggende kompetanse for Java.
Bendik har litt kompetanse med git

Roller:
#### Teamleder: Matias Sunde
	Gruppens Alpha
#### Customer Relations: Ola Tønnes Straum
	Har sosiale ferdigheter oss andre mangler.
#### Designer: Hans-Christian Siebke
	Et øye for pene farger
#### Tester: Jostein Phung
	En testperson siden fødsel
#### Developer: Bendik Solevåg
	Fikk A i inf101.

## Oppgave 2: Få oversikt over forventet produkt


##### Ha et spillebrett
##### Vise en brikke
##### Kunne flytte en brikke
##### spille fra ulike maskiner
##### dele ut kort
##### velge kort (5 av 9)
##### flytte brikke utfra kort
##### dele ut nye kort ved ny runde
##### vise flere (i alle fall to) brikker på samme brett
##### dele ut kort til hver robot
##### flytte flere brikker samtidig
##### flytte brikker utfra prioritet på kort
##### flagg på brettet
##### kunne registrere at en robot har vært innom et flagg
##### håndtere konflikter i bevegelse riktig
##### kunne legge igjen backup
##### restarte fra backup ved ødeleggelse
##### går du i et hull, blir du ødelagt, mister et liv og begynner fra forrige backup
##### går du av brettet, blir du ødelagt, mister et liv og begynner fra forrige backup
##### blir du skutt i fillebiter (9 i skade) blir du ødelagt, mister et liv og begynner fra forrige backup
##### vender en robot mot deg ved slutten av en fase blir du skutt og får en i skade
##### får du skade får du mindre kort i henhold til skaden du har
##### kan ikke gå gjennom vegger
##### for mye skade brenner fast programkort fra runde til runde

Regler for spillet:
Går man i hullet mister man et liv.
Mister en 9 liv er du død.
Går man til alle flaggene vinner man.

Overordnet mål: Vi ønsker å implementere en fungerende digital versjon av spillet Robo Rally.

## Oppgave 3: Velg og tilpass en prosess for laget

Prosjektmetodikk: Kjører Kanban stil med innspill fra testing og parprogrammering

Møter: Hver torsdag 10:00 - 14:00
Mer om nødvendig, f. eks. før en innlevering, under obligatoriske oppgaver etc.
Kommunikasjon mellom møter: Sjekker slack hver dag, og en messanger grupper for små beskjeder
Arbeidsfordeling:
Hvem som gjør hva blir delt inn på møtene, evt på slack
Oppfølging av arbeid:
På møtene går vi over hva som hver person har gjort slik at alle har kontroll på programmet
#### Deling og oppbevaring av felles dokumenter, diagram og kodebase:
Github, Slack og Google Docs

## Oppgave 4: Kode

“Jeg som kunde ønsker…”
#### Jeg som spiller ønsker å kunne se brettet for å:
##### For å se hvor jeg starter
##### Se hvordan brettet ser ut
##### Planlegge mest effektiv programmering for brikken min
##### Se hvor feller og fiendtlige brikker er
##### For å se hvor flaggene er

Jeg som spiller ønsker å plassere brikken min for å:
Spille

Jeg som spiller ønsker å få utdelt programkort for å:
Kunne lage et program for brikken min 



## Summary of assignment 1

Vi har jobbet bra sammen som en gruppe. Vi slet litt med å plassere kameraet korrekt, men det gikk til slutt.

Arbeidsfordelingen gikk bra, alle fikk gjort noe, selv om kompetansenivået er variert.
Det var få ting å gjøre så vi parprogramerte det som måtte gjøres.
Vi har litt forskjellig med commits, men mange av de er bare små endringer for å lære hvordan git fungerer.

Det som må gjøres nå er å få laget flere regler for spillet, som at et spiller dør, vinner, har flere liv, skyter
lasere og bånd på gulvet som beveger seg. Det er bedre å få slik til å funke, før en fokuserer på runder, programerings
kort og lignende.
