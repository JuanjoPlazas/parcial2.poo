/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcial2;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class Personaprovider {
    
    CollectionReference reference;
    
    static Firestore db;
    
    public static boolean guardarcomida(String coleccion, String documento, Map<String, Object> data){
        
        
        db = FirestoreClient.getFirestore();
        
        try {DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("guardado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("error:" +e.getMessage());
        }
        return false;
    }
    
     public static boolean actualizarcomida(String coleccion, String documento, Map<String, Object> data){
        
        
        db = FirestoreClient.getFirestore();
        
        try {DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("actualizado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("error:" +e.getMessage());
        }
        return false;
    }
     
          public static boolean eliminarcomida(String coleccion, String documento){
        
        
        db = FirestoreClient.getFirestore();
        
        try {DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("eliminado correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("error:" +e.getMessage());
        }
        return false;
          }
          
          public static void cargartablacomida(JTable table){
              
              DefaultTableModel model = new DefaultTableModel();
              model.addColumn("id");
              model.addColumn("nombre");
              model.addColumn("tipo");
              model.addColumn("precio");
             
              
              
              
              try {
                  CollectionReference comida = conexion.db.collection("comida");
                  ApiFuture<QuerySnapshot> querysnap = comida.get();
                  
                  
                  for(DocumentSnapshot document: querysnap.get().getDocuments()){
                      model.addRow(new Object[]{
                          document.getId(),
                          document.getString("nombre"),
                          document.getString("tipo"),
                          document.getString("precio"),
                          
                      });
              }
                  
              
                  
              } catch (InterruptedException | ExecutionException e) {
                  System.err.println("error" +e.getMessage());
              }
 
              
              table.setModel(model);
                  
          }                
}
