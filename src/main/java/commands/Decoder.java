package commands;

import exceptions.CryptoAnalyzerException;
import models.Result;
import models.ResultCode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static models.Alphabet.ALPHABET;

public class Decoder implements Action {

    private static HashMap<Character, Character> prepareDecodeAlphabet(int key) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            int temp = i - key;
            temp = temp < 0 ? ALPHABET.length - Math.abs(temp) : temp;
            hashMap.put(ALPHABET[i], ALPHABET[temp]);
        }
        return hashMap;
    }

    public void doDecode(int key, String from, String to) {
        HashMap<Character, Character> tempMap = prepareDecodeAlphabet(key);
        try (FileReader fileReader = new FileReader(from); FileWriter fileWriter = new FileWriter(to)) {
            while (fileReader.ready()) {
                Character input = (char) fileReader.read();
                Character outPut = tempMap.get(input);
                if (outPut != null) {
                    fileWriter.write(outPut);
                }
            }
        } catch (IOException e) {
            throw new CryptoAnalyzerException("Не удалось найти файл в: " + from, e);
        }
    }

    @Override
    public Result execute(String[] parameters) {
        int key = Integer.parseInt(parameters[0]);
        if (key < 0 || key > ALPHABET.length - 1) {
            throw new CryptoAnalyzerException("Некорректное значение ключа. Возможно значение от 0 до " + ALPHABET.length);
        }
        String from = parameters[1];
        String to = parameters[2];
        doDecode(key, from, to);
        return new Result("Файл успешно дешифрован", ResultCode.OK);
    }
}
