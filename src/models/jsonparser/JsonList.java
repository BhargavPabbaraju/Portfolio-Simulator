package models.jsonparser;

import java.util.ArrayList;

/**
 * This class represents a Json list.
 */
class JsonList implements JsonObject {
  private ArrayList<JsonObject> list;

  /**
   * This constructor is used to create a json list from an array list.
   *
   * @param list
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
  public String toString() {
    return this.list.toString();
  }
}
