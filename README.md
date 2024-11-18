# PROCESOS
# 1. Introducción
En este apartado veremos los diferentes procesos que se pueden llevar a cabo en una aplicación de Java:
- **Llamadas a procesos Java**: Ejecución de procesos o subprocesos dentro de la propia JVM (Java Virtual Machine).
- **Llamadas a procesos/programas de Windows**: Ejecución de programas o comandos nativos del sistema operativo, como ejecutables .exe en Windows.
- **Llamadas a comandos de Windows**: Ejecución de comandos del sistema operativo directamente desde Java, como los comandos de la línea de comandos (cmd).

# 2. Clases
Java permite trabajar con procesos mediante diferentes clases que tienen sus propios métodos.

## 2.1. System
Permite algunas interacciones importantes con el sistema operativo. 

### 2.1.1. Métodos principales
Mediante el método System.**exit**(_int status_) termina la ejecución del programa Java. El valor se le pasa al sistema operativo para indicar el código de salida del proceso, donde el código 0 indica una salida exitosa, mientras que cualquier número diferente de 0 indica un error.

## 2.2. ProcessBuiler
La clase ProcessBuilder es la más utilizada cuando se desea lanzar procesos externos, ya que proporciona una forma más flexible de crear y manejar procesos nativos del sistema operativo. 

### 2.2.1. Instanciación
Instanciación para crear un proceso:
```java
String[] infoProceso = {"cmd.exe", "/c", "dir"};
ProcessBuilder pb = new ProcessBuiler(infoProceso);
Process p = pb.start();
```

### 2.2.2. Métodos principales
- **start**(): Inicia el proceso. Devuelve un objeto Process que puedes usar para controlar la ejecución del proceso.
- **directory**(File directory): Establece el directorio de trabajo para el proceso. Por defecto, el proceso hereda el directorio de trabajo de la JVM que lo ejecuta.
- **redirectOutput**(File output): Redirige la salida estándar del proceso a un archivo.
- **redirectError**(File error): Redirige la salida de error estándar del proceso a un archivo.
- **environment**(): Retorna información sobre el entorno de ejecución.
```java
Map<String, String> env = processBuilder.environment();
```

## 2.3. Runtime
La clase Runtime permite interactuar con la JVM en un nivel más bajo, proporcionando métodos para ejecutar procesos externos, obtener la memoria disponible y manipular el entorno del sistema operativo.

### 2.3.1. Instanciación
Instanciación para crear un proceso:
```java
String[] infoProceso = {"cmd.exe", "/c", "dir"}
Runtime r = Runtime.getRuntime();
Process p = r.exec(infoProceso);
```

### 2.3.2. Métodos principales
- **exec**(String command): Lanza un proceso externo con el comando especificado. 
- **exit**(int status): Finaliza la JVM de manera inmediata, enviando un código de estado al sistema operativo. 

## 2.4. Process
La clase Process representa un proceso que se está ejecutando en el sistema operativo. Cuando usas ProcessBuilder o Runtime.exec(), obtienes un objeto Process que te permite interactuar con el proceso en ejecución.

### 2.4.1. Métodos principales

- **getInputStream**(): Obtiene el flujo de entrada del proceso, lo que te permite leer la salida del proceso.
- **getOutputStream**(): Obtiene el flujo de salida del proceso, lo que te permite enviar datos al proceso.
- **getErrorStream**(): Obtiene el flujo de error del proceso, lo que te permite leer cualquier mensaje de error generado por el proceso.
- **waitFor**(): Espera a que el proceso termine y devuelve su código de salida. Este método es bloqueante, lo que significa que la ejecución de tu programa se detendrá hasta que el proceso termine.
- **exitValue**(): Devuelve el código de salida del proceso. Si el proceso aún está en ejecución, se lanza una excepción IllegalThreadStateException.
- **pid**(): Devuelve el identificador de proceso (PID) del proceso en ejecución. Esto te permite obtener una referencia única al proceso en el sistema operativo, lo que puede ser útil si necesitas realizar operaciones específicas con el proceso, como matarlo desde otro proceso.
- **destroy**(): Este método finaliza el proceso de manera abrupta. Llama al sistema operativo para detener el proceso de forma inmediata.
- **isAlive**(): Devuelve un valor booleano que indica si el proceso está todavía en ejecución. Si el proceso ha terminado, devolverá false; si está en ejecución, devolverá true.

