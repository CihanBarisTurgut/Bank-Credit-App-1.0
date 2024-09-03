package org.example.creditinterestapp;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;
import org.w3c.dom.Text;

import javax.xml.stream.EventFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.EventListener;


public class AppGui extends Application {

    private static final String TRY_UNIT = "TL";
    private static final String COST_UNIT = "%";
    private static final int MONTHLY_PAYMENT = 12;
    private static final int APP_WIDTH = 510;
    private static final int APP_HEIGHT = 575;
    private static final DecimalFormat df = new DecimalFormat("#0.00");
    private Button calculate;

    private ComboBox<String> bank, creditType;
    private static TextArea creditAmountText;
    private TextArea creditInterestRateText;
    private TextArea creditTermText;
    private ImageView bankLogo, ziraatBankasi, garantiBankasi, isBankasi;
    private Label creditInterestRateLabel, paymentLabel, paymentOutput, costCalculationLabel, costCalculationOutput, amountPayableLabel, amountPayableOutput, bankLabel, creditTypeLabel, InterestRateOutput, InterestRateLabel, creditAmountLabel, creditTermLabel, creditInteresRateLabel;
    private VBox box;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = createScene();
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Credit Interest Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private Scene createScene() {
        // create a scene and text for app name
        box = new VBox();
        box.getStyleClass().addAll("body");
        creditInterestRateLabel = new Label("Credit Calculator");
        creditInterestRateLabel.getStyleClass().addAll("credit-interest");
        creditInterestRateLabel.setMaxWidth(Double.MAX_VALUE);
        creditInterestRateLabel.setAlignment(Pos.CENTER);
        box.getChildren().addAll(creditInterestRateLabel);

        // create a gridpane for three column logo, bank and creditType
        GridPane types = createTypesComponents();
        types.setAlignment(Pos.CENTER);
        GridPane inputs = createInputComponents();
        inputs.setAlignment(Pos.CENTER);
        // set vertical margin between inputs and types GridPanes
        box.setMargin(inputs, new Insets(30, 0, 0, 0));
        inputs.setAlignment(Pos.CENTER);
        GridPane outputs = createOutputComponents();
        outputs.setAlignment(Pos.CENTER);
        box.setMargin(outputs, new Insets(30, 0, 0, 0));

        box.getChildren().addAll(types);
        box.getChildren().addAll(inputs);
        box.getChildren().addAll(outputs);


        return new Scene(box, APP_WIDTH, APP_HEIGHT);


    }


    private GridPane createOutputComponents() {
        // add credit amount, credit term and credit interest rate
        GridPane gridPane = new GridPane();
        gridPane.setHgap(60);
        gridPane.setPadding(new Insets(10, 30, 0, 20));

        paymentLabel = new Label("Monthly Payment");
        paymentLabel.getStyleClass().addAll("ziraatbankasi-label");

        paymentOutput = new Label("");
        paymentOutput.getStyleClass().addAll("ziraatbankasi-label");

        costCalculationLabel = new Label("Cost Rate");
        costCalculationLabel.getStyleClass().addAll("ziraatbankasi-label");

        InterestRateLabel = new Label("Interest Payable");
        InterestRateLabel.getStyleClass().addAll("ziraatbankasi-label");

        InterestRateOutput = new Label("");
        InterestRateOutput.getStyleClass().addAll("ziraatbankasi-label");

        costCalculationOutput = new Label("");
        costCalculationOutput.getStyleClass().addAll("ziraatbankasi-label");

        amountPayableLabel = new Label("Amount Payable");
        amountPayableLabel.getStyleClass().addAll("ziraatbankasi-label");

        amountPayableOutput = new Label("");
        amountPayableOutput.getStyleClass().addAll("ziraatbankasi-label");

        // even change the size of app, objects are in center
        GridPane.setHalignment(paymentLabel, HPos.CENTER);
        GridPane.setHalignment(paymentOutput, HPos.CENTER);
        GridPane.setHalignment(costCalculationLabel, HPos.CENTER);
        GridPane.setHalignment(costCalculationOutput, HPos.CENTER);
        GridPane.setHalignment(InterestRateLabel, HPos.CENTER);
        GridPane.setHalignment(InterestRateOutput, HPos.CENTER);
        GridPane.setHalignment(amountPayableLabel, HPos.CENTER);
        GridPane.setHalignment(amountPayableOutput, HPos.CENTER);
        // margin between invoked label and above
        gridPane.setMargin(InterestRateLabel, new Insets(30, 0, 0, 0));

        // add variables to gridPane
        gridPane.add(paymentLabel, 0, 0);
        gridPane.add(paymentOutput, 0, 1);
        gridPane.add(costCalculationLabel, 1, 0);
        gridPane.add(costCalculationOutput, 1, 1);
        gridPane.add(amountPayableLabel, 2, 0);
        gridPane.add(amountPayableOutput, 2, 1);
        gridPane.add(InterestRateLabel, 1, 2);
        gridPane.add(InterestRateOutput, 1, 3);


        return gridPane;
    }

