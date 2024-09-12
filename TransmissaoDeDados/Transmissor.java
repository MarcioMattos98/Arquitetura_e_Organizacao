package transferenciadadostp;

import java.util.Random;

public class Transmissor {

    private String mensagem;
    //1 0 1 0 1
    private final boolean polinomio[] = {true, false, true, false, true};

    public Transmissor(String mensagem) {
        this.mensagem = mensagem;
    }

    //convertendo um símbolo para "vetor" de boolean (bits)
    private boolean[] streamCaracter(char simbolo) {

        //cada símbolo da tabela ASCII é representado com 8 bits
        boolean bits[] = new boolean[8];

        //convertendo um char para int (encontramos o valor do mesmo na tabela ASCII)
        int valorSimbolo = (int) simbolo;
        int indice = 7;

        //convertendo cada "bits" do valor da tabela ASCII
        while (valorSimbolo >= 2) {
            int resto = valorSimbolo % 2;
            valorSimbolo /= 2;
            bits[indice] = (resto == 1);
            indice--;
        }
        bits[indice] = (valorSimbolo == 1);

        return bits;
    }

    //não modifique (seu objetivo é corrigir esse erro gerado no receptor)
    
    private void geradorRuido(boolean bits[]) {
        Random geradorAleatorio = new Random();

        //pode gerar um erro ou não..
        
        if (geradorAleatorio.nextInt(5) > 1) {
            int indice = geradorAleatorio.nextInt(8);
            bits[indice] = !bits[indice];
        }
    }

    private boolean[] dadoBitsCRC(boolean bits[]) {
        /*
        Este método calcula o CRC para os bits que forma fornecidos. Ele utiliza o polinomio 
        definido na variável polinomio[] (10101) para realizar o cálculo
        e adiciona os bits de virificação ao final do array de bits original.        
         */

        //Tamanho 12 - Bits para a realização do XOR
        
        System.out.println(vtoString(bits));
        boolean bitsParaCRC[] = new boolean[bits.length + polinomio.length - 1];
        System.arraycopy(bits, 0, bitsParaCRC, 0, bits.length);
        boolean bitsComCRC[] = new boolean[bitsParaCRC.length];
        System.arraycopy(bitsParaCRC, 0, bitsComCRC, 0, bitsParaCRC.length);
        
        //Teste com XOR no bitsParaCRC
        
        for (int i = 0; i < bitsParaCRC.length - polinomio.length + 1; i++) {
            if (bitsParaCRC[i]) {
                for (int j = i; j < i + polinomio.length; j++) {
                    bitsParaCRC[j] = !(bitsParaCRC[j] == polinomio[j - i]);
                }
            }
        }

        System.arraycopy(bitsParaCRC, 8, bitsComCRC, 8, polinomio.length - 1);
        
        //Completando o novo vetor booleano com o bits e 0
        
        System.out.println(vtoString(bitsParaCRC));
        System.out.println(vtoString(bitsComCRC));
        return bitsComCRC;
    }

    public void enviaDado(Receptor receptor) {
        for (int i = 0; i < this.mensagem.length(); i++) {
            boolean bits[] = streamCaracter(this.mensagem.charAt(i));

            /*-------AQUI você deve adicionar os bits do códico CRC para contornar os problemas de ruidos
                        você pode modificar o método anterior também
                
             */
            //add ruidos na mensagem a ser enviada para o receptor
            boolean bitsCRC[] = dadoBitsCRC(bits);
            geradorRuido(bitsCRC);

            //enviando a mensagem "pela rede" para o receptor (uma forma de testarmos esse método)
            
            boolean indicadorCRC = receptor.receberDadoBits(bitsCRC);
            if (indicadorCRC == false) {
                i--;
            }
            
            //o que faremos com o indicador quando houver algum erro? qual ação vamos tomar com o retorno do receptor
        }
    }

    /*
    Este método converte um array de bits em uma string de representação binária.
     */
    
    public String vtoString(boolean v[]) {
        String msg = "";
        for (boolean i : v) {
            msg += (i ? "1" : "0");
        }
        return msg;
    }
}