## 2.5. ProcessHandle
El método **toHandle**() de la clase __Process__ permite convertir un objeto Process en un ProcessHandle. Esto abre la puerta para trabajar con características más avanzadas de los procesos, como la obtención del proceso padre. A continuación, se indica cómo obtener el PID del proceso padre:

```java
ProcessBuilder pb = new ProcessBuilder(infoProceso);
Process proceso = pb.start();
long pidProceso = proceso.pid();
ProcessHandle parentHandle = proceso.toHandle().parent().get();
long pidProcesoPadre = parentHandle.pid();
```

# 3. Lectura de la salida
Leer la salida de un proceso en Java es una parte importante cuando trabajas con la ejecución de comandos o programas externos. Puedes hacerlo utilizando los flujos de entrada del proceso, específicamente el **InputStream** del objeto _Process_. Este flujo contiene la salida estándar del proceso, es decir, lo que normalmente verías en la terminal o consola si ejecutaras el comando manualmente.

## 3.1. Lectura de caracteres
Una forma simple de leer la salida de un proceso es leyendo los caracteres individuales del flujo de entrada. Este enfoque es adecuado si deseas obtener todos los caracteres, sin importar si el contenido se divide en líneas o no. El proceso de lectura se detiene cuando no quedan más caracteres por leer (es decir, cuando el flujo de entrada llega al final).

```java
	private String leerResultado(Process p) throws IOException {
		// Leer el contenido de la consola
		InputStream is = p.getInputStream();
		int c;
		StringBuilder sb = new StringBuilder();
		// El valor -1 indica fin del input, no hay nada más que leer
		while ((c = is.read()) != -1) {
			sb.append((char) c);
		}

		is.close();

		return sb.toString();
	}
```

## 3.2. Lectura de líneas
En muchos casos, la salida del proceso estará organizada en líneas (por ejemplo, los resultados de un comando de terminal). Leer las líneas de la salida es más conveniente y te permite manejar la salida de una manera más estructurada. Puedes usar un BufferedReader para leer cada línea de la salida del proceso.

```java
	private String leerResultadoBuffered(Process p) throws IOException {
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String linea;
		StringBuilder sb = new StringBuilder();
		// Se llega al final de la lectura si la línea es un valor nulo
		while ((linea = br.readLine()) != null) {
			sb.append(linea);
		}

		br.close();

		return sb.toString();
	}
```

# 4. Matar procesos
En algunas situaciones, es posible que necesites finalizar un proceso que esté en ejecución desde tu programa Java. Para hacerlo, puedes usar comandos del sistema operativo como taskkill en Windows o kill en sistemas basados en Unix/Linux. En Java, puedes ejecutar estos comandos utilizando la clase Runtime o ProcessBuilder.

El siguiente ejemplo muestra cómo puedes matar un proceso si conoces su nombre. En este caso, se ejecuta el comando tasklist en Windows para listar los procesos en ejecución y luego se verifica si el proceso que deseas terminar está en la lista. Si es así, se usa el comando taskkill para terminarlo.

```java
	public void matarSiEstaVivo(String nombreProceso) throws IOException {
		String[] infoProceso = { "cmd.exe", "/c", "tasklist" };
		ProcessBuilder pb = new ProcessBuilder(infoProceso);

		System.out.println("Buscando el proceso " + nombreProceso);
		Process proceso = pb.start();

		String resultado = leerResultadoBuffered(proceso);

		if (resultado.contains(nombreProceso)) {
			Runtime.getRuntime().exec("taskkill /F /IM " + nombreProceso);
			System.out.println("Se ha cerrado el proceso " + nombreProceso);
		} else {
			System.out.println("El proceso " + nombreProceso + " no está en ejecución");
		}
	}
```

# HILOS
# 1. Introducción
En Java, un hilo (o thread) es una unidad básica de ejecución dentro de un programa. Los hilos permiten que un programa realice múltiples tareas simultáneamente, mejorando la eficiencia, especialmente en aplicaciones que requieren procesamiento en paralelo o multitarea. Java proporciona dos maneras principales de crear y manejar hilos:
- **La clase Thread**: Permite crear un hilo extendiendo la clase y sobrescribiendo el método run().
- **La interfaz Runnable**: Permite crear hilos sin necesidad de extender Thread. En su lugar, implementas la interfaz Runnable y luego se pasa una instancia de esta interfaz al constructor de un hilo.

