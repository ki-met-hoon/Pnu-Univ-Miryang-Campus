package com.example.pnuunivmiryangcampus.support.token;

import com.example.pnuunivmiryangcampus.auth.OIDCDecodePayload;
import com.example.pnuunivmiryangcampus.auth.OIDCPublicKeyDto;
import com.example.pnuunivmiryangcampus.support.token.exception.ExpiredTokenException;
import com.example.pnuunivmiryangcampus.support.token.exception.InvalidTokenException;
import com.example.pnuunivmiryangcampus.support.token.exception.JsonParsingException;
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
        String encodedHeader = getEncodedHeader(token);
        String decodedHeader = getDecodedHeader(encodedHeader);

        try {
            JSONObject jsonObject = new JSONObject(decodedHeader);
            return jsonObject.get(KID).toString();
        } catch (JSONException e) {
            throw new JsonParsingException(e.getMessage());
        }
    }

    public Jws<Claims> getOIDCTokenJws(String token, OIDCPublicKeyDto oidcPublicKeyDto, String iss, String aud) {
        try {
            return Jwts.parser()
                    .verifyWith(getRSAPublicKey(oidcPublicKeyDto.n(), oidcPublicKeyDto.e()))
                    .requireAudience(aud)
                    .requireIssuer(iss)
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            log.error(e.toString());
            throw new InvalidTokenException();
        }
    }

    public OIDCDecodePayload getOIDCTokenBody(String token, OIDCPublicKeyDto oidcPublicKeyDto, String iss, String aud) {
        Claims payload = getOIDCTokenJws(token, oidcPublicKeyDto, iss, aud).getPayload();

        return new OIDCDecodePayload(
                payload.getIssuer(),
                payload.getAudience().toString(),
                payload.getSubject(),
                payload.get("email", String.class));
    }

    private static String getDecodedHeader(String encodedHeader) {
        byte[] decodedHeaderBytes = Base64.getDecoder().decode(encodedHeader);
        return new String(decodedHeaderBytes);
    }

    private static String getEncodedHeader(String token) {
        String[] splitToken = token.split("\\.");
        return splitToken[0];
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
