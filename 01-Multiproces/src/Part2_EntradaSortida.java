import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Part2_EntradaSortida {
    public static void main(String[] args) throws Exception{
        System.out.println("Sistema operatiu detectat: " + SO.nomSO());
        
        ProcessBuilder pb = new ProcessBuilder(SO.ordenar());
        pb.redirectErrorStream(true);
        Process process = pb.start();
        
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(process.getOutputStream()));

        String[] fruites = {"platan", "poma", "albergínia", "cireres", "maduixa"};

        System.out.println("Enviem al procés 'sort':");

        for(String fruita: fruites){
            System.out.println("  -> " + fruita);
            pw.println(fruita);
        }

        pw.close();

        System.out.println("\nResposta del procés 'sort' (ordenat):");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String linia;

        while ((linia = br.readLine()) != null) {
            System.out.println("  <- " + linia);
        }

        int codi = process.waitFor();
        System.out.println("\nCodi de retorn: " + codi);
    }
}
