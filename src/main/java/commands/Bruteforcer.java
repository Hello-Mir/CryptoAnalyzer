package commands;

import exceptions.CryptoAnalyzerException;
import models.Result;
import models.ResultCode;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static models.Alphabet.ALPHABET;

public class Bruteforcer implements Action {

    private static void getDecodingKey(String fromFile, String toFile) {
        long startTime = System.currentTimeMillis();
        int spaceCounter = 0;
        int minSpaces = -99999;
        int minKey = -99999;
        int key = 0;
        while (key <= ALPHABET.length - 1) {
            try (FileReader fileReader = new FileReader(fromFile)) {
                while (fileReader.ready()) {
                    Character encodedSymbol = (char) fileReader.read();
                    Character decodedSymbol = doDecodeSymbol(key, encodedSymbol);
                    if (decodedSymbol == ' ') {
                        spaceCounter++;
                    }
                }
                if (spaceCounter > minSpaces) {
                    minKey = key;
                    minSpaces = spaceCounter;
                }
                key++;
                spaceCounter = 0;
            } catch (IOException e) {
                throw new CryptoAnalyzerException("Не удалось найти файл", e);
            }
        }
//        System.out.println("Ключ для дешифровки :" + minKey);
        new Decoder().doDecode(minKey, fromFile, toFile);
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println(finishTime);
    }


    private static Character doDecodeSymbol(int key, Character encodedSymbol) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            int temp = i - key;
            temp = temp < 0 ? ALPHABET.length - Math.abs(temp) : temp;
            hashMap.put(ALPHABET[i], ALPHABET[temp]);
        }
        return hashMap.get(encodedSymbol);
    }

    @Override
    public Result execute(String[] parameters) {
        String from = parameters[0];
        String to = parameters[1];
        getDecodingKey(from, to);
        return new Result("Брутфорс успешно завершен", ResultCode.OK);
    }
}
