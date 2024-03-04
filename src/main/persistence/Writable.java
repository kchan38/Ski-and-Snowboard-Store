package persistence;

import org.json.JSONObject;


// Interface to represent a class that is writable with JsonWriter
// CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
