# agents.md — Taller Craps con Git (FPOE 750014C)

Seguimiento de tareas y progreso del taller, organizado por estudiante.
Archivo vivo: debe actualizarse cada vez que se complete una tarea o cambie el estado de Git.

---

## 1. Estado general del proyecto

| Dato | Valor |
|---|---|
| Proyecto | Craps Game (JavaFX 21.0.6 + Maven, JDK 17) |
| Repo remoto | https://github.com/Sahurtaro/craps-game.git |
| Rama principal | `main` |
| Rama de Estudiante 1 | `sh-1` (actualmente activa) |
| Último commit | `ff14d90 "first commit"` (igual en `main` y `origin/main`) |
| Config Git | `user.name = sh-1`, `user.email = sahurtaro@gmail.com` |
| Archivos ignorados | `Enunciado Taller Craps Git FPOE.pdf` (agregado a `.gitignore`, **no se sube**) |

**Regla de oro (flujo de trabajo de todos):**
1. Trabajar siempre en la propia rama (`main` solo se toca vía Pull Request).
2. `git add` → `git status` → `git commit -m "..."` → `git push origin <rama>`.
3. Abrir Pull Request hacia `main` en GitHub.
4. Revisar y aprobar los Pull Requests de los compañeros.
5. `main` es la versión final del entregable.

### Herramientas de compilación (nota técnica)

- `./mvnw` **no funciona**: falta `.mvn/wrapper/maven-wrapper.properties` y no hay red para descargarlo.
- Compilación válida con el Maven empaquetado de IntelliJ IDEA 2026.2:
  ```
  "C:\Program Files\JetBrains\IntelliJ IDEA 2026.2\plugins\maven-plugin\lib\maven3\bin\mvn.cmd" -o compile
  ```
- También se puede compilar/ejecutar directamente desde IntelliJ IDEA (Build / Run).

---

## 2. Resumen de progreso

| Estudiante | Rama | Rol principal | Estado |
|---|---|---|---|
| Estudiante 1 | `sh-1` | Setup del repo + `GameController` (mostrar datos en la vista) | 🟡 En curso (código listo, falta compilar con `Game`) |
| Estudiante 2 | *pendiente* | Modelo `Game` (lógica del juego Craps) | ⚪ No iniciado |
| Estudiante 3 | *pendiente* | Botón de ayuda (alerta con instrucciones) + mejora visual de `game-view` | ⚪ No iniciado |

**Progreso global:** 20 de 43 tareas completadas (47%).

---

## 3. Estudiante 1 — SETUP DEL REPO Y `GameController` (TÚ)

### 3.1 Paso 1 — Repo e inicialización (✅ completado)

- [x] Clonar/abrir el proyecto "Craps Game" en IntelliJ IDEA
- [x] Borrar el repositorio anterior (`.git` recreado limpio: 1 solo commit)
- [x] Crear el repositorio en GitHub (`Sahurtaro/craps-game`)
- [x] Iniciar repo de Git en la carpeta del juego (`git init`)
- [x] Configurar nombre y correo de Git (`user.name`, `user.email`)
- [x] Asociar remoto (`git remote add origin https://github.com/Sahurtaro/craps-game.git`)
- [x] `git add -A` → staging area
- [x] `git status` → verificar staging
- [x] Renombrar rama principal a `main` (`git branch -M main`)
- [x] `git commit -m "first commit"`
- [x] `git push origin main` → `main == origin/main == ff14d90`
- [x] PDF del enunciado **no se sube** → agregado a `.gitignore`

### 3.2 Paso 2 — Ajustes en `GameController` (🟡 código terminado, pendiente compilar/commitear)

Objetivo: usar el modelo `Game` para mostrar en la vista **Tiro, Punto, Juegos ganados y Juegos perdidos**.

