package utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.internal.com.fasterxml.jackson.core.JsonGenerationException;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonMappingException;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class JWTUtil {
	private static final String SECRET = "asdfghjkl";
	private static final String EXP = "exp";
	private static final String PAYLOAD = "payload";

	public static <T> String encode(T object, long maxTime) {
		final JWTSigner signer = new JWTSigner(SECRET);
		final Map<String, Object> data = new HashMap<String, Object>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonStrig = objectMapper.writeValueAsString(object);
			data.put(PAYLOAD, jsonStrig);
			data.put(EXP, System.currentTimeMillis() + maxTime);
			return signer.sign(data);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T decode(String jwt, Class<T> tClass) {

		final JWTVerifier jwtVerifier = new JWTVerifier(SECRET);
		Map<String, Object> data;
		try {
			data = jwtVerifier.verify(jwt);
			if (data.containsKey(EXP) && data.containsKey(PAYLOAD)) {
				long exp = (long) data.get(EXP);
				long currunTime = System.currentTimeMillis();
				if (exp > currunTime) {
					String json = (String) data.get(PAYLOAD);
					ObjectMapper objectMapper = new ObjectMapper();
					return objectMapper.readValue(json, tClass);
				}
			}
			return null;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JWTVerifyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static boolean checkToken(String token) {
		final JWTVerifier jwtVerifier = new JWTVerifier(SECRET);
		Map<String, Object> data;
		try {
			data = jwtVerifier.verify(token);
			if (data.containsKey(EXP) && data.containsKey(PAYLOAD)) {
				long exp = (long) data.get(EXP);
				long currunTime = System.currentTimeMillis();
				if (exp < currunTime) {
					return false;
				}
				else
					return true;
			}
			else
				return false;
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException | SignatureException
				| IOException | JWTVerifyException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("error token");
			return false;
		}

//		return true;
	}

}
