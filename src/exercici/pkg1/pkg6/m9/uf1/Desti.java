package exercici.pkg1.pkg6.m9.uf1;

import static exercici.pkg1.pkg6.m9.uf1.Exercici16M9UF1.fitxerOr;
import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;


public class Desti {

    public void descifrar() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException, Exception{
         
            Origen o = new Origen();
           
            o.Xifrar(fitxerOr, "123456");
            KeyStore ks = o.getMagatzem();
            
            Enumeration<String> aliases = ks.aliases();
            
            while (aliases.hasMoreElements()) {
                System.out.println(aliases.nextElement());
            }
            
            PrivateKey pkey = (PrivateKey) ks.getKey("origen", "1423586709".toCharArray());
            
            X509Certificate c = (X509Certificate) ks.getCertificate("desticert");
            
            System.out.println(c.getPublicKey());
            c.verify(c.getPublicKey());
    }
    
}
