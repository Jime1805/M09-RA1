import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class Part3_Pipe {
    public static void main(String[] args) throws Exception {
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());

        ProcessBuilder pb1 = new ProcessBuilder(SO.llistarFitxers());
        pb1.directory(new File("./src"));

        ProcessBuilder pb2 = new ProcessBuilder(SO.filtrar(".java"));

        List<Process> pipeline = ProcessBuilder.startPipeline(List.of(pb1, pb2));
    
        Process darrer = pipeline.get(pipeline.size() - 1);

        System.out.println("=== Fitxers .java trobats ===");
        BufferedReader br = new BufferedReader(new InputStreamReader(darrer.getInputStream()));

        String linia;

        while ((linia = br.readLine()) != null) {
            System.out.println(linia);
        }

        for (Process p: pipeline){
            p.waitFor();
        }

        System.out.println("\nPipeline completat.");
    }
}
