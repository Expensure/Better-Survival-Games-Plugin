package com.Techtician.survivalgames.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import com.Techtician.survivalgames.LobbyManager;
import com.Techtician.survivalgames.SurvivalGames;



public class KeepLobbyLoadedEvent implements Listener{
    
    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e){
        LobbyManager.getInstance();
		if(LobbyManager.lobbychunks.contains(e.getChunk())){
//			e.setCancelled(true)
//			TODO find a alternative way to keep the lobby chunks loaded
			SurvivalGames.debug("[KeepLobbyLoadedEvent] Lobby Chunk unloading");
        }
        //System.out.println("Chunk unloading");
    }

}
