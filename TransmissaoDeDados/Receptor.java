package transferenciadadostp;

public class Receptor {

    //mensagem recebida pelo transmissor
    private String mensagem;
    //1 0 1 0 1
    private final boolean polinomio[] = {true, false, true, false, true};

    public Receptor() {
        //mensagem vazia no inicio da execução
        this.mensagem = "";
    }

    public String getMensagem() {
        return mensagem;
    }

    private boolean decodificarDado(boolean bits[]) {
        int codigoAscii = 0;
        int expoente = bits.length - 1;

        //converntendo os "bits" para valor inteiro para então encontrar o valor tabela ASCII
        
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                codigoAscii += Math.pow(2, expoente);
            }
            expoente--;
        }

        //concatenando cada simbolo na mensagem original
        
        this.mensagem += (char) codigoAscii;

        //esse retorno precisa ser pensado... será que o dado sempre chega sem ruído???
        
        return true;
    }

    private boolean decodificarDadoCRC(boolean bits[]) {
        boolean dadoFinal[] = new boolean[bits.length - polinomio.length + 1];
        System.out.println("Receptor");
        System.out.println(vtoString(bits));

        System.arraycopy(bits, 0, dadoFinal, 0, 8); // Copia os bits recebidos para um array temporario
        System.out.println();
        
        // Cálculo do CRC usando o polinomio definido e faz a verificação de erros
        
        boolean bitsXor[] = bits.clone();
        //XOR
        for (int i = 0; i < bitsXor.length - polinomio.length + 1; i++) {
            if (bitsXor[i]) {
                for (int j = i; j < i + polinomio.length; j++) {
                    bitsXor[j] = !(bitsXor[j] == polinomio[j - i]);
                }
            }

        }
        System.out.println(vtoString(bitsXor));

        for (int i = bitsXor.length - polinomio.length + 1; i < bitsXor.length; i++) {
            if (bitsXor[i]) {
                return false; // retorna falso se houber algum erro detectado
            }
        }

        System.out.println(vtoString(dadoFinal));
        
        // Se não houver erros, decodifica os bits para um caractere da tabela ASCII
        
        return decodificarDado(dadoFinal);
        
        //implemente a decodificação **CRC** aqui e encontre os 
        //erros e faça as devidas correções para ter a imagem correta
    }

    //recebe os dados do transmissor
    
    public boolean receberDadoBits(boolean bits[]) {

        //aqui você deve trocar o médodo decofificarDado para decoficarDadoCRC (implemente!!)
        //será que sempre teremos sucesso nessa recepção
        
        return decodificarDadoCRC(bits);
    }

    public String vtoString(boolean v[]) {
        String msg = "";
        for (boolean i : v) {
            msg += (i ? "1" : "0");
        }
        return msg;
    }
}
