## Oblig 3
### Deloppgave 1 Team og Prosjekt

####Møtereferat
13.03.20
Snakket om hva som må  gjøres fremover. Vi ble enige om at Kort, main menu er første prioritet for øyeblikket. Vi fordelte oppgaver igjen for at alle skulle ha noe å jobbe med.
Hvor Bendik skal jobbe med spiller logikk og kort og Ola tar seg av lyd og main menu. Matias jobber med conveyorbelts. Jostein og Hans-Christian hjelper til der det trengs
og skriver brukerhistorier og svarer på oppgavene i oblig3. Vi bestemte oss også for at alle skulle sette seg ordentlig inn i reglene.
meeting minutes 60min.

20.03.20
Oppdaterte hverandre på hva vi har gjort så langt, og hva vi skal gjøre fremover. Mathias fikset blant annet converyorbelts, og Bendik la til player class, og fikset renderer.
Snakket om struktur. Satt opp board elements.
meeting minutes 30min.

24.03.20
Oppdaterte hverandre på hva vi har gjort siden sist. Gikk gjennom oblig3 og fordelete oppgaver. Ble enige om at vi må være flinkere med å holde prosjekt boardet oppdatert.
meeting minutes 60min.

26.03.20
Oppdaterte hverandre på hva vi har gjort siden sist. Gikk gjennom obli3 og retteskjema et par ganger for å være sikre på at vi har alt klar til innlevrering.
Endret litt på tester for å tilpasse nye movement. Gått igjennom kode for å rydde litt i ubrukt kode.
meeting minutes 90min.

27.03.20
Jobbet med innlevering nummer 3. Bendik oppdaterte gruppen på endringen han gjorde med de nye kortene. Jobbet mye med main menu, men fikk det ikke til å fungere 100%.
meeting minutes 180min.

Rollene fungerer fint, og Matias gjør stadig vekk en fabelaktig jobb som teamleader. Vi ser derfor ingen grunn til å endre på vår rollestruktur.
Vår bruk av Kanban som prosjektmetodikk fungerer fremdeles fint. Vi er kjappe med å si ifra om forandringer, og å gi tilbakemeldinger om forbedringer. Vi er stort sett fornøyd med våre valg, så ser ikke noe grunn til å endre på noe for øyeblikket.
Vi er flinke til å høre på hverandres meninger, og gi gode tilbakemeldinger. Ingen spørsmål er dumme og man får hjelp når det trengs. Nå som skolen er stengt
har vi måtte flytte over til digitale løsninger som screenshare via discord, og all kommunikasjon over discord/slack/messanger. Det er selvsagt lettere å vise ting fysisk, men denne løsningen fungerer greit.
Vi har blitt rimelig flink på møter via discord.
I det siste har vi vært litt slappe med å oppdatere prosjektboard, noe som vi må forbedre oss på. Samtidig burde vi nok spre arbeidsmengeden litt mer. Vi har hatt tendenser til å benytte oss av "skippertak".
Kanban bygger jo trossalt på att man skal jobbe litt hele tiden, for å ikke bli "overwhelmed".
Programmeringsmessig er det også et frustrasjonsmoment å ikke få sitte sammen. I tilfeller hvor man sitter fast og trenger hjelp fra et annet gruppemedlem, blir hjelpen mindre effektiv. Vi merker hvor viktig peking og kroppsspråk er når man diskuterer kode.
En stemme i seg selv er ikke nok.

####Noen ting å forbedre
Vi må bli bedre på å bruke prosjektboard igjen (slappe med å oppdatere).
Vi må spre arbeidsmengenden mer effektivt. Per nå har vi lange dager hvor vi skriver enorme mengder kode, og dager hvor vi ikke gjør noen ting.

Vi har priortiert Kort og main menu fremover.
Det funker bra med møter en til to ganger i uken, og det er god kommunikasjon både gjennom Slack, Messenger og på discord. Etter at universitet ble stengt har det blitt litt flere møter enn før. Som helt klart er en fordel.
Gruppedynamikken og vår kommunikasjon fungerer.  




### Deloppgave 2 Krav
Krav: Fungerende kort og main menu
Siden sist har vi fått in en player class, conveyor belts....

### Brukerhistorie
Jeg som kunde ønsker:

* Å se brettet sånn at jeg kan se hvor på brettet man vinner, hvor man ikke kan gå, hvor man dør etc.

* Brikkens plassering på brettet er for å finne ut hvor man starter hen for å finne en strategi til å komme seg til mål.

* En roteringsfunksjon til brikken sånn at jeg kan endre på hvilken vei jeg skal til.

* Farlige og ufarlige fellefunksjoner, der ved at man går til de farlige vil man miste liv, eventuelt dø.
Og ved at man går til de ufarlige vil brikken bevege seg eller rotere seg i en ny retning uten sin vilje.

* En kortfunksjon der man velger hva man skal gjøre når det er min tur.

* Laserfunksjon, både i brettet som skader brikkens helse og en for brikkens bruk for å skyte på andre brikker.

* Et flagg for å se hvor man vinner.

* At når en brikke dør, vil brikken bli borte fra brettet og det vil komme opp at du har dødd.

Jeg som spiller ønsker:

* At RoboRally er et flerspiller spill for å kunne spille med andre.

*

MVP er nå at vi skal ha kort som kan styres av spilleren, og at roboten kan interagere med farer på brettet.

### Deloppgave 3 Produktleveranse og kodekvalitet

Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelderne å
bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.
Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OSX.
Lever klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.)
Kodekvalitet og testdekning vektlegges. Merk at testene dere skriver skal brukes i produktet. Det kan
være smart å skrive manuelle tester for å teste det som er grafisk.
Utførte oppgaver skal være ferdige.
Hvis dere tester manuelt: lever beskrivelser av hvordan testen foregår, slik at gruppeleder kan utføre
testen selv.
Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som
committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også
designfiler)

#### Manuelle tester

* Å bevege seg et skritt fram bruker man arrow key up, og rotere brikken bruker man arrow key right / left.
Eks. et skritt nord er bare å trykke på arrow key up, et skritt west er arrow key left & up,
et skritt east er arrow key right & up og et skritt south er 2 trykk på arrow key left / right & 1 up.
Dette er eksempel fra når brikken peker opp mot nord

* Ved at man går på *Hult* vil brikken dø

* Ved at man går på *Flagg* vil brikken "vinne"

* Ved at man går på *Gears* vil brikken snu seg

* Ved at man går på *Conveyor belt* vil brikken flytte seg i den retningen beltet peker
