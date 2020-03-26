## Oblig 3
### Deloppgave 1 Team og Prosjekt

####Møtereferat 
13.03.20
Snakket om hva som må  gjøres fremover. Vi ble enige om at Kort, main menu er første prioritet for øyeblikket. Vi fordelte oppgaver igjen for at alle skulle ha noe å jobbe med.
Hvor Bendik skal jobbe med spiller logikk og kort og Ola tar seg av lyd og main menu. Matias jobber med conveyorbelts. Jostein og Hans-Christian hjelper til der det trengs
og skriver brukerhistorier og svarer på oppgavene i oblig3. Vi bestemte oss også for at alle skulle sette seg ordentlig inn i reglene.
meeting minuts 60min. 

20.03.20
Oppdaterte hverandre på hva vi har gjort så langt, og hva vi skal gjøre fremover. Mathias fikset blant annet converyorbelts, og Bendik la til player class, og fikset renderer.
Snakket om struktur. Satt opp board elements. 
meeting minuts 30min. 
Oppdaterte hverandre på hva vi har gjort så langt, og hva vi skal gjøre fremover. Matias fikset blant annet converyorbelts, og Bendik la til player class, og fikset renderer.

24.03.20
meeting minuts 60min.


Rollene fungerer fint, og Matias gjør stadig vekk en fabelaktig jobb som teamleader. 
Vår bruk av Kanban som prosjektmetodikk fungerer fremdeles fint. Vi er kjappe med å si ifra om forandringer, og å gi tilbakemeldinger om forbedringer.
Nå som skolen er stengt har det blitt noe mindre par-programmering, men dette gjøres over discord når vi føler vi har behov eller det vil være en fordel.
Vår bruk av Kanban som prosjektmetodikk fungerer fremdeles fint. Vi er kjappe med å si ifra om foraandringer, og gi tilbakemeldinger om forbedringer.
Nå som skolen er stengt har det blitt lit par progarmmering, men vi hjelper hverandre via screenshare på discord. 
Vi har tilpasset oss godt med møter på discord nå som skolen er stengt. 


####Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette
####skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke
####om feilretting, men om hvordan man jobber og kommuniserer.
####Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.

Vi har priortiert Kort og main menu fremover.
Det funker bra med møter en til to ganger i uken, og det er god kommunikasjon både gjennom Slack, Messenger og på discord.
Gruppedynamikken fungerer utmerket.  


### Deloppgave 2 Krav
Krav: Fungerende kort og main menu
Siden sist har vi fått in en player class, conveyor bellts....

### Brukerhistorie
Jeg som kunde ønsker:

* Å se brettet sånn at jeg kan se hvor på brettet man vinner, hvor man ikke kan gå, hvor man dør etc.

* Brikkens plassering på brettet er for å finne ut hvor man starter hen for å finne en strategi til å komme seg til mål.

* En roteringsfunksjon til brikken sånn at jeg kan endre på hvilken vei jeg skal til.
Farlige og ufarlige fellefunksjoner, der ved at man går til de farlige vil man miste liv, eventuelt dø.
Og ved at man går til de ufarlige vil brikken bevege seg eller rotere seg i en ny retning uten sin vilje.

* En kortfunksjon der man velger hva man skal gjøre når det er min tur.

* Et flagg for å se hvor man vinner.

Jeg som spiller ønsker:

* At RoboRally er et flerspiller spill for å kunne spille med andre

* 


KRAV Må lage flere tester for å sjekke at koden fungerer nå og i fremtiden 
Flere spillere
Main menu
Lasere må sjekke 1 og 1 rute foran seg til de finner en vegg eller spiller så stopper de 

MVP: Flere spiller, må kunne styres av spilleren med kort, må kunne dø og vinne 
Ingen bugs for øyeblikket (som vi vet om) 


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

* Ved at man går på *Hullet* vil brikken dø

* Ved at man går på *Gears* vil brikken snu seg

* Ved at man går på *Conveyor belt* vil brikken flytte seg i den retningen den peker

Prioritet fremover:
Tester alle tulle med for å få de til å funke, om noen finner god mal send til andre
Må få kortene til å kunne bli brukt av spillere så vi kan teste de (ikke nødvendigvis flere kort atm) 
