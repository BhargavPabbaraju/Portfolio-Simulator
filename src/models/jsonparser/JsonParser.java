package models.jsonparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * This class represents a Json Parser. It takes a json file and represents it as a
 * Json Object.
 */
public class JsonParser {
  static LinkedList<String> inputStack;
  static LinkedList<JsonObject> resultStack;

  JsonObject result;


  private void printContents() {
    System.out.println("Contents of result stack");
    while (!resultStack.isEmpty()) {
      System.out.println(resultStack.pop());
    }
    System.out.println("Contents of input stack");
    while (!inputStack.isEmpty()) {
      System.out.println(inputStack.pop());
    }
  }


  private static String readFile(String filepath) throws FileNotFoundException {
    Scanner file = new Scanner(new File(filepath));
    //Input Processing
    StringBuilder input = new StringBuilder();
    while (file.hasNext()) {
      input.append(file.next());
    }
    return input.toString();

  }

  private static String[] formatInput(String input) {
    String[] formattedInput = input.split(":");

    String p = String.format("%c", 1114111);
    String[] separators = new String[]{"{", "}", "[", "]", ",", " "};
    for (String sep : separators) {
      input = String.join(p, formattedInput);
      formattedInput = input.replace(sep, sep + p).split(p);
    }
    return formattedInput;
  }

  private static boolean isEscapeCharacter(String i) {
    return i.equals("{") || i.equals("[");
  }

  private static String cleanInput(String i) {
    return i.replace(",", "").replace("\"", "");
  }


  private static void endArray() {
    JsonObject ele = resultStack.pop();
    ArrayList<JsonObject> list = new ArrayList<>();
    while (ele.getType() != JsonType.LIST) {
      list.add(ele);
      ele = resultStack.pop();
    }
    inputStack.pop();// Pop [
    String key = inputStack.pop();
    JsonObject dic = resultStack.pop();
    dic.addElement(key, new JsonList(list));
    resultStack.push(dic);
  }

  private static void endDict(String input) {
    String key = inputStack.pop();
    input = input.replace("}", "");

    JsonObject dic = resultStack.pop();
    dic.addElement(key, new JsonString(input));
    resultStack.push(dic);

    if (inputStack.peek().equals("{")) {
      inputStack.pop(); // Pop the {
    }

    if (!inputStack.peek().equals("[")) {
      key = inputStack.pop();
      dic = resultStack.pop();
      JsonObject dic2 = resultStack.pop();
      dic2.addElement(key, dic);
      resultStack.push(dic2);
    }

  }

  private static void processKeyValuePairs(String key, String input) {
    if (!isEscapeCharacter(key)) {
      JsonObject dic = resultStack.pop();
      dic.addElement(key, new JsonString(input));
      resultStack.push(dic);
    } else {
      inputStack.push(key);
      inputStack.push(input);
    }
  }

  private static void processStrings(String input) {
    if (!inputStack.isEmpty()) {
      String key = inputStack.pop();
      processKeyValuePairs(key, input);
    } else {
      inputStack.push(input);
    }
  }

  /**
   * This method takes a filepath as an input and parses it and returns a json object representing
   * key value pairs present in the file, provided the file is correctly formatted.
   *
   * @param filepath path of the file
   * @return returns a JsonObject
   * @throws FileNotFoundException if the provided filepath doesn't exist.
   */
  public static JsonObject parse(String filepath) throws FileNotFoundException {
    String file = readFile(filepath);
    String[] formattedFile = formatInput(file);
    inputStack = new LinkedList<>();
    resultStack = new LinkedList<>();
    for (String input : formattedFile) {
      input = cleanInput(input);


      if (input.equals("")) {
        continue;
      }
      if (input.equals("{")) {
        inputStack.push(input);
        HashMap<String, JsonObject> dic = new HashMap<>();
        resultStack.push(new JsonDict(dic));
      } else if (input.equals("[")) {
        inputStack.push(input);
        ArrayList<JsonObject> list = new ArrayList<>();
        resultStack.push(new JsonList(list));
      } else if (input.equals("}")) {
        inputStack.pop();// Pop the {
        endDict2();
      } else if (input.equals("]")) {
        endArray();
      } else if (input.endsWith("}")) {
        endDict(input);
      } else {
        processStrings(input);
      }
    }
    return resultStack.pop();
  }

  private static void endDict2() {
    if (!inputStack.isEmpty() && !isEscapeCharacter(inputStack.peek())) {
      String key = inputStack.pop();
      JsonObject value = resultStack.pop();
      JsonObject dic = resultStack.pop();
      dic.addElement(key, value);
      resultStack.push(dic);
    }
  }

}



