package Connection;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.TimeZone;
import java.util.UUID;

public class Utils {
    public static final String JDBC_Driver_SQLite = "org.sqlite.JDBC";
    public static final String JDBC_URL_SQLite = String.format("jdbc:sqlite:C:Final.db"); //Aggiunto Final come fa lui nei video
            //Paths.get("C:\\Users\\ferri\\PizzaJJJ").toString());
    //dobbiamo mettere il path del database
    //fACCIAMO IN MODO DI METTERLO ALLA FINE UNA VOLTA TUTTO RISOLTO direttamente
    //all' interno del prj e in modo che genera il file con ordini eventuale all' aperturra e li salvi

    // public static final String JDBC_URL_SQLite = String.format("jdbc:sqlite:%s",
    //         Paths.get(Connection.Utils.ooprogrammingdir(), "ooprogramming.sqlite").toString());
    // //Path da modificare con la cartella di progetto (?)


    //Funzione che ritorna un path "creato", io l'ho incollato direttamente sopra
    /*public static String ooprogrammingdir() {
        String path = String.format("%s%s%s%s%s", System.getProperty("user.home"), System.getProperty("file.separator"),
                "Desktop", System.getProperty("file.separator"), "ooprgramming");
        new File(path).mkdirs();
        return path;
    }*/


    public static UUID asUUID(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }

    public static byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
}