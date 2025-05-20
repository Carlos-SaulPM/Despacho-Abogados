module mx.unam.aragon.despachoabogados {
    requires javafx.controls;
    requires javafx.fxml;


    opens mx.unam.aragon.despachoabogados to javafx.fxml;
    exports mx.unam.aragon.despachoabogados;
    exports mx.unam.aragon.despachoabogados.controllers;
    opens mx.unam.aragon.despachoabogados.controllers to javafx.fxml;
}