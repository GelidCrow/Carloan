package business.command;

public class CommandFactory {
private CommandFactory(){
	
}

public static Command getInstance(String commandName) {
	try {
		return (Command) Class.forName(commandName).newInstance();
	} catch (InstantiationException | IllegalAccessException
			| ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return null;	
}
}
