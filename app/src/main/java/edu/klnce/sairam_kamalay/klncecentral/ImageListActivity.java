package edu.klnce.sairam_kamalay.klncecentral;

         import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

         public class ImageListActivity extends AppCompatActivity {
             private DatabaseReference mDatabaseRef;
             private List<imageUpload> imgList;
             private ListView lv;
             private ImageListAdapter adapter;
             private ProgressDialog progressDialog;
             private FirebaseAuth fAuth = FirebaseAuth.getInstance();


             @Override
             protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_image_list);
                 imgList = new ArrayList<>();
                 lv = (ListView) findViewById(R.id.listViewImage);
                 progressDialog = new ProgressDialog(this);
                 progressDialog.setMessage("please wait loading News...");
                 progressDialog.show();
                 mDatabaseRef = FirebaseDatabase.getInstance().getReference(MainActivity.FB_DATABASE_PATH);
                 mDatabaseRef.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         progressDialog.dismiss();


                         for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                             imageUpload img = snapshot.getValue(imageUpload.class);
                             imgList.add(img);
                         }
                         adapter = new ImageListAdapter(ImageListActivity.this, R.layout.image_item, imgList);
                         lv.setAdapter((ListAdapter) adapter);
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {
                         progressDialog.dismiss();

                     }
                 });
             }
         }