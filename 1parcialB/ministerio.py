class Ministerio:
    def __init__(self, nombre, direccion, empleados, edades, sueldos):
        self.nombre = nombre
        self.direccion = direccion
        self.empleados = empleados
        self.edades = edades
        self.sueldos = sueldos
        self.nroEmpleados = len(empleados)
    # a) Instanciar 2 objetos Ministerio de diferente forma
    @staticmethod
    def ejemplo_instanciacion():
        # Forma 1: Asignando valores directamente después de la creación del objeto
        ministerio_gobierno = Ministerio(None, None, [], [], [])  # Pasar listas vacías en lugar de None
        ministerio_gobierno.nombre = "Ministerio de Gobierno"
        ministerio_gobierno.direccion = "Plaza Murillo, La Paz"
        ministerio_gobierno.empleados = [["Carlos", "Mamani", "Perez"], ["Elena", "Quispe", "Rojas"]]
        ministerio_gobierno.edades = [40, 35]
        ministerio_gobierno.sueldos = [3000, 2800]
        ministerio_gobierno.nroEmpleados = len(ministerio_gobierno.empleados)

        # Forma 2: Utilizando el constructor (esta parte ya debería estar bien)
        ministerio_economia = Ministerio(
            "Ministerio de Economía y Finanzas Públicas",
            "Av. Mariscal Santa Cruz, La Paz",
            [["Sofia", "Vargas", "Luna"], ["Andres", "Flores", "Gutierrez"], ["Lucia", "Paz", "Sosa"]],
            [32, 45, 28],
            [3500, 4200, 3100]
        )

        print(f"Ministerio de Gobierno: Nombre - {ministerio_gobierno.nombre}, Dirección - {ministerio_gobierno.direccion}, Nro. Empleados - {ministerio_gobierno.nroEmpleados}")
        print(f"Ministerio de Economía: Nombre - {ministerio_economia.nombre}, Dirección - {ministerio_economia.direccion}, Nro. Empleados - {ministerio_economia.nroEmpleados}")
    # b) Eliminar a los empleados con edad X
    def eliminar_empleados_por_edad(self, edad_eliminar):
        nuevos_empleados = []
        nuevas_edades = []
        nuevos_sueldos = []  # Aquí defines la variable

        for i in range(self.nroEmpleados):
            if self.edades[i] != edad_eliminar:
                nuevos_empleados.append(self.empleados[i])
                nuevas_edades.append(self.edades[i])
                nuevos_sueldos.append(self.sueldos[i])

        self.empleados = nuevos_empleados
        self.edades = nuevas_edades
        self.sueldos = nuevos_sueldos  
        self.nroEmpleados = len(self.empleados)
    # c) Sobrecargar un operador para: Transferir al empleado X del segundo objeto al primer objeto Ministerio
    def __add__(self, otro_ministerio):
        nombre_empleado_transferir = input("Ingrese el nombre del empleado a transferir del segundo al primer ministerio: ")
        empleado_transferido = None
        indice_transferido = -1
        for i in range(otro_ministerio.nroEmpleados):
            if otro_ministerio.empleados[i][0] == nombre_empleado_transferir:
                empleado_transferido = [otro_ministerio.empleados[i], otro_ministerio.edades[i], otro_ministerio.sueldos[i]]
                indice_transferido = i
                break
        if empleado_transferido:
            self.empleados.append(empleado_transferido[0])
            self.edades.append(empleado_transferido[1])
            self.sueldos.append(empleado_transferido[2])
            self.nroEmpleados += 1

            # Eliminar al empleado del segundo objeto
            del otro_ministerio.empleados[indice_transferido]
            del otro_ministerio.edades[indice_transferido]
            del otro_ministerio.sueldos[indice_transferido]
            otro_ministerio.nroEmpleados -= 1
            print(f"\nEmpleado {nombre_empleado_transferir} transferido exitosamente al primer ministerio.")
        else:
            print(f"\nNo se encontró al empleado {nombre_empleado_transferir} en el segundo ministerio.")
        return otro_ministerio 
    # d) Sobrecargar un Método para:
    def mostrar_empleados_por_criterio(self, criterio="menor_edad"):
        if not self.empleados:
            print("No hay empleados registrados.")
            return
        if criterio == "menor_edad":
            menor_edad = min(self.edades)
            print(f"\nEmpleado(s) con la menor edad ({menor_edad} años):")
            for i in range(self.nroEmpleados):
                if self.edades[i] == menor_edad:
                    print(f"- {self.empleados[i][0]} {self.empleados[i][1]} {self.empleados[i][2]} ({self.edades[i]} años)")
        elif criterio == "menor_sueldo":
            menor_sueldo = min(self.sueldos)
            print(f"\nEmpleado(s) con el menor sueldo ({menor_sueldo} Bs.):")
            for i in range(self.nroEmpleados):
                if self.sueldos[i] == menor_sueldo:
                    print(f"- {self.empleados[i][0]} {self.empleados[i][1]} {self.empleados[i][2]} ({self.sueldos[i]} Bs.)")
        else:
            print(f"Criterio '{criterio}' no válido. Use 'menor_edad' o 'menor_sueldo'.")
