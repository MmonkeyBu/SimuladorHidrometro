package simuladorhidrometro;

public class Hidrometro {
    
    private double consumo;
    private double vazao;

    public Hidrometro(){
        this.consumo = 0.0;
        this.vazao = 0.0;
    }

    public void setVazao(double vazao){
        if(vazao >= 0){
            this.vazao = vazao;
        } else {
            System.out.println("Vazão inválida. Deve ser um valor não negativo.");
        }
    }

    public double getVazao(){
        return this.vazao;
    }

    public double getConsumo(){
        return this.consumo;
    }

    public void atualizarConsumo(){
        this.consumo += this.vazao;
    }

    public void resetar(){
        this.consumo = 0.0;
        this.vazao = 0.0;
    }
}
