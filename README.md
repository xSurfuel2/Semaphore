# 🅿️ Control de Acceso Concurrente a un Aparcamiento con *Semaphore*

## 🚗 Descripción General
Este proyecto simula un **aparcamiento con plazas limitadas** donde varios coches intentan acceder al mismo tiempo.  
Su propósito es comprender cómo **gestionar recursos compartidos** y **sincronizar hilos** en Java mediante la clase `Semaphore`.

> En este caso, el aparcamiento dispone de **3 plazas**, pero llegan **7 coches** simultáneamente.  
> Solo tres pueden aparcar a la vez; el resto espera pacientemente a que se libere una plaza.

---

## 🧩 Arquitectura del Sistema

El proyecto se estructura en tres clases principales, según las pautas del ejercicio:

### 🅰️ `Aparcamiento`
Gestiona el recurso limitado (las plazas) mediante un objeto `Semaphore`.  
Sus métodos:
- `entrar(String nombre)`: intenta adquirir un permiso; si no hay plazas, el coche espera.
- `salir(String nombre)`: libera un permiso y permite que otro coche entre.
Incluye un control de estado (`plazasOcupadas`) y mensajes legibles por consola.

### 🚙 `Coche` *(implements Runnable)*
Cada hilo representa un vehículo.  
En su método `run()`:
1. Intenta entrar al aparcamiento.  
2. Permanece aparcado un tiempo aleatorio entre **1 y 4 segundos**.  
3. Sale liberando su plaza.

### 💻 `PrincipalParking`
Clase principal del programa.  
- Crea el aparcamiento con 3 plazas.  
- Genera y lanza **7 hilos de tipo `Coche`** (Coche 1 a Coche 7) en orden.  
- Coordina la ejecución concurrente.

---

## 🧠 Funcionamiento y Salida

Durante la ejecución, verás cómo los tres primeros coches entran inmediatamente, mientras los demás esperan turno:

```
Coche 1 ha entrado. Plazas ocupadas: 1
Coche 2 ha entrado. Plazas ocupadas: 2
Coche 3 ha entrado. Plazas ocupadas: 3
Coche 4 está esperando...
Coche 5 está esperando...
Coche 1 ha salido. Plazas ocupadas: 2
Coche 4 ha entrado. Plazas ocupadas: 3
...
```

📸 **Captura de ejecución !(cap1.png)[Captura consola]**  
Muestra el programa corriendo en consola, donde se observan los coches entrando, esperando y saliendo, garantizando que nunca haya más de **3 vehículos simultáneamente aparcados**.  
Esto confirma que el control de concurrencia con `Semaphore` funciona correctamente.

---

## ⚙️ Instrucciones de Ejecución
Compila y ejecuta desde consola:

```bash
javac Model/Aparcamiento.java Model/Coche.java Model/PrincipalParking.java
java Model.PrincipalParking
```

O desde tu IDE (IntelliJ / Eclipse) ejecuta la clase `PrincipalParking`.

---

## 💬 Reflexión sobre el uso de `Semaphore`

Elegí **`Semaphore`** porque es la herramienta más adecuada para **gestionar un número limitado de recursos compartidos** (en este caso, plazas de aparcamiento).  
A diferencia de `synchronized`, que solo garantiza exclusión mutua **para un único recurso**, el semáforo permite **controlar múltiples accesos simultáneos**.  
Su contador interno modela de forma natural la disponibilidad de plazas.  
Frente a alternativas como `wait()/notify()`, `Semaphore` ofrece una **API más intuitiva, segura y legible**, evitando errores de sincronización manual.  
Comparado con `ReentrantLock`, aporta **equidad (FIFO)** y una semántica más simple para bloquear y liberar permisos, ideal para problemas de concurrencia educativa o de control de acceso.

En resumen, `Semaphore` combina **claridad conceptual**, **seguridad frente a condiciones de carrera** y **control preciso** sobre el número de hilos activos, siendo la elección más elegante y didáctica para este tipo de simulación.

---

## 🧾 Autoría
📚 **Alumno:** A  
👨‍💻 **Repositorio:** [github.com/xSurfuel2/Semaphore](https://github.com/xSurfuel2/Semaphore.git)  
🏫 **Actividad:** *Control de Acceso Concurrente a un Aparcamiento con Semaphore*  
📄 **Fuente de enunciado:** Documento oficial de la práctica (Davante Academy)
