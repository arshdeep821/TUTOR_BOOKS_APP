package persistence;


import model.Student;
import model.TutorOrganization;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Represents a reader that reads TutorOrganization from JSON data stored in file
public class JsonReader {
    private String source;

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // Method taken from JSONReader class in
   // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads TutorOrganization from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TutorOrganization read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTutorOrganization(jsonObject);
    }

    // Method taken from JSONReader class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads sourcefile as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses TutorOrganization from JSON object and returns it
    private TutorOrganization parseTutorOrganization(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        TutorOrganization tutorOrganization = new TutorOrganization();
        addStudents(tutorOrganization, jsonObject);
        return tutorOrganization;
    }

    // MODIFIES: tutorOrganization
    // EFFECTS: parses Students from JSON object and adds them to TutorOrganization
    private void addStudents(TutorOrganization tutorOrganization, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tutorOrganization");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(tutorOrganization, nextStudent);
        }
    }

    // MODIFIES: tutorOrganization
    // EFFECTS: parses Student from JSON object and adds it to tutorOrganization
    private void addStudent(TutorOrganization tutorOrganization, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int bookedSession = jsonObject.getInt("int");
        Student student = new Student(name, bookedSession);
        tutorOrganization.makeNewTutorSession(student, bookedSession);
    }

    // getters
    public String getSource() {
        return source;
    }


}
