package noughtsn;

import java.util.Map;

public class App implements Runnable {

    private final Map<String, Runnable> commands;

    @Override
    public void run() {
        if (args.length > 0) if (commands.containsKey(args[0])) commands.get(args[0]).run();
        else new Game(args, System.in).run();
    }

    public App(String[] args) {
        this.args = args;
        commands = Map.of("--help", new Help(args));
    }

    private final String[] args;

}
