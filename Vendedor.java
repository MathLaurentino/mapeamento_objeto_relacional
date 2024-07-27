import java.time.LocalDate;

public class Vendedor {
    
    private int codigo;

    private String nome;
    private String sobrenome;
    private Float salario;
    private Integer quantVendas;
    
    

    public Vendedor(int codigo, String nome, String sobrenome, Float salario,
            Integer quantVendas) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.salario = salario;
        this.quantVendas = quantVendas;
    }


    public Vendedor() {

    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public Float getSalario() {
        return salario;
    }
    public void setSalario(Float salario) {
        this.salario = salario;
    }
    public Integer getQuantVendas() {
        return quantVendas;
    }
    public void setQuantVendas(Integer quantVendas) {
        this.quantVendas = quantVendas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vendedor other = (Vendedor) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "codigo=" + codigo + ", nome=" + nome + ", sobrenome=" + sobrenome + ", salario=" + salario
                + ", quantVendas=" + quantVendas;
    }

    
}