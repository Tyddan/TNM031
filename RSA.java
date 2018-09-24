
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
	
	
	private BigInteger p;
	private BigInteger q;
	private BigInteger N;
	private BigInteger phi;
	private BigInteger e;
	private BigInteger d;
	private String message;
	private BigInteger c;
	private BigInteger decryptedBigInteger;
	private String decryptedMessage;
	

    private BigInteger lPrime() {
	  SecureRandom random = new SecureRandom();
	  return BigInteger.probablePrime(1024, random);
	  
    }

  public RSA() throws IOException {
	
	  p = lPrime();
	  q = lPrime();
	  e = lPrime();
	  
	  N = p.multiply(q);
	  
	  phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
	  
	  
	  while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
		  e.add(BigInteger.ONE);
	  }
	  
	  d = e.modInverse(phi); 
	  
	  
	  System.out.println("Enter plaintext: ");
	  message = (new BufferedReader(new InputStreamReader(System.in))).readLine();
	  
	  
	  c = encrypt(message);
	  
	  decryptedBigInteger = decrypt(c);
	  
	  decryptedMessage = new String(decryptedBigInteger.toByteArray());
	  
  }
  
  public RSA(BigInteger e, BigInteger N) {
	  this.e = e;
	  this.N = N;
  }
  
  public static void main(String[] args) throws IOException {
	  
	  
	  RSA rsa = new RSA();
	  
	  
	  
	  System.out.println("Encrypted message: " + rsa.message);
	  System.out.println("BigInteger Encryption:" + rsa.c);
	  System.out.println("Decrypted message: " + rsa.decryptedMessage);
	  
	  
	  
	  
  }
  
  
  public BigInteger encrypt(String m) {
	  return (new BigInteger(m.getBytes())).modPow(e,N);
  }
  
  public BigInteger decrypt(BigInteger c) {
	  return c.modPow(d,N);
  }
  
	
	
//// to convert an integer b into a BigInteger
//	int b = 170; 
//	BigInteger bigB = new BigInteger(String.valueOf(b));
//
//		
////	 	to read a string from keyboard
//    String input = (new BufferedReader(new InputStreamReader(System.in))).readLine();
//
////	 	to convert a string s into a BigInteger
//    String s = "abc";
//    BigInteger c = new BigInteger(s.getBytes());
//
//
//// to convert a BigInteger back to a string	
//    BigInteger a;
//    String s = new String(a.toByteArray());

}
