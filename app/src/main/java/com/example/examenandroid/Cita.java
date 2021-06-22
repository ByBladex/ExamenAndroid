package com.example.examenandroid;

public class Cita {
    private Integer codigo,numVeces;
    private String cita, autor, valoracion;

    Cita(Integer codigo, String cita, String autor, Integer numVeces, String valoracion){
        this.codigo=codigo;
        this.cita=cita;
        this.autor=autor;
        this.numVeces=numVeces;
        this.valoracion=valoracion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumVeces() {
        return numVeces;
    }

    public void setNumVeces(Integer numVeces) {
        this.numVeces = numVeces;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return codigo + "\t" + numVeces + "\t" + cita + "\t" + autor + "\n" + valoracion;
    }
}
