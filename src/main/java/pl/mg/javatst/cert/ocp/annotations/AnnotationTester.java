package pl.mg.javatst.cert.ocp.annotations;

public class AnnotationTester {

    public static void main(String[] args) {
        Class<Product> obj = Product.class;
        // accessing annotations using reflection
        if (obj.isAnnotationPresent(BusinessPolicies.class)) {
            BusinessPolicies annotation = obj.getAnnotation(BusinessPolicies.class);
            for (BusinessPolicy businessPolicy : annotation.value()) {
                System.out.println("business policy name: " + businessPolicy.name());
            }
        }
    }
}
