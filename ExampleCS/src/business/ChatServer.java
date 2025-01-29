package business;

import controller.ServerController;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class ChatServer {
    private static final int PORT = 126;
    private ServerController controller;
    private String inputMessage;
    private HashMap<String, String[]> responseMap;
    private Random random;
    private ServerSocket serverSocket;
    private boolean running = true; // Bandera para controlar el servidor

    public ChatServer(ServerController controller) {
        this.controller = controller;
        this.random = new Random();
        initializeResponses();
    }

    private void initializeResponses() {
        responseMap = new HashMap<>();

        responseMap.put("hola", new String[]{
                "¡Hola! ¿Cómo estás?", 
                "Hola, ¿cómo te va?", 
                "¡Hola! ¿En qué puedo ayudarte?", 
                "Hola, ¿qué tal?", 
                "¡Buenas! 😊"
        });

        responseMap.put("¿cómo estás?", new String[]{
                "Estoy bien, gracias por preguntar.", 
                "¡Muy bien! ¿Y tú?", 
                "Estoy funcionando al 100% 😊", 
                "¡Todo genial por aquí! ¿Y contigo?", 
                "No puedo quejarme, ¿cómo estás tú?"
        });

        responseMap.put("¿qué puedes hacer?", new String[]{
                "Puedo responder preguntas y conversar contigo.", 
                "Soy un chatbot simple, pero aprendo rápido.", 
                "Puedo ayudarte a resolver dudas o simplemente charlar.", 
                "Respondo preguntas y aprendo de cada conversación.", 
                "Estoy aquí para ayudarte con lo que necesites. 😊"
        });

        responseMap.put("adios", new String[]{
                "Hasta luego, que tengas un buen día.", 
                "¡Adiós! Vuelve pronto.", 
                "Nos vemos, cuídate.", 
                "¡Adiós! Espero verte de nuevo pronto.", 
                "Hasta pronto, fue un gusto hablar contigo."
        });

        responseMap.put("¿qué tal tu día?", new String[]{
                "¡Genial! Gracias por preguntar. 😊", 
                "Un día tranquilo, ¿y el tuyo?", 
                "Mi día va bien, ¿qué tal el tuyo?", 
                "Todo está bajo control por aquí. ¿Qué tal tú?", 
                "No me puedo quejar, gracias. 😊"
        });

        responseMap.put("gracias", new String[]{
                "¡De nada! 😊", 
                "Para eso estoy, no te preocupes.", 
                "¡Un placer ayudarte!", 
                "No tienes por qué darme las gracias.", 
                "¡Con gusto! ¿Algo más en lo que te pueda ayudar?"
        });

        responseMap.put("¿cuál es tu nombre?", new String[]{
                "Me llamo ChatServer, ¿y tú?", 
                "Soy un simple servidor de chat. 😊", 
                "Puedes llamarme ChatBot.", 
                "Mi nombre es ChatServer, pero también me gusta que me llamen amigo. 😄"
        });

        responseMap.put("¿cuál es tu propósito?", new String[]{
                "Estoy aquí para conversar contigo.", 
                "Mi propósito es ayudarte y entretenerte.", 
                "Soy un chatbot, así que mi objetivo es facilitar tu vida.", 
                "Estoy aquí para hacerte compañía o responder tus preguntas."
        });

        responseMap.put("¿dónde estás?", new String[]{
                "Estoy aquí mismo, en tu servidor. 😊", 
                "Vivo en el mundo digital, cerca de ti.", 
                "Estoy en tu computadora o en la nube, ¡listo para ayudarte!", 
                "Siempre estoy cerca para echarte una mano."
        });

        responseMap.put("¿te gusta hablar conmigo?", new String[]{
                "¡Por supuesto! Es lo que más me gusta hacer. 😊", 
                "Sí, disfruto mucho nuestras charlas.", 
                "Claro que sí, me hace feliz conversar contigo.", 
                "Hablar contigo es lo mejor de mi día."
        });

        responseMap.put("¿cómo aprendes?", new String[]{
                "Aprendo de cada conversación que tengo.", 
                "Mi conocimiento se basa en lo que programan en mí.", 
                "No dejo de aprender, cada día me vuelvo más útil.", 
                "Estoy en constante aprendizaje gracias a mis desarrolladores."
        });

        responseMap.put("¿cuál es el sentido de la vida?", new String[]{
                "42. 😉", 
                "Creo que eso depende de cada persona.", 
                "El sentido de la vida es disfrutarla al máximo.", 
                "Quizás el sentido de la vida sea aprender y crecer."
        });

        responseMap.put("cuéntame un chiste", new String[]{
                "¿Qué hace una abeja en el gimnasio? ¡Zum-ba!", 
                "¿Por qué no hay hospitales en los videojuegos? Porque nadie quiere llamar al médico.", 
                "¿Cómo se despiden los químicos? ¡Ácido un placer!", 
                "¿Qué hace una vaca con los ojos cerrados? ¡Leche concentrada! 😂"
        });

        responseMap.put("¿qué opinas del clima?", new String[]{
                "El clima siempre es un buen tema de conversación. 😄", 
                "No siento el clima, pero espero que esté agradable para ti.", 
                "Espero que haga un buen día donde estás.", 
                "El clima no me afecta, pero dime, ¿qué tal está por allá?"
        });

        responseMap.put("me siento triste", new String[]{
                "Lo siento mucho, ¿hay algo en lo que pueda ayudarte?", 
                "Recuerda que después de la tormenta, siempre viene la calma. 😊", 
                "Estoy aquí para ti, cuéntame más si quieres.", 
                "No estás solo, siempre hay alguien dispuesto a escuchar.", 
                "Es normal sentirse así a veces. Tómate tu tiempo, todo mejora."
        });

        responseMap.put("me siento feliz", new String[]{
                "¡Me alegra mucho escuchar eso! 😊", 
                "¡Genial! La felicidad es contagiosa.", 
                "Es maravilloso que estés feliz, ¡sigue así!", 
                "Eso me pone feliz a mí también. 😄"
        });
    }


    public void startServer() {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(PORT)) {
                this.serverSocket = server;
                controller.addMessage("Servidor iniciado en el puerto " + PORT);

                while (running) {
                    try {
                        Socket clientSocket = server.accept();
                        controller.addMessage("Cliente conectado: " + clientSocket.getInetAddress());
                        new Thread(() -> handleClient(clientSocket)).start();
                    } catch (Exception e) {
                        if (!running) {
                            controller.addMessage("Servidor detenido.");
                        } else {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopServer() {
        running = false; // Detiene el bucle principal
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Cierra el ServerSocket
            }
            controller.addMessage("Servidor detenido correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            while ((inputMessage = in.readLine()) != null) {
                String response = getResponse(inputMessage.toLowerCase());

                Platform.runLater(() -> controller.addMessage("Cliente: " + inputMessage));
                out.println(response);

                if (inputMessage.equalsIgnoreCase("adios")) {
                    clientSocket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getResponse(String input) {
        if (responseMap.containsKey(input)) {
            String[] possibleResponses = responseMap.get(input);
            return "Servidor: " + possibleResponses[random.nextInt(possibleResponses.length)];
        } else {
            return "Servidor: No entiendo bien tu pregunta, ¿puedes reformularla?";
        }
    }
}
