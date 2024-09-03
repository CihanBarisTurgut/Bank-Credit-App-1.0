package org.example.creditinterestapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ZiraatBankasi extends Banks {

    public ZiraatBankasi() {
        this.bankName = "Ziraat Bankasi";
        this.creditTypeNumber = 3;
        this.bankLogo = new ImageView(
                new Image(
                        getClass().getResourceAsStream("zb.png")
                ));
        this.creditType1Interest = 4.54;
        this.creditType2Interest = 3.49;
        this.creditType3Interest = 5.29;
    }

    public String getBankBame() {
        return this.bankName;

    }


}
