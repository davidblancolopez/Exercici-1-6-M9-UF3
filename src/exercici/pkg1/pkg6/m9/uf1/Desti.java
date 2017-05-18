package exercici.pkg1.pkg6.m9.uf1;


import java.io.File;
import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Desti {

    private KeyStore magatzem;
    private PrivateKey clauPrivada;
    private X509Certificate certificatOrigen;

    public Desti(String ksFile, String ksPwd) throws Exception {
        carregarMagatzemClaus(ksFile, ksPwd);
        obtindreClauPrivada("desti", ksPwd);
        loadCertificatOrigen("origencert");
    }

    /**
     * Metode per carregar el magatzem de claus.
     *
     * @param ksFile
     * @param ksPwd
     * @return
     * @throws Exception
     */
    public KeyStore carregarMagatzemClaus(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("JCEKS"); // JCEKS ó JKS
        File f = new File(ksFile);
        if (f.isFile()) {
            FileInputStream in = new FileInputStream(f);
            ks.load(in, ksPwd.toCharArray());
        }
        return ks;
    }

    /**
     * Metode per obtenir la clau privada.
     *
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    public Key obtindreClauPrivada(String alias, String password) throws Exception {
        clauPrivada = (PrivateKey) carregarMagatzemClaus(alias, password).getKey(alias, password.toCharArray());
        return clauPrivada;
    }

    /**
     * Metode per carregar el certificat d'origen.
     *
     * @param alias Alias.
     * @throws Exception
     */
    private void loadCertificatOrigen(String alias) throws Exception {
        certificatOrigen = (X509Certificate) magatzem.getCertificate(alias);
    }

    /**
     * Metode per verificar.
     *
     * @param informacio Informació en byte[].
     * @param signaturaEnBytes Signatura en byte[].
     * @return true si s'ha verificat correctament, false el contrari.
     */
    public boolean verificar(byte[] informacio, byte[] signaturaEnBytes) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        boolean esCorrecte = false;

        Signature signatura = Signature.getInstance("SHA256withRSA");
        signatura.initVerify(certificatOrigen.getPublicKey());
        signatura.update(informacio);
        esCorrecte = signatura.verify(signaturaEnBytes);

        return esCorrecte;
    }

    /**
     * Metode per desxifrar la informació.
     *
     * @param missatgeXifrat Informació a desxifrar.
     * @return Missatge desxifrat en byte[].
     */
    public byte[] desxifraDadesReceptor(byte[] missatgeXifrat) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        byte[] missatgeDesxifrat = null;

        Cipher descifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
        descifrador.init(Cipher.DECRYPT_MODE, clauPrivada);
        missatgeDesxifrat = descifrador.doFinal(missatgeXifrat);

        return missatgeDesxifrat;
    }

}
