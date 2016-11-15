package ucd.app.views.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.gson.Gson;
import it.sephiroth.android.library.tooltip.Tooltip;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.Complaint;
import ucd.app.entities.Imgur;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;
import ucd.app.rest.ImgurClient;
import ucd.app.rest.ImgurService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ucd.app.views.activities.MainActivity.*;

public class PhotoFragment extends Fragment {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView addImageButton;
    private ImageView mainPhoto;
    private Button submitComplaint;
    private ProgressBar progressBar;
    private View rootView;

    public PhotoFragment() {
        // Required empty public constructor.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_photo, container, false);
        updateScreenOrientation();

        imageView1 = (ImageView) rootView.findViewById(R.id.image_view1);
        imageView2 = (ImageView) rootView.findViewById(R.id.image_view2);
        imageView3 = (ImageView) rootView.findViewById(R.id.image_view3);
        mainPhoto = (ImageView) rootView.findViewById(R.id.main_photo);
        addImageButton = (ImageView) rootView.findViewById(R.id.add_image_botton);
        submitComplaint = (Button) rootView.findViewById(R.id.submit_complaint);
        submitComplaint.setVisibility(View.INVISIBLE);

        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageCapture(v);
            }
        });
        this.progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        this.progressBar.setVisibility(View.INVISIBLE);

        // Caso de um click simples na primeira imagem.
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se existir imagem no primeiro ImageView.
                if (imageView1.getDrawable() != null) {

                    // Pega a imagem do primeiro ImageView e coloca como imagem principal.
                    BitmapDrawable drawable = (BitmapDrawable) imageView1.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                }
            }
        });

        // Caso de um click simples na segunda imagem.
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView2.getDrawable() != null) {

                    // Pega a imagem do segundo ImageView e coloca como imagem principal.
                    BitmapDrawable drawable = (BitmapDrawable) imageView2.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                }
            }
        });

        // Caso de um click simples na terceira imagem.
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView3.getDrawable() != null) {

                    // Pega a imagem do terceiro ImageView e coloca como imagem principal.
                    BitmapDrawable drawable = (BitmapDrawable) imageView3.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    mainPhoto.setImageBitmap(bitmap);
                }
            }
        });

        // Caso de um click longo na primeira imagem.
        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Se existir imagem no primeiro ImageView.
                if (imageView1.getDrawable() != null) {
                    new AlertDialog.Builder(imageView1.getContext())
                            .setTitle(R.string.photo_remove_dialog_title)
                            .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    BitmapDrawable drawable;
                                    Bitmap bitmap;

                                    // Limpa o lugar da primeira imagem.
                                    imageView1.setImageDrawable(null);

                                    // Verifica se há uma segunda imagem.
                                    if (imageView2.getDrawable() != null) {

                                        // Pega a segunda imagem e coloca no lugar da primeira.
                                        drawable = (BitmapDrawable) imageView2.getDrawable();
                                        bitmap = drawable.getBitmap();
                                        imageView1.setImageBitmap(bitmap);

                                        // Limpa o lugar da segunda imagem.
                                        imageView2.setImageDrawable(null);

                                        // Verifica se há uma terceira imagem.
                                        if (imageView3.getDrawable() != null) {

                                            // Pega a terceira imagem e coloca no lugar da segunda.
                                            drawable = (BitmapDrawable) imageView3.getDrawable();
                                            bitmap = drawable.getBitmap();
                                            imageView2.setImageBitmap(bitmap);

                                            // Limpa o lugar da terceira imagem.
                                            imageView3.setImageDrawable(null);
                                        }
                                    }

                                    if (imageView1.getDrawable() != null) {

                                        // Coloca a primeira imagem na foto principal.
                                        drawable = (BitmapDrawable) imageView1.getDrawable();
                                        bitmap = drawable.getBitmap();
                                        mainPhoto.setImageBitmap(bitmap);
                                        addImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_box_black_48dp));
                                        addImageButton.setEnabled(true);
                                    } else {
                                        drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_tab_photo_light);
                                        bitmap = drawable.getBitmap();
                                        mainPhoto.setImageBitmap(bitmap);
                                        submitComplaint.setVisibility(View.INVISIBLE);
                                    }
                                }
                            })
                            .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                }
                return true;
            }
        });

        // Caso de um click longo na segunda imagem.
        imageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Se existir imagem no segundo ImageView.
                if (imageView2.getDrawable() != null) {
                    new AlertDialog.Builder(imageView2.getContext())
                            .setTitle(R.string.photo_remove_dialog_title)
                            .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    BitmapDrawable drawable;
                                    Bitmap bitmap;

                                    imageView2.setImageDrawable(null);

                                    // Verifica se há uma terceira imagem.
                                    if (imageView3.getDrawable() != null) {
                                        //pega a terceira imagem e coloca no lugar da segunda;
                                        drawable = (BitmapDrawable) imageView3.getDrawable();
                                        bitmap = drawable.getBitmap();
                                        imageView2.setImageBitmap(bitmap);
                                        imageView3.setImageDrawable(null);
                                    }

                                    // Coloca a primeira imagem na foto principal.
                                    drawable = (BitmapDrawable) imageView1.getDrawable();
                                    bitmap = drawable.getBitmap();
                                    mainPhoto.setImageBitmap(bitmap);
                                    addImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_box_black_48dp));
                                    addImageButton.setEnabled(true);


                                }

                            })
                            .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
                return true;
            }
        });

        // Caso de um click longo na terceira imagem.
        imageView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // Se existir imagem no segundo ImageView.
                if (imageView3.getDrawable() != null) {
                    new AlertDialog.Builder(imageView3.getContext())
                            .setTitle(R.string.photo_remove_dialog_title)
                            .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {

                                //botão sim;
                                public void onClick(DialogInterface dialog, int which) {

                                    BitmapDrawable drawable;
                                    Bitmap bitmap;

                                    // Coloca a primeira imagem na foto principal.
                                    drawable = (BitmapDrawable) imageView1.getDrawable();
                                    bitmap = drawable.getBitmap();
                                    mainPhoto.setImageBitmap(bitmap);

                                    // Exclui a terceira imagem.
                                    imageView3.setImageDrawable(null);
                                    addImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_box_black_48dp));
                                    addImageButton.setEnabled(true);
                                }

                            })
                            .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
                return true;
            }
        });

        submitComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView textview_latitude = new TextView(v.getContext());
                final TextView textview_longitude = new TextView(v.getContext());

                if (location != null) {
                    textview_latitude.setText(latitude.toString());
                    textview_longitude.setText(longitude.toString());
                }

                final EditText input = new EditText(v.getContext());

                // Define que ele receberá uma entrada do tipo text, o hint(dica) e limita o tamanho máximo.
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setHint(R.string.hint_obs);
                input.setMaxWidth(100);

                new AlertDialog.Builder(submitComplaint.getContext())
                        .setTitle(R.string.photo_complaint_dialog_title)
                        .setView(textview_latitude)
                        .setView(textview_longitude)
                        .setView(input)
                        .setPositiveButton(R.string.dialog_button_sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createComplaint(input.getText().toString());
                            }
                        })
                        .setNegativeButton(R.string.dialog_button_nao, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });

        return rootView;
    }

    private void createComplaint(String description) {

        if (location == null) {
            Toast.makeText(rootView.getContext(), "Localização desconhecida!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (loggedUser == null) {
            Toast.makeText(rootView.getContext(), "Usuário desconhecido!", Toast.LENGTH_SHORT).show();
            return;
        }

        postImages(description);
    }

    private void postImages(final String description) {
        final ArrayList<String> photoPaths = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);

        // POST IMAGE ONE.
        String photoBase64 = makePhotoBase64((BitmapDrawable) imageView1.getDrawable());
        ImgurService imgurService = ImgurClient.getClient().create(ImgurService.class);
        imgurService.postImage(ImgurClient.MY_IMGUR_CLIENT_ID, photoBase64).enqueue(new Callback<Imgur>() {
            @Override
            public void onResponse(Call<Imgur> call, Response<Imgur> response) {
                Imgur imgur = response.body();
                photoPaths.add(imgur.data.link);
                if (imageView2.getDrawable() != null) {

                    // POST IMAGE TWO.
                    String photoBase64 = makePhotoBase64((BitmapDrawable) imageView2.getDrawable());
                    ImgurService imgurService = ImgurClient.getClient().create(ImgurService.class);
                    imgurService.postImage(ImgurClient.MY_IMGUR_CLIENT_ID, photoBase64).enqueue(new Callback<Imgur>() {
                        @Override
                        public void onResponse(Call<Imgur> call, Response<Imgur> response) {
                            Imgur imgur = response.body();
                            photoPaths.add(imgur.data.link);
                            if (imageView3.getDrawable() != null) {

                                // POST IMAGE THREE.
                                String photoBase64 = makePhotoBase64((BitmapDrawable) imageView3.getDrawable());
                                ImgurService imgurService = ImgurClient.getClient().create(ImgurService.class);
                                imgurService.postImage(ImgurClient.MY_IMGUR_CLIENT_ID, photoBase64).enqueue(new Callback<Imgur>() {
                                    @Override
                                    public void onResponse(Call<Imgur> call, Response<Imgur> response) {
                                        Imgur imgur = response.body();
                                        photoPaths.add(imgur.data.link);
                                        postComplaint(description, photoPaths);
                                    }

                                    @Override
                                    public void onFailure(Call<Imgur> call, Throwable throwable) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(rootView.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                postComplaint(description, photoPaths);
                            }
                        }

                        @Override
                        public void onFailure(Call<Imgur> call, Throwable throwable) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(rootView.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    postComplaint(description, photoPaths);
                }
            }

            @Override
            public void onFailure(Call<Imgur> call, Throwable throwable) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(rootView.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateScreenOrientation() {
        ImageView mainPhoto = (ImageView) rootView.findViewById(R.id.main_photo);
        View cardView = rootView.findViewById(R.id.bar_bottom);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cardView.getLayoutParams();

        if (rootView.getResources().getConfiguration().orientation == 2) {
            mainPhoto.setVisibility(View.INVISIBLE);
            params.addRule(RelativeLayout.BELOW, R.id.bar_top);
        } else {
            mainPhoto.setVisibility(View.VISIBLE);
            params.addRule(RelativeLayout.BELOW, R.id.main_photo);
        }
    }

    private void postComplaint(String description, ArrayList<String> photoPaths) {
        String photos = "";
        for (String photoPath : photoPaths) {
            if (!photos.isEmpty()) {
                photos += ",";
            }
            photos += photoPath;
        }

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.insertComplaint(latitude.toString(), longitude.toString(), description, loggedUser.getId(), photos).enqueue(new Callback<Complaint>() {

            @Override
            public void onResponse(Call<Complaint> call, Response<Complaint> response) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(rootView.getContext(), "Denúncia realizada com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Complaint> call, Throwable throwable) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(rootView.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String makePhotoBase64(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image = stream.toByteArray();
        String photoBase64 = Base64.encodeToString(image, Base64.NO_WRAP);
        photoBase64 = photoBase64.replaceAll("\n", "").replaceAll("\r", "");
        return photoBase64;
    }

    /**
     * Método chamado após tirar uma foto. Ele gerencia qual será a imagem principale a as posições das imagens nos imageViews:
     * Se não existir imagem no primeiro ImageView; Coloca a imagem no primeiro ImageView.
     * Se não existir imagem na segunda ImageView: Coloca a imagem no segundo ImageView.
     * Se não existir imagem na terceira ImageView: Coloca a imagem no terceiro ImageView e desabilita o botao que adiciona fotos.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = (Bitmap) bundle.get("data");

                // Coloca a imagem recém-tirada como imagem principal.
                mainPhoto.setImageBitmap(bitmap);

                // Gerencia a posição das imagens nos imagens views.
                if (imageView1.getDrawable() == null) {
                    imageView1.setImageBitmap(bitmap);
                    showBarMessage(rootView);
                } else if (imageView2.getDrawable() == null) {
                    imageView2.setImageBitmap(bitmap);
                } else if (imageView3.getDrawable() == null) {
                    imageView3.setImageBitmap(bitmap);
                    addImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_box_grey_48dp));
                    addImageButton.setEnabled(false);
                }
            }


            // Habilita o botão de realizar denuncias.
            submitComplaint.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Método que chama a camera apara capturar uma foto.
     *
     * @param view
     */
    public void imageCapture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    /**
     * Mostra menssagem demonstrativa de como excluir as imagens.
     *
     * @param view
     */
    private void showBarMessage(View view) {
        Tooltip.make(rootView.getContext(),
                new Tooltip.Builder(101)
                        .anchor(imageView1, Tooltip.Gravity.RIGHT)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 4000)
                        .activateDelay(500)
                        .showDelay(700)
                        .text("Clique e segure na foto para excluir.")
                        .maxWidth(600)
                        .withArrow(true)
                        .withOverlay(true)
                        .floatingAnimation(Tooltip.AnimationBuilder.SLOW)
                        .build()
        ).show();
    }
}