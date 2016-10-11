package ucd.app.views.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import ucd.app.R;

public class PhotoFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView addImageButton;
    ImageView mainPhoto;
    Button submitComplaint;
    View view;


    private GoogleApiClient mGoogleApiClient;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_photo, container, false);

        //pega os 3 campos ImageView, o campo da imagem principal, o botão de adicionar e o botão de realizar a denuncia;
        imageView1 = (ImageView) view.findViewById(R.id.image_view1);
        imageView2 = (ImageView) view.findViewById(R.id.image_view2);
        imageView3 = (ImageView) view.findViewById(R.id.image_view3);
        mainPhoto = (ImageView) view.findViewById(R.id.main_photo);
        addImageButton = (ImageView) view.findViewById(R.id.add_image_botton);
        submitComplaint = (Button) view.findViewById(R.id.submit_complaint);

        //coloca um Listner no botão de adicionar a foto;
        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageCapture(v);
            }
        });

        submitComplaint.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });


        //Caso de um click simples na primeira imagem;
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se existir imagem no primeiro ImageView;
                if (imageView1.getDrawable() != null) {
                    //pega a imagem do primeiro ImageView e coloca como imagem principal;
                    BitmapDrawable drawable = (BitmapDrawable) imageView1.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                }
            }
        });

        //Caso de um click simples na segunda imagem;
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView2.getDrawable() != null) {
                    //pega a imagem do segundo ImageView e coloca como imagem principal;
                    BitmapDrawable drawable = (BitmapDrawable) imageView2.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                }
            }
        });

        //Caso de um click simples na terceira imagem;
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView3.getDrawable() != null) {
                    //pega a imagem do terceiro ImageView e coloca como imagem principal;
                    BitmapDrawable drawable = (BitmapDrawable) imageView3.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                }
            }
        });

        //Caso de um click longo na primeira imagem;
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //se existir imagem no primeiro ImageView;
                if (imageView1.getDrawable() != null) {

                    BitmapDrawable drawable;
                    Bitmap bitmap;

                    //limpa o lugar da primeira imagem;
                    imageView1.setImageDrawable(null);

                    //verifica se há uma segunda imagem;
                    if (imageView2.getDrawable() != null) {

                        //pega a segunda imagem e coloca no lugar da primeira;
                        drawable = (BitmapDrawable) imageView2.getDrawable();
                        bitmap = drawable.getBitmap();
                        imageView1.setImageBitmap(bitmap);

                        //limpa o lugar da segunda imagem;
                        imageView2.setImageDrawable(null);

                        //verifica se há uma terceira imagem;
                        if (imageView3.getDrawable() != null) {

                            //pega a terceira imagem e coloca no lugar da segunda;
                            drawable = (BitmapDrawable) imageView3.getDrawable();
                            bitmap = drawable.getBitmap();
                            imageView2.setImageBitmap(bitmap);

                            //limpa o lugar da terceira imagem;
                            imageView3.setImageDrawable(null);
                        }
                    }

                    if(imageView1.getDrawable() != null){
                        //coloca a primeira imagem na foto principal;
                        drawable = (BitmapDrawable) imageView1.getDrawable();
                        bitmap = drawable.getBitmap();
                        mainPhoto.setImageBitmap(bitmap);
                        addImageButton.setEnabled(true);
                    } else {
                        mainPhoto.setImageDrawable(null);
                    }
                }
                return true;
            }
        });

        //Caso de um click longo na segunda imagem;
        imageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //se existir imagem no segundo ImageView;
                if (imageView2.getDrawable() != null) {

                    BitmapDrawable drawable;
                    Bitmap bitmap;

                    imageView2.setImageDrawable(null);

                    //verifica se há uma terceira imagem;
                    if (imageView3.getDrawable() != null) {
                        //pega a terceira imagem e coloca no lugar da segunda;
                        drawable = (BitmapDrawable) imageView3.getDrawable();
                        bitmap = drawable.getBitmap();
                        imageView2.setImageBitmap(bitmap);
                        imageView3.setImageDrawable(null);
                    }

                    //coloca a primeira imagem na foto principal;
                    drawable = (BitmapDrawable) imageView1.getDrawable();
                    bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                    addImageButton.setEnabled(true);
                }
                return true;
            }
        });

        //Caso de um click longo na terceira imagem;
        imageView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //se existir imagem no segundo ImageView;
                if (imageView3.getDrawable() != null) {
                    BitmapDrawable drawable;
                    Bitmap bitmap;

                    //coloca a primeira imagem na foto principal;
                    drawable = (BitmapDrawable) imageView1.getDrawable();
                    bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);

                    //exclui a terceira imagem;
                    imageView3.setImageDrawable(null);
                    addImageButton.setEnabled(true);
                }
                return true;
            }
        });

        //chama a função para pegar as coordenadas do GPS;
        callConnection();

        return view;
    }

    public void imageCapture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");

                //coloca a imagem recém-tirada como imagem principal;
                mainPhoto.setImageBitmap(bitmap);

                //entra no if se não existir imagem no primeiro ImageView;
                if (imageView1.getDrawable() == null) {
                    //coloca a imagem no primeiro ImageView;
                    imageView1.setImageBitmap(bitmap);
                } else {
                    //entra no if se não existir imagem na segunda ImageView;
                    if (imageView2.getDrawable() == null) {
                        //coloca a imagem no segundo ImageView;
                        imageView2.setImageBitmap(bitmap);
                    } else {
                        //entra no if se não existir imagem na terceira ImageView;
                        if (imageView3.getDrawable() == null) {
                            //coloca a imagem no terceiro ImageView;
                            imageView3.setImageBitmap(bitmap);
                            //desabilita o botao que adiciona fotos;
                            addImageButton.setEnabled(false);
                        }
                    }
                }

                view.findViewById(R.id.obs).setEnabled(true);
                submitComplaint.setEnabled(true);
            }
        }
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    //LISTENER GPS
    @Override
    public void onConnected(Bundle bundle) {


        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        //entra se conseguir um sinal de GPS
        if (l != null) {

            Double la = l.getLatitude();
            Double lo = l.getLongitude();

        } else {
            //mostra uma mensagem de GPS desligado;
            Toast.makeText(PhotoFragment.this.getContext(), "GPS desligado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed(" + connectionResult + ")");
    }
}