<p align="center"> 
	<img alt="carcassonne logo" src="src/main/resources/splash@200pct.png?raw=true" width="500">
</p>

[![CI status](https://github.com/tsaglam/Carcassonne/actions/workflows/CI.yml/badge.svg)](https://github.com/tsaglam/Carcassonne/actions)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/y/tsaglam/Carcassonne)](https://github.com/tsaglam/Carcassonne/pulse)
[![Lines of code](https://img.shields.io/tokei/lines/github/tsaglam/Carcassonne)](https://github.com/tsaglam/Carcassonne/graphs/contributors)
[![License](https://img.shields.io/github/license/tsaglam/Carcassonne?color=informational)](https://github.com/tsaglam/Carcassonne/blob/master/LICENSE)

Naam: Bart Hogenes
Studentnummer 500694053

Dit open-source project (origineel) heb ik gebruikt voor het vak Testing om aan te tonen dat ik de vereiste JUnit testtechnieken kan toepassen uit het boek "Pragmatic Unit Testing in Java 8 with JUnit" van Langr, Hunt en Thomas. 
Zoals te zien is aan de badge boven deze tekst voldoet dit project aan de minimale 1000 regels code.
Ook is in de onderstaande screenshot te zien dat de twee geteste classes (`TileStack` en `Round`) beide voor 100% getest zijn op zowel method, line als branch niveau:
![Run all with coverage result](coverage.png)

De unit tests zijn opgedeeld aan de hand van hun setup code om dubbele code tegen te gaan.  
De testmethodes zelf zijn triple-A opgebouwd.  
Er is gedacht aan de acroniemen `FIRST`, `RIGHT` en `CORRECT` bij het maken van de unit tests om aan de 100% branch coverage te komen en om moeilijk te vinden fouten te testen.  
De volgende 5 verschillende `Assert.assertXXXX` methodes zijn gebruikt:  
- assertEquals
- assertNotEquals
- assertTrue
- assertFalse
- assertThrows

De volgende 5 verschillende Hamcrest matchers zijn gebruikt in `EmptyTileDistributionTest` (ook al waren de `Assert` varianten duidelijker):
- equalTo
- is
- nullValue
- not
- describedAs

De vereiste `Invariant` wordt getest in de `tearDown` van de `EmptyTileDistributionTest` behaald.  
Er wordt ook getest op `forced errors` in iedere testklasse.

### Over dit project
Dit is een licht aangepaste, digitale versie van het bordspel Carcassonne. <br>
Carcassonne is een tegelgebasseerd bordspel met een middeleeuwse setting gecreëerd door [Klaus-Jürgen Wrede](https://www.kjwrede.de/) en gepubliceerd door [Hans im Glück](https://www.hans-im-glueck.de/en/verlag.html).
De officiele regels zijn hier te vinden: [Part 1](https://images.zmangames.com/filer_public/d5/20/d5208d61-8583-478b-a06d-b49fc9cd7aaa/zm7810_carcassonne_rules.pdf), [Part 2](https://images.zmangames.com/filer_public/14/af/14af825c-9879-42b8-851d-35ce41df7767/carcassonne-supplement.pdf).

<p align="center">
	<img alt="a screenshot of the game" src="preview.jpg?raw=true" width="850">
</p>

#### Vereisten
- [Java SE 11](https://www.oracle.com/de/java/technologies/javase-downloads.html) of nieuwer.



