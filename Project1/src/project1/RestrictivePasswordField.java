/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

/**
 *
 * @author Jeankie
 */
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.PasswordField;


public final class RestrictivePasswordField extends PasswordField {

    private final IntegerProperty maxLength = new SimpleIntegerProperty(this, "maxLength", -1);

    private final StringProperty restrict = new SimpleStringProperty(this, "restrict");

    public RestrictivePasswordField() {

        textProperty().addListener(new ChangeListener<String>() {

            private boolean ignore;

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (ignore || s1 == null)
                    return;
                if (maxLength.get() > -1 && s1.length() > maxLength.get()) {
                    ignore = true;
                    setText(s1.substring(0, maxLength.get()));
                    ignore = false;
                }

                if (restrict.get() != null && !restrict.get().equals("") && !s1.matches(restrict.get() + "*")) {
                    ignore = true;
                    setText(s);
                    ignore = false;
                }
            }
        });
    }

    public final IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    public final int getMaxLength() {
        return maxLength.get();
    }

    public final void setMaxLength(final int maxLength) {
        this.maxLength.set(maxLength);
    }

    public final StringProperty restrictProperty() {
        return restrict;
    }

    public final String getRestrict() {
        return restrict.get();
    }

    public final void setRestrict(final String restrict) {
        this.restrict.set(restrict);
    }
}

