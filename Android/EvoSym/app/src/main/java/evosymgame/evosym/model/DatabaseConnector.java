package evosymgame.evosym.model;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Jorge on 20/11/2017.
 */

public class DatabaseConnector {
    private String user;
    private String pass;
    private static FirebaseFirestore db;

    public DatabaseConnector(){
        db = FirebaseFirestore.getInstance();
    }

    public void addData(Object data, String collection, final Context appContext){
        db.collection(collection)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("INFO", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR", "Error adding document", e);
                    }
                });
    }

    public ArrayList<MapMod> retrieveData(String collection){
        final ArrayList<MapMod> data = new ArrayList<>();
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d("INFO", document.getId() + " => " + document.getData());
                                MapMod map = new MapMod();
                                map.uid = document.getId();
                                map.map = document.toObject(Object.class);
                                data.add(map);
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
        return data;
    }

    public boolean contains(final Object o, String collection){
        return contains(o, collection,"none");
    }

    public boolean contains(final Object o, String collection, final String objectConversion){
        final boolean[] retval = {false};
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot document : task.getResult()){
                                if(document.toObject(Object.class) == o){
                                    retval[0] = true;
                                } else{
                                    switch (objectConversion){
                                        case Constants.ClassMejora:
                                            Mejora aux1 = document.toObject(Mejora.class);
                                            Mejora aux2 = (Mejora)o;
                                            if(aux1.similar(aux2)){
                                                retval[0] = true;
                                            }
                                            break;
                                        case Constants.ClassEspecie:

                                            break;
                                        case Constants.ClassEcosistema:

                                            break;
                                        case Constants.ClassMejoras:

                                            break;
                                        case Constants.ClassPlaneta:

                                            break;
                                        case Constants.ClassUserData:

                                            break;
                                    }
                                }
                            }
                        }
                    }
                });
        return retval[0];
    }
}
