package pl.mg.javatst.certificate.oca;

public class BirdWatcher {

    public void watchBird() {
        Bird bird = new Bird();
        //można odwoływać się do elementów protected w tym samym pakiecie
        bird.floatInWater();
    }
}
