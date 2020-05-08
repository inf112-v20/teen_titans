## Oblig 4
### Deloppgave 1 Team og Prosjekt

####Møtereferat
03.04.20
Meeting minutes: 90min.
Meeting attendance: alle på gruppen
Ny oblig ute, så vi har gått igjennom den. Vi ble enige om hva som burdee gjøres fremover og hva som har prioritet.
Vi ble enige om at check points, lasere lydeffekter hadde størst prioritering. Alle oppdaterte hverandre på hva som har blitt endret
på siden sist, for å holde alle oppdatert. Vi fordelte oppgaver, og ble enige om nyttt møtte skulle bli etter påske, men at det bare var
å ta kontakt på messanger/discord/slack om det var noe.

17.04.20
Meeting minutes: 100min.
Meeting attendance: alle på gruppen
En stund siden sist, og derfor en god del oppdateringer. Blant annet main menu som fungerer. Muligheter for å kunne velge karaakter, og gjør turn via kort.
Etter å ha oppdart hverandre ble vi enige om prioriteringer som da ble: Fungerende vegger og lasere, meste parten er "ferdig" bortsett fra spill logikk.
Enige om å ta neste møte på fredag med mindre noe viktig dukker opp.

24.04.20
Meeting minutes: 120min.
Meeting attendance: alle på gruppen
Startet som vanlig med å oppdatere hverandre på vår progress. Nye klasser conveyor belts og gears. Vegger og conveyor belts skal fungere, og lasere startet på.
Flere kort lagt til som U-turn.
Mål for idag: Brennbart gulv og lasere til å fungere, og starte på et hp-system. (for å displaye hp osv.)
Nytt møte neste fredag. 

02.05.20
Meeting minutes: 90min.
Meeting attendance: Ola & Mathias
Små arbeid for presentsajon, litt bug fixes, la til kjente bugs på projectboard, og gikk igjennom ting som manglet for oblig4. 

04.05.20
Meeting minutes:120min
meeting attendance: Alle sammen
Brukte  2timer før presentasjon for å gå igjennom bugs/bug fixes, og klaregjøre presentasjon.


07.08.20
Meeting minutes: 150min
Meeting attendance: All sammen
Tiden ble brukt for å gå igjennom bugs og prøve å fikse de. Innspurt til innlevering.

08.08.20
Meeting minutes:
Meeting attendance: all sammen
Tiden ble brukt for siste bug fixes før innlevereing


Rollene fungerer fint, og Matias gjør stadig vekk en fabelaktig jobb som teamleader. Vi ser derfor ingen grunn til å endre på vår rollestruktur, men vi muligens burde være litt tydligeree på tilldeling av arbeid.
Vår bruk av Kanban som prosjektmetodikk fungerer fint som oftest. Vi er kjappe med å si ifra om forandringer, og å gi tilbakemeldinger om forbedringer. Til tider er vi slappe med vår arbeidsmetode og de blir mye koding på kort tid,
og ikk en jevn progresjon. 
Vi er stort sett fornøyd med våre valg, så ser ikke noe grunn til å endre på noe for øyeblikket.
Vi er flinke til å høre på hverandres meninger, og gi gode tilbakemeldinger. Ingen spørsmål er dumme og man får hjelp når det trengs. 
Nå som skolen er stengt har vi måtte flytte over til digitale løsninger som screenshare via discord, og all kommunikasjon over discord/slack/messanger. Det er selvsagt lettere å vise ting fysisk, men denne løsningen fungerer greit.
Vi har blitt rimelig flink på møter via discord. Arbeidsmoralen har blitt litt dårligere, som har ført til at vi jobber mer i sprint enn vi burde.
Vi har bare hatt møter 1 gang i uken i det siste, dette hara ført til ganske store oppdateringer mellom hvert møte. Dette er noe vi kan
forbedre oss på. Samtidig burde vi nok spre arbeidsmengeden litt mer. Vi har hatt tendenser til å benytte oss av "skippertak".
Kanban bygger jo trossalt på att man skal jobbe litt hele tiden, for å ikke bli "overwhelmed".
I starten fikk vi møtes i person for møtene, og dette var til stor fordel når vi trengte hjelp. Etter nedstengningen ble det slutt på dette,
som førte til litt tap av motivasjon, og litt tregere start. Vi har blitt bedre og bedre på onlin gruppe møter, og det fungerer greit nå.
Programmeringsmessig er det også et frustrasjonsmoment å ikke få sitte sammen. I tilfeller hvor man sitter fast og trenger hjelp fra et annet gruppemedlem, blir hjelpen mindre effektiv. Vi merker hvor viktig peking og kroppsspråk er når man diskuterer kode.
En stemme i seg selv er ikke nok.


####Noen ting å forbedre
Vi må bli bedre på å bruke prosjektboard igjen (slappe med å oppdatere).
Vi må spre arbeidsmengenden mer effektivt, og mer tydligere inndeling av arbeid.  Per nå har vi lange dager hvor vi skriver enorme mengder kode, og dager hvor vi ikke gjør noen ting.





### Deloppgave 2 Krav
#### Krav:
Krav #1: 
Multiplayer. Som en del av MVP må flere spillere kunne spille sammen. Akseptansekriterie er at man skal kunne hoste et spill/joine via en IP adresse, og spille symkronisert sammen.<br>

Krav 2: Spillregler. Som en del av MVP må man kunne starte et spill som følger spillreglene. Akseptansekriterie er at man kan starte et spill hvor man kan gå tom for liv og tape, eller nå alle flaggene og vinne.

Krav 3: Valg av robotmodell. Spilleren skal selv kunne velge sin egen spillkarakter i lobbymenyen. Akseptansekriterie er at spiller man har valgt i lobbyen skal være den samme som dukker opp i selve spillet.

Krav 4: Conveyor belts. En robot som trår på en conveyor-belt-tile skal flyttes i retningen beltet viser. Akseptansekriterie er at beltet fungerer som beskrevet.

Krav 5: Lasere. En robot som står i veien for en laser skal miste et halvt hjerte. Akseptansekriterie er at laser fungerer som beskrevet.

Krav 6: Win-HUD. Etter en spiller har vunnet skal modellen dens vises i stor form på skjermen. Akseptansekriterie: etter en spiller har nådd alle 4 checkpoints skal modellen dens dukke opp.

### Brukerhistorie

Jeg som kunde ønsker:

* Å ha checkpoints på brettet for å kunne respawne hvor man dør.
 
* Lasere.

* Forskjellige karakterer (pokemon sprites) man kan velge mellom for å kjenne igjen min egen robot.

* 9 tilfeldige utdelte kort, hvor jeg kan velge 3.

* Brennbare gulv.

Jeg som spiller ønsker:

* Forskjellige kort for hver tur som går sånn at jeg ikke kan bruke de samme bevegelsene hele tiden

* Velge 5 kort som beveger seg i den retningen jeg har valgt i rekkefølge.
Altså at hvis jeg velger kortet som beveger meg framover først og snu til venstre som andre kort så vil jeg bevege meg framover først og så snu til venstre.

* At alle får sin egen tur, slik at en spiller ikke får flere turer før det er en annen spillers tur.

* Å se hvor mye hp jeg har igjen, hvis jeg går i brennbart gulv, laser etc så vil jeg se at hp'en min minker.

Liste over hva vi har fokusert på:

* Multiplayer multiplayer multiplayer
* UI
* Abstraksjon
* Checkpoints
* Bakgrunnsmusikk
* Forskjellige velgbare karakterer
* Flere kort
* Brennbare gulv
* Respawn
* Spillere får sine tur til velge kort
* Hp