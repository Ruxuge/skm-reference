# Przerobic nasz symulator SKM tak, by:
[ ] pociagi i przedzialy pochodzily z bazy danych (pociagi moga teraz miec rozna ilosc przedzialow, kazdy przedzial moze miec rozna pojemnosc),
    konfiguracja poczatkowa jest dowolna (przyklad - po jednym pociagu w Gdyni i w Gdansku, kazdy pociag inna ilosc przedzialow, zaczynaja puste),

[ ] Controllery do CRUD'a dla pociagu i przedzialu (Create, Read, Update, Delete),

[ ] wykorzystac liquibase do zainicjalizowania i obslugi zmian w schemie bazodanowej, 

[ ] Controller powinien teraz uzywac ResponseEntity do komunikacji zwrotnej + odpowiednie HttpStatus (prosta obsluga bledow -> blad = kod 500),

[ ] pociagi powinny dalej jezdzic, ladowac i rozladowywac ludzi, robic postoje, zawracac na stacjach koncowych.