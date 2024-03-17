package com.example.pnuunivmiryangcampus.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtOIDCProvider {

    public String getKidFromTokenHeader(String token) {

        String KID = "kid";
        String[] splitToken = token.split("\\.");
        String header = splitToken[0];

        byte[] decodeJson = Base64.getDecoder().decode(header);
        String decodeHeader = new String(decodeJson);

        try {
            JSONObject jsonObject = new JSONObject(decodeHeader);
            return jsonObject.get(KID).toString();
        } catch (JSONException e) {
            return e.toString();
        }
    }

    public Jws<Claims> getOIDCTokenJws(String token, String modulus, String exponent, String iss, String aud) {

        try {
            return Jwts.parser()
                    .verifyWith(getRSAPublicKey(modulus, exponent))
                    .requireAudience(aud)
                    .requireIssuer(iss)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new Exception500(e.getMessage());
        } catch (Exception e) {
            log.error(e.toString());
            throw new Exception500(e.getMessage());
        }
    }

    public OIDCDecodePayload getOIDCTokenBody(String token, String modulus, String exponent, String iss, String aud) {

        Claims payload = getOIDCTokenJws(token, modulus, exponent, iss, aud).getPayload();

        return new OIDCDecodePayload(
                payload.getIssuer(),
                payload.getAudience().toString(),
                payload.getSubject(),
                payload.get("email", String.class));
    }

    private PublicKey getRSAPublicKey(String modulus, String exponent) throws NoSuchAlgorithmException, InvalidKeySpecException {

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }
}
