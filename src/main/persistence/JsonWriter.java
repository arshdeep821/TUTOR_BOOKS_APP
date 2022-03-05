package persistence;

import model.TutorOrganization;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of tutorOrganization rile
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer, throws FIleNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        //File t1 = new File(destination);
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writesJSON representation of tutorOrganization to file
    public void write(TutorOrganization tutorOrganization) {
        JSONObject json = tutorOrganization.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    //getters
    public String getDestination() {
        return destination;
    }
}

