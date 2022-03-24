import controllers.MainController;
import exceptions.CryptoAnalyzerException;
import exceptions.ErrorCodes;
import models.Result;

import java.util.Arrays;

public class Application {
    private final MainController mainController;

    public Application() {
        mainController = new MainController();
    }

    public Result run(String[] args) throws CryptoAnalyzerException {
        if (args.length > 0) {
            String action = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return mainController.doAction(action, parameters);
        }
        throw new CryptoAnalyzerException(ErrorCodes.TEN, "Количество параметров меньше допустимого значения. Текущее количество: " + args.length);
    }
}