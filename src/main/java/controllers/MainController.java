package controllers;

import commands.Action;
import models.Result;

public class MainController {
    public Result doAction(String commandAction, String[] parameters) {
        Action action = Actions.defineAction(commandAction);
        return action.execute(parameters);
    }
}
