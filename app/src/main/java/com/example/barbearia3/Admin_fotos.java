package com.example.barbearia3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Admin_fotos extends AppCompatActivity {
    private Button btnChoose, btnUpload, capa2, bt1,prof1,prof2;
    private ImageView imageView;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference mDatabase;
    FirebaseDatabase refe;

    RecyclerView recyclerView;
    ArrayList<ClasseFotos> list;
    AdapterAdmFotos adapterFotos;


    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_fotos);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        capa2 = (Button) findViewById(R.id.btnCapa2);
        prof1 = (Button) findViewById(R.id.prof1);
        prof2 = (Button) findViewById(R.id.prof2);
        bt1 = (Button) findViewById(R.id.bt1);

        recyclerView = (RecyclerView) findViewById(R.id.myrecyclerimg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageView = (ImageView) findViewById(R.id.imgView);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        refe = FirebaseConfig.getDatabase();

        mDatabase =FirebaseDatabase.getInstance().getReference("Users").child("imagens");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                list = new ArrayList<ClasseFotos>();
                for (DataSnapshot s1 : dataSnapshot.getChildren()) {
                    ClasseFotos p = s1.getValue(ClasseFotos.class);
                    list.add(p);
                }
                adapterFotos = new AdapterAdmFotos(Admin_fotos.this, list);
                recyclerView.setLayoutManager(new GridLayoutManager(Admin_fotos.this, 2));
                recyclerView.setAdapter(adapterFotos);
                recyclerView.setNestedScrollingEnabled(false);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        capa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage2();
            }
        });

    prof1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(Admin_fotos.this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageReference.child("prof1.jpg");
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(Admin_fotos.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Admin_fotos.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                            }
                        });
            }else{
                Toast.makeText(Admin_fotos.this, "Selecione uma imagem ", Toast.LENGTH_SHORT).show();

            }
        }
    });
        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    final ProgressDialog progressDialog = new ProgressDialog(Admin_fotos.this);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference ref = storageReference.child("prof2.jpg");
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Admin_fotos.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Admin_fotos.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                            .getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                                }
                            });
                }else{
                    Toast.makeText(Admin_fotos.this, "Selecione uma imagem ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void uploadImage() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(Admin_fotos.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("capa.jpg");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Admin_fotos.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Admin_fotos.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }else{
            Toast.makeText(Admin_fotos.this, "Selecione uma imagem ", Toast.LENGTH_SHORT).show();

        }
    }

    private void uploadImage2() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(Admin_fotos.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("capa2.jpg");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Admin_fotos.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Admin_fotos.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
        else{
            Toast.makeText(Admin_fotos.this, "Selecione uma imagem ", Toast.LENGTH_SHORT).show();

        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Admin_fotos.super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    final String id = UUID.randomUUID().toString();
                    final StorageReference ref = storageReference.child("Imagem" + id);
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String url = uri.toString();
                                            EditText estilo = (EditText) findViewById(R.id.txtestilo);
                                            String estilocabelo = estilo.getText().toString();

                                            Map<String, Object> map = new HashMap<>();

                                            if (url != null) {
                                                map.put("img", url);
                                                map.put("id", id);
                                                map.put("estilo", estilocabelo);
                                                Toast.makeText(Admin_fotos.this, "Enviado", Toast.LENGTH_SHORT).show();

                                                imageView.setImageResource(0);
                                                filePath = null;


                                            }
                                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                                            mDatabase.child("imagens").child(id).setValue(map);

                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Admin_fotos.this, "Failed ", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(Admin_fotos.this, "Selecione uma imagem!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
