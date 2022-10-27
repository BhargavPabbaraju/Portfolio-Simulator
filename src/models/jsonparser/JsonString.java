package models.jsonparser;

/**
 * This class represents a json string.
 */
class JsonString implements JsonObject {

  private String value;

  /**
   * This constructor creates a json string with a given string.
   *
   * @param value
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
    return this.value.toString();
  }
}
