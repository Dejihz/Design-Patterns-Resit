package Controller.Commands;


import java.util.ArrayList;

public class Invoker {
    public ArrayList<Command> commands = new ArrayList<>();

    public void setCommands(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
        commands.clear();
    }

}
