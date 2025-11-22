package pl.mg.javatst.pattern.adapter;

/**
 * Adapter to most pomiędzy dwoma niekompatybilnymi
 * interfejsami.
 * Wzorzec strukturalny.
 *
 *
 */
public class AdapterTest {

    public static void main(String[] args) {

        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "file");
        player.play("vlc", "file");
        player.play("mp4", "file");
        player.play("mpdasdas3", "file");
    }
}
