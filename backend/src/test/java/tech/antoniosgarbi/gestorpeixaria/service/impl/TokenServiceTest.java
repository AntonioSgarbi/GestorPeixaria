package tech.antoniosgarbi.gestorpeixaria.service.impl;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import tech.antoniosgarbi.gestorpeixaria.configuration.UserDetailsImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
class TokenServiceTest {

    private static MockedStatic<Jwts> jwts;

    @Mock
    private JwtBuilder jwtBuilder;

    @Mock
    private JwtParser jwtParser;

    @Mock
    private Jws<Claims> jwtClaims;

    @Mock
    private Claims claims;

    @InjectMocks
    private TokenServiceImpl underTest;

    @BeforeAll
    static void setUp() {
        jwts = mockStatic(Jwts.class);

    }

//    @Value("${personal.security.jwtSecret}")
//    private String jwtSecret;
//    @Value("${personal.security.jwtExpirationMs}")
//    private int jwtExpirationMs;
//    @Value("${personal.security.jwtRefreshExpirationMs}")
//    private Long refreshTokenDurationMs;
//    @Value("${personal.security.passwordGenerate}")
//    private String passwordToEncript;

    @Test
    void generateAccessTokenFromUserDetails() {
        String expectedValue = "expectedValue";

        ReflectionTestUtils.setField(underTest, "jwtExpirationMs", 360000000);
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::builder).thenReturn(jwtBuilder);

        when(jwtBuilder.setSubject(anyString())).thenReturn(jwtBuilder);
        when(jwtBuilder.setIssuedAt(any(Date.class))).thenReturn(jwtBuilder);
        when(jwtBuilder.setExpiration(any(Date.class))).thenReturn(jwtBuilder);
        when(jwtBuilder.signWith(any(), anyString())).thenReturn(jwtBuilder);
        when(jwtBuilder.compact()).thenReturn(expectedValue);

        UserDetailsImpl user = new UserDetailsImpl();
        user.setUsername("test");

        assertEquals(expectedValue, this.underTest.generateAccessToken(user));
    }

   @Test
    void generateAccessTokenFromName() {
        String expectedValue = "expectedValue";

        ReflectionTestUtils.setField(underTest, "jwtExpirationMs", 360000000);
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::builder).thenReturn(jwtBuilder);

        when(jwtBuilder.setSubject(anyString())).thenReturn(jwtBuilder);
        when(jwtBuilder.setIssuedAt(any(Date.class))).thenReturn(jwtBuilder);
        when(jwtBuilder.setExpiration(any(Date.class))).thenReturn(jwtBuilder);
        when(jwtBuilder.signWith(any(), anyString())).thenReturn(jwtBuilder);
        when(jwtBuilder.compact()).thenReturn(expectedValue);

        UserDetailsImpl user = new UserDetailsImpl();
        user.setUsername("test");

        assertEquals(expectedValue, this.underTest.generateAccessToken("test"));
    }
   @Test
    void generateRefreshTokenFromName() {
        String expectedValue = "expectedValue";

        ReflectionTestUtils.setField(underTest, "refreshTokenDurationMs", 6000000);
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::builder).thenReturn(jwtBuilder);

        when(jwtBuilder.setSubject(anyString())).thenReturn(jwtBuilder);
        when(jwtBuilder.setIssuedAt(any(Date.class))).thenReturn(jwtBuilder);
        when(jwtBuilder.setExpiration(any(Date.class))).thenReturn(jwtBuilder);
        when(jwtBuilder.signWith(any(), anyString())).thenReturn(jwtBuilder);
        when(jwtBuilder.compact()).thenReturn(expectedValue);

        UserDetailsImpl user = new UserDetailsImpl();
        user.setUsername("test");

        assertEquals(expectedValue, this.underTest.generateRefreshTokenFromUsername("test"));
    }

    @Test
    void getUserNameFromJwtToken() {
        String expectedValue = "expectedValue";

        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenReturn(jwtClaims);
        when(jwtClaims.getBody()).thenReturn(claims);
        when(claims.getSubject()).thenReturn(expectedValue);

        assertEquals(expectedValue, this.underTest.getUserNameFromJwtToken("token"));
    }

    @Test
    void validateJwtTokenSuccess() {
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenReturn(jwtClaims);

        assertTrue(this.underTest.validateJwtToken("token"));
    }

    @Test
    void validateJwtTokenSignatureException() {
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenThrow(new SignatureException("erro"));

        assertFalse(this.underTest.validateJwtToken("token"));
    }

    @Test
    void validateJwtTokenMalformedJwtException() {
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenThrow(new MalformedJwtException("erro"));

        assertFalse(this.underTest.validateJwtToken("token"));
    }

    @Test
    void validateJwtTokenExpiredJwtException() {
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenThrow(new ExpiredJwtException(null, null, null));

        assertFalse(this.underTest.validateJwtToken("token"));
    }

    @Test
    void validateJwtTokenUnsupportedJwtException() {
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenThrow(new UnsupportedJwtException("erro"));

        assertFalse(this.underTest.validateJwtToken("token"));
    }

    @Test
    void validateJwtTokenIllegalArgumentException() {
        ReflectionTestUtils.setField(underTest, "jwtSecret", "secret");

        jwts.when(Jwts::parser).thenReturn(jwtParser);

        when(jwtParser.setSigningKey(anyString())).thenReturn(jwtParser);
        when(jwtParser.parseClaimsJws(anyString())).thenThrow(new IllegalArgumentException("erro"));

        assertFalse(this.underTest.validateJwtToken("token"));
    }


}
