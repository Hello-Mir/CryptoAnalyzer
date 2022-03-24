package controllers;

import commands.*;
import exceptions.CryptoAnalyzerException;
import exceptions.ErrorCodes;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new Bruteforcer()),
    ANALYZE(new Analizer());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action defineAction(String action) {
        try {
            return Actions.valueOf(action.toUpperCase()).action;
        } catch (IllegalArgumentException e) {
            throw new CryptoAnalyzerException(ErrorCodes.TWENTY, "Передано некорректное действие " + "\"" + action + "\"");
        }
    }
}
