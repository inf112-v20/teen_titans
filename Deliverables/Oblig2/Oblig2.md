## Oblig 2
### Deloppgave 1 Prosjekt og Prosjektstruktur
Rollene fungerer fint, og Matias gjør en fabelaktig jobb som teamleader.
Vi måtte få inn en dedikert test-skriver i prosjektet, som ble både Jostein og 
Hans Christian. Bendik fungerer veldig fint som interior designer. Det funker bra med møter hvertfall en gang i uken, og det er god
kommunikasjon både gjennom Slack, Messenger og på skolen.
Kanban fungerer bra som prosjektmetodikk der vi er kjappe med å si ifra
om forandringer men også gi tilbakemelding om forbedringer. Vi begrenser
oss ved å ta få ting om gangen, får de til å fungere før vi beveger oss
videre. Vi skal forbedre oss på møter, med tanke på hyppighet og oppmøte i tide.
Vi har kommet til et punkt der vi må spille Robo Rally brettspillet for å
være sikkre på at det vi gjør videre er korrekt.
Selv om det er spredd erfaring innenfor koding i gruppen
er det bra med arbeida fra alle, der de med mye erfaring lærer
de andre. Bendik tar seg av seg av refaktorering og finpussing av koden,
siden han har ett øye for detaljer. Grunner for mye commits fra en person er fordi
vi som oftes jobber i par. Par-koding har sine fordeler som at begge kan
lære direkte av hverandre, men og at det er lettere å se sammenhenger
og samtidig diskutere valg. Hans Christian og Jostein har jobbet ilag med tester, hovedsakelig på Hans Christian sin PC,
så derfor vil det slå ut litt på commits fra Jostein.
Forberedings punkt til neste sprint: Flere møter, arbeidsfordeling, mer spredde commits.

####Møtereferat
 Møte 23.02.20
 - Matias og Ola hadde et møte der det ble gjennomgang av hva som måttte gjøres før neste innlevering,
 hva som holdt på å bli gjort, og når det skulle gjøres. Avtalte en arbeidsdag
 den 27.02 der alle i gruppen møtes og jobber. Det ble bestemt hvem som
 skal jobbe med hva, for å få spredt arbeidet, og slik at det ble mer jevne commits.

### Deloppgave 2 Krav
1. Ett viktig krav som må følges er at spillet er kompatibelt med både
Windows og Linux (OSX), slik at spillet fungerer for hvilken som helst spiller. Og siden vi jobber med 3 Windows PC-er og 2
MACs får vi alltid testet kompabilitet. Ved å kjøre spillet på forskjellige OS arbeider vi alltid med dette kravet.
2. Det viktigste å få til nå er å ha klasser for de forskjellige
objektene. Å få mappet til lese av robotene sine posisjoner, for å
si noe om posisjonen (legal, illegal, legal but dead). 
3. Tester er viktige for at vi skal oppnå krav. Viktige tester som for
eksempel å se at en robot går korrekt, og at den "dør" når den faller ut av mappet.
4. Multithreading vil få spillet til å kjøre mer effektiv, og det er en naturlig abstraksjon (seperation of church and  state).
Akseptansekriteriet for multithreading er at critical sections ikke blir skadet av parrallelt kjørende tråder. Arbeidsoppgaver
gjør Bendik siden han har god peiling på multithreading. 
5. Legge til programkort slik at spilleren kan gi komandoer til roboten sin. Akseptansekriteriene 
blir at spilleren kan feilfritt få roboten til bevege seg og at game-loopen klarer å 
uavhengig utføre komandoene på roboten. Arbeidsoppgaven blir da å finne ut hvordan vi skal designe og
implementere kortene.

Multithreading og testing blir aktivt jobbet med. Kort er høgt prioritert siden 
de er en stor del av spillmekanikken, siden det er slik spilleren skal bevege roboten.
Forskjellige klasser for hvert obkjekt blir gjort hver gang vi lager et nytt obkjet.
MVP er nå at vi skal ha kort som kan styres av spilleren, og at roboten kan interacte med
vegger og farer med brettet, som laser, vegger og hull (utfordring). Flag som er 
win-condition er fortsatt noe som må fikses.
Siden forrgie gang har vi begynt på multithreading, noe som kan minne om hva programkortene skal kunne gjøre,
og refactoring. Krava vi hadde før var å se spillerbrettet og spiller, som vi er ferdig med.
For øyeblikket har vi ingen bugs, men vi er klar over at det kommer til å skje.

###
Vi er ferdige med kravene som vi mente var viktigst, som å vise spiller,
spillerbrett og flag. Nå som vi vet at dette fungerer, så skal vi kunne
si at en spiller får poeng/vinner når den går på flagget, og at forskjellige
tiles på brettet gjør forskjellige ting (f.eks. vegger, hull, rullebånd, etc.)


