package models.jsonparser;

import java.util.ArrayList;

/**
 * This class represents a Json list. It is used to handel the list which appears in a json files
 */
class JsonList implements JsonObject {
  private ArrayList<JsonObject> list;

  /**
   * This constructor is used to create a json list from an array list.
   *
   * @param list ArrayList from the loaded file.
   */
  JsonList(ArrayList<JsonObject> list) {
    this.list = list;
  }

  @Override
  public JsonObject get(String key) throws IllegalArgumentException {
    return this.list.get(Integer.parseInt(key));
  }

  @Override
  public JsonType getType() {
    return JsonType.LIST;
  }

  @Override
  public void addElement(String key, JsonObject obj) {
    this.list.add(obj);
  }

  @Override
  public int length() {
    return this.list.size();
  }

  @Override
  public String toString() {
    return this.list.toString();
  }
}
