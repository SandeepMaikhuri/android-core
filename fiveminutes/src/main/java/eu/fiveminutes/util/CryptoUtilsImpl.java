package eu.fiveminutes.util;

import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class CryptoUtilsImpl implements CryptoUtils {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private Mac macSha1Instance;

    public CryptoUtilsImpl() {
        try {
            macSha1Instance = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String hmacSha1(String data, String key) {
        String result = "";
        try {

            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);

            // get an hmac_sha1 Mac instance and initialize with the signing key
            macSha1Instance.init(signingKey);

            // compute the hmac on input data bytes
            byte[] rawHmac = macSha1Instance.doFinal(data.getBytes());

            // base64-encode the hmac
            result = toHexString(rawHmac);

        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public int getUnixTimestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    @Override
    public String getUnixTimestampAsString() {
        return Integer.toString(getUnixTimestamp());
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }
}
