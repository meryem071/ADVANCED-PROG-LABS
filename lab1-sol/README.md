# Lab 1 — Proposed SOLUTION 

English port of TP01 with reference solutions. Identical to `../lab1` except:
`Createur.java` (Q2/Q3/Q4) and `Afficheur2.java` (Ex2) are implemented.

## What the solution shows (and why)

### Q2 — `creer()`: "new T()" via reflection

```java
return clazz.getDeclaredConstructor().newInstance();
```

Java forbids `new T()` for a generic `T` — the type is erased at runtime.
But we stored `Class<T> clazz` in the constructor, so we can ask it for its
no-arg constructor and instantiate it. This is how frameworks create objects
described by a DB row or config file. Any failure (no default constructor,
private class, ...) is wrapped in `IntrospectionException`.

Test: `new Createur<>(Adresse.class).creer()` must not be null.

### Q3 — `setProprieteTexte`: call the setter by name

```java
String setterName = IntrospectionHelper.construireNomMethode("set", nomPropriete);
// "rue" -> "setRue"
Method setter = clazz.getMethod(setterName, String.class);
setter.invoke(objet, valeur);
```

Two-step reflection: (1) compute the setter name from the property,
(2) look it up by name + parameter type, (3) invoke it on the object.
Text-only version: the parameter type is fixed to `String.class`.

Test: after `setProprieteTexte(a,"rue","r1")`, `a.getRue()` equals `"r1"`.

### Q4 — `setPropriete`: same, but with an explicit type

```java
Method setter = clazz.getMethod(setterName, clazzPropriete);
setter.invoke(objet, valeur);
```

Needed because `getMethod` requires exact parameter types. Key trap for the
lesson: an `int` setter needs `Integer.TYPE`, not `Integer.class` — reflection
distinguishes primitives from wrappers.

Test: `setPropriete(p,"nom","n1",String.class)` and
`setPropriete(p,"anneeNaissance",1995,Integer.TYPE)`.

### Ex2 — `Afficheur2`: prefer `@Label`

Same getter loop as `Afficheur`, plus one branch:

```java
String displayName;
if (m.isAnnotationPresent(Label.class)) {
    displayName = m.getAnnotation(Label.class).value();
} else {
    displayName = IntrospectionHelper.extraireNomDePropriete("get", m.getName());
}
```

Works only because `Label` is declared `@Retention(RUNTIME)` — with
`SOURCE` or `CLASS` retention, `getAnnotation` would return null. `getNom()`
has no annotation → `"nom"`. `getAnneeNaissance()` has
`@Label("annee de naissance")` → `"annee de naissance"`.

## Why no `println` in the lab?

`afficher()` returns a `String` instead of printing so JUnit can do
`assertEquals(expected, actual)`. No `System.out` capture, deterministic
(`sort()` + `" ; "` join), CI-friendly. For a live demo, add a temporary
`main` with `System.out.println(new Afficheur().afficher(p))` if you want
visible output — but grading stays on return values.

## How tests work

JUnit 4 (`@Test`, `assertEquals`, `assertNotNull`). Maven Surefire compiles
`src/main`, then `src/test`, and runs every `*Test.java`:
`TestAfficheurV1` (1 test, already green in starter),
`TestCreateurV1` (3 tests: `creer`, `setProprieteTexte`, `setPropriete`),
`TestAfficheurV2` (1 test, label display). Expected here: `Tests run: 5,
Failures: 0, Errors: 0`.

Run from this folder:

```powershell
.\mvnw.cmd test
.\mvnw.cmd -Dtest=TestCreateurV1 test
.\mvnw.cmd surefire-report:report
```
