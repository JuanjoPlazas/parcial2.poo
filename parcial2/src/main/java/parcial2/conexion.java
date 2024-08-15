package parcial2;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author USUARIO
 */
public class conexion {

    public static Firestore db;

    public static void conectarfirebase() {

        try {
            FileInputStream sa = new FileInputStream("javafirebase.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(sa))
                    .build();

            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
            System.out.println("exito al conectar");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
