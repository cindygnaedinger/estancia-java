import servicios.FamiliaServicio;

import java.util.Scanner;

public class Main {
public static Scanner scan = new Scanner(System.in);

public static void main(String[] args) {

menu();
}

private static void menu() {
int opcion;
System.out.println("Ingrese una opcion");
opcion = scan.nextInt();
switch (opcion){
case 1: listarFamiliasConAlMenosTresHijos();
case 2:
default:
System.out.println("Se ha ingresado un valor incorrecto");

}
}

private static void listarFamiliasConAlMenosTresHijos() {
FamiliaServicio familiaServicio= new FamiliaServicio();
familiaServicio.listarFamiliasConAlMenosTresHijos();
}


}
