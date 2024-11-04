/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dados;

/**
 *
 * @author renat
 */
public class Quartos {
    private int id_quartos;
    private String numero;
    private String andar;
    private String descricao;
    private String caracteristicas;
    private Double valor_diaria;
    private String estado;
    private String tipo_quarto;

    public Quartos(int id_quartos, String numero, String andar, String descricao, String caracteristicas, Double valor_diaria, String estado, String tipo_quarto) {
        this.id_quartos = id_quartos;
        this.numero = numero;
        this.andar = andar;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.valor_diaria = valor_diaria;
        this.estado = estado;
        this.tipo_quarto = tipo_quarto;
    }

    public Quartos() {
    }

    public int getId_quartos() {
        return id_quartos;
    }

    public void setId_quartos(int id_quartos) {
        this.id_quartos = id_quartos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Double getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(Double valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_quarto() {
        return tipo_quarto;
    }

    public void setTipo_quarto(String tipo_quarto) {
        this.tipo_quarto = tipo_quarto;
    }
    
    
}
