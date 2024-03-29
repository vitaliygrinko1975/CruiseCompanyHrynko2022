package ua.nure.hrynko.services;

import org.apache.log4j.Logger;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodePassword {
    private static final Logger LOG = Logger.getLogger(EncodePassword.class);
    private static final String MD5 = "MD5";
    private static final String STRING_ZERO = "0";
    private static final int POSITIVE_SIGN = 1;
    private static final int RADIX = 16;
    private static final int HASH_LENGTH = 32;
    private static final int ZERO_ARRAY = 0;
    private static final int OFFSET = 0;

    public String getHashPassword(String input){
        LOG.debug("getHashPassword starts");
        MessageDigest messageDigest;
        byte[] byteDigest = new byte[ZERO_ARRAY];

        try {
            messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.reset();
            byteDigest = messageDigest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException exception) {
            LOG.error(exception, exception);
        }

        BigInteger number = new BigInteger(POSITIVE_SIGN, byteDigest);
        StringBuilder hashHex = new StringBuilder(number.toString(RADIX));
        while (hashHex.length() < HASH_LENGTH) {
            hashHex.insert(OFFSET, STRING_ZERO);
        }
        LOG.debug("getHashPassword starts");
        return hashHex.toString();
    }
}