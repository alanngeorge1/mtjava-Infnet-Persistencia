package br.edu.infnet.pedido.model.entidade;

public class Usuario {

    private Long codigo;

    private String nome;

    public Usuario() { }


    public Usuario(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Usuario [codigo=" + codigo + ", nome="+ nome + "]";
    }

}
