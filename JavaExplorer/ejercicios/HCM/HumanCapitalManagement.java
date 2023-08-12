
/**
 * El sistema de Manejo del Capital Humano (HCM) debe ser capaz de
 * asignar un nuevo empleado a un departamento.
 * a. Primero, crear el empleado.
 * b. Luego, recuperar el departamento específico al usar el
 * comportamiento de find del DepartamentFinder.
 * c. Finalmente, asignar el empleado recientemente creado a ese
 * departamento específico.
 * d. Luego de que el nuevo empleado es asignado, mostrar todos los
 * empleados en ese departamento.
 * e. Usando lo aprendido en clase, ¿qué clases necesitaremos y
 * qué atributos y comportamientos deberían tener?
 */
import java.util.ArrayList;
import java.util.Random;

public class HumanCapitalManagement {

  ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

  public HumanCapitalManagement() {
    departamentos.add(new Departamento("informatica"));
    departamentos.add(new Departamento("obras públicas"));
    departamentos.add(new Departamento("zoonosis"));
  }

  /**
   * Departamento debe poder retornar la lista de
   * todos sus empleados
   */
  public class Departamento {

    String nombre;
    ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    public Departamento(String name) {
      this.nombre = name;
      departamentos.add(this);
      Random random = new Random();
      String nombreEmpleado = "" + (char) ((random.nextInt(90 - 65 + 1) + 65));
      String apellidoEmpleado = "" + (char) ((random.nextInt(90 - 65 + 1) + 65));
      Empleado empleado = new Empleado(nombreEmpleado, apellidoEmpleado);
      asignarEmpleado(empleado);
    }

    public String getName() {
      return this.nombre;
    }

    public Departamento asignarEmpleado(Empleado empleado) {
      empleados.add(empleado);
      empleado.asignarDepartamento(this);
      return this;
    }

    public void getEmpleados() {
      for (Empleado empleado : empleados) {
        System.out.println(empleado);
      }
    }

    @Override
    public String toString() {
      return this.nombre;
    }

  }

  /**
   * Empleado que puede ser asignado a un departamento
   * y mostrar su nombre y apellido
   */
  public class Empleado {

    String nombre;
    String apellido;
    Departamento departamento;

    public Empleado(String name, String lastName) {
      this.nombre = name;
      this.apellido = lastName;
      this.departamento = null;
    }

    public void asignarDepartamento(Departamento depto) {
      this.departamento = depto;
    }

    @Override
    public String toString() {
      return this.apellido + ", " + this.nombre + ". Departamento: " + this.departamento;
    }

  }

  /**
   * find puede recuperar un departamento
   * de acuerdo a su nombre
   */
  public Departamento find(String depto) {
    for (Departamento elemento : departamentos) {
      if (elemento.getName().equals(depto)) {
        return elemento;
      }
    }
    return null;
  }

  public static void main(String[] args) {

    HumanCapitalManagement consultora = new HumanCapitalManagement();

    Empleado saimon = consultora.new Empleado("Cayetano Simón", "Paradiso");
    Departamento depto = consultora.find("informatica");
    depto.asignarEmpleado(saimon);
    depto.getEmpleados();
  }

}