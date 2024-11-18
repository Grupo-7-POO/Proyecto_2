package modelo.preguntas;

public class Opcion {
    private String texto;
    private boolean esCorrecta;

    public Opcion(String texto, boolean esCorrecta) {
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }

    public String getTexto() {
        return this.texto;
    }

    public boolean getEsCorrecta() {
        return this.esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}