- [x] Crear rama propia con iniciales (`sh-1`)
- [x] Definir/validar la API del modelo `Game` → contrato en §5 (añadidos `getDice1()`/`getDice2()` para pintar los dados)
- [x] `game-view.fxml`: agregar `fx:id` → `throwLabel`, `pointLabel`, `gamesWonLabel`, `gamesLostLabel`, `gameLogTextArea`
- [x] `game-view.fxml`: agregar filas "Juegos ganados:" y "Juegos perdidos:" con sus valores
- [x] `game-view.fxml`: TextArea del log → `editable="false"`, `wrapText="true"`
- [x] Inyectar los nuevos `@FXML` en `GameController` (`Label` x4 + `TextArea`)
- [x] Instanciar el modelo `Game` por composición (`private final Game game = new Game()`)
- [x] Actualizar `onMouseClickedDropButton`:
      - delega la jugada en `game.throwDice()` (los dados ahora los tira `Game`)
      - pinta ambos dados desde `game.getDice1()` / `game.getDice2()`
      - refresca Tiro, Punto (0 → `"--"`), Ganados y Perdidos
- [x] Mostrar cada jugada en el `TextArea` (log acumulado): `"Tiro: <n> - <estado>"`
- [ ] **Compilar y probar** → ⛔ bloqueado: `mvn compile` falla SOLO con 3 errores
      `cannot find symbol: class Game` (aún no existe el modelo, tarea del Est. 2)
- [ ] `git add` → `git status` → `git commit` (ej. `"feat: mostrar tiro, punto y marcadores en la vista"`)
- [ ] `git push origin sh-1`
- [ ] Abrir **Pull Request** hacia `main` en GitHub
- [ ] Revisar y aprobar los PR de Estudiante 2 y Estudiante 3

### 3.3 Verificación realizada (2026-10-24)

| Prueba | Resultado |
|---|---|
| XML bien formado (`game-view.fxml`) | ✅ OK |
| `mvn -o compile` antes de los cambios | ✅ BUILD SUCCESS |
| `mvn -o compile` después de los cambios | ❌ 3 errores, **todos** `symbol: class Game` en `GameController.java:3,16` |

Interpretación: todo el resto del código nuevo (FXML + controlador) es correcto;
el proyecto compilará en cuanto el Estudiante 2 cree `models/Game.java` con el contrato de §5.

### 3.4 Código modificado en esta rama (sin commitear todavía)

| Archivo | Cambio |
|---|---|
| `.gitignore` | Ignora el PDF del enunciado |
| `src/main/resources/.../game-view.fxml` | `fx:id` + filas de Juegos ganados/perdidos + TextArea de log |
| `src/main/java/.../controllers/GameController.java` | Lógica de presentación contra el modelo `Game` |
| `agents.md` | Este archivo de seguimiento (nuevo) |

> ⚠️ Pendiente: hacer `git add` de estos archivos y commitear en `sh-1`.

---

## 4. Estudiante 2 — MODELO `Game` (lógica del Craps)

> ⚠️ Tarea crítica: Estudiante 1 ya programó su controlador **contra el contrato de §5** y está
> bloqueado hasta que esta clase exista. Debe respetarse ese contrato (o avisar en el PR si cambia).

- [ ] Clonar el repo creado por el Estudiante 1
- [ ] Crear rama con sus iniciales (`git checkout -b <iniciales>`)
- [ ] Crear la clase `com.example.crapsgame262.models.Game` respetando el contrato de §5
- [ ] Implementar la lógica del juego:
      - tiro de salida: **7 u 11 → gana**; **2, 3 o 12 → pierde** (Craps)
      - **4, 5, 6, 8, 9, 10 → establece el punto**
      - con punto: gana si repite el punto antes del 7; pierde si sale 7 primero
- [ ] Contar estadísticas acumuladas: juegos ganados, juegos perdidos
- [ ] Mantener estado: tiro actual, punto actual (0 si no hay punto), si la partida terminó
- [ ] Implementar `getDice1()` / `getDice2()` (se usan para pintar las imágenes en la vista)
- [ ] Implementar `getStatus()` con texto legible para el log de la vista
- [ ] Compilar (`mvn -o compile`), commitear y hacer push de su rama
- [ ] Abrir **Pull Request** hacia `main`
- [ ] Revisar y aprobar los PR de sus compañeros

---

