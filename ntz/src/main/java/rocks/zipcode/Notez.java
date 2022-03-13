package rocks.zipcode;

import java.util.Arrays;
import java.util.Locale;

/**
 * ntz main command.
 */
public final class Notez {

    private FileMap filemap;

    public Notez() {
        this.filemap  = new FileMap();
    }
    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String argv[]) {
        boolean _debug = true;
        // for help in handling the command line flags and data!
        if (_debug) {
            System.err.print("Argv: [");
            for (String a : argv) {
                System.err.print(a+" ");
            }
            System.err.println("]");
        }

        Notez ntzEngine = new Notez();

        ntzEngine.loadDatabase();

        /*
         * You will spend a lot of time right here.
         *
         * instead of loadDemoEntries, you will implement a series
         * of method calls that manipulate the Notez engine.
         * See the first one:
         */
        ntzEngine.loadDemoEntries();
        ntzEngine.saveDatabase();

        if (argv.length == 0) { // there are no commandline arguments
            //just print the contents of the filemap.
            ntzEngine.printResults();
        } else {
            if (argv[0].equals("-r")) {
                ntzEngine.addToCategory("General", Arrays.copyOfRange(argv,1,argv.length));
            } // this should give you an idea about how to TEST the Notez engine
              // without having to spend lots of time messing with command line arguments.
            else if (argv[0].equals("-f")){
                if (argv.length==2)
                    ntzEngine.removeFromCategory("General", Integer.parseInt(argv[1]));
                else
                    ntzEngine.removeFromCategory(argv[1], Integer.parseInt(argv[2]));
            }
            else if (argv[0].equals("-e")){
                if (argv.length==3)
                    ntzEngine.editFromCategory("General", Integer.parseInt(argv[1]),argv[2]);
                if (argv.length==4)
                    ntzEngine.editFromCategory(argv[1], Integer.parseInt(argv[2]), argv[3]);
            }
            else if (argv[0].equals("-c")){
                ntzEngine.addToCategory(argv[1], Arrays.copyOfRange(argv,2,argv.length));
            }
        }
        /*
         * what other method calls do you need here to implement the other commands??
         */

    }

    private void addToCategory(String string, String[] argv) {
        NoteList arg = filemap.get(string);
        arg.addAll(Arrays.asList(argv));
        filemap.put(string,arg);
    }

    private void saveDatabase() {
        filemap.save();
    }

    private void loadDatabase() {
        filemap.load();
    }

    public void printResults() {
        System.out.println(this.filemap.toString());
    }

    public void loadDemoEntries() {
        filemap.put("General", new NoteList("The Very first Note"));
        filemap.put("note2", new NoteList("A secret second note"));
        filemap.put("category3", new NoteList("Did you buy bread AND eggs?"));
        filemap.put("anotherNote", new NoteList("Hello from ZipCode!"));
    }
    /*
     * Put all your additional methods that implement commands like forget here...
     */
    public void removeFromCategory(String category, Integer noteNumber){
        NoteList arg = filemap.get(category);
        arg.remove(noteNumber-1);
        filemap.put(category,arg);
    }
    public void editFromCategory(String category, Integer noteNumber, String replacement){
        NoteList arg = filemap.get(category);
        arg.add(noteNumber-1,replacement);
        filemap.put(category,arg);
    }
}
