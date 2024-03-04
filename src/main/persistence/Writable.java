package persistence;

import org.json.JSONObject;

// CITATION: Copied from JsonSerializationDemo - WorkroomApp.java
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
