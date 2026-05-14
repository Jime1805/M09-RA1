import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Part1_LlegirSortida {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());

        ProcessBuilder pb = new ProcessBuilder(SO.llistarFitxers());
        pb.directory(new File("."));
        pb.redirectErrorStream(true);
        Process process = pb.start();

        System.out.println("=== Contingut del directori ===");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String linia;
        while ((linia = br.readLine()) != null) {
            System.out.println(linia);
        }

        int codi = process.waitFor();
        System.out.println("\nEl procés ha acabat amb el codi: " + codi);
    }
}