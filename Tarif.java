package Lab_2;

import java.util.*;

abstract class Tarif {

    Double calls;
    Double sms;
    Double internet;
    Double cost;
    int term;

    String getClassName() {
        String str = this.getClass().getName().replaceAll("^.*\\.", "");
        return str;
    }
}

class Space extends Tarif {
    Double calls = 450.0;
    Double sms = 450.0;
    Double internet = Double.POSITIVE_INFINITY;
    Double cost = 450.0;
    int term = 30;
}

class SeaCommunication extends Tarif {
    Double calls = 600.0;
    Double sms = 300.0;
    Double internet = Double.POSITIVE_INFINITY;
    Double cost = 300.0;
    int term = 30;
}

class Resort extends Tarif {
    Double calls = Double.POSITIVE_INFINITY;
    Double sms = Double.POSITIVE_INFINITY;
    Double internet = Double.POSITIVE_INFINITY;
    Double cost = 550.0;
    int term = 20;
}

class Wind extends Tarif {
    Double calls = 3.0;
    Double sms = 1.0;
    Double internet = 20.0;
    Double cost = 300.0;
    int term = 30;
}