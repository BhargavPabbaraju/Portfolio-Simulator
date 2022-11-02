package models.jsonparser;

/**
 * This class represents a json string. It handles the string values in a json file.
 */
class JsonString implements JsonObject {

  private String value;

  /**
   * This constructor creates a json string with a given string.
   *
   * @param value String value from the json file
   */
  JsonString(String value) {
    this.value = value;
  }

  @Override
  public JsonObject get(String key) throws IllegalArgumentException {
    return this;
  }

  @Override
  public JsonType getType() {
    return JsonType.STRING;
  }

  @Override
  public void addElement(String key, JsonObject obj) {
    //You cannot add elements to a string.

  }

  @Override
  public int length() {
    return this.value.length();
  }

  @Override
  public String toString() {
    return this.value;
  }
}
