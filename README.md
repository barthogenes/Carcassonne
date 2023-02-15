<p align="center"> 
	<img alt="carcassonne logo" src="src/main/resources/splash@200pct.png?raw=true" width="500">
</p>

[![CI status](https://github.com/tsaglam/Carcassonne/actions/workflows/CI.yml/badge.svg)](https://github.com/tsaglam/Carcassonne/actions)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/y/tsaglam/Carcassonne)](https://github.com/tsaglam/Carcassonne/pulse)
[![Lines of code](https://img.shields.io/tokei/lines/github/tsaglam/Carcassonne)](https://github.com/tsaglam/Carcassonne/graphs/contributors)
[![License](https://img.shields.io/github/license/tsaglam/Carcassonne?color=informational)](https://github.com/tsaglam/Carcassonne/blob/master/LICENSE)

Naam: Bart Hogenes

Dit project heb ik gebruikt om aan te tonen dat ik de JUnit testtechnieken kan toepassen uit het boek "Pragmatic Unit Testing in Java 8 with JUnit" van Langr, Hunt en Thomas.   
In de onderstaande screenshot te zien dat de twee geteste classes (`TileStack` en `Round`) beide voor 100% getest zijn op zowel method, line als branch niveau:
![Run all with coverage result](coverage.png)

De unit tests zijn opgedeeld op basis van hun setup om zoveel mogelijk dubbele code te voorkomen.  
De testmethodes zelf zijn triple-A opgebouwd.  
Er is rekening gehouden met de acroniemen `FIRST`, `RIGHT` en `CORRECT` bij het maken van de unit tests.  
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

Er is een `Invariant test` te vinden in de `tearDown` van de `EmptyTileDistributionTest`.  
Er wordt ook getest op `forced errors` voor zover ik die heb kunnen vinden.

### Over dit project
Dit is een licht aangepaste, digitale versie van het bordspel Carcassonne. <br>
Carcassonne is een tegelgebasseerd bordspel met een middeleeuwse setting gecreëerd door [Klaus-Jürgen Wrede](https://www.kjwrede.de/) en gepubliceerd door [Hans im Glück](https://www.hans-im-glueck.de/en/verlag.html).
De officiele regels zijn hier te vinden: [Part 1](https://images.zmangames.com/filer_public/d5/20/d5208d61-8583-478b-a06d-b49fc9cd7aaa/zm7810_carcassonne_rules.pdf), [Part 2](https://images.zmangames.com/filer_public/14/af/14af825c-9879-42b8-851d-35ce41df7767/carcassonne-supplement.pdf).

<p align="center">
	<img alt="a screenshot of the game" src="preview.jpg?raw=true" width="850">
</p>

#### Vereisten
- [Java SE 11](https://www.oracle.com/de/java/technologies/javase-downloads.html) of nieuwer.



