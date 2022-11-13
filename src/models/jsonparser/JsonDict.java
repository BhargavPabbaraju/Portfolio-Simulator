package models.jsonparser;

import java.util.HashMap;
import java.util.Set;

/**
 * This class represents a Json Dictionary. It is used to parse the loaded file.
 */
class JsonDict implements JsonObject {
  private HashMap<String, JsonObject> dict;

  /**
   * This constructor creates a json dictionary from a hashmap.
   *
   * @param dic Hashmap of json dictionary
   */
  JsonDict(HashMap<String, JsonObject> dic) {
    this.dict = dic;
  }

  @Override
  public JsonObject get(String key) throws IllegalArgumentException {
    if (!this.dict.containsKey(key)) {
      throw new IllegalArgumentException("Invalid key: " + key + "Field doesn't exist");
    }
    return this.dict.get(key);
  }

  @Override
  public JsonType getType() {
    return JsonType.DICT;
  }

  public Set<String> getKeys() {
    return this.dict.keySet();
  }

  @Override
  public void addElement(String key, JsonObject obj) {
    this.dict.put(key, obj);
  }

  @Override
  public int length() {
    return this.dict.size();
  }

  @Override
  public String toString() {
    return this.dict.toString();
  }

}
