package slogo.model.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Finds all user commands in a program
 * Used for retrieving the String as it is written by the user
 *
 * @author Jake Heller
 */
public class UserCommandFinder {

  private static int NUM_LISTS = 2;

  public static Map<String, String> findUserCommands(String program, Parser p, Set<String> compiledUserCommands) {
    Map<String, String> userCommands = new HashMap<>();
    String[] tokens = program.split(Compiler.WHITESPACE);

    int index = 0;

    while (index < tokens.length) {

      if (isToCommand(tokens[index], p)) {
        String userCommand = getUserCommand(tokens, index, p);
        String commandName = tokens[index+1];
        if (compiledUserCommands.contains(commandName)) {
          userCommands.put(commandName, userCommand);
        }
      }
      index++;
    }
    return userCommands;
  }

  private static String getUserCommand(String[] programTokens, int index, Parser p) {
    int completeLists = 0;
    int openLists = 0;
    String command = "";
    while (completeLists < 2) {
      String token = programTokens[index];
      command += token + " ";
      if (isListStart(token, p)) {
        openLists++;
      } else if (isListEnd(token, p)) {
        openLists--;
        if (openLists == 0) {
          completeLists++;
          command += "\n";
        }
      }
      index++;
    }
    return command;
  }

  private static boolean isToCommand(String token, Parser p) {
    return p.getSymbol(token).equals("MakeUserInstruction");
  }

  private static boolean isListStart(String token, Parser p) {
    return p.getSymbol(token).equals("ListStart");
  }

  private static boolean isListEnd(String token, Parser p) {
    return p.getSymbol(token).equals("ListEnd");
  }
}
