package testeabrirfilepassword;

import java.io.File;
import java.util.List;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class TesteAbrirFilePassword {

    //caminho absoluto da pasta
    public static final String caminho = "D:\\camiho\\para\\os\\arquivos\\";

    public static boolean testaSenha(String senha){
        
        //necessário trocar o nome do arquivo de maneira iterativa
        //sugiro add um parâmetro para tal...
        ZipFile zipFile = new ZipFile(new File(caminho + "doc1.zip"));
        try {
            
            //encriptado?
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(senha.toCharArray());
            }
            List fileHeaderList = zipFile.getFileHeaders();

            for (int i = 0; i < fileHeaderList.size(); i++) {
                FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
                //onde você deseja extrair (neste caso no mesmo caminho)
                zipFile.extractFile(fileHeader, caminho);
                System.out.println("encontramos a senha e o arquivo");
                return true;
            }

        } catch (net.lingala.zip4j.exception.ZipException ex) {
            //erro na extração do arquivo
            return false;
        }
        
        return false;
    }
    
    
    public static void main(String[] args) {
        
    }
}
