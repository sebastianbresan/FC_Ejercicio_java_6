package FC;

import java.util.Locale;

public class Candidato {

    private String ciudad;
    private String pais;
    private Boolean tipoTraslado;
    private String email;
    private String telefono;
    private Presencialidad Presencialidad;


    public Candidato() {
    }

    public Candidato(String ciudad, String pais, Boolean tipoTraslado, String email, String telefono, FC.Presencialidad presencialidad) {
        this.ciudad = ciudad;
        this.pais = pais;
        this.tipoTraslado = tipoTraslado;
        this.email = email;
        this.telefono = telefono;
        Presencialidad = presencialidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public Boolean getTipoTraslado() {
        return tipoTraslado;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public FC.Presencialidad getPresencialidad() {
        return Presencialidad;
    }


    @Override
    public String toString() {

        String traslado;
        if (tipoTraslado) {
            traslado = "SI";
        } else traslado = "NO";

        return "Candidato =" +
                " Ciudad: '" + ciudad.toUpperCase(Locale.ROOT) + '\'' +
                ", Pais: " + pais.toUpperCase(Locale.ROOT) +
                ", Traslado: " + traslado +
                ", Email: '" + email.toUpperCase(Locale.ROOT) + '\'' +
                ", Teléfono: '" + telefono.toUpperCase(Locale.ROOT) + '\'' +
                ", Presencialidad: " + Presencialidad +
                '\n';
    }
}
