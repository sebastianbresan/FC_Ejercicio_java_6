package FC;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        CandidatosList candidatosList = new CandidatosList();

        ArrayList<Candidato> candidatos = new ArrayList<>();

        Candidato candidato2 = new Candidato("Buenos Aires".toLowerCase(Locale.ROOT), "Argentina".toLowerCase(Locale.ROOT), false, "sebastian@sebastian.com", "34563456", Presencialidad.REMOTO);
        Candidato candidato3 = new Candidato("Barcelona".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "barcelona@sebastian.com", "34563457", Presencialidad.MIXTO);
        Candidato candidato4 = new Candidato("Barcelona".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), true, "arveja@sebastian.com", "34563458", Presencialidad.PRESENCIAL);
        Candidato candidato5 = new Candidato("Madrid".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "madrid@sebastian.com", "34563459", Presencialidad.REMOTO);
        Candidato candidato6 = new Candidato("Aragon".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), true, "aragon@sebastian.com", "34563460", Presencialidad.MIXTO);
        Candidato candidato7 = new Candidato("Barcelona".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "toledo@sebastian.com", "34563461", Presencialidad.PRESENCIAL);
        Candidato candidato8 = new Candidato("Buenos Aires".toLowerCase(Locale.ROOT), "Argentina".toLowerCase(Locale.ROOT), true, "zaragoza@sebastian.com", "34563462", Presencialidad.REMOTO);
        Candidato candidato9 = new Candidato("Barcelona".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "barcelona6@sebastian.com", "3445463457", Presencialidad.MIXTO);
        Candidato candidato10 = new Candidato("Barcelona".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "arveja5@sebastian.com", "345643458", Presencialidad.PRESENCIAL);
        Candidato candidato11 = new Candidato("Lisboa".toLowerCase(Locale.ROOT), "Portugal".toLowerCase(Locale.ROOT), true, "madrid4@sebastian.com", "345633459", Presencialidad.REMOTO);
        Candidato candidato12 = new Candidato("Aragon".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "aragon3@sebastian.com", "345633460", Presencialidad.MIXTO);
        Candidato candidato13 = new Candidato("Barcelona".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "toledo2@sebastian.com", "3124563461", Presencialidad.PRESENCIAL);
        Candidato candidato14 = new Candidato("Madrid".toLowerCase(Locale.ROOT), "España".toLowerCase(Locale.ROOT), false, "zaragoza1@sebastian.com", "3456233462", Presencialidad.REMOTO);

        candidatos.add(candidato2);
        candidatos.add(candidato3);
        candidatos.add(candidato4);
        candidatos.add(candidato5);
        candidatos.add(candidato6);
        candidatos.add(candidato7);
        candidatos.add(candidato8);
        candidatos.add(candidato9);
        candidatos.add(candidato10);
        candidatos.add(candidato11);
        candidatos.add(candidato12);
        candidatos.add(candidato13);
        candidatos.add(candidato14);

        candidatosList.setCandidatosList(candidatos);

        String ciudad;
        String pais;
        boolean tipoTraslado;
        boolean emailRepetido;
        boolean masAcciones;
        String email;
        String telefono;
        Presencialidad Presencialidad = FC.Presencialidad.PRESENCIAL;

        int posicion;
        StringBuilder sb = new StringBuilder();

        while (true) {
            masAcciones = true;
            int menu = JOptionPane.showOptionDialog(null, "Seleccione una opcion",
                    "FC Ejercicio Java 7", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                    new Object[]{"Generar PDF de candidato", "Mas acciones", "Salir"}, "Generar PDF de candidato");


            switch (menu) {
                case 0 -> {
                    posicion = 1;
                    sb.setLength(0);
                    for (Candidato candidato1 : candidatosList.getCandidatosList()
                    ) {
                        sb.append(posicion).append("- ").append(candidato1).append("\n");
                        posicion++;
                    }
                    try {
                        int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                                """
                                        ELIJA CANDIDATO A GENERAR CV
                                                                            
                                        """ + sb));
                        while (opcion > candidatosList.getCandidatosList().size()) {
                            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                                    """ 
                                             RESPUESTA INCORRECTA
                                             
                                             ELIJA CANDIDATO A GENERAR CV
                                             
                                            """ + sb));
                        }
                        candidatosList.generarPDF(candidatosList.getCandidatosList().get(opcion - 1));

                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(null, "Elija una opcion valida");
                    }
                    sb.setLength(0);
                }
                case 1 -> {
                    while (masAcciones) {
                        menu = JOptionPane.showOptionDialog(null, "Seleccione una opcion",
                                "FC Ejercicio Java 7", JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                                new Object[]{"Añadir Candidato", "Borrar Candidato", "Buscar Candidato", "Ver todos", "Mas filtros...", "Volver"}, "Añadir Candidato");

                        switch (menu) {

                            case 0 -> {
                                ciudad = JOptionPane.showInputDialog("Ingrese ciudad").toLowerCase(Locale.ROOT);
                                pais = JOptionPane.showInputDialog("Ingrese pais").toLowerCase(Locale.ROOT);
                                int respTraslado = JOptionPane.showConfirmDialog(null, "¿Traslado disponible?");
                                tipoTraslado = JOptionPane.OK_OPTION == respTraslado;
                                email = JOptionPane.showInputDialog("Ingrese email").toLowerCase(Locale.ROOT);
                                while (!email.contains("@") || !email.contains(".")) {
                                    email = JOptionPane.showInputDialog("Formato invalido, reintente").toLowerCase(Locale.ROOT);
                                }
                                emailRepetido = false;
                                for (Candidato candidatoEmail : candidatosList.getCandidatosList()
                                ) {
                                    if (email.equals(candidatoEmail.getEmail())) {
                                        JOptionPane.showMessageDialog(null, "El email ya se encuentra registrado");
                                        emailRepetido = true;
                                        break;
                                    }
                                }
                                if (emailRepetido) break;
                                telefono = JOptionPane.showInputDialog("Ingrese telefono").replace(" ", "");
                                int menuPresencialidad = JOptionPane.showOptionDialog(null, "Seleccione una opcion",
                                        "PRESENCIALIDAD", JOptionPane.YES_NO_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                                        new Object[]{FC.Presencialidad.PRESENCIAL, FC.Presencialidad.REMOTO, FC.Presencialidad.MIXTO}, FC.Presencialidad.PRESENCIAL);
                                if (menuPresencialidad == 0) Presencialidad = FC.Presencialidad.PRESENCIAL;
                                if (menuPresencialidad == 1) Presencialidad = FC.Presencialidad.REMOTO;
                                if (menuPresencialidad == 2) Presencialidad = FC.Presencialidad.MIXTO;

                                Candidato nCandidato = new Candidato(ciudad, pais, tipoTraslado, email, telefono, Presencialidad);
                                candidatos.add(nCandidato);
                                candidatosList.setCandidatosList(candidatos);
                                JOptionPane.showMessageDialog(null, "Candidato añadido correctamente");
                            }

                            case 1 -> {
                                posicion = 1;
                                sb.setLength(0);
                                for (Candidato candidato1 : candidatosList.getCandidatosList()
                                ) {
                                    sb.append(posicion).append("- ").append(candidato1).append("\n");
                                    posicion++;
                                }
                                try {
                                    int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                                            """
                                                    ELIJA CANDIDATO A ELIMINAR
                                                                                        
                                                    """ + sb));
                                    while (opcion > candidatosList.getCandidatosList().size()) {
                                        opcion = Integer.parseInt(JOptionPane.showInputDialog(
                                                """ 
                                                         RESPUESTA INCORRECTA
                                                         ELIJA CANDIDATO A ELIMINAR
                                                         
                                                        """ + sb));
                                    }
                                    JOptionPane.showMessageDialog(null, "Se eliminara al candidatosList con email" + candidatosList.getCandidatosList().get(opcion - 1).getEmail());
                                    candidatosList.eliminar(opcion - 1);


                                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                                    JOptionPane.showMessageDialog(null, "Elija una opcion valida");
                                }
                                sb.setLength(0);
                            }

                            case 2 -> {
                                sb.setLength(0);
                                int buscar = JOptionPane.showOptionDialog(null, "Seleccione una búsqueda",
                                        "FC Ejercicio Java 7", JOptionPane.YES_NO_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                                        new Object[]{"Por ciudad", "Por presencialidad", "Por traslado", "Por email", "Por teléfono", "Ciudad, Presencialidad, Traslado"}, "Añadir Candidato");

                                switch (buscar) {

                                    case 0 -> {
                                        sb.setLength(0);
                                        posicion = 1;
                                        ciudad = JOptionPane.showInputDialog("Ingrese ciudad").toLowerCase(Locale.ROOT);
                                        if (candidatosList.filtrar(ciudad).size() < 1) {
                                            JOptionPane.showMessageDialog(null, "No se han encotrados candidatos con el filtro '" + ciudad + "'");
                                        } else {
                                            for (int i = 0; i < candidatosList.filtrar(ciudad).size(); i++) {
                                                sb.append(posicion).append("- ").append(candidatosList.filtrar(ciudad).get(i)).append("\n");
                                                posicion++;
                                            }
                                        }
                                        JOptionPane.showMessageDialog(null, sb);
                                    }
                                    case 1 -> {
                                        sb.setLength(0);
                                        posicion = 1;
                                        int menuPresencialidad = JOptionPane.showOptionDialog(null, "Seleccione una opcion",
                                                "PRESENCIALIDAD", JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                                                new Object[]{FC.Presencialidad.PRESENCIAL, FC.Presencialidad.REMOTO, FC.Presencialidad.MIXTO}, FC.Presencialidad.PRESENCIAL);
                                        if (menuPresencialidad == 0) Presencialidad = FC.Presencialidad.PRESENCIAL;
                                        if (menuPresencialidad == 1) Presencialidad = FC.Presencialidad.REMOTO;
                                        if (menuPresencialidad == 2) Presencialidad = FC.Presencialidad.MIXTO;
                                        if (candidatosList.filtrar(Presencialidad).size() < 1) {
                                            JOptionPane.showMessageDialog(null, "No se han encotrados candidatos con el filtro '" + Presencialidad + "'");
                                        } else {
                                            for (int i = 0; i < candidatosList.filtrar(Presencialidad).size(); i++) {
                                                sb.append(posicion).append("- ").append(candidatosList.filtrar(Presencialidad).get(i)).append("\n");
                                                posicion++;
                                            }
                                        }
                                        JOptionPane.showMessageDialog(null, sb);
                                    }

                                    case 2 -> {
                                        sb.setLength(0);
                                        posicion = 1;
                                        int respTraslado = JOptionPane.showConfirmDialog(null, "¿Traslado disponible?");
                                        tipoTraslado = JOptionPane.OK_OPTION == respTraslado;
                                        if (candidatosList.filtrar(tipoTraslado).size() < 1) {
                                            JOptionPane.showMessageDialog(null, "No se han encotrados candidatos con traslados '" + tipoTraslado + "'");
                                        } else {
                                            for (int i = 0; i < candidatosList.filtrar(tipoTraslado).size(); i++) {
                                                sb.append(posicion).append("- ").append(candidatosList.filtrar(tipoTraslado).get(i)).append("\n");
                                                posicion++;
                                            }
                                        }
                                        JOptionPane.showMessageDialog(null, sb);
                                    }

                                    case 3 -> {

                                        email = JOptionPane.showInputDialog("Ingrese email").toLowerCase(Locale.ROOT);
                                        if (candidatosList.buscarEmail(email).getEmail().isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "No se encontraron candidatos con el email " + email);
                                        } else {
                                            JOptionPane.showMessageDialog(null, candidatosList.buscarEmail(email));
                                        }
                                    }
                                    case 4 -> {

                                        telefono = JOptionPane.showInputDialog("Ingrese teléfono").replace(" ", "");
                                        if (candidatosList.buscarTelefono(telefono).getTelefono().isEmpty()) {
                                            JOptionPane.showMessageDialog(null, "No se encontraron candidatos con el telefono " + telefono);
                                        } else {
                                            JOptionPane.showMessageDialog(null, candidatosList.buscarTelefono(telefono));
                                        }
                                    }
                                    case 5 -> {
                                        sb.setLength(0);
                                        posicion = 1;
                                        ciudad = JOptionPane.showInputDialog("Ingrese ciudad").toLowerCase(Locale.ROOT);
                                        int menuPresencialidad = JOptionPane.showOptionDialog(null, "Seleccione una opcion",
                                                "PRESENCIALIDAD", JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.QUESTION_MESSAGE, null,
                                                new Object[]{FC.Presencialidad.PRESENCIAL, FC.Presencialidad.REMOTO, FC.Presencialidad.MIXTO}, FC.Presencialidad.PRESENCIAL);
                                        if (menuPresencialidad == 0) Presencialidad = FC.Presencialidad.PRESENCIAL;
                                        if (menuPresencialidad == 1) Presencialidad = FC.Presencialidad.REMOTO;
                                        if (menuPresencialidad == 2) Presencialidad = FC.Presencialidad.MIXTO;
                                        int respTraslado = JOptionPane.showConfirmDialog(null, "¿Traslado disponible?");
                                        tipoTraslado = JOptionPane.OK_OPTION == respTraslado;

                                        if (candidatosList.filtrar(ciudad, Presencialidad, tipoTraslado).size() < 1) {
                                            JOptionPane.showMessageDialog(null, "No se han encontrado candidatos con " +
                                                    " los filtos\n" +
                                                    "Ciudad: '" + ciudad + "'\nPresencialidad: '" + Presencialidad + "'\nTraslado: '" + tipoTraslado + "'"
                                            );
                                        } else {
                                            for (int i = 0; i < candidatosList.filtrar(ciudad, Presencialidad, tipoTraslado).size(); i++) {
                                                sb.append(posicion).append("- ").append(candidatosList.filtrar(ciudad, Presencialidad, tipoTraslado).get(i)).append("\n");
                                                posicion++;
                                            }
                                        }
                                        JOptionPane.showMessageDialog(null, sb);
                                    }
                                }
                            }
                            case 3 -> {
                                posicion = 1;
                                sb.setLength(0);
                                for (Candidato candidato1 : candidatosList.getCandidatosList()
                                ) {
                                    sb.append(posicion).append("- ").append(candidato1).append("\n");
                                    posicion++;
                                }
                                JOptionPane.showMessageDialog(null, sb);
                            }

                            case 4 -> {
                                {
                                    menu = JOptionPane.showOptionDialog(null, "Seleccione una opcion",
                                            "FC Ejercicio Java 7", JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null,// null para icono por defecto.
                                            new Object[]{"Ver ciudades TOP", "Ver paises TOP", "Total candidatos remoto", "Total candidatos traslado", "Total presencial sin traslado"}, "Ver ciudades TOP");

                                    switch (menu) {

                                        case 0 -> candidatosList.imprimeCiudadesTop();
                                        case 1 -> candidatosList.imprimePaisesTop();
                                        case 2 -> JOptionPane.showMessageDialog(null, candidatosList.totalRemotos() + " candidatos dispuestos a trabajar en REMOTO");
                                        case 3 -> JOptionPane.showMessageDialog(null, candidatosList.totalPosibilidadTraslado() + " candidatos dispuestos a trasladarse");
                                        case 4 -> JOptionPane.showMessageDialog(null, candidatosList.totalPresencialSinTraslado() + " candidatos dispuestos a trabajar de forma presencial\ny no están dispuestos a trasladarse");
                                    }
                                }
                            }
                            case 5 -> masAcciones = false;
                        }
                    }
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Closing ....");
                    System.exit(0);
                }
            }
        }
    }
}
