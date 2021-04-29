package application;

import utils.AskManager;
import utils.Client;
import utils.ClientLauncher;
import utils.Console;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args){
        ClientLauncher clientLauncher = new ClientLauncher();
        clientLauncher.launchClient();

    }
}
