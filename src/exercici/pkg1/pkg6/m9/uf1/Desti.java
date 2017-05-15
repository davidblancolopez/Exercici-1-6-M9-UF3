package exercici.pkg1.pkg6.m9.uf1;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Desti {

    private byte[] buffer;

    /**
     * Metode per a desxifrar la clau privada.
     * 
     * @param clavePrivadaCifrada
     * @param k
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    static byte[] desxifrarClauPrivada(byte[] clavePrivadaCifrada, SecretKey k) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher ci = Cipher.getInstance("PBEWithMD5AndDES");
        ci.init(Cipher.DECRYPT_MODE, k);
        return ci.doFinal(clavePrivadaCifrada);
    }

    /**
     * Metode per a desxifrar les dades.
     * @param data
     * @throws NoSuchProviderException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    public void desxifraDadesReceptor(byte[] data, PrivateKey clauPrivada) throws NoSuchProviderException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        Cipher descifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding", "SunJCE");
        descifrador.init(Cipher.DECRYPT_MODE, clauPrivada);
        buffer = descifrador.doFinal(data);
    }
    
    
}
