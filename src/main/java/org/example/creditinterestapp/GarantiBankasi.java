package org.example.creditinterestapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GarantiBankasi extends Banks {

    public GarantiBankasi() {
        this.bankName = "Garanti Bankasi";
        this.creditTypeNumber = 3;

        this.bankLogo = new ImageView(
                new Image(
                        getClass().getResourceAsStream("gb.png")
                ));
        this.creditType1Interest = 4.66;
        this.creditType2Interest = 3.07;
        this.creditType3Interest = 4.55;
    }

    public String getBankBame() {
        return this.bankName;

    }

}
