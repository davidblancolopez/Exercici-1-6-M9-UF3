package exercici.pkg1.pkg6.m9.uf1;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Origen {

    String texto;
    private byte[] missatgeEncriptat;

    private KeyStore magatzem;
    private  X509Certificate certificate;
    PrivateKey clauPrivada;
    

    public Origen() {
    }

    public KeyStore getMagatzem() {
        return magatzem;
    }

    public void setMagatzem(KeyStore magatzem) {
        this.magatzem = magatzem;
    }

    
    /**
     * Metode que retorna el magatzem de claus. Li arriba un string on s'indica
     * la ruta del arxiu .jks que es el magatzem, la contrasenya del magatzem de
     * claus.
     *
     * @param ksFile
     * @param ksPwd
     * @return
     * @throws Exception
     */
    public void CarregarMagatzemClaus(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("JCEKS"); // JCEKS ó JKS
        File f = new File(ksFile);
        if (f.isFile()) {
            FileInputStream in = new FileInputStream(f);
            ks.load(in, ksPwd.toCharArray());
        }
        magatzem = ks;
    }

    /**
     * Metode que xifra les dades del String que li arriba per parametre. Li
     * arriba un missatge en String i la clau publica que es fara servir per a
     * xifrar la informació.
     *
     * @param missatge_text
     * @param pub
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     */
    public void xifraDadesEmissor(String missatge_text, PublicKey pub) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException {
        byte[] data = missatge_text.getBytes("UTF-8");

        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
        cifrador.init(Cipher.ENCRYPT_MODE, pub);
        missatgeEncriptat = cifrador.doFinal(data);

    }
    /**
     * Metode per a firmar.
     * @param data
     * @param clauPrivada
     * @return 
     */
    public byte[] signData(byte[] data, PrivateKey clauPrivada) {
        byte[] signature = null;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(clauPrivada); //Inicialitzem la firma digital a partir
            signer.update(data); //Li assignem a l’objecte firma les dades a
            signature = signer.sign();
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

    /**
     * Metode per a obtenir la clau privada.
     * Li arriba el alias i la contrasenya.
     * 
     * @param alias
     * @param contrasenya 
     */
    public void obtindreClauPrivada(String alias, String contrasenya) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        clauPrivada = (PrivateKey) magatzem.getKey("origen", "1423586709".toCharArray());
    }

    /**
     * Metode que serveix per a introduir en la variable certificate el certificat
     * que es troba en el magatzem de claus.
     * Es pasa el alias i amb el metode getCertificate obtenim el certificat.
     * 
     * @param alias
     * @throws KeyStoreException 
     */
    public void obtindreCertificatPub(String alias) throws KeyStoreException {
       certificate = (X509Certificate) magatzem.getCertificate(alias);
    }

}
