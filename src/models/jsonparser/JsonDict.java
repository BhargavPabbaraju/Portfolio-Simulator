package models.jsonparser;

import java.util.HashMap;

/**
 * This class represents a Json Dictionary.
 */
class JsonDict implements JsonObject {
  private HashMap<String, JsonObject> dict;

  /**
   * This constructor creates a json dictionary from a hashmap.
   *
   * @param dic
   */
  JsonDict(HashMap<String, JsonObject> dic) {
    this.dict = dic;
  }

  @Override
  public JsonObject get(String key) throws IllegalArgumentException {
    if (!this.dict.containsKey(key)) {
      throw new IllegalArgumentException("Invalid key");
    }
    return this.dict.get(key);
  }

  @Override
  public JsonType getType() {
    return JsonType.DICT;
  }

  @Override
  public void addElement(String key, JsonObject obj) {
    this.dict.put(key, obj);
  }

  @Override
  public String toString() {
    return this.dict.toString();
  }

}
