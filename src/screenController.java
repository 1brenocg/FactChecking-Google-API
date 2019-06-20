import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class screenController implements Initializable {

    @FXML
    private JFXTextArea text_area;

    @FXML
    private JFXButton btn_verificar;

    @FXML
    private JFXSlider slider;

    @FXML
    void funcao_verificar(ActionEvent event) throws Exception{
    	Gcloud gcloud = new Gcloud();
    	gcloud = Gcloud.GetTextSentiment(text_area.getText());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		slider.dis desabilitar a opcao de trocar...
	}
    

}