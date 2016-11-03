package ucd.app.views.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucd.app.R;
import ucd.app.entities.Complaint;
import ucd.app.rest.ApiClient;
import ucd.app.rest.ApiService;

import static ucd.app.views.activities.MainActivity.*;

public class PhotoFragment extends Fragment {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView addImageButton;
    private ImageView mainPhoto;
    private Button submitComplaint;
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
        imageView1 = (ImageView) rootView.findViewById(R.id.image_view1);
        imageView2 = (ImageView) rootView.findViewById(R.id.image_view2);
        imageView3 = (ImageView) rootView.findViewById(R.id.image_view3);
        mainPhoto = (ImageView) rootView.findViewById(R.id.main_photo);
        addImageButton = (ImageView) rootView.findViewById(R.id.add_image_botton);
        submitComplaint = (Button) rootView.findViewById(R.id.submit_complaint);

        addImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageCapture(v);
            }
        });

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
                                        addImageButton.setEnabled(true);
                                    } else {
                                        mainPhoto.setImageDrawable(null);
                                        submitComplaint.setEnabled(false);
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

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.insertComplaint(latitude.toString(), longitude.toString(), description, loggedUser.getId()).enqueue(new Callback<Complaint>() {

            @Override
            public void onResponse(Call<Complaint> call, Response<Complaint> response) {
                Toast.makeText(rootView.getContext(), "Denúncia realizada com sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Complaint> call, Throwable throwable) {
                Toast.makeText(rootView.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
                    addImageButton.setEnabled(false);
                }
            }
        }

        // Habilita o botão de realizar denuncias.
        submitComplaint.setEnabled(true);
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
        Snackbar bar = Snackbar.make(view, "Clique e segure na foto para excluir.", Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.dialog_neutral, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle user action
                    }
                });
        bar.show();
    }
}