# 2. Clases para el maneo de hilos
## 2.1. Thread
La clase Thread es la implementación directa de un hilo en Java. Para crear un hilo, puedes extender esta clase y sobrescribir el método run(). Cada instancia de Thread representa un hilo que puede ejecutarse de forma independiente en el programa.

### 2.1.1. Ejemplo
```java
public class MiHilo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo en ejecución: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }
    }

    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();
    }
}
```

## 2.2. Runnable
La interfaz Runnable proporciona una forma más flexible de crear hilos. En lugar de extender la clase Thread, puedes implementar esta interfaz y pasar una instancia de tu clase Runnable al constructor de un objeto Thread.

### 2.2.1. Instanciación
```java
MiHiloRunnable tarea = new MiHiloRunnable();
Thread hilo = new Thread(tarea); // Pasamos el Runnable al constructor de Thread
```

### 2.2.2. Ejemplo
```java
public class MiHiloRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo en ejecución: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }
    }

    public static void main(String[] args) {
        MiHiloRunnable tarea = new MiHiloRunnable();
        Thread hilo = new Thread(tarea);
        hilo.start();
    }
}
```

# 3. Métodos de los hilos
- **start**(): Inicia la ejecución del hilo. Al llamar a este método, el hilo comienza a ejecutarse en paralelo, invocando el método run().
- **run**(): Es el cuerpo del hilo. El código dentro de este método se ejecutará cuando se inicie el hilo.
- **sleep**(long millis): Suspende la ejecución del hilo durante el tiempo especificado (en milisegundos).
- **join**(): Hace que el hilo actual espere hasta que el hilo en el que se llama termine su ejecución.
- **getName**(): Devuelve el nombre del hilo.
- **setPriority**(int priority): Establece la prioridad de un hilo. (Thread.NORM_PRIORITY, Thread.MIN_PRIORITY, Thread.MAX_PRIORITY)
- **isAlive**(): Devuelve true si el hilo ha comenzado y aún está en ejecución.
- **interrupt**(): Lanza una _InterruptedException_ para que el hilo pueda manejar la interrupción.

# 4. Muerte de los hilos
Un hilo en Java puede "morir" de varias maneras:
- **Cuando termina su método run**(): Si el método run() finaliza, el hilo también termina su ejecución.
- **Interrupción**: Si el hilo recibe una interrupción mientras está bloqueado en una operación, puede terminar a través de una InterruptedException.
- **Excepción no controlada**: Si una excepción no controlada ocurre dentro del hilo (como un NullPointerException o ArrayIndexOutOfBoundsException) y no se captura, el hilo terminará de manera abrupta.

## 4.1. Bucles infinitos
### 4.1.1. Flag
Cuando un hilo está ejecutando un bucle infinito o una tarea que no tiene un punto claro de finalización, es recomendable usar un flag booleano para permitir que el hilo termine su ejecución de forma controlada. Esta es una manera segura y eficiente de manejar la terminación de hilos sin necesidad de forzar su interrupción.

1. **Definir un booleano de control**: Este booleano indica si el hilo debe seguir ejecutándose.
2. **Método terminar**(): Este método cambiará el valor del booleano para que el hilo se detenga.
3. **Comprobar el estado del booleano en el bucle**: El hilo comprobará periódicamente si debe seguir ejecutándose.

```java
public class HiloControlado extends Thread {
    private volatile boolean continuarEjecucion = true;  // Bandera para controlar la ejecución del hilo

    @Override
    public void run() {
        // Simulamos un bucle infinito
        while (continuarEjecucion) {
            System.out.println("El hilo está en ejecución...");
            try {
                Thread.sleep(1000);  // Simula trabajo del hilo
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
        }
        System.out.println("El hilo ha terminado.");
    }

    // Método para detener el hilo
    public void terminar() {
        continuarEjecucion = false;
    }
}
```

### 4.1.2. Interrupción
Cuando tenemos bucles infinitos, podemos forzar que el hilo se interrumpa y gestionarlo en la excepción InterruptedException.

