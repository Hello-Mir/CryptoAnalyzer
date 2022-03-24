package commands;

import exceptions.CryptoAnalyzerException;
import models.Result;
import models.ResultCode;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static models.Alphabet.ALPHABET;

public class Encoder implements Action {

    private static HashMap<Character, Character> prepareEncodeAlphabet(int key) {
        HashMap<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i < ALPHABET.length; i++) {
            int temp = i + key;
            temp = temp >= ALPHABET.length ? temp - ALPHABET.length : temp;
            hashMap.put(ALPHABET[i], ALPHABET[temp]);
        }
        return hashMap;
    }

    private void doEncode(int key, String from, String to) {
        HashMap<Character, Character> tempMap = prepareEncodeAlphabet(key);
        try (FileReader fileReader = new FileReader(from); FileWriter fileWriter = new FileWriter(to)) {
            while (fileReader.ready()) {
                char inputSymbol = (char) fileReader.read();
                Character outputSymbol = tempMap.get(Character.toLowerCase(inputSymbol));
                if (outputSymbol != null) {
                    fileWriter.write(outputSymbol);
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
        doEncode(key, from, to);
        return new Result("Файл успешно зашифрован", ResultCode.OK);
    }
}
