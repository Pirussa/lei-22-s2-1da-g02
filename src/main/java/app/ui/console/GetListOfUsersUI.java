package app.ui.console;

import app.controller.LoadCSVController;

public class GetListOfUsersUI implements Runnable{
    LoadCSVController controller = new LoadCSVController();

    public void run(){
        getListOfSNSUsers();
    }

    public void getListOfSNSUsers() {
        LoadCSVController controller = new LoadCSVController();
        if (!controller.getSNSUserList().isEmpty()) {
            for (int i = 0; i < controller.getSNSUserList().size(); i++) {
                System.out.println("\nPosition " + i + ": " + "\n" + controller.getSNSUserList().get(i));
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println("There aren't any registered SNS Users.");
        }
    }
}
