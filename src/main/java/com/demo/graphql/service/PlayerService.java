package com.demo.graphql.service;

import com.demo.graphql.model.Player;
import com.demo.graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id){
        return players.stream().filter(player -> player.id()==id).findFirst();
    }

    public Player create(String name, Team team){
        Player addPlayer = new Player(id.incrementAndGet(),name,team);
        players.add(addPlayer);
        return addPlayer;
    }

    public Player deletePlayer(Integer id){
        Player deletePlayer =players.stream().filter(player1 -> player1.id()==id).findFirst().orElseThrow(()-> new IllegalArgumentException());
        players.remove(deletePlayer);
        return deletePlayer;
    }

    public Player updatePlayer(Integer id, String name,Team team){
        Player updatePlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(player2 -> player2.id()==id ).findFirst();
        if(optional.isPresent()){
            Player player=optional.get();
            int index = players.indexOf(player);
            players.set(index,updatePlayer);
        }else throw new IllegalArgumentException();
        return updatePlayer;
    }
    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(),"Ms Dhoni",Team.CSK));
        players.add(new Player(id.incrementAndGet(),"Rohit Sharma",Team.MI));
        players.add(new Player(id.incrementAndGet(),"Shubham Gill",Team.GT));
        players.add(new Player(id.incrementAndGet(),"Virat Kohli",Team.RCB));
        players.add(new Player(id.incrementAndGet(),"Ravindra Jadeja",Team.CSK));
        players.add(new Player(id.incrementAndGet(),"Hardik Pandiya",Team.MI));
        players.add(new Player(id.incrementAndGet(),"Mohhamad Shami",Team.GT));
        players.add(new Player(id.incrementAndGet(),"Glen Maxwell",Team.RCB));

    }
}
