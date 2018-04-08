package pl.mg.javatst.pattern.adapter;

public class AudioPlayer implements MediaPlayer {

    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        //built in type
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("MP3 playing");
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("invalid audioType");
        }
    }
}
