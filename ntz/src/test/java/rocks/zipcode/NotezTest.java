package rocks.zipcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class NotezTest {
    Notez notez = new Notez();
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {}

    @Test
    void removeFromCategory() {
        String testCategory = "test";
        String testString = "testing note";
        NoteList noteList = new NoteList();
        String expectedString =  new StringBuilder()
            .append(testCategory)
            .append("\n")
            .append(noteList.toString())
            .append("\n").toString();
        notez.addToCategory(testCategory, new String[]{testString});
        notez.removeFromCategory(testCategory, 1);
        Assertions.assertEquals(expectedString,notez.mapToString());
    }

    @Test
    void addToCategory() {
        String testCategory = "test";
        String testString = "testing note";
        NoteList noteList = new NoteList();
        noteList.add(testString);
        String expectedString =  new StringBuilder()
            .append(testCategory)
            .append("\n")
            .append(noteList.toString())
            .append("\n").toString();
        notez.addToCategory(testCategory, new String[]{testString});
        Assertions.assertEquals(expectedString,notez.mapToString());
    }

    @Test
    void editFromCategory() {
        String testCategory = "test";
        String testString = "testing note";
        notez.addToCategory(testCategory, new String[]{testString});
        notez.editFromCategory(testCategory, 1, "test2");
        NoteList noteList = new NoteList();
        noteList.add("test2");
        String expectedString =  new StringBuilder()
            .append(testCategory)
            .append("\n")
            .append(noteList.toString())
            .append("\n").toString();
        Assertions.assertEquals(expectedString,notez.mapToString());
    }
}
