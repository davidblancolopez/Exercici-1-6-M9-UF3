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

    private byte[] missatgeXifrat;
    private Key clauPrivada;

    /**
     * 
     * @param ksFile
     * @param ksPwd
     * @return
     * @throws Exception 
     */
    public KeyStore loadKeyStore(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("JCEKS"); // JCEKS ó JKS
        File f = new File(ksFile);
        if (f.isFile()) {
            FileInputStream in = new FileInputStream(f);
            ks.load(in, ksPwd.toCharArray());
        }
        return ks;
    }

    /**
     * 
     * @param alias
     * @param password
     * @return
     * @throws Exception 
     */
    public Key obtindreClauPrivada(String alias, String password) throws Exception {
        clauPrivada = loadKeyStore(alias, password).getKey(alias, password.toCharArray());
        return clauPrivada;
    }

}
