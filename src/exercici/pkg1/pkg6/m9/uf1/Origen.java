package exercici.pkg1.pkg6.m9.uf1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Origen {

    String texto;

    private KeyStore magatzem;
    private X509Certificate certificate;
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
        FileInputStream in = new FileInputStream(f);
        if (f.isFile()) {

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
     * @param password
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     */
    public void xifraDadesEmissor(String missatge_text, String password) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, IOException, CertificateException, KeyStoreException {
        byte[] data = missatge_text.getBytes("UTF-8");

        magatzem = KeyStore.getInstance("JCEKS");

        FileInputStream fis = new FileInputStream(ficheroOrigen);

        File f = new File(missatge_text);
        if (f.isFile()) {

            magatzem.load(fis, "123456".toCharArray());
        }

    }

    /**
     * Metode per a firmar.
     *
     * @param data
     * @param clauPrivada
     * @return
     */
    public byte[] signData(String fitxer, PrivateKey clauPrivada) {
        byte[] signature = null;

        byte[] buffer = new byte[1024];
        int mida;
        try {
            FileInputStream fis = new FileInputStream(fitxer);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(clauPrivada); //Inicialitzem la firma digital a partir

            while (bis.available() != 0) {
                mida = bis.read(buffer);
                signer.update(buffer, 0, mida);
            }
            bis.close();
            signature = signer.sign();
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

    KeyStore getKs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
