/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Cliente;
import objects.ColaMensajes;
import objects.Organizador;

/**
 *
 * @author House
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    private static ServerSocket server;
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.iniciar();
    }
    public void iniciar(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        ColaMensajes cola = new ColaMensajes();
        Organizador organizador = new Organizador(clientes, cola);
        try {
            server = new ServerSocket(1234);
            organizador.start();
            while(true){
                System.out.println("pasando1");
                Socket socket = server.accept();
                Cliente cliente = new Cliente(socket, cola);
                clientes.add(cliente);
                cliente.start();
                        System.out.println("pasando2");
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
