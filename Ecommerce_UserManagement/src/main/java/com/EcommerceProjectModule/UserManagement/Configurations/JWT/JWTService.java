package com.EcommerceProjectModule.UserManagement.Configurations.JWT;

import com.EcommerceProjectModule.UserManagement.Models.User;
import com.EcommerceProjectModule.UserManagement.Repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWTService {
    // this class needs depencencies jjwt-api,impl, and jackson


    private final String SECRET_KEY = "ad1bd707be36b87a1ad6fc07f8cff67a4bc8e0b0ca680e678ccae5ed160b7181";
    public String extractUsername(String jwt) {
        String username = this.extractClaim(jwt, Claims::getSubject);
        return username;
    }

    public  <T> T  extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwt) {
        return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public  String generateToken( UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);

    }
    public  String generateToken(Map<String, Objects> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isJWTValid(String jwt, UserDetails userDetails){
        final String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    @Autowired
    private UserRepository userRepository;

    public boolean isTokenValid(String token){
        Claims claims = Jwts.parser().setSigningKey(getSignInKey()).build().parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
//        User dbUser = userRepository.findByUsername(username);
//        if(dbUser == null){
//            return false;
//        }
        Date date = claims.getExpiration();
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        return !isTokenExpired(token);
    }

    public Date extractExpiration(String jwt) {

        return extractClaim(jwt, Claims::getExpiration);
    }

    public boolean isTokenExpired(String jwt) {
        Date date = extractExpiration(jwt);
        return date.before(new Date());
    }

}
