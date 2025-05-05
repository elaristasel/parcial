import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LineaTeleferico {
    private String color;
    private List<String> tramo;
    private int nroCabinas;
    private List<String[]> empleados;
    private List<Integer> edades;
    private List<Double> sueldos;

    public LineaTeleferico(String color, List<String> tramo, int nroCabinas, List<String[]> empleados, List<Integer> edades, List<Double> sueldos) {
        this.color = color;
        this.tramo = tramo;
        this.nroCabinas = nroCabinas;
        this.empleados = empleados;
        this.edades = edades;
        this.sueldos = sueldos;
    }
    // a) Instanciar 2 objetos LineaTeleferico de diferente forma
    public static void ejemploInstanciacion() {
        // Forma 1: Asignando valores directamente después de la creación del objeto
        LineaTeleferico lineaRoja = new LineaTeleferico("Rojo", new ArrayList<>(), 20, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        lineaRoja.tramo.addAll(List.of("Estación Central", "Estación Cementerio", "Estación 16 de Julio"));
        lineaRoja.empleados.addAll(List.of(new String[]{"Pedro", "Rojas", "Luna"}, new String[]{"Lucy", "Sosa", "Rios"}, 
        new String[]{"Ana", "Perez", "Rojas"}, new String[]{"Saul", "Arce", "Calle"}));
        lineaRoja.edades.addAll(List.of(35, 43, 26, 29));
        lineaRoja.sueldos.addAll(List.of(2500.0, 3250.0, 2700.0, 2500.0));
        lineaRoja.nroCabinas = 20;
        // Forma 2: Utilizando el constructor
        List<String[]> empleadosAmarilla = List.of(new String[]{"Carla", "Flores", "Mendoza"}, new String[]{"Luis", "Gutierrez", "Paz"});
        List<Integer> edadesAmarilla = List.of(28, 38);
        List<Double> sueldosAmarilla = List.of(2800.0, 3000.0);
        List<String> tramoAmarilla = List.of("Estación Mirador", "Estación Cotahuma", "Estación Jach'a Qhathu");
        LineaTeleferico lineaAmarilla = new LineaTeleferico("Amarillo", tramoAmarilla, 15, empleadosAmarilla, edadesAmarilla, sueldosAmarilla);
        System.out.println("Línea Roja: Color - " + lineaRoja.color + ", Tramo - " + lineaRoja.tramo + ", Cabinas - " + lineaRoja.nroCabinas);
        System.out.println("Línea Amarilla: Color - " + lineaAmarilla.color + ", Tramo - " + lineaAmarilla.tramo + ", Cabinas - " + lineaAmarilla.nroCabinas);
    }
    // b) Eliminar a los empleados con apellido X, paterno o materno
    public void eliminarEmpleadosPorApellido(String apellido) {
        List<String[]> nuevosEmpleados = new ArrayList<>();
        List<Integer> nuevasEdades = new ArrayList<>();
        List<Double> nuevosSueldos = new ArrayList<>();

        for (int i = 0; i < empleados.size(); i++) {
            if (!empleados.get(i)[1].equals(apellido) && !empleados.get(i)[2].equals(apellido)) {
                nuevosEmpleados.add(empleados.get(i));
                nuevasEdades.add(edades.get(i));
                nuevosSueldos.add(sueldos.get(i));
            }
        }
        this.empleados = nuevosEmpleados;
        this.edades = nuevasEdades;
        this.sueldos = nuevosSueldos;
    }
    // c) Sobrecargar un operador para: Transferir al empleado X del primer objeto al segundo objeto LineaTeleferico
    public LineaTeleferico transferirEmpleado(LineaTeleferico otraLinea, String nombreEmpleadoTransferir) {
        int indiceTransferido = -1;
        String[] empleadoTransferido = null;
        Integer edadTransferida = null;
        Double sueldoTransferido = null;

        for (int i = 0; i < this.empleados.size(); i++) {
            if (this.empleados.get(i)[0].equals(nombreEmpleadoTransferir)) {
                empleadoTransferido = this.empleados.get(i);
                edadTransferida = this.edades.get(i);
                sueldoTransferido = this.sueldos.get(i);
                indiceTransferido = i;
                break;
            }
        }
        if (empleadoTransferido != null) {
            otraLinea.empleados.add(empleadoTransferido);
            otraLinea.edades.add(edadTransferida);
            otraLinea.sueldos.add(sueldoTransferido);

            this.empleados.remove(indiceTransferido);
            this.edades.remove(indiceTransferido);
            this.sueldos.remove(indiceTransferido);
            System.out.println("\nEmpleado " + nombreEmpleadoTransferir + " transferido exitosamente.");
        } else {
            System.out.println("\nNo se encontró al empleado " + nombreEmpleadoTransferir + " en la primera línea.");
        }
        return otraLinea;
    }
    // d) Sobrecargar un Método para:
    public void mostrarEmpleadosPorCriterio(String criterio) {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        if (criterio.equalsIgnoreCase("edad")) {
            int mayorEdad = edades.get(0);
            for (int edad : edades) {
                if (edad > mayorEdad) {
                    mayorEdad = edad;
                }
            }
            System.out.println("\nEmpleado(s) con la mayor edad (" + mayorEdad + " años):");
            for (int i = 0; i < empleados.size(); i++) {
                if (edades.get(i) == mayorEdad) {
                    System.out.println("- " + empleados.get(i)[0] + " " + empleados.get(i)[1] + " " + empleados.get(i)[2] + " (" + edades.get(i) + " años)");
                }
            }
        } else if (criterio.equalsIgnoreCase("sueldo")) {
            double mayorSueldo = sueldos.get(0);
            for (double sueldo : sueldos) {
                if (sueldo > mayorSueldo) {
                    mayorSueldo = sueldo;
                }
            }
            System.out.println("\nEmpleado(s) con el mayor sueldo (" + mayorSueldo + " Bs.):");
            for (int i = 0; i < empleados.size(); i++) {
                if (sueldos.get(i) == mayorSueldo) {
                    System.out.println("- " + empleados.get(i)[0] + " " + empleados.get(i)[1] + " " + empleados.get(i)[2] + " (" + sueldos.get(i) + " Bs.)");
                }
            }
        } else {
            System.out.println("Criterio '" + criterio + "' no válido. Use 'edad' o 'sueldo'.");
        }
    }
    public static void main(String[] args) {
        // Crear un objeto para las siguientes pruebas
        List<String[]> empleadosPrueba = new ArrayList<>(List.of(new String[]{"Pedro", "Rojas", "Luna"}, new String[]{"Lucy", "Sosa", "Rios"}, new String[]{"Ana", "Perez", "Rojas"}, new String[]{"Saul", "Arce", "Calle"}));
        List<Integer> edadesPrueba = new ArrayList<>(List.of(35, 43, 26, 29));
        List<Double> sueldosPrueba = new ArrayList<>(List.of(2500.0, 3250.0, 2700.0, 2500.0));
        LineaTeleferico lineaPrueba = new LineaTeleferico("Naranja", new ArrayList<>(List.of("A", "B")), 10, empleadosPrueba, edadesPrueba, sueldosPrueba);
        // b) Eliminar empleados por apellido
        System.out.println("Empleados antes de eliminar:");
        for (String[] empleado : lineaPrueba.empleados) {
            System.out.println(String.join(" ", empleado));
        }
        lineaPrueba.eliminarEmpleadosPorApellido("Rojas");
        System.out.println("Empleados después de eliminar los 'Rojas':");
        for (String[] empleado : lineaPrueba.empleados) {
            System.out.println(String.join(" ", empleado));
        }
        // c) Transferir un empleado
        List<String[]> empleadosDestino = new ArrayList<>();
        empleadosDestino.add(new String[]{"Carmen", "Diaz", "Vargas"});
        List<Integer> edadesDestino = new ArrayList<>(List.of(31));
        List<Double> sueldosDestino = new ArrayList<>(List.of(2800.0));
        LineaTeleferico lineaDestino = new LineaTeleferico("Celeste", new ArrayList<>(List.of("C", "D")), 8, empleadosDestino, edadesDestino, sueldosDestino);
        System.out.println("Empleados de la Línea Naranja antes de la transferencia:");
        for (String[] empleado : lineaPrueba.empleados) {
            System.out.println(String.join(" ", empleado));
        }
        System.out.println("Empleados de la Línea Celeste antes de la transferencia:");
        for (String[] empleado : lineaDestino.empleados) {
            System.out.println(String.join(" ", empleado));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del empleado a transferir de la Línea Naranja a la Celeste: ");
        String nombreTransferir = scanner.nextLine();
        lineaDestino = lineaPrueba.transferirEmpleado(lineaDestino, nombreTransferir);
        System.out.println("Empleados de la Línea Naranja después de la transferencia:");
        for (String[] empleado : lineaPrueba.empleados) {
            System.out.println(String.join(" ", empleado));
        }
        System.out.println("Empleados de la Línea Celeste después de la transferencia:");
        for (String[] empleado : lineaDestino.empleados) {
            System.out.println(String.join(" ", empleado));
        }
        scanner.close();
        // d) Mostrar empleados por criterio
        System.out.println("\n--- Ejemplo de Mostrar Empleados por Criterio ---");
        lineaPrueba.mostrarEmpleadosPorCriterio("edad");
        lineaPrueba.mostrarEmpleadosPorCriterio("sueldo");
        lineaPrueba.mostrarEmpleadosPorCriterio("nombre"); 
    }
}