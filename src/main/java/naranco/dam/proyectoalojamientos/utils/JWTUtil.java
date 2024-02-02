package naranco.dam.proyectoalojamientos.utils;



import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {

    private SecretKey signingKey = Jwts.SIG.HS256.key().build();


    /**
     * Create a new token.
     *
     * @param id
     * @param subject
     * @return
     */
    public String create(String id, String subject) {
        long ttlMillis = TimeUnit.DAYS.toMillis(7);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Clave aleatoria utilizada para darle más aleatoridad al token generado
        String key = "ynnygt89r5fy9iuy94jfuey3hfkfds4laskdflasdkf0w9riw0p9ireok";
        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder().header().keyId(key).and().id(id).issuedAt(now)
                .subject(subject).issuer("Main")
                .signWith(signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.expiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String getValue(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        return Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(jwt).getPayload().getSubject();

    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public boolean validate(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        try {
            Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(jwt);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}