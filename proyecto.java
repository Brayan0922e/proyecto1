// CLASE EMPLEADO 
class Empleado {
    private String nombre;
    private String cargo;
    private double sueldoBase;
    private int horasExtras;

    public Empleado(String nombre, String cargo, double sueldoBase, int horasExtras) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.sueldoBase = sueldoBase;
        this.horasExtras = horasExtras;
    }

    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public double getSueldoBase() { return sueldoBase; }
    public int getHorasExtras() { return horasExtras; }
}

// CLASE NOMINA 
class Nomina {
    private final int VALOR_HORA_EXTRA = 35000;

    public double calcularPagoHorasExtras(int horas) {
        return horas * VALOR_HORA_EXTRA;
    }

    public double calcularRetencion(double sueldoBase) {
        double salarioMinimo = 1300000;

        if (sueldoBase >= 3 * salarioMinimo && sueldoBase <= 5 * salarioMinimo) {
            return sueldoBase * 0.10;
        } else if (sueldoBase > 5 * salarioMinimo) {
            return sueldoBase * 0.16;
        } else {
            return 0;
        }
    }

    public double calcularSeguridadSocial(double sueldoBase) {
        return sueldoBase * 0.04;
    }

    public double calcularSalarioNeto(Empleado emp) {
        double pagoExtras = calcularPagoHorasExtras(emp.getHorasExtras());
        double retencion = calcularRetencion(emp.getSueldoBase());
        double seguridad = calcularSeguridadSocial(emp.getSueldoBase());
        double salarioBruto = emp.getSueldoBase() + pagoExtras;
        double descuentos = retencion + seguridad;
        return salarioBruto - descuentos;
    }
}

// CLASE MAIN 
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Empleado> empleados = new ArrayList<>();
        Nomina nomina = new Nomina();
        boolean continuar = true;

        while (continuar) {

            System.out.println("\n--- REGISTRO DE EMPLEADO ---");
            System.out.print("Nombre del empleado: ");
            String nombre = sc.nextLine();

            System.out.print("Cargo del empleado: ");
            String cargo = sc.nextLine();

            System.out.print("Sueldo base: ");
            double sueldoBase = sc.nextDouble();

            System.out.print("Horas extras trabajadas: ");
            int horasExtras = sc.nextInt();
            sc.nextLine();

            Empleado emp = new Empleado(nombre, cargo, sueldoBase, horasExtras);
            empleados.add(emp);

            System.out.print("¿Desea registrar otro empleado? (s/n): ");
            String resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }

        System.out.println("\n\n RESULTADOS DE NÓMINA ");
        double totalNomina = 0;

        for (Empleado emp : empleados) {
            double pagoExtras = nomina.calcularPagoHorasExtras(emp.getHorasExtras());
            double retencion = nomina.calcularRetencion(emp.getSueldoBase());
            double seguridad = nomina.calcularSeguridadSocial(emp.getSueldoBase());
            double salarioNeto = nomina.calcularSalarioNeto(emp);

            totalNomina += salarioNeto;

            System.out.println("\nEmpleado: " + emp.getNombre());
            System.out.println("Cargo: " + emp.getCargo());
            System.out.println("Sueldo Base: $" + emp.getSueldoBase());
            System.out.println("Pago por Horas Extras: $" + pagoExtras);
            System.out.println("Descuento Retención: $" + retencion);
            System.out.println("Descuento Seguridad Social: $" + seguridad);
            System.out.println("Salario Neto: $" + salarioNeto);
        }

        System.out.println("\nTOTAL NÓMINA A PAGAR POR LA EMPRESA: $" + totalNomina);
    }
}
