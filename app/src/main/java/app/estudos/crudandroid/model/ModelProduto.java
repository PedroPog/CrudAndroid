package app.estudos.crudandroid.model;

public class ModelProduto {
    private int idproduto;
    private String descricao;
    private double preco;
    private double quantestoque;
    private String codigoean;
    private boolean precovariavel;
    private String sigla;
    private String imagem;
    private String cor;
    private boolean status;

    public boolean getPrecovariavel() {
        return precovariavel;
    }

    public void setPrecovariavel(boolean precovariavel) {
        this.precovariavel = precovariavel;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getQuantestoque() {
        return quantestoque;
    }

    public void setQuantestoque(double quantestoque) {
        this.quantestoque = quantestoque;
    }

    public String getCodigoean() {
        return codigoean;
    }

    public void setCodigoean(String codigoean) {
        this.codigoean = codigoean;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