1. **Método terminar**(): Este método interrumpe el hilo llamando a interrupt().
2. **Bucle en run**(): Dentro del bucle infinito en run(), el hilo verifica si ha sido interrumpido (lo que lanzará una InterruptedException en el caso de que esté en un estado de espera, como sleep()). Si ocurre, el hilo sale del bucle de manera controlada.

```java
public class HiloInterrumpido extends Thread {
    
    @Override
	public void run() {
		int i = 0;
		while (true) {
			label.setText(this.getName() + ": " + i);
			i++;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
    
    // Método para terminar el hilo, interrumpiéndolo
    public void terminar() {
        interrupt();  // Llama a interrupt() para interrumpir el hilo
    }
}
```

# 5. Sincronización de los hilos
En un entorno multihilo, cuando varios hilos acceden a recursos compartidos (como variables o estructuras de datos) de manera simultánea, pueden surgir problemas de sincronización. La sincronización garantiza que los hilos accedan a los recursos de forma controlada, evitando que dos o más hilos modifiquen los mismos datos al mismo tiempo.

Java proporciona varias formas de sincronizar hilos, tanto a nivel de métodos como de bloques de código.

## 5.1. Tipos de sincronización
### 5.1.1. Sincronización de métodos
La forma más simple de sincronizar el acceso a un recurso es mediante la palabra clave synchronized en el método. Cuando un método es marcado como synchronized, Java garantiza que solo un hilo pueda ejecutar ese método en una instancia de la clase a la vez.

### 5.1.2. Sincronización de bloques
En lugar de sincronizar todo un método, se puede usar la sincronización a nivel de bloque. Esto permite que solo una parte del código que accede a un recurso compartido sea sincronizada, lo que puede mejorar el rendimiento en algunos casos.

### 5.1.3. Palabra clave volatile
La palabra clave volatile es utilizada para declarar que una variable puede ser modificada por varios hilos. Cuando una variable es marcada como volatile, Java garantiza que los cambios en esa variable sean visibles inmediatamente para todos los hilos. Esto es útil cuando se necesita que un hilo observe los cambios en una variable sin necesidad de bloqueos adicionales.

## 5.2. Métodos especiales
En Java, cuando se trabaja con múltiples hilos, es posible que algunos hilos necesiten esperar a que se cumpla una cierta condición antes de continuar su ejecución. Para este tipo de coordinación entre hilos, Java proporciona tres métodos clave: wait(), notify() y notifyAll(). Estos métodos se utilizan junto con bloques synchronized y permiten la comunicación entre hilos que comparten recursos.

- **wait**(): El método wait() es utilizado por un hilo para ceder el control del CPU y esperar hasta que otro hilo lo notifique. Es llamado dentro de un bloque sincronizado y provoca que el hilo actual se suspenda hasta que otro hilo lo despierte.
- **notify**(): El método notify() es utilizado para despertar a un solo hilo que está esperando en el objeto monitor. Solo un hilo será despertado. Si hay múltiples hilos esperando, se elegirá uno al azar para reanudarse.
- **notifyAll**(): El método notifyAll() es similar a notify(), pero en lugar de despertar solo a un hilo, despierta a todos los hilos que están esperando en el objeto monitor. Este método es útil cuando hay más de un hilo esperando y queremos asegurarnos de que todos tengan la oportunidad de ejecutarse.

# EJERCICIOS
## 1. Ejercicios de Hilos
### EJERCICIO 1 - Contador
Ejecutar dos hilos. Tienen que contar hasta 1000  y una vez lleguen deben finalizar.

### EJERCICIO 2 - Operaciones
1. Crear una clase de nombre HiloRunnable. El programa principal lanzará dos hilos (empleando la interface Runnable) y después ejecutará un  bucle. Emplea isAlive para chequear cuándo acaban los hilos
2. Crear una clase de nombre HiloThread. El programa principal lanzará dos hilos (heredando de la clase Thread) y después ejecutará un bucle. Emplea isAlive para chequear cuándo acaban los hilos.
3. Crea una clase ej3.Tenemos una aplicación a la que se conectan de manera  concurrente 2 personas (2 hilos). Cada una de estas personas debe realizar  3 operaciones. Suponemos que cada una de estas operaciones tardará 10  milisegundos en realizarse. Representaremos las 3 tareas que realizará  cada una de estas personas con el siguiente código:
```java
for (int i = 0; i <3; i++){
	System.out.println("operación " + i);
	Thread.sleep(10);
}
```

