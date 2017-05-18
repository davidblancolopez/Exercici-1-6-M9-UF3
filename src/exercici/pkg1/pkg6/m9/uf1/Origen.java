package exercici.pkg1.pkg6.m9.uf1;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Origen {

    private PrivateKey clauPrivada;
    private X509Certificate certificatDesti;
    private KeyStore magatzem;

    public Origen(String ksFile, String ksPwd) throws Exception {
        carregarMagatzemClaus(ksFile, ksPwd);
        carregarClauPrivada("origen", ksPwd);
        carregarCertificatDesti("desticert");
    
    }

    public Origen() {

    }

    /**
     * Metode per carregar la clau privada.
     * 
     * @param alias
     * @param ksPwd
     * @throws Exception 
     */
    private void carregarClauPrivada(String alias, String ksPwd) throws Exception {
        clauPrivada = (PrivateKey) magatzem.getKey(alias, ksPwd.toCharArray());
    }
    
    /**
     * Metode per a xifrar la informació.
     *
     * @param missatge Missatge a xifrar.
     * @return missatge xifrat en byte[].
     */
    public byte[] xifrar(String missatge) {
        byte[] missatgeEncriptat = null;
        try {
            Cipher xifrador = Cipher.getInstance("RSA", "SunJCE");
            xifrador.init(Cipher.ENCRYPT_MODE, certificatDesti.getPublicKey());
            missatgeEncriptat = xifrador.doFinal(missatge.getBytes());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return missatgeEncriptat;
    }
    
    
    /**
     * Metode per carregar el certificat del desti.
     * 
     * @param alias
     * @throws Exception 
     */
    private void carregarCertificatDesti(String alias) throws Exception {
        certificatDesti = (X509Certificate) magatzem.getCertificate(alias);
    }
    
    /**
     * Metode per carregar el magatzem de claus.
     * @param ksFile
     * @param ksPwd
     * @return
     * @throws Exception
     */
    public void carregarMagatzemClaus(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("JCEKS"); // JCEKS ó JKS
        File f = new File(ksFile);
        FileInputStream in = new FileInputStream(f);
        if (f.isFile()) {

            ks.load(in, ksPwd.toCharArray());
        }
        magatzem = ks;
    }

    /**
     * Metode per a firmar la informació.
     *
     * @param data
     * @param clauPrivada
     * @return
     */
    public byte[] signData(byte [] missatge) {
        byte[] signature = null;

        try {
            
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(clauPrivada); //Inicialitzem la firma digital a partir
            signer.update(missatge);
            
            signature = signer.sign();
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

    public KeyStore getMagatzem() {
        return magatzem;
    }

    public void setMagatzem(KeyStore magatzem) {
        this.magatzem = magatzem;
    }

}
