package InterfacesInTheCallbackEngine;

public class IventsApp {

    public static void main(String[] args) {
        
        Button button = new Button(new ButtonClickHandler());

        button.click();
        button.click();
        button.click();
    }
}

interface Eventhandler {

    void execute();
}

class ButtonClickHandler implements Eventhandler {

    public void execute() {

        System.out.println("Кнопка нажата");
    }
}


class Button {
    
    Eventhandler handler;
    
    Button(Eventhandler action) {

        this.handler = action;
    }

    public void click() {

        handler.execute();
    }
}