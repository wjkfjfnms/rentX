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
//            String privateKeyStr ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDLTEg+rMvH7hPW\n" +
//                    "mYXkCIzOj9aBSsbZv3kEB79l9aoCjmWgQZVQWKYJh7GccIq0tUUKUf8uyve8WBxq\n" +
//                    "eKf8af4w7IU6zwAcodFc2hWgz3rxaJTyvLKRyc+u0jkU0b+HJX3kr45gkqA5e3Hi\n" +
//                    "xJ9pujczw2+qRvUF2ZdSU4TdZmHfFfeBZxhHM1u4+x3I3g2IbLOqHthgpUL3oqkN\n" +
//                    "mDxTjKVX7eCg3Z98CvrXCFFFG/+N81eGVS4WH61NQW16mD7MyklZaVK0YvcqV5T6\n" +
//                    "QIhc6TunveQFhlcvHBt6fbf5XD7sONkb6renOmza0pa0cpcewGM3Uqhksfa7d35m\n" +
//                    "+qn3OHxbAgMBAAECggEALtQLcCRtbEgy2aPTa4tso8gOKQoIc+/e+Eawl9PWrXAP\n" +
//                    "iLKSqkdBN8DB8L8btDRtCsuIdEbcec3x/bR9PW9M1gglSD6WUAIWL8UsZjZko2br\n" +
//                    "qF4+48YQyLwz2c/WxlKkhSDLMwOTVKpSuWGBNuFmBc5m0lo15nsQmC4U1MrYkIId\n" +
//                    "uz9Nzd5pbLFh4L9vsMKEXbAA97WEQ0+XfrJu3mVzpnkzJ0AzdtG9gQms/Zp0W/Y3\n" +
//                    "9Qimx4k2PrtXB2jHxaQ3exXl+49NpEgfeDRWzNuF8GEgtLpKauGDrgFiCMIg27Zv\n" +
//                    "7gpHVidiFxXcPVYj4Q+z2x1ThOOJ3jDQ4l0sT4cUAQKBgQDoSkHmW3JpsM6eX8Zx\n" +
//                    "pr4L61U4vgor7LJy3ftBB0F4KA+2phxwWPPnRQKsN+QqXL1a8/pcHsSYasduetP4\n" +
//                    "DT/krGgjuqyas7tm7Lyz5MdwyMoiMx3Is/R7u4Acx8CKHoDuqaXAy4tkMPsj+hrW\n" +
//                    "cvyevUZ5fW6WokN92nzM5jLdGwKBgQDgDHYIl0xrznfzo61gL4Q9ptYgqFRXVX0e\n" +
//                    "vrUJHy35S95FpcV6uBkJwWIQ6ud8W8QY3twLWXEY0eYGAc4O4YZfQtLEltvIzIYj\n" +
//                    "dyIrANfOvS/5WGULNIrR0IqPwvh5SIHb/Wyfe4TWfks/71jz90T5ESiPdZeCQbiy\n" +
//                    "wj965qMRwQKBgFlKchqCfHB0LnhiiH2XguZCB/hP1Mkdq1rMSZCtoVT20VN8duIY\n" +
//                    "z2jO9kANG//Mtl0H++z9RsTwBslJHcH0FeNpAn75eu/ctuTRi5f7EU8hX9HVpFVK\n" +
//                    "F2iYop5tim2NXDJhs/1wQJy61071DOvyN3kteLGqrZ/pr9fo7KA2vMaRAoGBANHR\n" +
//                    "GaCGAB1dp9vUxoKzPQZ3fUPsH8AB4UCvm95FAsraD/ftfHime1w1a98n/Uzqq0QX\n" +
//                    "QL6oLk/uEZpCinRJI4uGitdr57uuPVhtMuROz8teRNUfwNCzjeVbhMeI8SSzkFky\n" +
//                    "hUz9YbN1ZocjYIxoXoFZXkCrAQ4M+ij93HD+GGyBAoGBAIM+F9uUQG+6cRxx0Meu\n" +
//                    "IAd/T8kLfw9PEM8DEF5om+3g0LeQDYC8vvwslhhfBkhFU4g9QpAQds5AhfMHCSZr\n" +
//                    "ursJCVolGMq6FvY5+qneVU6P3rIfj+FEd1i6SNQMDjufQ2DnUX21goDAwn/HH9BH\n" +
//                    "1LHEIFKPoQ6707vWudt8ZU0b";

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
