# S3 Pràctica de xarxes (Java) — Threads i servidor concurrent

## Objectiu
Aquesta pràctica té 2 parts:

1) **Threads bàsics**: crear i llançar threads concurrents, passant paràmetres via constructor.
2) **Xat client-servidor concurrent**: convertir un servidor interactiu (sessió 2) en un servidor que atengui múltiples clients alhora, creant **un thread per connexió**.

> Requisit transversal: **control d’errors amb `try-catch`** tant en el client com en el servidor, i tancament correcte de recursos.

---

## Requisits previs
- JDK 17+ (o el que indiqui l’assignatura).
- Compilació i execució per terminal:
    - Compilar: `javac *.java`
    - Executar: `java NomClasse`

---

# Part 1 — Threads

## 1.1 `ThreadsExample.java`

### Enunciat
Fer un exemple de threads concurrents en què es disparin **5 threads**.
- Cada thread fa un bucle de **5 passades**:
    - Pinta “qui és” (nom del thread) i el número de passada
    - **Espera 1 segon** entre passades
- La creació del thread s’ha de fer **passant-li el nom del thread** via constructor.

### Requisits tècnics
- Crear una classe que:
    - Extengui `Thread` **o**
    - Implementi `Runnable`
- Rebre el nom del thread al constructor.
- Llançar els 5 threads des del `main` amb `start()`.
- Gestionar correctament:
    - `InterruptedException`
    - Possibles excepcions generals
- Opcional però recomanat: utilitzar `join()` per assegurar que el `main` espera que tots els threads acabin.

### Criteris d’avaluació
- Els 5 threads s’executen de forma concurrent.
- Cada thread fa exactament 5 iteracions.
- Hi ha pausa real d’1 segon entre iteracions.
- El programa acaba netament.

---

## 1.2 `ThreadsExample2.java`

### Enunciat
Modificar l’exemple anterior per enviar-li al thread el seu **identificador** com a paràmetre via constructor.

### Requisits tècnics
- El constructor del thread ha de rebre:
    - El nom
    - Un identificador (`int` o similar)
- El `run()` ha de mostrar:
    - Nom del thread
    - Identificador
    - Número de passada
- Mantenir:
    - 5 threads
    - 5 iteracions per thread
    - Espera d’1 segon
    - Control d’errors amb `try-catch`

### Criteris d’avaluació
- L’identificador s’ha passat correctament via constructor.
- Cada thread mostra el seu id correcte.
- Execució concurrent funcional.

---

# Part 2 — Servidor concurrent

## Objectiu
Modificar el xat client-servidor de la sessió 2 perquè el **servidor sigui concurrent**.

El client **no cal adaptar-lo**.

El servidor ha de:

1. Partir de l’**interactive server** de la sessió 2 (clonar-lo i refactoritzar-lo).
2. Cada vegada que rebi una nova connexió:
    - Crear un **nou thread**
3. Moure el codi d’atenció al client dins del thread.
4. Controlar que el thread:
    - Gestiona errors
    - Tanca recursos correctament
    - Finalitza netament quan el client es desconnecta

