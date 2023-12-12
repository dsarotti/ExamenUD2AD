package constructor;

import helpers.Helper;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author adrianfg
 */
public class Main {

    // ====================================================================
    // ===================== INICIO DE LO EDITABLE ========================
    // ====================================================================

    /**
     * Realiza las operaciones necesarias antes de la ejecución del programa
     */
    private static void preInit() {
        File carpetaPedidos = new File("out/pedidos");
        File carpetaInformes = new File("out/informes");
        carpetaPedidos.mkdirs();
        carpetaInformes.mkdirs();
    }

    /**
     * Genera un informe del día actual.
     * 
     * @param obra La obra de la cual generar el informe
     * @param day  El día actual
     */
    private static void genReport(Obra obra, int day) {
        String fileName = Helper.getCurrentTime() + "_" + day; // Nombre del fichero a usar. No tocar
        BufferedOutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream("out/informes/"+fileName));
            streamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            streamWriter.write(obra.toString());
            streamWriter.flush();
        } catch (IOException e) {
            System.err.println("Error al escribir el informe en el archivo: " + e.getMessage());
        } finally {
            try {
                if (streamWriter != null) {
                    streamWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    /**
     * Genera un nuevo pedido aleatorio en formato XML.
     * 
     * @param day El día actual
     */
    private static void newOrder(int day) {
        String fileName = Helper.getCurrentTime() + "_" + day; // Nombre del fichero a usar. No tocar

        // Crea un documento XML
        Document document = DocumentHelper.createDocument();

        // Crea el elemento raíz "pedido" con el atributo "dia"
        Element pedidoElement = document.addElement("pedido");
        pedidoElement.addAttribute("dia", String.valueOf(day));

        // Genera un nombre de proveedor aleatorio
        String proveedor = getProveedorAleatorio();

        // Agrega el elemento "proveedor"
        Element proveedorElement = pedidoElement.addElement("proveedor");
        proveedorElement.setText(proveedor);

        // Agrega el elemento "materiales"
        Element materialesElement = pedidoElement.addElement("materiales");

        // genera cada material con cantidades aleatorias entre 0 y 15
        Element ladrillos = materialesElement.addElement("ladrillos");
        ladrillos.setText(String.valueOf(Helper.getRandomNumber(0, 16)));

        Element acero = materialesElement.addElement("acero");
        acero.setText(String.valueOf(Helper.getRandomNumber(0, 16)));

        Element cemento = materialesElement.addElement("cemento");
        cemento.setText(String.valueOf(Helper.getRandomNumber(0, 16)));

        Element cristal = materialesElement.addElement("cristal");
        cristal.setText(String.valueOf(Helper.getRandomNumber(0, 16)));


        // Guarda el documento en un archivo con formato Pretty Print
        FileWriter fileWriter = null;
        XMLWriter xmlWriter = null;

        try {
            fileWriter = new FileWriter("out/pedidos/" + fileName);
            OutputFormat format = OutputFormat.createPrettyPrint();
            xmlWriter = new XMLWriter(fileWriter, format);
            xmlWriter.write(document);
            xmlWriter.flush();
        } catch (IOException e) {
            System.err.println("Error al guardar el informe XML en el archivo: " + e.getMessage());
        } finally {
            try {
                if (xmlWriter != null) {
                    xmlWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

    /**
     * Método auxiliar que devuelve un nombre de proveedor aleatorio de una lista
     * preestablecida.
     * 
     * @return un nombre de un proveedor
     */
    private static String getProveedorAleatorio() {
        // Genera un nombre aleatorio para el proveedor
        String[] nombresProveedores = { "ProveedorA", "ProveedorB", "ProveedorC", "ProveedorX", "ProveedorY",
                "ProveedorZ" };
        return nombresProveedores[Helper.getRandomNumber(0, nombresProveedores.length) ];
    }

    /**
     * Lee los pedidos, recoge los materiales y borra los archivos.
     * 
     * @return Los materiales
     */
    private static Materiales getOrders() {
        File directory = new File("out/pedidos");
        Materiales materiales = new Materiales();
        Boolean existePedido = false;
        // Obtiene la lista de archivos en el directorio
        File[] files = directory.listFiles();

        // Procesa cada archivo en el directorio
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        SAXReader reader = new SAXReader();
                        Document document = reader.read(file);

                        Element pedidoElement = document.getRootElement();

                        // Obtener los valores de los elementos de materiales
                        Element materialesElement = pedidoElement.element("materiales");
                        int ladrillos = Integer.parseInt(materialesElement.elementText("ladrillos"));
                        int acero = Integer.parseInt(materialesElement.elementText("acero"));
                        int cemento = Integer.parseInt(materialesElement.elementText("cemento"));
                        int cristal = Integer.parseInt(materialesElement.elementText("cristal"));

                        materiales.setLadrillos(materiales.getLadrillos()+ladrillos);;
                        materiales.setAcero(materiales.getAcero()+acero);
                        materiales.setCemento(materiales.getCemento()+cemento);
                        materiales.setCristal(materiales.getCristal()+cristal);

                        //Establece la variable existePedido para inidicar que se ha encontrado y procesado un XML de pedido válido
                        if(!existePedido)existePedido=true;

                        file.delete();
                    } catch (Exception e) {
                        System.err.println("Error al leer el archivo XML: " + e.getMessage());
                    }
                }
            }
        }

        //Si se ha establecido la variable devuelve materiales, si no devuelve null.
        if(existePedido){
             return materiales;
        }else{
            return null;
        }
    }

    /**
     * Realiza las operaciones al terminar el programa
     * Borra la carpeta out y su contenido
     */
    private static void end() {
        File carpetaOut = new File("out");
        try {
            if (carpetaOut.exists() && carpetaOut.isDirectory()) {
                borrarContenidoDirectorio(carpetaOut);  // Borra el contenido de la carpeta (archivos y subdirectorios)
                if (!carpetaOut.delete()) {
                    System.err.println("No se pudo borrar la carpeta: " + carpetaOut.getAbsolutePath());
                } else {
                    System.out.println("Carpeta borrada correctamente: " + carpetaOut.getAbsolutePath());
                }
            } else {
                System.err.println("La carpeta especificada no existe o no es un directorio válido.");
            }
        } catch (Exception e) {
            System.err.println("Error al borrar la carpeta: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para borrar el contenido de un directorio y de sus subdirectorios.
     * @param directorio
     */
    private static void borrarContenidoDirectorio(File directorio){
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    borrarContenidoDirectorio(archivo);
                }
                if (!archivo.delete()) {
                    System.err.println("No se pudo borrar el archivo: " + archivo.getAbsolutePath());
                }
            }
        }
    }

    // ====================================================================
    // ====================== FIN DE LO EDITABLE ==========================
    // ====================================================================

    /**
     * Muestra el menú por pantalla
     */
    private static void showMenu() {
        System.out.println("-----------------------------------");
        System.out.println("0. Estado.");
        System.out.println("1. Solicitar materiales.");
        System.out.println("2. Construir.");
        System.out.println("3. Terminar");
        System.out.println("Operación a realizar: ");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        preInit();

        boolean run = true;
        int day = 0;

        Materiales mat = new Materiales(100, 50, 50, 20);
        Obra con = new Obra("Skyreaper", mat);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Día " + day + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        con.status();

        while (run) {
            String op = "";
            showMenu();
            try {
                op = reader.readLine();
            } catch (IOException e) {
                System.err.println("Fallo de lectura.");
                e.printStackTrace();
                op = "6"; // Finalizo
            }

            switch (op) {
                case "0":
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Día " + day + " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    con.status();
                    break;
                case "1":
                    newOrder(day);
                    System.out.println("Pedido generado");
                    break;
                case "2":
                    Materiales pedidos = getOrders();
                    if (pedidos != null) {
                        con.construir(pedidos);
                    }
                    genReport(con, day);
                    day++;
                    break;
                default:
                    run = false;
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Terminado ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
        try {
            reader.close();
        } catch (IOException e) {

        }

        end();
    }

}