### EJERCICIO 3 - Escritora
Crea un programa que contenga dos clases, Principal y Escritora. La clase Principal con tiene el main, que instanciará dos hilos de tipo Escritora y los ejecutará. Cada hilo hará lo  siguiente (indefinidamente):
- Si se inicializa con un True, escribirá números del 1 al 30.	
- Si se inicializa con un False, escribirá letras de la ‘a’ a la ‘z’
Comprueba que las salidas de ambos hilos salen mezcladas por la consola.

### EJERCICIO 4 - Ejemplo Join
Crea un programa que contenga dos clases, Principal y DetonadorConRetardo. La clase  Principal contiene el main, que instanciará cuatro hilos de tipo DetonadorConRetardo y  los ejecutará. Cada hilo hará lo siguiente:
- Se le inicializa con un nombre y un valor numérico (contador).
- Cuando el hilo se ejecute, escribe su nombre y el contador. A continuación, reduce  el valor del contador en 1. Repite estas acciones hasta que el valor de contador sea 0
- El completar su tarea, informa de que ha finalizado.
El hilo principal del programa (main) NO debe de finalizar antes que los demás hilos.

### EJERCICIO 5 - Prioridades
Se van a definir 3 hilos contadores, para ello hace falta definir la clase HiloContador. Cada hilo tendrá  asociado 3 botones:  
- El botón para finalizar el hilo  
- El botón -- que hace que el hilo tenga la prioridad mínima  
- El botón ++ que hace que el hilo tenga la prioridad máxima

Todos los hilos parten con una prioridad normal.
El botón “Finalizar todos” finaliza la ejecución de todos los hilos.  
En las etiquetas de abajo a la izquierda se muestran los contadores de cada hilo. Al iniciarse el  programa cada hilo empieza a contar desde cero y cada segundo incrementa su contador,  mostrándolo en la etiqueta.  
En las etiquetas de abajo a la derecha se muestra la prioridad que tiene cada hilo. Cada vez que se  cambie la prioridad se debe actualizar la etiqueta.

### EJERCICIO 6 - Consumidor - Productor
#### Buffer
El objeto compartido entre el productor y el consumidor está descrito por la clase  denominada Buffer.
La clase Buffer tiene dos atributos: el primero contenido guarda un carácter (es el buffer),  el segundo, bufferLleno indica si el buffer está lleno o está vacío, según que ésta variable  valga true o false.
La definición de las funciones poner y recoger son muy simples:  
 - La función _poner_ guarda el carácter que se le pasa en su parámetro c en el  miembro dato contenido, y pone el miembro bufferLleno en true (el buffer está lleno)
 - La función _recoger_ devuelve el carácter guardado en el atributo contenido,  siempre que este bufferLleno (el buffer lleno, o bufferLleno valga true). Si el buffer  esta lleno, lo deberé poner a false. En el caso de que no esté bufferLleno  (bufferLleno valga false) devuelve un carácter no alfabético: un espacio, un  tabulador, lo que desee el usuario.

#### Consumidor
La clase que describe al consumidor denominado Consumidor, deriva también de la clase  Thread y redefine el método run
La definición de run es similar a la de la clase Productor, salvo que, en vez de poner un  carácter en el buffer, recoge el carácter guardado en el buffer intermedio llamando a la  función recoger
El tiempo de pausa (argumento de la función sleep) puede ser distinto en la clase Productor  que en la clase Consumidor. Por ejemplo, para hacer que el productor sea más rápido que  el consumidor, se pone un tiempo menor en la primera que en la segunda.

#### Productor
La clase que describe el productor denominada Productor deriva de la clase Thread y  redefine la función run.
Tiene como atributo un objeto buffer de la clase Buffer

La función run ejecuta un bucle for, cuando se completa el bucle se alcanza el final de run y  el subproceso entra en el estado Death (muerto), y detiene su ejecución En el bucle for:
1. se genera una letra al azar
2. se pone en el objeto buffer, llamando a la función poner de la clase Buffer
3. se imprime  
4. se hace una pausa por un número determinado de milisegundos llamando a la  función sleep y pasándole el tiempo de pausa, durante este tiempo el subproceso está en el  estado Not Runnable

