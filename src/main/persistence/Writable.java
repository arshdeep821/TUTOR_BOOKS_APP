package persistence;

import org.json.JSONObject;

// An interface that contains 1 method signature for TutorOrganization and Student class to implement
public interface Writable {

    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
