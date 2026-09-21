# Lab 1 — Metaprogramming in Java: Introspection and Annotations

English port of TP01 (`dev5-tp01`). Same Maven project, same tests, same
`mvnw` wrapper. Class/method names are kept in French (`Afficheur`,
`Createur`, `Personne`) so slides and tests still match — only docs and
comments are translated.

Related lecture: `docs/cours/01.pdf` — "Metaprogramming in Java".

## Preliminary note

The goal of this lab is NOT to encourage you to use Java introspection
everywhere. Think about **readability** first.

The idea is to manipulate mechanisms used inside frameworks like **Spring**,
to understand how they work internally. In practice you will use these
mechanisms through **already-written libraries**.

## Exercise 1 — manipulate objects whose class you don't know in advance

Think of objects described by a text file, or rows pulled from a database.

### Question 1

Study `Afficheur.afficher` (`src/main/java/tp01/q1/Afficheur.java`).

Also look at `IntrospectionHelper` — it contains methods you will need.

What it does: loops over `o.getClass().getMethods()`, keeps zero-arg methods
starting with `get` (excluding `getClass`), invokes each getter, builds
`"property : value"` strings, sorts them, joins with `" ; "`.
It returns a `String` instead of printing, so tests can use `assertEquals`.

### Question 2

Create and initialize arbitrary classes (because the description comes from a
DB/text file). Assume a default constructor for now. Complete `Createur.creer()`:

```java
Createur<Adresse> createur = new Createur<>(Adresse.class);
Adresse a = createur.creer();
assertNotNull(a);
```

Hint: `clazz.getDeclaredConstructor().newInstance()`.

### Question 3

Initialize *text* fields. Complete
`Createur.setProprieteTexte(objet, nomPropriete, valeur)` — it must call the
matching setter. Hint: build the setter name with
`IntrospectionHelper.construireNomMethode("set", nomPropriete)`, find it with
`clazz.getMethod(setterName, String.class)`, call `invoke(objet, valeur)`.

### Question 4

More generic: complete
`Createur.setPropriete(objet, nomPropriete, valeur, clazzPropriete)` — same as
Q3 but you also receive the value's class (normally also found by
introspection, we stop here). The lab text calls it `setChamp`, the code calls
it `setPropriete`.

Trap: an `int` field needs `Integer.TYPE`, not `Integer.class`.

## Exercise 2 — annotations

We created an annotation `Label` that can be put on a method (in practice, on a
getter). See `src/main/java/tp01/q2/Label.java` (`RUNTIME` retention,
`METHOD` target).

Complete `Afficheur2` — same as `Afficheur`, but display the *label* attached
to the getter instead of the property name:

```java
Personne2 p = new Personne2("Alfred", 1999);
String expected = "annee de naissance : 1999 ; nom : Alfred";
String actual = new Afficheur2().afficher(p);
assertEquals(expected, actual);
```

`getNom()` has no annotation → fall back to the property name as in Exercise 1.
`getAnneeNaissance()` has `@Label("annee de naissance")` → use the label value.
Hint: `m.isAnnotationPresent(Label.class)`,
`m.getAnnotation(Label.class).value()`.

Such annotations are heavily used in professional Java frameworks (Spring,
J2EE, Lombok, Android).

## Exercise 3 (next session, out of scope)

Left for those who want to understand `java.lang.reflect.Proxy`. Not required.

## How to run (VS Code + JDK 17+, tested with JDK 25)

1. Open THIS folder (`lab1`, the one containing `pom.xml`) via
   `File > Open Folder`. Opening the parent breaks package resolution.
2. Install `Extension Pack for Java` (Microsoft). Accept `Trust workspace`,
   wait for the Maven import to finish.
3. Run tests:
   - UI: `Testing` panel (beaker icon) > `Run Tests`, or `Run Test` above each `@Test`.
   - Terminal (project root):
     ```powershell
     .\mvnw.cmd test
     .\mvnw.cmd -Dtest=TestAfficheurV1 test
     .\mvnw.cmd -Dtest=TestCreateurV1 test
     .\mvnw.cmd -Dtest=TestAfficheurV2 test
     .\mvnw.cmd surefire-report:report
     ```
   - `mvn` may not be on PATH — always use `.\mvnw.cmd` (Windows) / `./mvnw` (Linux/Mac).

## How tests work (why no `println`?)

There is no `System.out.println` in the lab. `afficher()` returns a `String`
so JUnit can compare it with `assertEquals(expected, actual)` — no console
capture needed, deterministic, CI-friendly. Maven Surefire auto-discovers
`*Test.java` (`TestAfficheurV1`: 1 test, `TestCreateurV1`: 3 tests,
`TestAfficheurV2`: 1 test). Starter state: 1 green / 4 red. Solution state:
5 green.

| Folder | Maps to | Status |
| --- | --- | --- |
| `src/main/java/tp01/q1` | Exercise 1 | `Afficheur` done, `Createur` TODO |
| `src/main/java/tp01/q2` | Exercise 2 | `Label` done, `Afficheur2` TODO |
| `src/test/java/...` | checks | JUnit 4, do not modify |
