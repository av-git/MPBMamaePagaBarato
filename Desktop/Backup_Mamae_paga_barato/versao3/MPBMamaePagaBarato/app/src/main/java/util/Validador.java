package util;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by ddark on 13/11/16.
 */

public class Validador {

    public static boolean validarCamposObrigatorios(View pView, String pMessage) {

        if (pView instanceof EditText) {

            EditText edText = (EditText) pView;
            Editable text = edText.getText();

            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    return true;
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

}
