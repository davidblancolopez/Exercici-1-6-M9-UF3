package exercici.pkg1.pkg6.m9.uf1;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Origen {

    String texto;
    private byte [] missatgeEncriptat;
    
    private KeyStore magatzem;

    public Origen() {
    }
    
    /**
     * Metode que retorna el magatzem de claus.
     * Li arriba un string on s'indica la ruta del arxiu .jks que es el magatzem,
     * la contrasenya del magatzem de claus.
     *
     * @param ksFile
     * @param ksPwd
     * @return
     * @throws Exception 
     */
    public void loadKeyStore(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("JCEKS"); // JCEKS ó JKS
        File f = new File(ksFile);
        if (f.isFile()) {
            FileInputStream in = new FileInputStream(f);
            ks.load(in, ksPwd.toCharArray());
        }
        magatzem = ks;
    }
    
    /**
     * Metode que xifra les dades del String que li arriba per parametre.
     * Li arriba un missatge en String i la clau publica que es fara servir 
     * per a xifrar la informació.
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
    public void xifraDadesEmissor(String missatge_text, PublicKey pub) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException{
        byte [] data = missatge_text.getBytes("UTF-8");
        
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
        cifrador.init(Cipher.ENCRYPT_MODE, pub);
        
    }
    
    
    public void obtindreClauPrivada(String alias, String contrasenya){
        
    }
    
    public void obtindreCertificatPub(){
        
    }



}
