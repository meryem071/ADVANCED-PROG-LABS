# Lab 2 — Generics in Java

English port of TP02. Same Maven project, same tests, same `mvnw` wrapper.
Class names are kept (`MaListe`, `MaListeHelper`, package `fip1`) so tests
still match — only docs and comments are translated.

## Objective

Get familiar with Java **generics**:
- creating **generic classes**,
- using **type bounds**,
- designing **generic methods**,
- understanding **wildcards (`? extends`, `? super`)**.

These are the basis of modern Java programming, especially *collections* and
frameworks like Spring or Hibernate.

> The expected code is checked automatically by test classes.
> You can run the tests right away — some will fail or not even compile until
> your code is complete. If needed, temporarily comment out test parts you have
> not implemented yet.

## Exercise 1 — generic classes

### Question 1

Create a generic class `MaListe<T>` with (exact signatures to be determined):

- `ajouter` : append an element at the end of the list
- `taille` : return the current size
- `element` : return the element at index `i`

Your implementation must make this code (and the other `TestQ1` tests) pass:

```java
MaListe<String> liste1 = new MaListe<>();
liste1.ajouter("un");
liste1.ajouter("deux");
assertEquals("un deux", liste1.toString());
```

👉 You may use an **Object array** internally, converting to the right type
when reading. 🧩 Don't forget a `toString()` method or the test will fail.

### Question 2

Add one more method to `MaListe`:

- `ajouterListe` : add all elements of another list of the same type.

```java
MaListe<Integer> liste1 = new MaListe<>();
MaListe<Integer> liste2 = new MaListe<>();
liste1.ajouter(1);
liste1.ajouter(2);
liste2.ajouter(3);
liste1.ajouterListe(liste2);
assertEquals("1 2 3", liste1.toString());
```

💡 Also check the other tests in `TestQ2`.

### Question 3

Is the following code reasonable?

```java
MaListe<String> liste1 = new MaListe<>();
liste1.ajouter("un");
liste1.ajouter("deux");
MaListe<Object> liste2 = new MaListe<>();
liste2.ajouter(Integer.valueOf(3));

liste2.ajouterListe(liste1);
assertEquals("3 un deux", liste2.toString());
```

🧠 Think: does this code **make sense**? If it doesn't compile, change your
method signatures so it works **without breaking the previous tests**.

👉 Hint: **wildcards** (`? extends` and `? super`) are your friends here.
Verify with `TestQ3`.

### Question 4

Now the reverse operation: `ajouterDansListe`, which adds **all elements of
the current list** into another list passed as argument.

```java
MaListe<String> liste1 = new MaListe<>();
liste1.ajouter("un");
liste1.ajouter("deux");
MaListe<Object> liste2 = new MaListe<>();
liste2.ajouter(Integer.valueOf(3));

liste1.ajouterDansListe(liste2);
assertEquals("3 un deux", liste2.toString());
```

🧩 Run `TestQ4` to verify.

## Exercise 2 — generic methods

Now we work on **generic methods**, which can be **static** and defined
**outside** `MaListe` (in `MaListeHelper`).

### Question 5 (`TestQ5`)

Write `MaListeHelper.concat` so this compiles and works:

```java
MaListe<String> l1 = new MaListe<>();
l1.ajouter("a");
MaListe<String> l2 = new MaListe<>();
l2.ajouter("b");
MaListe<String> l3 = MaListeHelper.concat(l1, l2);
assertEquals("a b", l3.toString());
```

### Question 6 (`TestQ6`)

Concatenate two lists of **different** types, provided they share a **common
super-type**:

```java
MaListe<Integer> l1 = new MaListe<>();
l1.ajouter(4);
MaListe<String> l2 = new MaListe<>();
l2.ajouter("b");
MaListe<Object> l3 = MaListeHelper.concat(l1, l2);
assertEquals("4 b", l3.toString());
```

Here the element types are `Integer` and `String`; their common super-type is
`Object`. 🔧 Adapt `concat` so this test and `TestQ6` pass.

> Note: the original French handout labels these two as "Ex2 Q1/Q2" and points
> at the wrong test class (`TestQ4`). The mapping above (`TestQ5`/`TestQ6`) is
> the correct one.

## How to run (VS Code + JDK 11+, tested with JDK 25)

1. Open THIS folder (`lab2`, the one containing `pom.xml`) via
   `File > Open Folder`.
2. Install `Extension Pack for Java` (Microsoft), trust the workspace, wait
   for the Maven import.
3. Run tests:
   - UI: `Testing` panel (beaker icon) > `Run Tests`.
   - Terminal (project root):
     ```powershell
     .\mvnw.cmd test
     .\mvnw.cmd -Dtest=TestQ1 test
     .\mvnw.cmd -Dtest=TestQ3 test
     ```

## Test map

| Question | Test class | What it checks |
| --- | --- | --- |
| Ex1 Q1 | `TestQ1` (4 tests) | generic `ajouter`/`taille`/`element`/`toString` |
| Ex1 Q2 | `TestQ2` (2 tests) | `ajouterListe` with same type |
| Ex1 Q3 | `TestQ3` (1 test) | `ajouterListe` into `MaListe<Object>` (wildcards) |
| Ex1 Q4 | `TestQ4` (2 tests) | `ajouterDansListe` (wildcards, incl. same-type case) |
| Ex2 Q5 | `TestQ5` (1 test) | `concat` with same type |
| Ex2 Q6 | `TestQ6` (1 test) | `concat` with different types, common super-type |

Starter state: the project doesn't even compile (no generic methods yet).
Solution state: 11 tests green.
