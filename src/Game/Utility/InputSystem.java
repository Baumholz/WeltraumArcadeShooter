package Game.Utility;

import Game.Object.Avatar;
import Game.Object.Canon;

//test123
public interface InputSystem {
	// return User Input Object
	public UserInput getUserInput();

	// interpret Game.Utility.UserInput and tell the User Object what to do
	public void command(Avatar userObject, Canon canon, UserInput userInput);
}
