package Curriculum_Design;

public class Center {
    public static void main(String[] args) {
        TOOLS tools = new TOOLS();
        LOGIN login = new LOGIN(tools);
        MENU menu = new MENU(tools);
        ADMINISTRATOR_WINDOW win1 = new ADMINISTRATOR_WINDOW(tools);
        USER_WINDOW win2 = new USER_WINDOW(tools);
        while (true) {
            login.visible(tools);
            menu.visible(tools);
            win1.visible(tools);
            win2.visible(tools);
        }
    }
}