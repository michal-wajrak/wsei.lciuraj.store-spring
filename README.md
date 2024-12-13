# Zadania do samodzielnego wykonania

## Zadanie 1: Utworzenie obiektu `Item`

- **Opis:** Utwórz klasę encji, która będzie reprezentować model danych przedmiotu do zakupu `Item`. Klasa powinna
  zawierać pola takie jak `id`, `name`, `quantity`. Dodatkowo, utwórz wszystkie inne obiekty, które będą niezbędne do
  zrealizowania funkcjonalności analogicznej do tej w przypadku koszyka `Basket`. Finalnie, w kontrolerze powinny
  znaleźć się metody analogiczne do tych, które są obecne dla koszyka `Basket`.
- **Wskazówki:**
    - Wzoruj się na obiekcie koszyka.

## Zadanie 2: Zmodyfikowanie obiektu koszyka `Basket`

- **Opis:** Zmodyfikuj obiekt koszyka `Basket`, tak aby przechowywał odniesienia do obiektów `Item`.
- **Wskazówki:**
    - Zamodeluj odpowiednią relację w klasie encji.
    - Zmodyfikuj pozostałe miejsca w kodzie, aby wszystkie funkcjonalności działały poprawnie.

## Zadanie 3: Rozszerzenie funkcjonalności obiektu `Item`

- **Opis:** Zmodyfikuj obiekt `Item`, dodając do niego nowe pole z ceną. Zaprojektuj nową funkcjonalność w REST API, tak
  aby można było pobrać całkowitą wartość pieniężną wszystkich elementów znajdujących się w koszyku.
- **Wskazówki:**
    - Zmodyfikuj klasę encji `Item` i dodaj do niej nowe pole.
    - Dodaj nowy endpoint REST do pobrania wartości koszyka.
    - Upewnij się, że logika biznesowa znajduje się w klasach serwisowych.
    - Pamiętaj o obsłudze wyjątków.

## Zadanie 4: Dodanie funkcjonalności "Akcja promocyjna"

- **Opis:** Dodaj funkcjonalność "Akcja promocyjna", która umożliwi wprowadzenie wartości procentowej promocji dla koszyka. Zrealizuj endpoint REST, który pozwoli na ustawienie procentowej wartości promocji. Jeśli promocja jest ustawiona, jej wartość powinna być uwzględniana przy obliczaniu całkowitej wartości koszyka. W przypadku braku promocji, wartość koszyka powinna pozostać bez zmian.
- **Wskazówki:**
  - Utwórz nową encję, np. `Promotion`, która będzie przechowywała wartość procentową promocji.
  - Dodaj odpowiednie repozytorium do zarządzania promocjami.
  - Zaimplementuj logikę biznesową w klasach serwisowych, która będzie odpowiedzialna za uwzględnianie promocji w wartości koszyka.
  - Dodaj odpowiedni model DTO do przekazywania danych między warstwami.
  - Utwórz endpoint REST w kontrolerze do ustawiania promocji.