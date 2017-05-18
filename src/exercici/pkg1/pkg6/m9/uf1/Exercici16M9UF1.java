package exercici.pkg1.pkg6.m9.uf1;

import java.io.File;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class Exercici16M9UF1 {
        
    public static void main(String[] args) throws Exception {

        String certOrigen = "C:\\Users\\ALUMNEDAM\\Documents\\Exercici-1.6-M9-UF1\\src\\SSL\\origen.jks";
        Origen origen = new Origen(certOrigen, "1423586709");
        String certDesti = "C:\\Users\\ALUMNEDAM\\Documents\\Exercici-1.6-M9-UF1\\src\\SSL\\desti.jks";
        Desti desti = new Desti(certDesti, "123456");

        //S'encripta la informació.
        byte[] infoXifrada = origen.xifrar("1423586709");

        // Es signa la informació.
        byte[] signatura = origen.signData(infoXifrada);

        // Es verifica la informació xifrada i es mostra o es mostra un missatge d'error.
        if (desti.verificar(infoXifrada, signatura)) {

            byte[] informacioDesxifrada = desti.desxifraDadesReceptor(infoXifrada);

            System.out.println(new String(informacioDesxifrada));
        } else {
            System.out.println("Document no validat.");
        }
    }
}
