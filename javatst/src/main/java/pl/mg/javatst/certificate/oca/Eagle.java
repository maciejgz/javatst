package pl.mg.javatst.certificate.oca;

interface Animal {
    public static void roar() {
        System.out.println("roar");
    }
}

class Bird2 implements Animal {
    public Bird2() {
    }

    public Object testMethod() throws Exception {
        System.out.println("bird2");
        return "";
    }
}

class Eagle extends Bird2 {
    public static char sample;
    public String name;

    public Eagle(String name) {
        this.name = name;
    }

    public static void main(String args[]) {


        int x = 1;
        int y = 2;
        int z = x++;
        int a = --y;
        int b = z--;
        b += ++z;

        int w = 0;
        int u = 0;

        int t = w++ + ++u + w++;

        String sam = "dsad";

    }

    public static float parseFloat(String s) {
        float f = 0.0f;
        try {
            f = Float.valueOf(s).floatValue();
            return f;
        } catch (NumberFormatException nfe) {
            f = Float.NaN;
            return f;
        } finally {
            f = 10.0f;
            return f;
        }
    }

    public String testMethod() {
        System.out.println("eagle");
        return "";
    }
}
