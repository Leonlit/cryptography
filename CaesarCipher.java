public class CaesarCipher {
    public static void main (String args[]) {
        String message = "I need to test this lol";
        String message_special = "I need to test this now! lol.";
        char characters[] = stringToChar(message.toLowerCase());
        char characters_special[] = stringToChar(message_special);
        int offset = 22;

        char ciphertext[] = originalCaeserCipher(characters, offset, false);
        System.out.println(charToString(ciphertext));

        char deciphertext[] = originalCaeserCipher(ciphertext, offset, true);
        System.out.println(charToString(deciphertext));
        
        char modifiedCiphertext[] = modifiedCaeserCipher(characters_special, offset, false);
        System.out.println(charToString(modifiedCiphertext));
        char modifiedDeciphertext[] = modifiedCaeserCipher(modifiedCiphertext, offset, true);
        System.out.println(charToString(modifiedDeciphertext));
    }

    public static char[] originalCaeserCipher (char characters[], int offset, boolean decipher) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        char cipher[] = new char[characters.length];
        for (int x = 0; x < characters.length; x++) {
            char curr = characters[x];
            int pos = alphabets.indexOf(curr);
            if (curr == ' ') {
                cipher[x] = ' ';
            }else {
                if (!decipher) {
                    pos = Math.abs(pos + (26 + (offset % 26))) % 26;
                }else {
                    pos = Math.abs(pos + (26 - (offset % 26))) % 26;
                }
                cipher[x] = alphabets.charAt(pos);
            }
        }
        return cipher;
    }

    public static char[] modifiedCaeserCipher (char characters[], int offset, boolean decipher) {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String special = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        String numbers = "0123456789";
        char cipher[] = new char[characters.length];
        for (int x = 0; x < characters.length; x++) {
            char curr = characters[x];
            String categories = "";
            if (uppercase.indexOf(curr) > -1) {
                categories = uppercase;
            }
            if (lowercase.indexOf(curr) > -1) {
                categories = lowercase;
            }
            if (special.indexOf(curr) > -1) {
                categories = special;
            }
            if (numbers.indexOf(curr) > -1) {
                categories = numbers;
            }

            int pos = categories.indexOf(curr);
            int length = categories.length();
            if (curr == ' ') {
                cipher[x] = ' ';
            }else {
                if (!decipher) {
                    pos = Math.abs(pos + (length + (offset % length))) % length;
                }else {
                    pos = Math.abs(pos + (length - (offset % length))) % length;
                }
                cipher[x] = categories.charAt(pos);
            }
        }
        return cipher;
    }

    public static char[] stringToChar (String message) {
        char characters[] = new char[message.length()];
        for (int x = 0; x<message.length(); x++) {
            characters[x] = message.charAt(x);
        }
        return characters;
    }

    public static String charToString (char[] characters) {
        return new String(characters);
    }
}