#### Ejecutable
Definimos una clase que describe una aplicación. En la función main, creamos tres objetos  un objeto b de la clase Buffer, un objeto p de la clase Productor y otro objeto c de la clase  Consumidor.  
Al constructor de las clases Productor y Consumidor le pasamos el objeto b compartido de  la clase Buffer.
Ponemos en marcha los subprocesos descritos por las clases Productor y Consumidor,  mediante la llamada a la función start de modo que el estado de los subprocesos pasa de  New Thread a Runnable. Los subprocesos una vez puestos en marcha mueren de muerte  natural, pasando al estado Death después de ejecutar un bucle for de 10 iteraciones en la  función run.

### EJERCICIO 7
#### Clase carrera
Se van a crear 4 JProgressBar para 4 caballos.
Un botón Empieza la carrera
Un Jlabel que indicará el ganador.

#### HiloCaballo
Constructor: JProgressBar barra, String caballo, Jlabel ganador Run: Se genera un numero aleatorio y se suma al value del JprogressBar. Se  espera un determinado tiempo para los siguientes cálculos.
Función: terminar(); (Termina el proceso)

#### Trampas
Especifica las prioridades antes de cada partida. 
Por defecto, aparecerá un 6.

**Importante: Los caballos se han de parar cuando uno de ellos llega al final.**

## 2. Ejercicios Extra de Hilos
### 1. Reto
Se trata de tener un cronómetro principal y un cronómetro regresivo. Cuando termina el regresivo se espera 3s y se finaliza el general. Se deben pausar a la vez y reanudar.

### 2. Cajero
El cajero permite dos tipos de operaciones: ingresar dinero y retirar dinero. Sin embargo, solo puede realizarse una operación a la vez, es decir, si una persona está ingresando dinero, nadie más podrá retirar dinero, y viceversa. 

## 3. Ejercicios de Procesos
### EJERCICIO 1
Con estos ejercicios se pretende revisar las clases relacionadas con los procesos en Java.
Crear dentro del proyecto Multiproceso un paquete ejercicio1 donde crearéis una clase por ejercicio.

#### 1. Ejer 1
Ejecutar una aplicación de Windows. (Bloc de notas, Word, …)

#### 2. Ejer 2
Ejecutar un comando de windows. (Dir, Taskmgr, …) y mostrar su resultado por pantalla.

#### 3. Ejer 3
Haz un programa que obtenga la dirección MAC y la muestre por pantalla

#### 4. Ejer 4
Haz un programa que muestre los procesos en ejecución.

#### 5. Ejer 5
Haz un programa que detecte si el bloc de notas se está ejecutando y en caso afirmativo cree un proceso que lo elimine de la ejecución (matar el proceso).

#### 6. Ejer 6
Programa que ejecuta un “.bat” previamente preparado y recoge la salida en un archivo y los errores en otro.

#### 7. Ejer 7
Haz un programa que llame a otro que envíe una cadena de caracteres y muestre el texto
Para ello, primero se creará un programa que llama a “EjemploLectura” que recoge una cadena de caracteres y escribe el resultado. Una vez que el programa funcione, probaremos que se ejecuta en el cmd con la instrucción “java nombredelpaquete.EjemploLectura.
Después, crearemos otro programa que llame Ejer7. Este programa pedirá un texto al usuario y lo enviará al programa EjemploLectura. Se recogerá la salida y se mostrará por pantalla.

### EJERCICIO 2
El programa consistirá en tres botones que iniciarán diferentes procesos:

1. Ejecutará un programa del sistema.
2. Ejecutará un comando sobre la consola de Windows (cmd).
3. Llamará 5 veces al programa ya implementado el cual pide un dato y lo imprime por pantalla.

Además de ejecutar los diferentes procesos, deberemos obtener el PID de cada proceso (en el caso del tercer proceso serán 5 PID diferentes). También obtendremos el PID del proceso padre y el resultado de la ejecución del segundo y el tercer proceso.

El resultado debería ser algo parecido a lo siguiente:

![image](https://github.com/user-attachments/assets/1ea459b2-9df7-4a8e-8a9c-b2252998bbfa)

