# Listado de Ejercicios

## Ejercicios de Hilos
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

## Ejercicios Extra de Hilos
1. Contador Concurrente
Crea un programa en el que varios hilos incrementen un contador compartido de manera concurrente. Asegúrate de que el valor final del contador sea correcto después de que todos los hilos hayan terminado su ejecución.

2. Simulación de Productor y Consumidor
Implementa una simulación de un sistema productor-consumidor, donde un productor genera datos y los coloca en un buffer, mientras que un consumidor toma esos datos para procesarlos. Asegúrate de que el acceso al buffer compartido sea adecuado para evitar errores de concurrencia.

3. Trabajo en Paralelo: Sumar una Gran Cantidad de Números
Diseña un programa que calcule la suma de una gran cantidad de números (por ejemplo, los primeros 100 millones) dividiendo el trabajo entre varios hilos. Cada hilo deberá sumar una parte del total, y el programa deberá esperar a que todos los hilos terminen para mostrar el resultado final.

4. Simulación de un Cajero Automático con Tiempo Límite
Crea un sistema de cajero automático donde el usuario tiene un tiempo limitado para completar una transacción. Si el tiempo se excede, el proceso de la transacción debe ser cancelado y se debe mostrar un mensaje indicando que el tiempo ha expirado.

5. Tareas Concurrentes con Bandera de Finalización
Desarrolla un programa en el que varios hilos ejecuten tareas independientes con tiempos de espera. Asegúrate de que, cuando un hilo termine su tarea, se actualice una bandera para indicar su finalización. Otros hilos deben verificar esa bandera para coordinar su ejecución.

6. Simulación de Billetera de Varios Hilos
Diseña una clase que simule una billetera en la que varios hilos realicen depósitos y retiros concurrentemente. Asegúrate de que las transacciones no interfieran entre sí y que el saldo final sea consistente.

7. Contador con Detención Temporal
Implementa un contador que se incremente de uno en uno cada segundo. El contador debe detenerse en cualquier momento, y el programa debe manejar la detención de manera controlada antes de que llegue a un número máximo determinado.

8. Simulación de un Restaurante con Meseros Concurrentes
Crea una simulación de un restaurante en la que varios meseros atienden a los clientes de manera concurrente. Los meseros deben atender a los clientes según el orden de llegada, pero pueden ser interrumpidos si es necesario atender a otro cliente que espera en la fila.

9. Ejecución Concurrente de Tareas Diversas
Diseña un sistema en el que múltiples hilos realicen diferentes tareas, como descargar archivos, procesar datos o ejecutar cálculos. Cada tarea tiene una duración distinta, y el programa debe asegurarse de que todas las tareas finalicen antes de pasar a la siguiente fase del programa.

10. Simulación de un Juego de Adivinanza
Desarrolla un juego en el que varios jugadores intentan adivinar un número secreto. Cada jugador hace una suposición y el sistema les informa si han acertado. El juego debe finalizar tan pronto como un jugador adivine correctamente el número.
