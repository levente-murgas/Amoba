# Amőba

Az amőba egy kétszemélyes, táblajáték, ahol a két játékos felváltva tesz X vagy O jeleket a ”táblára”,
amit egy négyzetácsos mező képez. A játékosoknak a győzelemhez P db (pl. P=3) saját ”bábut” kell
egy vonalban rakniuk. Ezt persze a másik játékos adakályozhatja, azáltal, hogy a kialakulni látszó
vonal végére, vagy közbe teszi a saját bábuját. A vonal lehet függőleges, vízszintes, vagy diagonális is.
Bábut csak még üres négyzetekre szabad rakni. Az a játékos, akinek nem sikerült összehozni a vonalat
időben, a játék vesztese lesz.

A tábla mérete a minimum 3X3-tól a maximum 20X20-ig bármekkora alakot ölthet. Amennyiben a
tábla összes négyzetére már került bábu, de egyik játékosnak sem sikerült, kirakni a 3 ugyanolyan
bábuból álló vonalat, akkor a játék döntetlennel ér véget.

## Use-Case-ek, avagy hogyan használd a programot

A felhasználót az alkalmazás indítása utána Főmenü nézet fogadja. A Főmenü a felhasználónak
lehetősége van beállítani a tábla méretét (min. 3X3 max. 20X20), új játékot indíthat, valamint a
korábbi játékok mentéseihez és innen navigálhat el.

**Játék paramétereinek beállítása:**

 A pálya mérete beállítható a magasság és szélesség paraméterek egymástól független
változtatásával. A győzelemhez szükséges egybefüggő bábuk száma is parametizált, beállítható.

**Új játék kezdése:**

Ha a felhasználó új játékot szeretne indítani a ”Start” gombra kell kattintania, ez átviszi őt a Játék
nézetbe.

**Játék betöltése:**

Ha a felhasználó egy korábbi játékot szeretne folytatni, akkor a Főmenüből a ”Betölt” gombon
keresztül eljuthat a Mentések nézethez.
Itt kiválaszthatja, hogy az 5 mentett játékállás közül, melyiket szeretné folytatni. Ha például a
”Mentés1” gombra kattint, azzal ugyanúgy eljut a Játék nézetbe, mintha a ”Startra” kattintott volna,
a Főmenüből, csak jelen esetben nem egy új játék, hanem egy korábbi be nem fejezett játszma során
a ”Mentés1” névre keresztelt játékállás fog megjelenni a táblán.
A Játék nézetben láthatjuk a négyzetrácsos mezőkből álló táblát, illetve a tábla felett van egy panel,
ahova a játék végén kiírásra kerül, hogy az X-szel vagy az O-rel játszó játékos nyert (döntetlen esetén
az alkalmazás döntetlent ír ki). Az ablak felső szegélyénél van egy menü sáv File és Help
menüpontokkal.

**Kilépés a programból:**

A felhasználó ki tud lépni a programból akár a főmenüben akár a játék felületen vagy a jobb felső
sarokban lévő X-re kattintva bárhol a programban vagy az „Exit Game” gombra kattintva a
főmenüben.

**Játék mentése:**
A File menüt megnyitva lenyílik egy ”Save & Exit” menüpont amivel úgy tud kilépni a felhasználó a
játékból, hogy lementheti a játékállást. A felhasználó a játékállás mentése után visszakerül a
főmenübe.

**Kilépés a főmenübe:**
A File menüt megnyitva lenyílik egy ”Back to Main menu” menüpont, amivel pedig mentés nélkül tud
kilépni a játékból.

**A játékról érdeklődés:**
Az Help menüt megnyitva lenyílik nekünk egy ”About” menüpont ami elvisz minket a játék wikipédia
oldalára.

**Bábu lerakása:**
Maga a játék a feljebb már ismertetett szabályokat követve zajlik, először az X-szel játszó játékos lép,
kiválaszt egy mezőt, rákattint. Ekkor egy X megjelenik a kiválasztott mezőn. Utána a O-rel játszó
játékos lép, és így tovább a játék végéig. A játék befejeztével kiírja az alkalmazás melyik játékos nyert
és utána visszalép a Főmenü nézetbe.
