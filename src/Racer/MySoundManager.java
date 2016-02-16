/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Racer;

import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import java.util.ArrayList;

/**
 *
 * @author LK
 */
public class MySoundManager extends SoundManager{
    
    public static MySoundManager getSoundManager(){
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(MySoundManager.RACING_NOISE, Source.RESOURCE, "/Racer/car_sound.wav" ));
        
        Playlist playlist = new Playlist(tracks);
        return new MySoundManager(playlist);
    }

    public MySoundManager(Playlist playlist) {
        super(playlist);
    }
    
    public static final String RACING_NOISE = "RACING";
    public static final String POWER_UP_NOISE = "POWER_UP";
    public static final String MUSIC_NOISE = "MUSIC";
    
    
    
}