    private GridPane createInputComponents() {
        // add credit amount, credit term and credit interest rate
        GridPane gridPane = new GridPane();
        gridPane.setHgap(60);
        gridPane.setPadding(new Insets(10, 30, 0, 20));

        creditAmountLabel = new Label("Credit Amount/TRY");
        creditAmountLabel.getStyleClass().addAll("ziraatbankasi-label");

        creditAmountText = new TextArea();
        creditAmountText.getStyleClass().addAll("ziraatbankasi-inputs-label");
        creditAmountText.setPrefSize(1, 1);

        creditTermLabel = new Label("Credit Term/M");
        creditTermLabel.getStyleClass().addAll("ziraatbankasi-label");

        creditTermText = new TextArea();
        creditTermText.getStyleClass().addAll("ziraatbankasi-inputs-label");
        creditTermText.setPrefSize(1, 1);

        creditInteresRateLabel = new Label("Interest Rate/%");
        creditInteresRateLabel.getStyleClass().addAll("ziraatbankasi-label");

        creditInterestRateText = new TextArea();
        creditInterestRateText.getStyleClass().addAll("ziraatbankasi-inputs-label");
        creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(0)));

        creditInterestRateText.setPrefSize(1, 1);

        // create a calculate button
        calculate = new Button("Calculate");
        // calculate button on run
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double calculatedAmount = 0;
                double calculatedCost = 0;
                double calculatedInterestAmount = 0;
                // get calculatedAmount from backend class Banks
                calculatedAmount = Banks.calculatePayableAmount(Double.parseDouble(creditAmountText.getText()), Double.parseDouble(creditTermText.getText()), Double.parseDouble(creditInterestRateText.getText()));
                amountPayableOutput.setText(String.valueOf(df.format((calculatedAmount))) + " " + TRY_UNIT);

                // get calculatedCostRate from backend class Banks
                calculatedCost = Banks.calculateCost(Double.parseDouble(creditAmountText.getText()), Double.parseDouble(creditTermText.getText()), Double.parseDouble(creditInterestRateText.getText()));
                costCalculationOutput.setText(String.valueOf(COST_UNIT + df.format(calculatedCost)));

                // calculate monthly payment by using calculatedAmount and MONTHLY_PAYMENT
                paymentOutput.setText(String.valueOf(df.format(calculatedAmount / MONTHLY_PAYMENT) + " " + TRY_UNIT));

                // get calculatedInterestAmount to be paid from backend class Banks
                calculatedInterestAmount = Banks.calculateInterestAmount(Double.parseDouble(creditAmountText.getText()), Double.parseDouble(creditTermText.getText()), Double.parseDouble(creditInterestRateText.getText()));
                InterestRateOutput.setText(String.valueOf(df.format(calculatedInterestAmount) + " " + TRY_UNIT));
            }
        });

        // set css "ziraatbankasi" because first index of combobox is ziraatbankasi
        calculate.getStyleClass().addAll("ziraatbankasi-calculate-btn");

        // set button margin, width, alignment and height size
        gridPane.setMargin(calculate, new Insets(30, 0, 0, 0));
        calculate.getStyleClass().addAll("calculate-btn");
        calculate.setMaxWidth(Double.MAX_VALUE);
        calculate.setPrefSize(1, 35);
        calculate.setAlignment(Pos.CENTER);

        // add variables to gridPane
        gridPane.add(creditAmountText, 0, 1);
        gridPane.add(creditAmountLabel, 0, 0);
        gridPane.add(creditTermText, 1, 1);
        gridPane.add(creditTermLabel, 1, 0);
        gridPane.add(creditInteresRateLabel, 2, 0);
        gridPane.add(creditInterestRateText, 2, 1);
        gridPane.add(calculate, 1, 3);


        return gridPane;


    }

    private GridPane createTypesComponents() {
        // add logo bank and creditType by using GridPane
        GridPane gridPane = new GridPane();


        gridPane.setHgap(44);
        gridPane.setPadding(new Insets(10, 30, 0, 20));

        // initialize logos
        ziraatBankasi = createLogo("Ziraat Bankasi");
        garantiBankasi = createLogo("Garanti Bankasi");
        isBankasi = createLogo("Is Bankasi");

        // create bank and credittype text
        bankLabel = new Label("Bank");
        // because combobox first index is ziraatbankasi
        bankLabel.getStyleClass().addAll("ziraatbankasi-label");
        creditTypeLabel = new Label("Credit Type");
        // because combobox first index is ziraatbankasi
        creditTypeLabel.getStyleClass().addAll("ziraatbankasi-label");

        // add variables to gridPane
        gridPane.add(ziraatBankasi, 0, 0);
        gridPane.add(bankLabel, 1, 0);
        gridPane.add(creditTypeLabel, 2, 0);

        // center labels
        GridPane.setHalignment(ziraatBankasi, HPos.CENTER);
        GridPane.setHalignment(bankLabel, HPos.CENTER);
        GridPane.setHalignment(creditTypeLabel, HPos.CENTER);

        // create combobox for banks
        bank = new ComboBox<>();
        bank.getItems().addAll(
                // get banks from backend Banks class
                Banks.getBankName());
        // set combobox first index of banks first index
        bank.setValue(bank.getItems().get(0));
        // because combobox first index is ziraatbankasi viz default
        bank.getStyleClass().add("ziraatbankasi-combo-box");

        // create combobox for banks

        creditType = new ComboBox<>();
        creditType.getItems().addAll(
                // get banks from backend Banks class
                Banks.getCreditTypeZiraat());
        creditType.setValue(creditType.getItems().get(0));
        // because combobox first index is ziraatbankasi viz default
        creditType.getStyleClass().add("ziraatbankasi-combo-box");

        bank.getSelectionModel().getSelectedItem().charAt(0);

        // change theme according to selected bank, reset logo, input output values
        bank.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                // check whether item is selected and delete all items and add new items
                if (bank.getSelectionModel().getSelectedItem().equals("Ziraat Bankasi")) {

                    // used this method to reset logo and load new
                    gridPane.getChildren().removeAll(isBankasi);
                    gridPane.getChildren().removeAll(garantiBankasi);
                    gridPane.add(ziraatBankasi, 0, 0);

                    // used this method to reset credit types and load credit type of selected bank
                    creditType.getItems().clear();
                    creditType.getItems().addAll(Banks.getCreditTypeZiraat()


                    );

                    // change interest rate according to selected bank and credit type
                    creditType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                        @Override
                        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                            // clear inputs and outputs when changed selected bank or credit type
                            clearIO();
                            if (creditType.getSelectionModel().getSelectedItem().equals("Personal Finance")) {
                                // get interest rate of related bank by using indexes from backend Banks class
                                // used this method to get interest rate from backend and setted to frontend
                                // same methods used the following code blocks of in changelistener function
                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(0)));

                            } else if (creditType.getSelectionModel().getSelectedItem().equals("Housing")) {

                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(1)));

                            } else if (creditType.getSelectionModel().getSelectedItem().equals("Transport")) {

                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(2)));

                            }
                        }
                    });
                } else if (bank.getSelectionModel().getSelectedItem().equals("Garanti Bankasi")) {

                    gridPane.getChildren().removeAll(isBankasi);
                    gridPane.getChildren().removeAll(ziraatBankasi);
                    gridPane.add(garantiBankasi, 0, 0);
                    creditType.getItems().clear();
                    creditType.getItems().addAll(Banks.getCreditTypeGaranti()

                    );
                    creditType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                        @Override

                        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                            clearIO();
                            if (creditType.getSelectionModel().getSelectedItem().equals("Personal Finance")) {
                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestGaranti(0)));

                            } else if (creditType.getSelectionModel().getSelectedItem().equals("Housing")) {

                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestGaranti(1)));

                            } else if (creditType.getSelectionModel().getSelectedItem().equals("Transport")) {

                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestGaranti(2)));

                            }
                        }
                    });
                } else if (bank.getSelectionModel().getSelectedItem().equals("Is Bankasi")) {


                    gridPane.getChildren().removeAll(ziraatBankasi);
                    gridPane.getChildren().removeAll(garantiBankasi);
                    gridPane.add(isBankasi, 0, 0);
                    creditType.getItems().clear();
                    creditType.getItems().addAll(Banks.getCreditTypeIs());

                    creditType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                            clearIO();
                            if (creditType.getSelectionModel().getSelectedItem().equals("Personal Finance")) {
                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestIs(0)));

                            } else if (creditType.getSelectionModel().getSelectedItem().equals("Housing")) {

                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestIs(1)));

                            } else if (creditType.getSelectionModel().getSelectedItem().equals("Transport")) {

                                creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestIs(2)));

                            }
                        }
                    });
                }

                // set as default value first index of creditType combobox
                creditType.setValue(creditType.getItems().get(0));

                // change theme according to selected bank by using changeTheme function
                changeTheme(bank.getSelectionModel().getSelectedItem());
            }

        });

        // use this method second time here because when app run, couldn't set interest rate
        // change interest rate according to selected bank and credit type
        creditType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                // clear inputs and outputs when changed selected bank or credit type
                clearIO();
                if (creditType.getSelectionModel().getSelectedItem().equals("Personal Finance")) {
                    // get interest rate of related bank by using indexes from backend Banks class
                    // used this method to get interest rate from backend and setted to frontend
                    // same methods used the following code blocks of in changelistener function
                    creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(0)));

                } else if (creditType.getSelectionModel().getSelectedItem().equals("Housing")) {

                    creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(1)));

                } else if (creditType.getSelectionModel().getSelectedItem().equals("Transport")) {

                    creditInterestRateText.setText(String.valueOf(Banks.getCreditInterestZiraat(2)));

                }
            }
        });
        // add variables to gridPane

        gridPane.add(bank, 1, 1);
        gridPane.add(creditType, 2, 1);
        return gridPane;

    }

    private void clearTheme(String bankName) {
        // label by label clear function

        InterestRateLabel.getStyleClass().clear();
        InterestRateOutput.getStyleClass().clear();
        amountPayableLabel.getStyleClass().clear();
        creditAmountLabel.getStyleClass().clear();
        creditInteresRateLabel.getStyleClass().clear();
        creditTermLabel.getStyleClass().clear();
        costCalculationLabel.getStyleClass().clear();
        paymentLabel.getStyleClass().clear();
        bankLabel.getStyleClass().clear();
        creditTypeLabel.getStyleClass().clear();
        calculate.getStyleClass().clear();
        amountPayableOutput.getStyleClass().clear();
        paymentOutput.getStyleClass().clear();
        costCalculationOutput.getStyleClass().clear();

        if (bankName.equals("Ziraat Bankasi")) {
            // use this method because after used .getStyle().clear(),  couldn't be set combobox background which wanted in css code
            // delete previously added themes manually
            bank.getStyleClass().removeIf(style -> style.equals("garantibankasi-combo-box"));
            bank.getStyleClass().removeIf(style -> style.equals("isbankasi-combo-box"));
            creditType.getStyleClass().removeIf(style -> style.equals("garantibankasi-combo-box"));
            creditType.getStyleClass().removeIf(style -> style.equals("isbankasi-combo-box"));
        } else if (bankName.equals("Garanti Bankasi")) {
            bank.getStyleClass().removeIf(style -> style.equals("ziraatbankasi-combo-box"));
            bank.getStyleClass().removeIf(style -> style.equals("isbankasi-combo-box"));
            creditType.getStyleClass().removeIf(style -> style.equals("ziraatbankasi-combo-box"));
            creditType.getStyleClass().removeIf(style -> style.equals("isbankasi-combo-box"));

        } else if (bankName.equals("Is Bankasi")) {
            bank.getStyleClass().removeIf(style -> style.equals("garantibankasi-combo-box"));
            bank.getStyleClass().removeIf(style -> style.equals("isbankasi-combo-box"));
            creditType.getStyleClass().removeIf(style -> style.equals("garantibankasi-combo-box"));
            creditType.getStyleClass().removeIf(style -> style.equals("isbankasi-combo-box"));
        }
    }

    private void setTheme(String bankName) {
        // set theme using a parameter and css file elements

        String bankNameCss = "";
        if (bankName.equals("Ziraat Bankasi")) bankNameCss = "ziraatbankasi";
        else if (bankName.equals("Garanti Bankasi")) bankNameCss = "garantibankasi";
        else if (bankName.equals("Is Bankasi")) {
            bankNameCss = "isbankasi";
        }

        InterestRateLabel.getStyleClass().add(bankNameCss + "-label");
        InterestRateOutput.getStyleClass().add(bankNameCss + "-label");
        amountPayableLabel.getStyleClass().add(bankNameCss + "-label");
        creditAmountLabel.getStyleClass().add(bankNameCss + "-label");
        creditInteresRateLabel.getStyleClass().add(bankNameCss + "-label");
        creditTermLabel.getStyleClass().add(bankNameCss + "-label");
        costCalculationLabel.getStyleClass().add(bankNameCss + "-label");
        paymentLabel.getStyleClass().add(bankNameCss + "-label");
        bankLabel.getStyleClass().add(bankNameCss + "-label");
        creditTypeLabel.getStyleClass().add(bankNameCss + "-label");
        amountPayableOutput.getStyleClass().add(bankNameCss + "-label");
        paymentOutput.getStyleClass().add(bankNameCss + "-label");
        costCalculationOutput.getStyleClass().add(bankNameCss + "-label");

        bank.getStyleClass().add(bankNameCss + "-combo-box");
        creditType.getStyleClass().add(bankNameCss + "-combo-box");
        calculate.getStyleClass().add(bankNameCss + "-calculate-btn");


    }

    private void changeTheme(String bankName) {
        // change theme by combining clearTheme and setTheme functions

        if (bankName.equals("Ziraat Bankasi")) {
            clearTheme(bankName);
            setTheme(bankName);
        } else if (bankName.equals("Garanti Bankasi")) {
            clearTheme(bankName);
            setTheme(bankName);

        } else if (bankName.equals("Is Bankasi")) {
            clearTheme(bankName);
            setTheme(bankName);
        }
    }

    private void clearIO() {
        // clear input and output text areas

        InterestRateOutput.setText("");
        creditTermText.setText("");
        creditAmountText.setText("");
        paymentOutput.setText("");
        costCalculationOutput.setText("");
        amountPayableOutput.setText("");
    }

    private ImageView createLogo(String bankName) {
        // create bank logo and set settings

        ImageView logoLabel = Banks.getBankLogo(bankName);
        logoLabel.getStyleClass().addAll("logo-label");
        logoLabel.setFitHeight(60);
        logoLabel.setFitWidth(50);
        return logoLabel;

    }

    public static void main(String[] args) {
        // launch the app

        launch();
    }

}