## 5. Contrato acordado de la API `Game` (dependencia entre Est. 1 y Est. 2)

Estudiante 1 **ya programó** `GameController` contra este contrato (verificado con el compilador).
Si Estudiante 2 necesita cambiarlo, debe avisarlo en el PR antes de hacer merge.

```java
package com.example.crapsgame262.models;

public class Game {

    public Game();                       // inicia una partida (punto = 0, gameOver = false)

    public int throwDice();              // tira los 2 dados, procesa la jugada, devuelve el total (2..12)

    public Dice getDice1();              // primer dado tirado (para pintar la imagen en la vista)
    public Dice getDice2();              // segundo dado tirado

    public int getThrow();               // último tiro (suma de los dados)
    public int getPoint();               // punto actual (0 si no hay punto)
    public boolean isGameOver();         // la partida terminó (gana o pierde)
    public boolean isWinner();           // true si el jugador ganó la partida terminada
    public int getGamesWon();            // juegos ganados (acumulado entre partidas)
    public int getGamesLost();           // juegos perdidos (acumulado entre partidas)
    public String getStatus();           // texto legible para el log, ej. "Ganaste", "Punto establecido: 9"
}
```

**Comportamientos que debe cumplir `Game` (acordados para que la vista funcione):**

1. `throwDice()` tira internamente dos dados y devuelve la suma (2..12).
2. Si `isGameOver()` era `true`, el siguiente `throwDice()` inicia **automáticamente** una nueva
   partida (punto vuelve a 0, `isGameOver()` pasa a `false`), manteniendo los contadores acumulados.
3. `getPoint()` devuelve 0 cuando no hay punto (inicio o después de terminar la partida), así la
   vista lo muestra como `"--"`.
4. Los contadores `getGamesWon()` / `getGamesLost()` **nunca** se reinician (son acumulados).
5. `getStatus()` debe cambiar en cada jugada, ya que se imprime en el `TextArea` como
   `"Tiro: <n> - <getStatus()>"`.

---

## 6. Estudiante 3 — AYUDA Y VISTA

- [ ] Clonar el repo creado por el Estudiante 1
- [ ] Crear rama con sus iniciales (`git checkout -b <iniciales>`)
- [ ] Agregar alerta con las instrucciones del juego al botón de ayuda (`?`)
      - usar `AlertBox` / `AlertBoxInterface` que ya existen en `models`
      - conectar el botón `?` de `game-view.fxml` al controlador (hoy no tiene handler)
- [ ] Ajustar la vista `game-view.fxml` para que los datos se vean con claridad
      - distribución, tipografía, alineación de los marcadores
      - **NO borrar** los `fx:id`: `throwLabel`, `pointLabel`, `gamesWonLabel`, `gamesLostLabel`, `gameLogTextArea`, `dice1ImageView`, `dice2ImageView`, `nicknameLabel`
- [ ] Compilar, commitear y hacer push de su rama
- [ ] Abrir **Pull Request** hacia `main`
- [ ] Revisar y aprobar los PR de sus compañeros

> ⚠️ Nota de coordinación: Estudiante 1 **ya modificó** `game-view.fxml` en la rama `sh-1`
> (agregó los `fx:id` y las filas de Juegos ganados/perdidos). Estudiante 3 debe hacer merge de
> `sh-1` (o esperar a que se mergee a `main`) antes de editar el diseño, para evitar conflictos.

---

## 7. Checklist final del entregable

- [ ] Todas las ramas fusionadas en `main` mediante Pull Requests aprobados
- [ ] Juego completo: tiro de salida, punto, reglas 7/11/2-3-12, marcador de ganados/perdidos
- [ ] Arquitectura MVC respetada (Model: `Game`, `Dice`, `Player`; View: FXML/`GameView`; Controller: `GameController`)
- [ ] Eventos de mouse y/o teclado implementados (botón Lanzar, ayuda)
- [ ] Interfaz clara (UX/UI básica)
- [ ] Estructura de datos de partida/estadísticas (en `Game`)
- [ ] Repo en GitHub con historial de commits por estudiante
