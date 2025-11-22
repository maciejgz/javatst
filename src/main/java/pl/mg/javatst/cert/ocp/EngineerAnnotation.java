package pl.mg.javatst.cert.ocp;

import java.util.Date;

public @interface EngineerAnnotation {

    String id();

    String synopsis();

    String engineer() default "[unassigned]";

    String date();
}
