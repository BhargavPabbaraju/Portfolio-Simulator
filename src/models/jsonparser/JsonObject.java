package models.jsonparser;

/**
 * This interface represents a Json Object and basic operations on it.
 * A Json object can be of three types. Dictionary , List or String.
 */
public interface JsonObject{

  /**
   * This method gets the value a given key present in this object.
   * @param key The key whose value we wish to find out.
   * @return JsonObject after parsing.
   * @throws IllegalArgumentException if the key is invalid.
   */
  public JsonObject get(String key) throws IllegalArgumentException;

  /**
   * This method is used to get the type of this json object. It can be one of
   * Dictionary , List or String.
   * @return
   */
  public JsonType getType();

  /**
   * This method adds an element with a given key to this json object.
   * @param key The key at which this element must be added.
   * @param obj The element to add.
   */

  public void addElement(String key,JsonObject obj);

  /**
   * The length of this json object.
   * @return int length of this object
   */
  public int length();
}