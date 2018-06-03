package pl.mg.javatst.certificate.oca.lambda;

public class CheckIfHoper implements CheckTrait {
    @Override
    public boolean test(Animal a) {
        return a.isCanHop();
    }
}
