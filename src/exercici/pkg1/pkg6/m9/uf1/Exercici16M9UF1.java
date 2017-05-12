package exercici.pkg1.pkg6.m9.uf1;

import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class Exercici16M9UF1 {
    public String text = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa";
    
    public static void main(String[] args) throws Exception {
        Origen o = new Origen();
        
        
        //Fem que origen obtingui el magatzem de claus.
        o.CarregarMagatzemClaus("C:\\Users\\ALUMNEDAM\\Documents\\Exercici-1.6-M9-UF1\\src\\SSL\\origen.jks", "1423586709");
        
        //Recuperem el magatzem de claus.
        KeyStore almacen = o.getMagatzem();
        
        Enumeration<String> aliases = almacen.aliases();
        while (aliases.hasMoreElements()) {
            System.out.println(aliases.nextElement());
        }
        
//        KeyStore almacen = o.loadKeyStore("C:\\Users\\ALUMNEDAM\\Documents\\Exercici-1.6-M9-UF1\\src\\SSL\\origen.jks", "1423586709");
//        Enumeration<String> aliases = almacen.aliases();
//        while (aliases.hasMoreElements()) {
//            System.out.println(aliases.nextElement());
//        }
//        //Obtenim la clau privada.
//        PrivateKey key1 = (PrivateKey) almacen.getKey("origen", "1423586709".toCharArray());
//        //Obtenim un certificat.
//        X509Certificate certificate = (X509Certificate) almacen.getCertificate("desticert");
//        //Obtenim la clau publica.
//        PublicKey clavePublica = certificate.getPublicKey();
//        System.out.println(clavePublica);
    }

    
    /**
     * Firmar con clave publica destino y firmar con origen
     */
}
