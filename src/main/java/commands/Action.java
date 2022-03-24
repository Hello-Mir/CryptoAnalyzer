package commands;

import models.Result;

public interface Action {
    Result execute(String[] parameters);
}
