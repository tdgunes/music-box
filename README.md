music-box
=========

Music-Box is a semantic based tool that generates an ontology of your iTunes Library, you can generate playlists from the ontology by writing in its own language. It's developed for CS461 Course in Özyeğin University, Istanbul.

##Usage

There are two operators: `OR` as `|`, `AND` as `&`.

For instance:
```
(( playedBy:U2 | playedBy:Floyd ) & :hasTotalTime:3M ) & :hasPlayCount:>5 )
```
This is equivalent of saying:
- I want songs that are played by U2 or Floyd which's duration is 3 minutes and played over 5 times.

Currently `:hasTotalTime`, `:hasBitRate` and `:hasPlayCount` are not available.  

![MusicBox](https://raw.githubusercontent.com/tdgunes/music-box/master/music-box.png)

##Dependencies
* dd-plist==1.8
* antlr4-runtime=4.2
* jdk==1.7
* pellet==2.3.0

##Bonus

I plotted my big iTunes Library, with using `generate_graphml.sh`, you can find it in `core/` folder.

![Ontology](https://raw.githubusercontent.com/tdgunes/music-box/master/ontology.png)