if __name__ == "__main__":
    # a) Instanciación
    print("--- Ejemplo de Instanciación ---")
    Ministerio.ejemplo_instanciacion()
    # Crear objetos para las siguientes pruebas
    ministerio_salud = Ministerio(
        "Ministerio de Salud y Deportes",
        "Av. Saavedra, La Paz",
        [["Raul", "Gutierrez", "Torres"], ["Carla", "Flores", "Mendoza"], ["Pedro", "Mamani", "Lima"]],
        [48, 29, 38],
        [3800, 2600, 3100]
    )

    ministerio_educacion = Ministerio(
        "Ministerio de Educación",
        "Av. Arce, La Paz",
        [["Ana", "Perez", "Vargas"], ["Jorge", "Ramos", "Quispe"], ["Sofia", "Condori", "Diaz"], ["Luis", "Arce", "Calle"]],
        [33, 52, 25, 41],
        [3200, 4500, 2700, 3600]
    )

    # b) Eliminar empleados por edad
    print("\n--- Ejemplo de Eliminación por Edad ---")
    print("Empleados del Ministerio de Salud antes de eliminar:")
    for empleado in ministerio_salud.empleados:
        print(empleado)
    ministerio_salud.eliminar_empleados_por_edad(29)
    print("Empleados del Ministerio de Salud después de eliminar los de 29 años:")
    for empleado in ministerio_salud.empleados:
        print(empleado)

    # c) Transferir un empleado del segundo al primer ministerio
    print("\n--- Ejemplo de Transferencia de Empleado ---")
    print("Empleados del Ministerio de Salud antes de la transferencia:")
    for empleado in ministerio_salud.empleados:
        print(empleado)
    print("Empleados del Ministerio de Educación antes de la transferencia:")
    for empleado in ministerio_educacion.empleados:
        print(empleado)

    ministerio_educacion = ministerio_salud + ministerio_educacion  # Transferimos del segundo (educacion) al primero (salud)
    print("Empleados del Ministerio de Salud después de la transferencia:")
    for empleado in ministerio_salud.empleados:
        print(empleado)
    print("Empleados del Ministerio de Educación después de la transferencia:")
    for empleado in ministerio_educacion.empleados:
        print(empleado)
    # d) Mostrar empleados por criterio
    print("\n--- Ejemplo de Mostrar Empleados por Criterio ---")
    ministerio_salud.mostrar_empleados_por_criterio("menor_edad")
    ministerio_salud.mostrar_empleados_por_criterio("menor_sueldo")
    ministerio_salud.mostrar_empleados_por_criterio("mayor_edad") # Criterio no válido