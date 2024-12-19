/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author JorgeLPR
 */
public class Main extends Application {
    
    int tipoOrientacion = 1;
    
    @Override
    public void init(){
    
        try{

            tipoOrientacion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de Orientación con el cual desea mostrar los NODOS\n"+
                                               "1- HORIZONTAL\n"+
                                               "2- VERTICAL"));
            switch(tipoOrientacion){
                case 1: 
                    tipoOrientacion=1;
                break;
                
                case 2: 
                    tipoOrientacion=2;
                break;
                
                default:
                    tipoOrientacion=1;
                break;
            }            
            
        }catch(NumberFormatException ex){
            tipoOrientacion=1;
            System.out.println(ex.getMessage());
        }
        
    }
    
    @Override
    public void start(Stage primaryStage) {

        //Creamos nuestro nodo raiz (FlowPane)
        FlowPane root = new FlowPane();
                
        if(tipoOrientacion==1){

            //Creamos 5 botones, los cuales agregaremos a nuestro nodo raiz
            Button btn1 = new Button("Boton # 1");
            Button btn2 = new Button("Boton # 2");
            Button btn3 = new Button("Boton # 3");
            Button btn4 = new Button("Boton # 4");
            Button btn5 = new Button("Boton # 5");            

            //Agregamos los nodos hijos(Botones) al nodo raiz(FlowPane Layout)
            root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);
            
        }else{

            //Creamos un array tipo Button de longitud 15
            Button btn[] = new Button[15];

            //asignamos propiedades al Layout (Orientación y espacio horizontal y vertical)
            root.setOrientation(Orientation.VERTICAL);
            root.setHgap(10);
            root.setVgap(10);
            
            //inicializamos y agregamos al nodo raiz(FlowPane los respectivos Botones)
            for (int i = 0; i < btn.length; i++) {

                btn[i] = new Button("BOTON # "+(i+1));
                root.getChildren().add(btn[i]);

            }
            
        }
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Ejemplo - FlowPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}