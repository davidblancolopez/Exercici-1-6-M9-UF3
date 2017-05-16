package exercici.pkg1.pkg6.m9.uf1;

import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Desti {



    public void descifrar() throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException, KeyStoreException, UnrecoverableKeyException {
       
          
            Origen o = new Origen();
           
            o.ametodo(ficheroOrigen, "123456");
            KeyStore ko = o.getKs();
           
            Enumeration<String> aliases = ko.aliases();
            
            while (aliases.hasMoreElements()) {
                System.out.println(aliases.nextElement());
            }
            
            PrivateKey pkey = (PrivateKey) ko.getKey("origen", "123456".toCharArray());
            
            X509Certificate c = (X509Certificate) ko.getCertificate("desticert");
           
            System.out.println(c.getPublicKey());
            c.verify(c.getPublicKey());

        
    }


    
    
}
