package com.example.user.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtils {
    /** PrivateKey * 生成秘钥 > openssl genrsa -out rsa_private_key.pem 2048
     * 转换成PKCS8格式 >openssl pkcs8 -topk8 -inform * PEM -in rsa_private_key.pem -outform PEM -nocrypt
     * 在终端输出结果，去掉“-----BEGIN PRIVATE KEY-----” * “-----END PRIVATE KEY-----”
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        try {
            String privateKeyStr ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDRpQ/8lBrRyQKa\n" +
                    "1+CKXfdFUKK2B/JUSF0z6thd2nCWw6pjU9ldkiNlO9tqv8ZM5Mh+URPs9fNJBdxs\n" +
                    "Jxjas69RfBt8QsKPkkRSHLHzOY4NdxZIhSvyU2gs223ZOh/PQAF4VYD1PcbOITAX\n" +
                    "3ho+BQTtO0PZjZ89jkOiGe0JZsT3NqSmWKjh0Qg71/JAbIN5Wr4aDx1i/7rtAEDu\n" +
                    "fl2dY4DuFLAx1kzmaNWJu1ECnw4eT3PoBwLdDQYuoVXRfqAp/crS8DIw73mnlrcQ\n" +
                    "XCtQkZ/f/GIIXdxmCe++bz44CVF69z4ILYBEaPaWdil9QhhDIWvuM0tfGhMMqnKt\n" +
                    "j+UpHJ9BAgMBAAECggEAGSjh8BzxfQoj0w55Eadbn3N2cOR864AwbfnPaVq9AV2j\n" +
                    "y2jgNiUYNLQyzdm3GY+paijJJzqQgyEoxh1eaRIrG7CKdzwR2nt3fJKaCUfNKbDd\n" +
                    "jpWA49j+Wx0jI/CgVFXo90FCXpbr5DLCcVLZO7vheVEQAFdz7BtwjXnmuHv0w9A7\n" +
                    "bQZnYCnsbWCwcYvL9vKqcCX3UqhKVXw/N7FgPoECiiCM3MFjCCWjlgcbTZnCHUAy\n" +
                    "L/QkPfCrcK6uyxXSA6bby2SM2VRSOc/4tnsaQGxMLeZx/GTL912PVqNHLlsX+hJq\n" +
                    "iwHW0N9scYkEqNu6CJQyrM0/i2WurfQ7BDu8vzsFAwKBgQD4t4MsTmPN4kvnIC4M\n" +
                    "+3X74/51g/A4UOeotaaJd2nIZQ9fgilImqPfZG99BeNqLiSvsJzNIvTvjff/49Yp\n" +
                    "2AuSvWWCQpyB9lKR0HheuOGgqXHmCG1QXHO/haNQbcT770+H9kAkVLdph6rd1BbU\n" +
                    "ZbCRJCGvkZOWj+ByyIqRUg27KwKBgQDXyKYuNSJx0ay005sg2TcZcose5yPJyjnC\n" +
                    "rDrsxnbY1dJWyjz8bzQsI2sbepdFbXlzAiCo9QPsSsiRGpJG74ooWBpsu5/HyDIX\n" +
                    "UJ1utI/YsNf27LoN7Zl2tdE8GP41USnTakHLwANQR7N9o0PgFpavIX8sEHLcxypd\n" +
                    "tMq1SUBpQwKBgQCTRl9XIgf5RvH5J1X9e/1uLaBkx768KjQy83Vs2xf5gNYskO+P\n" +
                    "PXqh8mAOISl7m10/543B7two6eEbVjdfyb14o8vy6SyxzEEydMaCGGoKb6uxMfZA\n" +
                    "6q3jj+eX3ZgSMq/ea5Opr7x3sc0c09S86oY2O7Xyt3o0YnfeoB9lU7grxwKBgDFg\n" +
                    "BEaGef85Tx+BW/nEOktkXlkP4qyFzXmyFzmeoAs1wg+B7zqD55hB8nwzLPIB/sxG\n" +
                    "LFr81Yz3/7TUM6xvm1ln74P52rHIKU4UcDGot1GKBaDGwjv1piv3pozt2x0wGTX7\n" +
                    "Flsc8KUqAvQ4q4tXH9uq+oKGsQG7wQH4IHoi7M4pAoGAR5e6xpuOG/xQJ1/k+k0n\n" +
                    "T7W6FMbAup7P5clARupz7RMOFGfIpAgtLzCC5YVt8Yw7PH42jFNWmeT+nO2+gRPc\n" +
                    "zh9hcu2ZnkiQ40bdMiGbtBEOMcj+5nqb32DE51sTNm/eRRhwiWwdpQnUCzk5/Xlt\n" +
                    "bSrCdLqZUyqU7t3wrGeCL3M=";

            // PKCS8格式的密钥
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(privateKeyStr));
            // RSA 算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /** PublicKey 根据 秘钥 生成public key > openssl rsa -in rsa_private_key.pem -out rsa_public_key.pem -pubout
     * @return PublicKey
     */
    public static PublicKey getPublicKey() {
        PublicKey publicKey = null;
        try {
            String publicKeyStr ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0aUP/JQa0ckCmtfgil33RVCitgfyVEhdM+rYXdpwlsOqY1PZXZIjZTvbar/GTOTIflET7PXzSQXcbCcY2rOvUXwbfELCj5JEUhyx8zmODXcWSIUr8lNoLNtt2Tofz0ABeFWA9T3GziEwF94aPgUE7TtD2Y2fPY5DohntCWbE9zakplio4dEIO9fyQGyDeVq+Gg8dYv+67QBA7n5dnWOA7hSwMdZM5mjVibtRAp8OHk9z6AcC3Q0GLqFV0X6gKf3K0vAyMO95p5a3EFwrUJGf3/xiCF3cZgnvvm8+OAlRevc+CC2ARGj2lnYpfUIYQyFr7jNLXxoTDKpyrY/lKRyfQQIDAQAB";
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return publicKey;
        }
    }
}
