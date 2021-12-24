package FC;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class CandidatosList {

    int contador;

    private ArrayList<Candidato> candidatosList = new ArrayList<>();

    public ArrayList<Candidato> getCandidatosList() {
        return candidatosList;
    }

    public void setCandidatosList(ArrayList<Candidato> candidatosList) {
        this.candidatosList = candidatosList;
    }

    public ArrayList<Candidato> filtrar(String ciudad) {

        ArrayList<Candidato> candidatosCiudad = new ArrayList<>();
        for (Candidato candidato : candidatosList) {
            if (Objects.equals(ciudad, candidato.getCiudad())) {
                candidatosCiudad.add(candidato);
            }
        }
        return candidatosCiudad;
    }

    //--------------------------------------------------//

    public ArrayList<Candidato> filtrar(boolean tipoTraslado) {

        ArrayList<Candidato> candidatosTraslado = new ArrayList<>();

        for (Candidato candidato : candidatosList) {
            if (tipoTraslado == candidato.getTipoTraslado()) {
                candidatosTraslado.add(candidato);
            }
        }
        return candidatosTraslado;
    }

    //--------------------------------------------------//


    public ArrayList<Candidato> filtrar(Presencialidad tipoPresencialidad) {

        ArrayList<Candidato> candidatosPresencialidad = new ArrayList<>();

        for (Candidato candidato : candidatosList) {
            if (tipoPresencialidad == candidato.getPresencialidad()) {
                candidatosPresencialidad.add(candidato);
            }
        }
        return candidatosPresencialidad;
    }

    //--------------------------------------------------//

    public Candidato buscarEmail(String email) {

        Candidato candidatoEmail = new Candidato();

        for (Candidato candidato : candidatosList) {
            if (Objects.equals(email, candidato.getEmail())) {
                candidatoEmail = candidato;
            }
        }
        return candidatoEmail;

    }

    //--------------------------------------------------//

    public Candidato buscarTelefono(String telefono) {

        Candidato candidatoTelefono = new Candidato();

        for (Candidato candidato : candidatosList) {
            if (Objects.equals(telefono, candidato.getTelefono())) {
                candidatoTelefono = candidato;
            }
        }
        return candidatoTelefono;
    }

    //--------------------------------------------------//

    public ArrayList<Candidato> filtrar(String ciudad, Presencialidad tipoPresencialidad, boolean tipoTraslado) {

        ArrayList<Candidato> candidatosCPT = new ArrayList<>();

        for (Candidato candidato : candidatosList) {
            if (
                    Objects.equals(ciudad, candidato.getCiudad())
                            && tipoPresencialidad == candidato.getPresencialidad()
                            && tipoTraslado == candidato.getTipoTraslado()) {

                candidatosCPT.add(candidato);
            }
        }
        return candidatosCPT;
    }

    //--------------------------------------------------//

    public void eliminar(int candidato) {
        candidatosList.remove(candidato);
    }


    public void imprimeCiudadesTop() {

        Map<String, Integer> cRepetidas = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (Candidato i : candidatosList) {
            cRepetidas.merge(i.getCiudad(), 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> pOrdenados = new ArrayList<>(cRepetidas.entrySet());
        pOrdenados.sort(Map.Entry.comparingByValue());
        Collections.reverse(pOrdenados);
        sb.append("Candidatos ordenados por cantidad en ciudades\nen forma descendiente\n");
        for (Map.Entry<String, Integer> pOrdenado : pOrdenados) {
            sb.append("CIUDAD: ").append(pOrdenado.getKey().toUpperCase(Locale.ROOT)).append("- CANTIDAD: ").append(pOrdenado.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb);


    }

    public void imprimePaisesTop() {

        Map<String, Integer> pRepetidas = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (Candidato i : candidatosList) {
            pRepetidas.merge(i.getPais(), 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> pOrdenados = new ArrayList<>(pRepetidas.entrySet());
        pOrdenados.sort(Map.Entry.comparingByValue());
        Collections.reverse(pOrdenados);
        sb.append("Candidatos ordenados por cantidad en paises\nen forma descendiente\n");
        for (Map.Entry<String, Integer> pOrdenado : pOrdenados) {
            sb.append("PAIS: ").append(pOrdenado.getKey().toUpperCase(Locale.ROOT)).append("- CANTIDAD: ").append(pOrdenado.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb);

    }

    public int totalRemotos() {
        contador = 0;
        for (Candidato candidato : candidatosList) {
            if (candidato.getPresencialidad() == Presencialidad.REMOTO) {
                contador++;
            }
        }
        return contador;
    }

    public int totalPosibilidadTraslado() {
        contador = 0;
        for (Candidato candidato : candidatosList) {
            if (candidato.getTipoTraslado()) {
                contador++;
            }
        }
        return contador;
    }

    public int totalPresencialSinTraslado() {
        contador = 0;
        for (Candidato candidato : candidatosList) {
            if (!candidato.getTipoTraslado() && candidato.getPresencialidad() == Presencialidad.PRESENCIAL) {
                contador++;
            }
        }
        return contador;
    }

    public void generarPDF(Candidato candidato) {

        try {

            JOptionPane.showMessageDialog(null, "Generando CV de " + candidato.getEmail());

            String fileName = "CV " + candidato.getEmail() + ".pdf";

            PDDocument doc = new PDDocument();

            PDPage page = new PDPage();

            doc.addPage(page);

            PDImageXObject imagen = PDImageXObject.createFromFile("src/main/java/FC/imagen.jpg", doc);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.drawImage(imagen, 70, 100);


            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 26);
            content.newLineAtOffset(180, 750);
            content.showText("CV " + candidato.getEmail());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.newLineAtOffset(80, 725);
            content.showText("Ciudad: " + candidato.getCiudad());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.newLineAtOffset(80, 700);
            content.showText("Pais: " + candidato.getPais());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.newLineAtOffset(80, 675);
            if (candidato.getTipoTraslado()) content.showText("Traslado: SI");
            else content.showText("Traslado: NO");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.newLineAtOffset(80, 650);
            content.showText("Telefono: " + candidato.getTelefono());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.newLineAtOffset(80, 625);
            content.showText("Presencialidad: " + candidato.getPresencialidad());
            content.endText();

            content.close();
            doc.save(fileName);
            doc.close();

            JOptionPane.showMessageDialog(null, "CV creado correctamente : " + System.getProperty("user.dir"));

            int verPdf = JOptionPane.showConfirmDialog(null, "¿Desea ver el CV de " + candidato.getEmail() + " ?");
            boolean ver = JOptionPane.OK_OPTION == verPdf;

            if (ver) {
                try {
                    File path = new File(fileName);
                    Desktop.getDesktop().open(path);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
