package org.example.creditinterestapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IsBankasi extends Banks {

    public IsBankasi() {
        this.bankName = "Is Bankasi";

        this.creditTypeNumber = 3;

        this.bankLogo = new ImageView(
                new Image(
                        getClass().getResourceAsStream("is.png")
                ));
        this.creditType1Interest = 4.55;
        this.creditType2Interest = 3.25;
        this.creditType3Interest = 4.99;


    }

    public String getBankBame() {
        return this.bankName;

    }

}
