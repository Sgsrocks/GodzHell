import java.io.*;
import clip.region.Region;

public class NPCHandler {

	public static int maxNPCs = 10000;
	public static int maxListedNPCs = 10000;
	public static int maxNPCDrops = 10000;
	public NPC npcs[] = new NPC[maxNPCs];
	public NPCList NpcList[] = new NPCList[maxListedNPCs];
	public NPCDrops NpcDrops[] = new NPCDrops[maxNPCDrops];

	NPCHandler() {
		for(int i = 0; i < maxNPCs; i++) {
			npcs[i] = null;
		}
		for(int i = 0; i < maxListedNPCs; i++) {
			NpcList[i] = null;
		}
		for(int i = 0; i < maxNPCDrops; i++) {
			NpcDrops[i] = null;
		}
		loadNPCList("./Data/CFG/npc.cfg");
		//loadNPCDrops("npcdrops.cfg");
		loadAutoSpawn("./Data/CFG/autospawn.cfg");
	}

    public void handleClipping(int i) {
		NPC npc = npcs[i];
			if(npc.moveX == 1 && npc.moveY == 1) {
					if((Region.getClipping(npc.absX + 1, npc.absY + 1, npc.heightLevel) & 0x12801e0) != 0)  {
							npc.moveX = 0; npc.moveY = 0;
							if((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) == 0)
								npc.moveY = 1;
							else 
								npc.moveX = 1; 				
							}
					} else if(npc.moveX == -1 && npc.moveY == -1) {
						if((Region.getClipping(npc.absX - 1, npc.absY - 1, npc.heightLevel) & 0x128010e) != 0)  {
							npc.moveX = 0; npc.moveY = 0;
							if((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) == 0)
								npc.moveY = -1;
							else
								npc.moveX = -1; 		
					}
					} else if(npc.moveX == 1 && npc.moveY == -1) {
							if((Region.getClipping(npc.absX + 1, npc.absY - 1, npc.heightLevel) & 0x1280183) != 0)  {
							npc.moveX = 0; npc.moveY = 0;
							if((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) == 0)
								npc.moveY = -1;
							else
								npc.moveX = 1; 
							}
					} else if(npc.moveX == -1 && npc.moveY == 1) {
						if((Region.getClipping(npc.absX - 1, npc.absY + 1, npc.heightLevel) & 0x128013) != 0)  {
							npc.moveX = 0; npc.moveY = 0;
							if((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) == 0)
								npc.moveY = 1;
							else
								npc.moveX = -1; 
										}
					} //Checking Diagonal movement. 
					
			if (npc.moveY == -1 ) {
				if ((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) != 0)
                    npc.moveY = 0;
			} else if( npc.moveY == 1) {
				if((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) != 0)
					npc.moveY = 0;
			} //Checking Y movement.
			if(npc.moveX == 1) {
				if((Region.getClipping(npc.absX + 1, npc.absY, npc.heightLevel) & 0x1280180) != 0) 
					npc.moveX = 0;
				} else if(npc.moveX == -1) {
				 if((Region.getClipping(npc.absX - 1, npc.absY, npc.heightLevel) & 0x1280108) != 0)
					npc.moveX = 0;
			} //Checking X movement.
	}
	public void newNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found
                 if(HP <= 0) { // This will cause client crashes if we don't use this :) - xero
                  HP = 3000;
                }
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int)Math.floor((HP / 100));
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 1;
		}
		newNPC.heightLevel = heightLevel;
                newNPC.Respawns = Respawns;
		npcs[slot] = newNPC;
	}

	public void newSummonedNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns, int summonedBy) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
if(npcs[1320].npcType == 1320) {
ItemHandler.addItem(Item2.randomBunny(), npcs[1320].absX, npcs[1320].absY, 1, GetNpcKiller(1320), false);
}
		if(slot == -1) return;		// no free slot found
                 if(HP <= 0) { // This will cause client crashes if we don't use this :) - xero
                  HP = 3000;
                }
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int)Math.floor((HP / 100));
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 10;
		}
		newNPC.heightLevel = heightLevel;
                newNPC.Respawns = Respawns;
                newNPC.followPlayer = summonedBy;
                newNPC.followingPlayer = true;
		npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPCList newNPCList = new NPCList(npcType);
		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}

	/**
	 * Dropping Items!
	 **/

	public boolean rareDrops(int i) {
		return misc.random(NPCDrops.dropRarity.get(npcs[i].npcType)) == 0;
	}

	public void dropItems(int i) {
		// long start = System.currentTimeMillis();
		client c = (client) PlayerHandler.players[GetNpcKiller(i)];
		if (c != null) {
			if (npcs[i].npcType == 912 || npcs[i].npcType == 913
					|| npcs[i].npcType == 914)
				//c.magePoints += 1;
			if (NPCDrops.constantDrops.get(npcs[i].npcType) != null) {
				for (int item : NPCDrops.constantDrops.get(npcs[i].npcType)) {
					server.itemHandler.addItem(item, npcs[i].absX, npcs[i].absY, 1, c.playerId, false);
					// if (c.clanId >= 0)
					// Server.clanChat.handleLootShare(c, item, 1);
				}
			}

			if (NPCDrops.dropRarity.get(npcs[i].npcType) != null) {
				if (rareDrops(i)) {
					int random = misc.random(NPCDrops.rareDrops
							.get(npcs[i].npcType).length - 1);
					server.itemHandler.addItem(NPCDrops.rareDrops.get(npcs[i].npcType)[random][0], npcs[i].absX, npcs[i].absY, NPCDrops.rareDrops.get(npcs[i].npcType)[random][1], c.playerId, false);
					//if (c.clanId >= 0)
						//Server.clanChat
								//.handleLootShare(
										//c,
										//NPCDrops.rareDrops.get(npcs[i].npcType)[random][0],
										//NPCDrops.rareDrops.get(npcs[i].npcType)[random][1]);
				} else {
					int random = misc.random(NPCDrops.normalDrops
							.get(npcs[i].npcType).length - 1);
					server.itemHandler.addItem(NPCDrops.normalDrops.get(npcs[i].npcType)[random][0], npcs[i].absX, npcs[i].absY, NPCDrops.normalDrops.get(npcs[i].npcType)[random][1], c.playerId, false);
					// Server.clanChat.handleLootShare(c,
					// NPCDrops.normalDrops.get(npcs[i].npcType)[random][0],
					// NPCDrops.normalDrops.get(npcs[i].npcType)[random][1]);
				}
			}

		}
		// System.out.println("Took: " + (System.currentTimeMillis() - start));
	}
        /*
	public boolean IsInWorldMap(int coordX, int coordY) {
		for (int i = 0; i < worldmap[0].length; i++) {
			//if (worldmap[0][i] == coordX && worldmap[1][i] == coordY) {
				return true;
			//}
		}
		return false;
	}
	public boolean IsInWorldMap2(int coordX, int coordY) {
		for (int i = 0; i < worldmap2[0].length; i++) {
			if (worldmap2[0][i] == coordX && worldmap2[1][i] == coordY) {
				return true;
			}
		}
		return true;
	}

	public boolean IsInRange(int NPCID, int MoveX, int MoveY) {
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if (NewMoveX <= npcs[NPCID].moverangeX1 && NewMoveX >= npcs[NPCID].moverangeX2 && NewMoveY <= npcs[NPCID].moverangeY1 && NewMoveY >= npcs[NPCID].moverangeY2) {
			if ((npcs[NPCID].walkingType == 1 && IsInWorldMap(NewMoveX, NewMoveY) == true) || (npcs[NPCID].walkingType == 2 && IsInWorldMap2(NewMoveX, NewMoveY) == false)) {
				if (MoveX == MoveY) {
					if ((IsInWorldMap(NewMoveX, npcs[NPCID].absY) == true && IsInWorldMap(npcs[NPCID].absX, NewMoveY) == true) || (IsInWorldMap2(NewMoveX, npcs[NPCID].absY) == false && IsInWorldMap2(npcs[NPCID].absX, NewMoveY) == false)) {
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}*/
    public int GetMove(int Place1,int Place2){ // Thanks to diablo for this! Fixed my npc follow code <3
        if ((Place1 - Place2) == 0)
            return 0;
        else if ((Place1 - Place2) < 0)
            return 1;
        else if ((Place1 - Place2) > 0)
            return -1;
        return 0;
    }
    public void FollowPlayer(int NPCID) {
        int follow = npcs[NPCID].followPlayer;
	int playerX = server.playerHandler.players[follow].absX;
	int playerY = server.playerHandler.players[follow].absY;
        npcs[NPCID].RandomWalk = false;
    if(server.playerHandler.players[follow] != null)
     {
      if(playerY < npcs[NPCID].absY) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY+1);
      }
      else if(playerY > npcs[NPCID].absY) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY-1);
      }
      else if(playerX < npcs[NPCID].absX) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX+1);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
      }
      else if(playerX > npcs[NPCID].absX) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX-1);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
      }
      npcs[NPCID].getNextNPCMovement();
      npcs[NPCID].updateRequired = true;
     }
    }
    public void FollowPlayerCB(int NPCID, int playerID) {
	int playerX = server.playerHandler.players[playerID].absX;
	int playerY = server.playerHandler.players[playerID].absY;
        npcs[NPCID].RandomWalk = false;
npcs[NPCID].faceplayer(playerID);
    if(server.playerHandler.players[playerID] != null)
     {
      if(playerY < npcs[NPCID].absY) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY+1);
      }
      else if(playerY > npcs[NPCID].absY) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY-1);
      }
      else if(playerX < npcs[NPCID].absX) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX+1);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
      }
      else if(playerX > npcs[NPCID].absX) {
      npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX-1);
      npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
      }
      handleClipping(NPCID);
      npcs[NPCID].getNextNPCMovement();
      npcs[NPCID].updateRequired = true;
     }
    }
	public boolean IsInWorldMap(int coordX, int coordY) {
		for (int i = 0; i < worldmap[0].length; i++) {
			//if (worldmap[0][i] == coordX && worldmap[1][i] == coordY) {
				return true;
			//}
		}
		return false;
	}
	public boolean IsInWorldMap2(int coordX, int coordY) {
		for (int i = 0; i < worldmap2[0].length; i++) {
			if (worldmap2[0][i] == coordX && worldmap2[1][i] == coordY) {
				return true;
			}
		}
		return false;
	}

	public boolean IsInRange(int NPCID, int MoveX, int MoveY) {
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if (NewMoveX <= npcs[NPCID].moverangeX1 && NewMoveX >= npcs[NPCID].moverangeX2 && NewMoveY <= npcs[NPCID].moverangeY1 && NewMoveY >= npcs[NPCID].moverangeY2) {
			if ((npcs[NPCID].walkingType == 1 && IsInWorldMap(NewMoveX, NewMoveY) == true) || (npcs[NPCID].walkingType == 2 && IsInWorldMap2(NewMoveX, NewMoveY) == false)) {
				if (MoveX == MoveY) {
					if ((IsInWorldMap(NewMoveX, npcs[NPCID].absY) == true && IsInWorldMap(npcs[NPCID].absX, NewMoveY) == true) || (IsInWorldMap2(NewMoveX, npcs[NPCID].absY) == false && IsInWorldMap2(npcs[NPCID].absX, NewMoveY) == false)) {
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}
        public void PoisonNPC(int NPCID) {
        npcs[NPCID].PoisonClear = 0;
        npcs[NPCID].PoisonDelay = 40;
        }
        public void Poison(int NPCID) {
          if(npcs[NPCID].PoisonDelay <= 1) {
           int hitDiff = 3 + misc.random(15);
           npcs[NPCID].poisondmg = true;
	   server.npcHandler.npcs[NPCID].hitDiff = hitDiff;
	   server.npcHandler.npcs[NPCID].updateRequired = true;
	   server.npcHandler.npcs[NPCID].hitUpdateRequired = true;
           npcs[NPCID].PoisonClear++;
           npcs[NPCID].PoisonDelay = 40;
         }
        }
	public void process() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null) continue;
			npcs[i].clearUpdateFlags();
		}
		

		for(int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}
				if (npcs[i].walkingType == 0) {
				    switch(npcs[i].npcType) {
			        case 1716: // ham
			        /** 
			        * absX and absY of a specific npc you want to change face direction, 
			        * if you want all the npcs of the same type to  face 
			        * the same direction remove it
			        **/
			        if(npcs[i].absX == 3182 && npcs[i].absY == 9631) { 

			        /** 
			         * this turns the npc 
			         * +1 to absX makes the npc face east, 
			         * -1 to absX makes it face west. 
			         * +1 to absY makes it face north and 
			         * -1 to absY makes it face south.
			         **/
			            npcs[i].turnNpc(npcs[i].absX-1, npcs[i].absY); 

			         }
				  break;
				        case 494: // banker
				        /** 
				        * absX and absY of a specific npc you want to change face direction, 
				        * if you want all the npcs of the same type to  face 
				        * the same direction remove it
				        **/
				        if(npcs[i].absX == 3256 && npcs[i].absY == 3418) { 

				        /** 
				         * this turns the npc 
				         * +1 to absX makes the npc face east, 
				         * -1 to absX makes it face west. 
				         * +1 to absY makes it face north and 
				         * -1 to absY makes it face south.
				         **/
				            npcs[i].turnNpc(npcs[i].absX, npcs[i].absY+1); 

				         }
					  break;

				         default: // applies to all npc who are only standing, remove if not needed
					  npcs[i].turnNpc(npcs[i].absX, npcs[i].absY+1); 
					  break;				
				    }
				}
                                Poison(i);
                                npcs[i].PoisonDelay -= 1;
                                if(npcs[i].PoisonClear >= 15)
                                npcs[i].PoisonDelay = 9999999;
				if (npcs[i].IsDead == false) {
					if (npcs[i].npcType == 1268 || npcs[i].npcType == 1266) {
						for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								if (GoodDistance(npcs[i].absX, npcs[i].absY, server.playerHandler.players[j].absX, server.playerHandler.players[j].absY, 2) == true && npcs[i].IsClose == false) {
									npcs[i].actionTimer = 2;
									npcs[i].IsClose = true;
								}
							}
						}

						if (npcs[i].actionTimer == 0 && npcs[i].IsClose == true) {
							for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
								if (server.playerHandler.players[j] != null) {
									server.playerHandler.players[j].RebuildNPCList = true;
								}
							}
                                                        if(npcs[i].Respawns) {
							int old1 = (npcs[i].npcType - 1);
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].moverangeX1;
							int old6 = npcs[i].moverangeY1;
							int old7 = npcs[i].moverangeX2;
							int old8 = npcs[i].moverangeY2;
							int old9 = npcs[i].walkingType;
							int old10 = npcs[i].MaxHP;
							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
                                                        }
						}
					} else if (npcs[i].RandomWalk == true && misc.random2(10) == 1 && npcs[i].moverangeX1 > 0 && npcs[i].moverangeY1 > 0 && npcs[i].moverangeX2 > 0 && npcs[i].moverangeY2 > 0) { //Move NPC
						int MoveX = misc.random(1);
						int MoveY = misc.random(1);
						int Rnd = misc.random2(4);
						if (Rnd == 1) {
							MoveX = -(MoveX);
							MoveY = -(MoveY);
						} else if (Rnd == 2) {
							MoveX = -(MoveX);
						} else if (Rnd == 3) {
							MoveY = -(MoveY);
						}
						if (IsInRange(i, MoveX, MoveY) == true) {
							npcs[i].moveX = MoveX;
							npcs[i].moveY = MoveY;
						}
						npcs[i].updateRequired = true;
					} else if (npcs[i].RandomWalk == false && npcs[i].IsUnderAttack == true) {
                                                if(npcs[i].npcType == 1913 || npcs[i].npcType == 1241 || npcs[i].npcType == 1974 || npcs[i].npcType == 1977 || npcs[i].npcType == 1975 || npcs[i].npcType == 1105 || npcs[i].npcType == 1914 || npcs[i].npcType == 1053 || npcs[i].npcType == 1246 || npcs[i].npcType == 1159 || npcs[i].npcType == 54)
                                                AttackPlayerMage(i);
                                                else 
						AttackPlayer(i);
					} else if (npcs[i].followingPlayer && npcs[i].followPlayer > 0 && server.playerHandler.players[npcs[i].followPlayer] != null) {
                                                if(server.playerHandler.players[npcs[i].followPlayer].AttackingOn > 0) {
                                                int follow = npcs[i].followPlayer;
                                                npcs[i].StartKilling = server.playerHandler.players[follow].AttackingOn;
                                                npcs[i].RandomWalk = true;
                                                npcs[i].IsUnderAttack = true;
                                                if(npcs[i].StartKilling > 0) {
                                                if(npcs[i].npcType == 1913 || npcs[i].npcType == 509 || npcs[i].npcType == 1977 || npcs[i].npcType == 1105 || npcs[i].npcType == 1974 || npcs[i].npcType == 1975 || npcs[i].npcType == 1053 || npcs[i].npcType == 1914 || npcs[i].npcType == 1241 || npcs[i].npcType == 1246 || npcs[i].npcType == 55)
                                                AttackPlayerMage(i);
                                                else 
                                                AttackPlayer(i);
                                                }

                                                }
                                                else {
                                                FollowPlayer(i);
                                                }
					} else if (npcs[i].followingPlayer && npcs[i].followPlayer > 0 && server.playerHandler.players[npcs[i].followPlayer] != null) {
                                                if(server.playerHandler.players[npcs[i].followPlayer].attacknpc > 0) {
                                                int follow = npcs[i].followPlayer;
                                                npcs[i].attacknpc = server.playerHandler.players[follow].attacknpc;
                                                npcs[i].IsUnderAttackNpc = true;
                                                npcs[npcs[i].attacknpc].IsUnderAttackNpc = true;
                                                if(npcs[i].attacknpc > 0) {
                                                if(npcs[i].npcType == 1913 || npcs[i].npcType == 1241 || npcs[i].npcType == 1977 || npcs[i].npcType == 1974 || npcs[i].npcType == 1105 || npcs[i].npcType == 1975 || npcs[i].npcType == 1914 || npcs[i].npcType == 1053 || npcs[i].npcType == 1246)
                                                AttackNPCMage(i);
                                                else
						AttackNPC(i);
                                                }
                                                }
                                                else {
                                                FollowPlayer(i);
                                                }


					}  else if (npcs[i].IsUnderAttackNpc == true) {
						AttackNPC(i);
                                                /*if(misc.random(50) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "I had ya ma last night bitch";
                                                }
                                                if(misc.random(50) == 3) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Haha I own you neeb";
                                                }
                                                if(misc.random(50) == 5) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Cmon then bitch";
                                                }
                                                if(misc.random(50) == 7) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "ARGHH!";
                                                }
                                                if(misc.random(50) == 9) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Owch that hurt!";
                                                }*/
					} /*else if (i == 94) {
                                                npcs[i].attacknpc = 95;
                                                npcs[i].IsUnderAttackNpc = true;
                                                npcs[95].IsUnderAttackNpc = true;
						AttackNPC(i);
					} else if (i == 96) {
                                                npcs[i].attacknpc = 97;
                                                npcs[i].IsUnderAttackNpc = true;
                                                npcs[97].IsUnderAttackNpc = true;
						AttackNPC(i);
					} */
					if (npcs[i].RandomWalk == true) {
						handleClipping(i);
						npcs[i].getNextNPCMovement();
					}
					if (npcs[i].npcType == 81 || npcs[i].npcType == 397 || npcs[i].npcType == 1766 || npcs[i].npcType == 1767 || npcs[i].npcType == 1768) {
						if (misc.random2(50) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Moo";
						}
					}
if (npcs[i].npcType == 541) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "SkillCapes";
}
}
                        if (npcs[i].npcType == 847) {
                        if (misc.random2(10) == 1) {
                            npcs[i].updateRequired = true;
                            npcs[i].textUpdateRequired = true;
                            npcs[i].textUpdate = "Are you brave enough to dare the bounty hunter?";
                        }
                        }
if (npcs[i].npcType == 217) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "You have been jailed for breaking the rules!";
}
}
if (npcs[i].npcType == 619) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "This is as close as im getting.";
}
}
if (npcs[i].npcType == 461) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Rune's Shop!";
}
}
if (npcs[i].npcType == 554) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Rare Gilded Shop!";
}
}
if (npcs[i].npcType == 550) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Range Shop!";
}
}
if (npcs[i].npcType == 1699) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Pure Shop!";
}
}
if (npcs[i].npcType == 920) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Alexandra is FUCKING AWSOME";
}
}
if (npcs[i].npcType == 1425) {
if (misc.random2(30) <= 3) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Please dont feed the dinosaurs!";
}
}
if (npcs[i].npcType == 355) {
if (misc.random2(30) <= 3) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Mommy can I play with the dinosaurs?";
}
}
if (npcs[i].npcType == 2579) {
if (misc.random2(30) <= 3) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Go ahead!";
}
}
if (npcs[i].npcType == 949)
					{
					if (misc.random2(30) <= 3)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Welcome to Moderator Island!";
						}
					}
					if (npcs[i].npcType == 2244)
					{
						if (misc.random2(30) <= 3)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Welcome to Moderator Island!";
						}
					}
if (npcs[i].npcType == 2872) {
if (misc.random2(30) <= 3) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Help me please! Im trapped!";
}
}
if (npcs[i].npcType == 2475) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "HAIL FIGMENT";
}
}
if (npcs[i].npcType == 28) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Apply for members at figmentscape.org";
}
}
if (npcs[i].npcType == 1917) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Rune Armor Shop!";
}
}
if (npcs[i].npcType == 522) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "General Store!";
}
}
if (npcs[i].npcType == 548) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Noob Clothing Store!";
}
}
if (npcs[i].npcType == 2253) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Member Portal!";
}
}
if (npcs[i].npcType == 530) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Fletching Shop!";
}
}
if (npcs[i].npcType == 528) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Woodcutting Shop!";
}
}
if (npcs[i].npcType == 213) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Your This Far? type ::dungeongate!!.";
}
}
if (npcs[i].npcType == 520) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Member Shop!";
}
}
if (npcs[i].npcType == 524) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Silab Member Shop!";
}
}
if (npcs[i].npcType == 555) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Im Yo Dealer!";
}
}
if (npcs[i].npcType == 561) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "skilling shop!";
}
}
if (npcs[i].npcType == 538) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Slayer Shop!";
}
}
						if (npcs[i].npcType == 847) {
						if (misc.random2(10) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Are you brave enough to dare the minigame?";
						}
						}
						if (npcs[i].npcType == 2203) {
						if (misc.random2(10) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Click that glowy thing to claim your prize, *hic*...";
						}
						}
						if (npcs[i].npcType == 847) {
						if (misc.random2(10) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Click the sacks over there to start!";
						}
						}
						if (npcs[i].npcType == 942) {
						if (misc.random2(10) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Almost done, use the key drop on the tunnel!";
						}
						}
						if (npcs[i].npcType == 942) {
						if (misc.random2(10) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Coded by Newb 2341, get your prize!";
						}
						}
 if (npcs[i].npcType == 1697) { //clankilla 1: you don't have to add him if you don't want
                        if (misc.random2(30) <= 2) {
                            npcs[i].updateRequired = true;
                            npcs[i].textUpdateRequired = true;
                            npcs[i].textUpdate = "Help me get my soul back, by entering in this energy barrier!";
                        }
                    }
		    if (npcs[i].npcType == 1686) {
                        if (misc.random2(30) <= 2) {
                            npcs[i].updateRequired = true;
                            npcs[i].textUpdateRequired = true;
                            npcs[i].textUpdate = "I hope you die! MWJAJAJAJAJAJA!!!!!!";
                        }
                    }
		    if (npcs[i].npcType == 1706) {
                        if (misc.random2(30) <= 2) {
                            npcs[i].updateRequired = true;
                            npcs[i].textUpdateRequired = true;
                            npcs[i].textUpdate = "Another lost soul, MWJAJAJAJAJA!!!!!";
                        }
                    }
		    if (npcs[i].npcType == 49) {
                        if (misc.random2(30) <= 2) {
                            npcs[i].updateRequired = true;
                            npcs[i].textUpdateRequired = true;
                            npcs[i].textUpdate = "We're the gateway to hell! fite us and die!!!!!!!!!";
                        }
                    }

if (npcs[i].npcType == 529) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "C00LIO!";
}
}

if (npcs[i].npcType == 3117) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Selling Shitty St00f";
}
}
if (npcs[i].npcType == 866) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Et .. Phone .. Home!";
}
}
if (npcs[i].npcType == 549) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Smithin' Shop";
}
}
if (npcs[i].npcType == 558) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "Herblore Shop!";
}
}
if (npcs[i].npcType == 553) {
if (misc.random2(30) <= 2) {
npcs[i].updateRequired = true;
npcs[i].textUpdateRequired = true;
npcs[i].textUpdate = "No Rules, Get Mage Bank PK supplies here!";
}
}
for (Player p : server.playerHandler.players)
{
  client person = (client)p;
 if(p != null && person != null)
{
   if(p != null && person != null)
{
     if (person.distanceToPoint(npcs[i].absX, npcs[i].absY) >= 5)
{
    npcs[i].RandomWalk = true;
}
}
}
}
for (Player p : server.playerHandler.players)
{
  client person = (client)p;
 if(p != null && person != null)
{
   if(p != null && person != null)
{
     if (person.distanceToPoint(npcs[i].absX, npcs[i].absY) >= 5)
     if (npcs[i].npcType != 1913 || npcs[i].npcType == 2745 || npcs[i].npcType == 1625 || npcs[i].npcType == 50 || npcs[i].npcType == 1977 || npcs[i].npcType == 1975 || npcs[i].npcType == 1160 || npcs[i].npcType == 1974 || npcs[i].npcType == 1053 || npcs[i].npcType == 1105 || npcs[i].npcType == 1566 || npcs[i].npcType == 1558 || npcs[i].npcType == 1914 || npcs[i].npcType == 2627 || npcs[i].npcType == 2630 || npcs[i].npcType == 2743 || npcs[i].npcType == 2740 || npcs[i].npcType == 2741 || npcs[i].npcType == 2746 || npcs[i].npcType == 1560 || npcs[i].npcType == 2610)
{
    npcs[i].RandomWalk = true;
}
}
}
}
						 for (Player p : server.playerHandler.players)
{
  client person = (client)p;
 if(p != null && person != null)
{
   if(p != null && person != null)
{
     if (person.distanceToPoint(npcs[i].absX, npcs[i].absY) <= 20 && p.heightLevel == npcs[i].heightLevel)
     if (npcs[i].npcType == 1913 || npcs[i].npcType == 2745 || npcs[i].npcType == 1977 || npcs[i].npcType == 1975 || npcs[i].npcType == 50 || npcs[i].npcType == 1625 || npcs[i].npcType == 1053 || npcs[i].npcType == 1974 || npcs[i].npcType == 1566 || npcs[i].npcType == 1105 || npcs[i].npcType == 1558 || npcs[i].npcType == 1914 || npcs[i].npcType == 1160 || npcs[i].npcType == 2627 || npcs[i].npcType == 2630 || npcs[i].npcType == 2740 || npcs[i].npcType == 2743 || npcs[i].npcType == 2741 || npcs[i].npcType == 2746 || npcs[i].npcType == 2610)
{
    npcs[i].StartKilling = person.playerId;
    npcs[i].RandomWalk = false;
    npcs[i].IsUnderAttack = true;
} else if (person.distanceToPoint(npcs[i].absX, npcs[i].absY) >= 21 || person.heightLevel != npcs[i].heightLevel)
     if (npcs[i].npcType == 1913 || npcs[i].npcType == 2745 || npcs[i].npcType == 1977 || npcs[i].npcType == 1975 || npcs[i].npcType == 50 || npcs[i].npcType == 1625 || npcs[i].npcType == 1053 || npcs[i].npcType == 1974 || npcs[i].npcType == 1566 || npcs[i].npcType == 1105 || npcs[i].npcType == 1558 || npcs[i].npcType == 1914 || npcs[i].npcType == 1160 || npcs[i].npcType == 2627 || npcs[i].npcType == 2630 || npcs[i].npcType == 2740 || npcs[i].npcType == 2743 || npcs[i].npcType == 2741 || npcs[i].npcType == 2746 || npcs[i].npcType == 2610)
{
    npcs[i].RandomWalk = true;
}
}
}
}				if (npcs[i].npcType == 1451) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Tele to varrock";
						}
						}
						if (npcs[i].npcType == 33) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Tele to varrock";
						}
						}
						if (npcs[i].npcType == 37) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Tele to tzhaar caves";
						}
					}

					if (npcs[i].npcType == 1201) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "DO YOU DARE ENTER THE BLACK DRAGONS LAIR?";
						}
					}

					if (npcs[i].npcType == 1199) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "GO THROUGH THIS DOOR TO TELEPORT TO THE BLACK DRAGON CAVE";
						}
					}

					if (npcs[i].npcType == 2301) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Tele to the monkey training area";
						}
					}
					if (npcs[i].npcType == 1659) {
						if (misc.random2(30) == 1) {
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Join Figment.exofire.net";
						}
					}
						else if (npcs[i].npcType == 1552)
					{
						if (misc.random2(50) <= 3) //this is the time delay
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "I shoulda never sold that crack! Ho HO HO!!";
							}
						}
						else if (npcs[i].npcType == 1552)
					{
						if (misc.random2(10) <= 3) //this is the time delay
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							if (PlayerHandler.isPlayerOn("admin"))
							{
								npcs[i].textUpdate = "Admin is ONLINE";
							}
							else
							{
								npcs[i].textUpdate = "Admin is OFFLINE";
							}
												}
			}
			} else if (npcs[i].IsDead == true) {
					if (npcs[i].actionTimer == 0 && npcs[i].DeadApply == false && npcs[i].NeedRespawn == false) {
						//npcs[i].killedBy = getNpcKillerId(i);
						if (npcs[i].npcType == 81 || npcs[i].npcType == 397 || npcs[i].npcType == 1766 || npcs[i].npcType == 1767 || npcs[i].npcType == 1768) {
							npcs[i].animNumber = 0x03E; //cow dead
						} else if (npcs[i].npcType == 41) {
							npcs[i].animNumber = 0x039; //chicken dead
						} else if (npcs[i].npcType == 87) {
							npcs[i].animNumber = 0x08D; //rat dead
                                                } else if (npcs[i].npcType == 75) {
							npcs[i].animNumber = 466; //chicken dead
						} else if (npcs[i].npcType == 2026){ //Barrows Dharok
							npcs[i].animNumber = 0x900;
                                                } else if (npcs[i].npcType == 1158) {
							npcs[i].animNumber = 1187; //chicken dead
						} else if (npcs[i].npcType == 2025){ //Barrows Ahrim
							npcs[i].animNumber = 2304;
						} else if (npcs[i].npcType == 2027){ //Barrows Guthan
							npcs[i].animNumber = 2304;
						} else if (npcs[i].npcType == 2028){ //Barrows Karil
							npcs[i].animNumber = 2304;
						} else if (npcs[i].npcType == 2029){ //Barrows Torag
							npcs[i].animNumber = 2304;
						} else if (npcs[i].npcType == 2030){ //Barrows Verac
							npcs[i].animNumber = 2304;
						}  else if (npcs[i].npcType == 2036){ //Barrows Skeletons
							npcs[i].animNumber = 2304;
                                                }  else if (npcs[i].npcType == 83){ //Greater Demon
							npcs[i].animNumber = 804;
                                                } else if (npcs[i].npcType == 3200) {
							npcs[i].animNumber = 3147; // drags: chaos ele emote ( YESSS )
						} else if (npcs[i].npcType == 1605) {
							npcs[i].animNumber = 1508; // drags: abberant spector ( YAY )
                                                } else if (npcs[i].npcType == 49) {
							npcs[i].animNumber = 92; //hellhound dead
                                                } else if (npcs[i].npcType == 55) {
							npcs[i].animNumber = 92; //blue dragon
						} else if (npcs[i].npcType == 941) {
							npcs[i].animNumber = 92; //green dragon
						} else if (npcs[i].npcType == 1590) {
							npcs[i].animNumber = 92; //bronze dragon
						} else if (npcs[i].npcType == 1591) {
							npcs[i].animNumber = 92; //iron dragon
						} else if (npcs[i].npcType == 1592) {
							npcs[i].animNumber = 92; //steel dragon
                                                } else if (npcs[i].npcType == 53) {
							npcs[i].animNumber = 92; //red dragon
						} else if (npcs[i].npcType == 54) {
							npcs[i].animNumber = 92; //black dragon
                                                } else if (npcs[i].npcType == 2745) {
							npcs[i].animNumber = 2654; //jad dead
                                                } else if (npcs[i].npcType == 2627) {
							npcs[i].animNumber = 2620; //birdy dead
                                               } else if (npcs[i].npcType == 1160) {
							npcs[i].animNumber = 1180; //birdy dead
                                                } else if (npcs[i].npcType == 2630) {
							npcs[i].animNumber = 2627; //fatty dead
                                                } else if (npcs[i].npcType == 50) {
							npcs[i].animNumber = 92; //fatty dead
                                                } else if (npcs[i].npcType == 2740) {
							npcs[i].animNumber = 2630; //fatty dead
                                                } else if (npcs[i].npcType == 2743) {
							npcs[i].animNumber = 2646; //fatty dead
                                                } else if (npcs[i].npcType == 2741) {
							npcs[i].animNumber = 2638; //fatty dead
						} else {
							npcs[i].animNumber = 2304; //human dead
						}
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						npcs[i].DeadApply = true;
						npcs[i].actionTimer = 10;
                                                if(npcs[i].followingPlayer && server.playerHandler.players[npcs[i].followPlayer] != null)
                                                server.playerHandler.players[npcs[i].followPlayer].summonedNPCS--;
					} else if (npcs[i].actionTimer == 0 && npcs[i].DeadApply == true && npcs[i].NeedRespawn == false && npcs[i] != null) {
  						MonsterDropItem(i);
												dropItems(i); // npc drops items!
						npcs[i].NeedRespawn = true;
						npcs[i].actionTimer = 60;
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].animNumber = 0x328;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;

					} else if (npcs[i].actionTimer == 0 && npcs[i].NeedRespawn == true) {
						for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								server.playerHandler.players[j].RebuildNPCList = true;
							}
						}
                                                if(npcs[i].Respawns) {
						int old1 = npcs[i].npcType;
						if (old1 == 1267 ||old1 == 1265) {
							old1 += 1;
						}
						int old2 = npcs[i].makeX;
						int old3 = npcs[i].makeY;
						int old4 = npcs[i].heightLevel;
						int old5 = npcs[i].moverangeX1;
						int old6 = npcs[i].moverangeY1;
						int old7 = npcs[i].moverangeX2;
						int old8 = npcs[i].moverangeY2;
						int old9 = npcs[i].walkingType;
						int old10 = npcs[i].MaxHP;
						npcs[i] = null;
						newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
						}
				}
			}
		}
	}
  }
/*	public void MonsterDropItems(int NPCID) {
		int Drop = misc.random2(5);
		boolean Go = true;
		int ArrayID = GetNPCDropArrayID(npcs[NPCID].npcType, 0);
		int rnd = 0;
		int FirstDrop = 526; //Normal Bones
		int FirstDropN = 1;
		int SecondDrop = -1;
		int SecondDropN = -1;
{
		if (ArrayID != -1) {
			for (int i = (NpcDrops[ArrayID].Items.length - 1); i >= 0; i--) {
				if (NpcDrops[ArrayID].Items[i] > -1) {
					FirstDrop = NpcDrops[ArrayID].Items[i];
					FirstDropN = NpcDrops[ArrayID].ItemsN[i];
					if (FirstDrop != -1 && FirstDropN != -1) {
						if (Item.itemStackable[FirstDrop] == true || Item.itemIsNote[FirstDrop] == true) {
							Go = true;
							while (Go == true) {
								if (IsDropping == false) {
MonsterDropItem(FirstDrop, FirstDropN, NPCID);
Go = false;
								}
							}
						} else {
							for (int j = FirstDropN; j > 0; j--) {
								Go = true;
								while (Go == true) {
									if (IsDropping == false) {
										MonsterDropItem(FirstDrop, 1, NPCID);
										Go = false;
									}
								}
							}
						}
					}
				}
			}
		} else {
			MonsterDropItem(FirstDrop, FirstDropN, NPCID);
		}
		ArrayID = GetNPCDropArrayID(npcs[NPCID].npcType, Drop);
		if (ArrayID != -1) {
			rnd = misc.random2(NpcDrops[ArrayID].Items.length);
			SecondDrop = NpcDrops[ArrayID].Items[rnd];
			SecondDropN = NpcDrops[ArrayID].ItemsN[rnd];
		}
		if (SecondDrop > -1 && SecondDropN > -1) {
			if (Item.itemStackable[SecondDrop] == true || Item.itemIsNote[SecondDrop] == true) {
				Go = true;
				while (Go == true) {
					if (IsDropping == false) {
						MonsterDropItem(SecondDrop, SecondDropN, NPCID);
						Go = false;
					}
				}
			} else {
				for (int i = SecondDropN; i > 0; i--) {
					Go = true;
					while (Go == true) {
						if (IsDropping == false) {
							MonsterDropItem(SecondDrop, 1, NPCID);
							Go = false;
						}
					}
				}
			}
		}
}
	}*/

	public static boolean IsDropping = false;
	public void MonsterDropItem(int NPCID)
{
  {
		if (IsDropping == false) {
			IsDropping = true;
                        int Play = GetNpcKiller(NPCID);
			int Maxi = server.itemHandler.DropItemCount;
			for (int i = 0; i <= Maxi; i++) {
				if (server.itemHandler.DroppedItemsID[i] > 0) {
				} else {
int NPCID2 = NPCID+34;
System.out.println("Npc id =" +NPCID);
if(npcs[NPCID] != null && server.playerHandler.players[Play] != null && server.playerHandler.players[GetNpcKiller(NPCID)] != null) {
if(npcs[NPCID].npcType == 275) {
ItemHandler.addItem(4273, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2026) //DH
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good job now kill Verac to go to Torag!");
ppl.teleportToX = 3551;
ppl.teleportToY = 9677;
}
if(npcs[NPCID].npcType == 2030) //Verac
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good job now kill Torag to go to Ahrims!");
ppl.teleportToX = 3551;
ppl.teleportToY = 9711;
}
if(npcs[NPCID].npcType == 2029) //Torg
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good job now kill Ahrim to go to Guthan!");
ppl.teleportToX = 3537;
ppl.teleportToY = 9712;
}
if(npcs[NPCID].npcType == 2025) //Ahrim
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good job now kill Guthan to go to Karil!");
ppl.teleportToX = 3535;
ppl.teleportToY = 9694;
}
if(npcs[NPCID].npcType == 2027) //Ghutan
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good job now kill Karil to go to the Chaos Elemental!!");
ppl.teleportToX = 3534;
ppl.teleportToY = 9677;
}
if(npcs[NPCID].npcType == 2028) //Karil
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good job now kill the Chaos Elemental to go to the chest!!");
ppl.teleportToX = 3045;
ppl.teleportToY = 3743;
}
if(npcs[NPCID].npcType == 3200) //Chaos elemntal
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("SWEET YOU DID IT! CLICK THE CHEST!");
ppl.teleportToX = 3045;
ppl.teleportToY = 3751;
}
if(npcs[NPCID].npcType == 1974)
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("RAAAAAAAAAAAR FEEL MY WRATH!!");
ppl.teleportToX = 2742;
ppl.teleportToY = 5084;
ppl.heightLevel = 4;
}
if(npcs[NPCID].npcType == 193) //Druid
{
int playerId = npcs[NPCID].StartKilling;
client c = (client) server.playerHandler.players[playerId];
c.Druidkills += 1; 
}
if(npcs[NPCID].npcType == 104) //Ghost
{
int playerId = npcs[NPCID].StartKilling;
client c = (client) server.playerHandler.players[playerId];
c.Ghostkills += 1; 
}
if(npcs[NPCID].npcType == 111) 
{
int playerId = npcs[NPCID].StartKilling;
client c = (client) server.playerHandler.players[playerId];
c.Giantkills += 1; 
}
if(npcs[NPCID].npcType == 752) //Lesser Demon
{
int playerId = npcs[NPCID].StartKilling;
client c = (client) server.playerHandler.players[playerId];
c.Demonkills += 1;
}
if(npcs[NPCID].npcType == 258) //General Khazard
{
int playerId = npcs[NPCID].StartKilling;
client c = (client) server.playerHandler.players[playerId];
c.Generalkills += 1;
}
if(npcs[NPCID].npcType == 1472) //Jungle demon
{
int playerId = npcs[NPCID].StartKilling;
client c = (client) server.playerHandler.players[playerId];
c.JDemonkills += 1;
}
if(npcs[NPCID].npcType == 752) //Lesser Demon
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Good! Now kill the General!");
ppl.teleportToX = 3246;
ppl.teleportToY = 3244;
}
if(npcs[NPCID].npcType == 258) //General Khazard
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("Wow, you have made it this far! Kill Him to beat the Mini game!");
ppl.teleportToX = 3202;
ppl.teleportToY = 3266;
}
if(npcs[NPCID].npcType == 1472) //Jungle demon
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("You finished the Mini game! Click on the Chest to claim your reward!");
ppl.teleportToX = 3207;
ppl.teleportToY = 3222;
}
if(npcs[NPCID].npcType == 1975)
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("RAAAAAAAAAAAR FEEL MY WRATH!!");
ppl.teleportToX = 2742;
ppl.teleportToY = 5084;
ppl.heightLevel = 0;
}
if(npcs[NPCID].npcType == 1975)
{
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.sendMessage("You Defeated Damis!");
ppl.teleportToX = 2742;
ppl.teleportToY = 5084;
ppl.heightLevel = 8;
}
if(npcs[NPCID].npcType == 18) {
ItemHandler.addItem(Item3.randomguard(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 21) {
ItemHandler.addItem(Item3.randomhero(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2256) {
ItemHandler.addItem(Item3.randomguardz(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
                            if (npcs[NPCID].npcType == 41) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((900), 18);
                            }

                            if (npcs[NPCID].npcType == 90) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((4000), 18);
                            }
                            if (npcs[NPCID].npcType == 1648) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((5500), 18);
                            }
                            if (npcs[NPCID].npcType == 1621) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((7500), 18);
                            }
                            if (npcs[NPCID].npcType == 1832) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((8000), 18);
                            }
                            if (npcs[NPCID].npcType == 1637) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((20000), 18);
                            }
                            if (npcs[NPCID].npcType == 1632) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((27000), 18);
                            }
                            if (npcs[NPCID].npcType == 1607) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((30000), 18);
                            }
                            if (npcs[NPCID].npcType == 1615) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((35000), 18);
                            }
                            if (npcs[NPCID].npcType == 2783) {
                                int Player = npcs[NPCID].StartKilling;
                                client ppl = (client) server.playerHandler.players[Player];
				ppl.addSkillXP((200000), 18);
                            }
if(npcs[NPCID].npcType == 1007) {
ItemHandler.addItem(6754, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 49) {
ItemHandler.addItem(4272, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 795) {
ItemHandler.addItem(4078, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
 if(npcs[NPCID].npcType == 1293) { //koeschi the deathless {
			       ItemHandler.addItem(Item2.randomkoeschi(),
					npcs[NPCID].absX, npcs[NPCID].absY, 1,
					GetNpcKiller(NPCID), false);
                            }
			    if(npcs[NPCID].npcType == 82) { //lesser demon {
			       ItemHandler.addItem(Item2.randomlesser(),
					npcs[NPCID].absX, npcs[NPCID].absY, 1,
					GetNpcKiller(NPCID), false);
                            }
			    if(npcs[NPCID].npcType == 83) { //greater demon {
			       ItemHandler.addItem(Item2.randomgreater(),
					npcs[NPCID].absX, npcs[NPCID].absY, 1,
					GetNpcKiller(NPCID), false);
                            }
			    if(npcs[NPCID].npcType == 1855) { //arzinian avatar of ranging {
			       ItemHandler.addItem(Item2.randomaar(),
					npcs[NPCID].absX, npcs[NPCID].absY, 1,
					GetNpcKiller(NPCID), false);
                            }
			    if(npcs[NPCID].npcType == 1852) { //arzinian avatar of strenght {
			       ItemHandler.addItem(Item2.randomaas(),
					npcs[NPCID].absX, npcs[NPCID].absY, 1,
					GetNpcKiller(NPCID), false);
			    }
			    if(npcs[NPCID].npcType == 1858) { //arzinian avatar of mage {
			       ItemHandler.addItem(Item2.randomaam(),
					npcs[NPCID].absX, npcs[NPCID].absY, 1,
					GetNpcKiller(NPCID), false);
			    }

if(npcs[NPCID].npcType == 509) {
ItemHandler.addItem(6104, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2880) {
ItemHandler.addItem(5585, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}

if(npcs[NPCID].npcType == 1859)
{
ItemHandler.addItem(6529, npcs[NPCID].absX, npcs[NPCID].absY, 10000000, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1160) {
ItemHandler.addItem(Item2.randomKQ(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1625) {
ItemHandler.addItem(Item2.randomdustdevil(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 391) {
ItemHandler.addItem(Item2.randomtroll(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 41) {
ItemHandler.addItem(Item2.randomchicken(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 90) {
ItemHandler.addItem(Item2.randomskeleton(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1648) {
ItemHandler.addItem(Item2.randomcrawlinghand(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1832) {
ItemHandler.addItem(Item2.randomcavebug(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 941) {
ItemHandler.addItem(Item2.randomgreen(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 53) {
ItemHandler.addItem(Item2.randomred(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1913) {
ItemHandler.addItem(Item2.randomkamil(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1914) {
ItemHandler.addItem(Item2.randomdes(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1975) {
ItemHandler.addItem(Item2.randomdamis(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1977) {
ItemHandler.addItem(Item2.randomfareed(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 54) {
ItemHandler.addItem(Item2.randomblue(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 55) {
ItemHandler.addItem(Item2.randomblack(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1637) {
ItemHandler.addItem(Item2.randomjelly(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 178) {
ItemHandler.addItem(Item2.randomknite(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1604) {
ItemHandler.addItem(Item2.randomaberrantspecter(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1615) {
ItemHandler.addItem(Item2.randomabyssaldemon(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2783) {
ItemHandler.addItem(Item2.randomdarkbeast(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 89) {
ItemHandler.addItem(Item2.randomunicorn(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 912) {
ItemHandler.addItem(Item2.randombattlemagesara(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 913) {
ItemHandler.addItem(Item2.randombattlemagezammy(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 914) {
ItemHandler.addItem(Item2.randombattlemageguthix(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 86) {
ItemHandler.addItem(Item2.randomrat(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 103) {
ItemHandler.addItem(Item2.randomghost(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 190) {
ItemHandler.addItem(Item2.randomzammy(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1592) {
ItemHandler.addItem(Item2.randomsteel(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2881) {
ItemHandler.addItem(Item2.randomdaggy(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2882) {
ItemHandler.addItem(Item2.randomdaggy2(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2883) {
ItemHandler.addItem(Item2.randomdaggy3(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 35) {
ItemHandler.addItem(Item2.randomsoldier(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 114) {
ItemHandler.addItem(Item2.randomogre(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 50) {
ItemHandler.addItem(Item2.randomKBD(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 55) {
ItemHandler.addItem(Item2.randomblue(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 3200) {
ItemHandler.addItem(Item2.randomce(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 17) {
ItemHandler.addItem(Item2.randombarbarian(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if (npcs[NPCID].npcType == 188) {
ItemHandler.addItem(16, npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1655 || npcs[NPCID].npcType == 1604 || npcs[NPCID].npcType == 2035) {
ItemHandler.addItem(Item.randomSlayeritem65(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1655 || npcs[NPCID].npcType == 1604) {
ItemHandler.addItem(Item.randomSlayeritem75(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1605) {
ItemHandler.addItem(Item.randomSlayer99item(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 52) {
ItemHandler.addItem(Item2.randombaby(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2026) {
ItemHandler.addItem(Item2.randomDharok(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 110) {
ItemHandler.addItem(Item2.randomfiregiant(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2030) {
ItemHandler.addItem(Item2.randomVerac(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2029) {
ItemHandler.addItem(Item2.randomTorag(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2028) {
ItemHandler.addItem(Item2.randomKaril(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2027) {
ItemHandler.addItem(Item2.randomGuthan(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2745) {
ItemHandler.addItem(Item2.randomjad(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2025) {
ItemHandler.addItem(Item2.randomAhrim(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 1320) {
ItemHandler.addItem(Item2.randomBunny(), npcs[NPCID].absX, npcs[NPCID].absY, 1, GetNpcKiller(NPCID), false);
}
if(npcs[NPCID].npcType == 2627) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
if(npcs[NPCID].npcType == 2630) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
if(npcs[NPCID].npcType == 2740) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
if(npcs[NPCID].npcType == 2746) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
if(npcs[NPCID].npcType == 2741) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
if(npcs[NPCID].npcType == 2743) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
if(npcs[NPCID].npcType == 2745) {
int Player = npcs[NPCID].StartKilling;
client ppl = (client) server.playerHandler.players[Player];
ppl.cavewave += 1;
break;
}
}

					if (i == Maxi) {
						if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
							server.itemHandler.DropItemCount = 0;
							println("! Notify item resterting !");
						}
					}
					break;
				}
			}
			IsDropping = false;
		}
	}
}

	public int GetNpcKiller(int NPCID) {
		int Killer = 0;
		int Count = 0;
		for (int i = 1; i < server.playerHandler.maxPlayers; i++) {
			if (Killer == 0) {
				Killer = i;
				Count = 1;
			} else {
				if (npcs[NPCID].Killing[i] > npcs[NPCID].Killing[Killer]) {
					Killer = i;
					Count = 1;
				} else if (npcs[NPCID].Killing[i] == npcs[NPCID].Killing[Killer]) {
					Count++;
				}
			}
		}
		if (Count > 1 && npcs[NPCID].Killing[npcs[NPCID].StartKilling] == npcs[NPCID].Killing[Killer]) {
			Killer = npcs[NPCID].StartKilling;
		}
		return Killer;
	}

/*
WORLDMAP: (walk able places)
01 - Aubury
02 - Varrock Mugger
03 - Lowe
04 - Horvik
05 - Varrock General Store
06 - Thessalia
07 - Varrock Sword Shop
08 - Varrock East Exit Guards
09 - Falador General Store
10 - Falador Shield Shop
11 - Falador Mace Shop
12 - Falador Center Guards
13 - Falador North Exit Guards
14 - Barbarian Village Helmet Shop
15 - Varrock Staff Shop
16 - Al-Kharid Skirt Shop
17 - Al-Kharid Crafting Shop
18 - Al-Kharid General Store
19 - Al-Kharid Leg Shop
20 - Al-Kharid Scimitar Shop
21 - Lumbridge Axe Shop
22 - Lumbridge General Store
23 - Port Sarim Battleaxe Shop
24 - Port Sarim Magic Shop
25 - Port Sarim Jewelery Shop
26 - Port Sarim Fishing Shop
27 - Port Sarim Food Shop
28 - Rimmington Crafting Shop
29 - Rommington Archery Shop
30 - Npc's around and in varrock
31 - Npc's at Rellekka
32 - Npc's around and in lumbridge
33 -
34 -
35 -
36 -
37 -
38 -
39 -
40 -
*/
	public static int worldmap[][] = {
		{
/* 01 */		3252,3453,3252,3453,3252,3253,3254,3252,3253,3254,3255,3252,3253,3252,3253,
/* 02 */			3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,3248,3249,3250,3251,3252,3253,3254,
/* 03 */		3235,3234,3233,3232,3231,3230,3235,3230,3235,3234,3233,3232,3231,3230,3234,3232,3231,3234,3233,3232,3231,3234,3233,3232,3233,3231,
/* 04 */			3231,3230,3229,3232,3231,3230,3229,3229,3228,3227,3229,3227,3232,3231,3230,3229,3228,3227,3232,3231,3230,3229,3228,3227,3226,3225,3232,3231,3230,3229,3228,3227,3225,3232,3231,3230,3229,3228,3227,3225,3232,3229,3228,3227,3226,3232,3231,3230,3229,
/* 05 */		3217,3216,3215,3214,3219,3218,3217,3216,3219,3218,3217,3219,3217,3216,3215,3219,3218,3217,3216,3215,3214,3220,3219,3217,3216,3215,3214,3219,3217,3216,3215,3214,3219,3217,3216,3215,3214,3218,3217,
/* 06 */			3207,3206,3205,3208,3207,3206,3203,3207,3206,3205,3204,3203,3207,3206,3205,3204,3203,3202,3208,3207,3206,3205,3208,3207,3206,3207,
/* 07 */		3206,3204,3203,3202,3209,3208,3207,3205,3203,3208,3207,3206,3205,3203,3208,3207,3206,3205,3204,3203,3202,3208,3207,3206,3205,3203,3207,3206,3203,3206,3203,3206,3205,3205,3205,
/* 08 */			3268,3268,3268,3268,3268,3269,3269,3269,3269,3269,3270,3270,3270,3270,3270,3271,3271,3271,3271,3271,3272,3272,3272,3272,3272,3273,3273,3273,3273,3273,3274,3274,3274,3274,3274,3275,3275,3275,3276,3276,3276,3276,3273,3274,3275,3276,3273,3274,3275,3273,
/* 09 */		2958,2957,2959,2958,2957,2959,2958,2957,2959,2958,2957,2956,2955,2954,2953,2960,2959,2956,2955,2953,2960,2959,2957,2956,2953,
/* 10 */			2979,2977,2976,2975,2974,2973,2972,2979,2978,2977,2972,2972,2974,2973,2972,
/* 11 */		2952,2950,2949,2948,2952,2951,2950,2949,2948,2952,2951,2950,2949,2948,2952,2951,2950,2949,2948,2952,2952,2951,
/* 12 */			2969,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,2969,2968,2967,2966,2965,2964,2963,2962,2961,2960,2959,2958,
/* 13 */		2968,2967,2966,2965,2964,2963,2967,2966,2965,2964,2966,2965,2967,2966,2965,2964,2968,2967,2966,2965,2964,2963,2968,2967,2966,2965,2964,2963,2967,2966,2965,2964,2968,2967,2966,2965,2964,2963,
/* 14 */			3076,3074,3076,3075,3074,3077,3076,3075,3074,3073,3077,3074,3077,3076,3075,3074,
/* 15 */		3204,3204,3203,3202,3201,3204,3203,3202,3201,3203,3201,3203,3202,3201,3204,3203,3201,3204,
/* 16 */			3315,3316,3313,3314,3315,3317,3318,3314,3317,3314,3315,3316,3317,3313,3314,3315,3316,3317,3318,3314,3315,3316,3317,
/* 17 */		3319,3320,3323,3318,3319,3320,3322,3323,3318,3319,3320,3321,3322,3323,3319,3320,3321,3322,3319,3320,3322,3323,3318,3319,3320,3323,3319,3320,
/* 18 */			3315,3316,3312,3313,3314,3315,3316,3312,3313,3314,3315,3316,3317,3312,3313,3314,3315,3316,3317,3318,3312,3313,3314,3316,3317,3312,3313,3314,3316,3317,3312,3313,3314,3316,3317,3314,3317,3315,
/* 19 */		3314,3315,3316,3318,3315,3316,3317,3318,3314,3315,3316,3317,3318,3314,3315,3316,3314,3315,
/* 20 */			3287,3288,3289,3285,3286,3287,3288,3289,3290,3287,3288,3289,3290,3287,3288,3289,3290,3286,3287,3288,3287,
/* 21 */		3229,3232,3228,3229,3230,3231,3232,3233,3228,3230,3231,3232,3233,3228,3230,3231,3232,3232,
/* 22 */			3210,3211,3209,3210,3211,3212,3214,3209,3211,3212,3213,3214,3209,3211,3212,3213,3209,3210,3211,3212,3213,3214,3209,3211,3212,3213,3209,3210,3211,3212,3213,3209,3211,3213,
/* 23 */		3026,3028,3024,3025,3026,3027,3028,3029,3025,3026,3027,3028,3029,3030,3024,3025,3028,3029,3030,3024,3025,3028,3029,3024,3025,3026,3027,3028,3029,3028,3029,3030,3025,3026,3027,3028,3029,
/* 24 */			3012,3013,3014,3015,3016,3012,3015,3016,3012,3015,3016,3011,3012,3013,3014,3015,3012,
/* 25 */		3012,3014,3012,3013,3014,3015,3012,3013,3014,3015,3012,3013,3015,3012,3013,3014,
/* 26 */			3013,3014,3013,3014,3013,3014,3015,3016,3012,3013,3014,3015,3016,3017,3012,3013,3014,3015,3011,3012,3013,3014,3015,3016,3011,3012,3013,3014,3015,3016,3011,3016,3011,3013,3014,3015,3016,3013,3014,3016,
/* 27 */		3012,3014,3012,3013,3014,3015,3016,3012,3015,3012,3013,3014,3015,3016,3013,3014,3015,3013,3014,3013,3013,
/* 28 */			2946,2947,2952,2946,2947,2950,2951,2952,2946,2948,2949,2950,2951,2946,2948,2949,2950,2951,2946,2947,2948,2949,2950,2951,2948,2949,2948,2949,
/* 29 */		2955,2958,2959,2954,2955,2956,2957,2958,2959,2953,2954,2956,2957,2958,2957,2958,2959,
/* 30 */			3236,3236,3237,3238,3239,3237,3238,3239,3240,3236,3237,3238,3239,3240,3236,3237,3238,3239,3237,3238,/**/3245,3246,3243,3244,3245,3246,3243,3244,3245,3246,3247,3246,3247,/**/3261,3260,3261,3262,3260,3261,3263,3260,3263,3260,3263,3260,3263,3260,3261,3262,3263,3260,3261,3263,/**/3234,3235,3238,3233,3234,3235,3236,3237,3238,3235,3233,3234,3235,3236,3233,3234,3235,3236,3237,3238,/**/3290,3291,3292,3293,3294,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,3290,3293,3294,3295,3296,3297,3298,3299,3290,3293,3294,3295,3296,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,3290,3291,3292,3293,3294,3295,3296,3297,3298,3299,
/* 31 */		2662,2663,2661,2662,2663,2661,2662,2663,2661,2662,2663,2662,2663,2664,2665,2666,2665,2666,/**/2664,2665,2666,2664,2665,2666,2664,2665,2666,2664,2665,2666,2664,2665,2666,/**/2679,2680,2679,2680,2676,2677,2678,2679,2680,2676,2677,2678,2679,2680,2676,2677,2678,2679,2680,2674,2675,2676,2677,2678,2679,2680,2675,2676,2677,2678,2679,2680,/**/2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,2667,2668,2669,2670,2671,/**/2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,2681,2682,2683,2684,2685,/**/2675,2676,2677,2678,2679,2675,2676,2677,2678,2679,2675,2676,2677,2678,2679,2676,2677,2678,2679,2677,2678,2679,/**/
			2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,2672,2673,2674,2675,/**/2674,2675,2676,2677,2678,2674,2675,2676,2677,2678,2674,2675,2676,2677,2678,2674,2675,2676,2677,2678,2674,2675,2677,2678,/**/2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,2685,2686,2687,2688,2689,/**/2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,2668,2669,2670,2671,2672,/**/2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,2679,2680,2681,2682,2683,/**/2673,2674,2675,2673,2674,2675,2676,2677,2673,2674,2675,2676,2677,2673,2674,2675,2676,2677,2673,2674,2675,2676,2677,/**/2669,2670,2671,2672,2669,2670,2671,2672,2673,2669,2670,2671,2672,2673,2669,2670,2671,2672,2673,2669,2670,2671,2672,2673,/**/
			2680,2681,2682,2679,2680,2681,2682,2678,2679,2680,2681,2682,2678,2679,2680,2681,2682,2678,2679,2680,2681,2682,
/* 32 */			3228,3229,3226,3227,3228,3225,3226,3228,3229,3226,3227,3228,3229,3230,3225,3226,3227,3228,3229,3230,3225,3229,3225,3226,3227,3228,3229,3226,/**/3232,3233,3234,3235,3236,3237,3232,3233,3234,3235,3236,3231,3232,3233,3234,3235,3236,3227,3228,3229,3231,3232,3233,3234,3235,3236,3225,3226,3227,3228,3229,3230,3231,3233,3234,3235,3236,3225,3226,3227,3228,3229,3230,3231,3232,3233,3234,3235,3236,3225,3228,3229,3230,3231,3232,3235,3236,3237,3225,3227,3228,3229,3230,3231,3232,3233,3235,3236,3237,3225,3227,3228,3229,3230,3231,3232,3233,3235,3236,3231,3235,
		},
		{
/* 01 */		3404,3404,3403,3403,4302,4302,4302,3401,3401,3401,3401,3400,3400,3399,3399,
/* 02 */			3398,3398,3398,3398,3398,3398,3398,3397,3397,3397,3397,3397,3397,3397,3396,3396,3396,3396,3396,3396,3395,3395,3395,3395,3395,3395,3395,3394,3394,3394,3394,3394,3394,3394,3393,3393,3393,3393,3393,3393,3393,3392,3392,3392,3392,3392,3392,3391,3391,3391,3391,3391,3391,3391,3390,3390,3390,3390,3390,3390,3390,3389,3389,3389,3389,3389,3389,3389,3388,3388,3388,3388,3388,3388,3388,3387,3387,3387,3387,3387,3387,3387,3386,3386,3386,3386,3386,3386,3386,
/* 03 */		3421,3421,3421,3421,3421,3421,3422,3422,3423,3423,3423,3423,3423,3423,3424,3424,3424,3425,3425,3425,3425,3426,3426,3426,3427,3427,
/* 04 */			3433,3433,3433,3434,3434,3434,3434,3435,3435,3435,3436,3436,3437,3437,3437,3437,3437,3437,3438,3438,3438,3438,3438,3438,3438,3438,3439,3439,3439,3439,3439,3439,3439,3440,3440,3440,3440,3440,3440,3440,3441,3441,3441,3441,3441,3442,3442,3442,3442,
/* 05 */		3411,3411,3411,3411,3412,3412,3412,3412,3413,3413,3413,3414,3414,3414,3414,3415,3415,3415,3415,3415,3415,3416,3416,3416,3416,3416,3416,3417,3417,3417,3417,3417,3418,3418,3418,3418,3418,3419,3419,
/* 06 */			3414,3414,3414,3415,3415,3415,3415,3416,3416,3416,3416,3416,3417,3417,3417,3417,3417,3417,3418,3418,3418,3418,3419,3419,3419,3420,
/* 07 */		3495,3495,3495,3495,3396,3396,3396,3396,3396,3397,3397,3397,3397,3397,3398,3398,3398,3398,3398,3398,3398,3399,3399,3399,3399,3399,3400,3400,3400,3401,3401,3402,3402,3403,3404,
/* 08 */			3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3426,3427,3428,3429,3430,3227,3228,3229,3426,3427,3430,3420,3421,3421,3421,3421,3422,3422,3422,3423,
/* 09 */		3385,3385,3386,3386,3386,3387,3387,3387,3388,3388,3388,3388,3388,3388,3388,3389,3389,3389,3389,3389,3390,3390,3390,3390,3390,
/* 10 */			3383,3383,3383,3383,3383,3383,3383,3384,3384,3384,3384,3385,3386,3386,3386,
/* 11 */		3385,3385,3385,3385,3386,3386,3386,3386,3386,3387,3387,3387,3387,3387,3388,3388,3388,3388,3388,3389,3390,3390,
/* 12 */			3376,3376,3376,3376,3376,3376,3376,3376,3376,3376,3376,3377,3377,3377,3377,3377,3377,3377,3377,3377,3377,3377,3377,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3380,3380,3380,3380,3380,3380,3380,3380,3380,3380,3380,3380,3381,3381,3381,3381,3381,3381,3381,3381,3381,3381,3381,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,3384,3384,3384,3384,3384,3384,3384,3384,3384,3384,3384,3384,
/* 13 */		3391,3391,3391,3391,3391,3391,3392,3392,3392,3392,3393,3393,3394,3394,3394,3394,3395,3395,3395,3395,3395,3395,3396,3396,3396,3396,3396,3396,3397,3397,3397,3397,3398,3398,3398,3398,3398,3398,
/* 14 */			3427,3427,3428,3428,3428,3429,3429,3429,3429,3429,3430,3430,3431,3431,3431,3431,
/* 15 */		3431,3432,3432,3432,3432,3433,3433,3433,3433,3434,3434,3435,3435,3435,3436,3436,3436,3437,
/* 16 */			3160,3160,3161,3161,3161,3161,3161,3162,3162,3163,3163,3163,3163,3164,3164,3164,3164,3164,3164,3165,3165,3165,3165,
/* 17 */		3191,3191,3191,3192,3192,3192,3192,3192,3193,3193,3193,3193,3193,3193,3194,3194,3194,3194,3195,3195,3195,3195,3196,3196,3196,3196,3197,3197,
/* 18 */			3178,3178,3179,3179,3179,3179,3179,3180,3180,3180,3180,3180,3180,3181,3181,3181,3181,3181,3181,3181,3182,3182,3182,3182,3182,3183,3183,3183,3183,3183,3184,3184,3184,3184,3184,3185,3185,3186,
/* 19 */		3173,3173,3173,3173,3174,3174,3174,3174,3175,3175,3175,3175,3175,3176,3176,3176,3177,3177,
/* 20 */			3187,3187,3187,3188,3188,3188,3188,3188,3188,3189,3189,3189,3189,3190,3190,3190,3190,3191,3191,3191,3192,
/* 21 */		3201,3201,3202,3202,3202,3202,3202,3202,3203,3203,3203,3203,3203,3204,3204,3204,3204,3205,
/* 22 */			3243,3243,3244,3244,3244,3244,3244,3245,3245,3245,3245,3245,3246,3246,3246,3246,3247,3247,3247,3247,3247,3247,3248,3248,3248,3248,3249,3249,3249,3249,3249,3250,3250,3250,
/* 23 */		3245,3245,3246,3246,3246,3246,3246,3246,3247,3247,3247,3247,3247,3247,3248,3248,3248,3248,3248,3249,3249,3249,3249,3250,3250,3250,3250,3250,3250,3251,3251,3251,3252,3252,3252,3252,3252,
/* 24 */			3257,3257,3257,3257,3257,3258,3258,3258,3259,3259,3259,3260,3260,3260,3260,3260,3261,
/* 25 */		3244,3244,3245,3245,3245,3245,3246,3246,3246,3246,3247,3247,3247,3248,3248,3248,
/* 26 */			3220,3220,3221,3221,3222,3222,3222,3222,3223,3223,3223,3223,3223,3223,3224,3224,3224,3224,3225,3225,3225,3225,3225,3225,3226,3226,3226,3226,3226,3226,3227,3227,3228,3228,3228,3228,3228,3229,3229,3229,
/* 27 */		3203,3203,3204,3204,3204,3204,3204,3205,3205,3206,3206,3206,3206,3206,3207,3207,3207,3208,3208,3209,3210,
/* 28 */			3202,3202,3202,3203,3203,3203,3203,3203,3204,3204,3204,3204,3204,3205,3205,3205,3205,3205,3206,3206,3206,3206,3206,3206,3207,3207,3208,3208,
/* 29 */		3202,3202,3202,3203,3203,3203,3203,3203,3203,3204,3204,3204,3204,3204,3205,3205,3205,
/* 30 */			3403,3404,3404,3404,3404,3405,3405,3405,3405,3406,3406,3406,3406,3406,3407,3407,3407,3407,3408,3408,/**/3393,3393,3394,3394,3394,3394,3395,3395,3395,3395,3395,3396,3396,/**/3396,3397,3397,3397,3398,3398,3398,3399,3399,3400,3400,3401,3401,3402,3402,3402,3402,3403,3403,3403,/**/3382,3382,3382,3383,3383,3383,3383,3383,3383,3384,3385,3385,3385,3385,3386,3386,3386,3386,3386,3386,/**/3377,3377,3377,3377,3377,3377,3377,3377,3378,3378,3378,3378,3378,3378,3378,3378,3378,3378,3379,3379,3379,3379,3379,3379,3379,3379,3379,3379,3380,3380,3380,3380,3380,3380,3380,3380,3381,3381,3381,3381,3381,3381,3381,3381,3382,3382,3382,3382,3382,3382,3382,3382,3382,3382,3383,3383,3383,3383,3383,3383,3383,3383,3383,3383,
/* 31 */		3713,3713,3714,3714,3714,3715,3715,3715,3716,3716,3716,3717,3717,3718,3718,3718,3719,3719,/**/3713,3713,3713,3714,3714,3714,3715,3715,3715,3716,3716,3716,3717,3717,3717,/**/3714,3714,3715,3715,3716,3716,3716,3716,3716,3717,3717,3717,3717,3717,3718,3718,3718,3718,3718,3719,3719,3719,3719,3719,3719,3719,3720,3720,3720,3720,3720,3720,/**/3712,3712,3712,3712,3712,3713,3713,3713,3713,3713,3714,3714,3714,3714,3714,3715,3715,3715,3715,3715,3716,3716,3716,3716,3716,3717,3717,3717,3717,3717,3718,3718,3718,3718,3718,3719,3719,3719,3719,3719,/**/3714,3714,3714,3714,3714,3715,3715,3715,3715,3715,3716,3716,3716,3716,3716,3717,3717,3717,3717,3717,3718,3718,3718,3718,3718,/**/3718,3718,3718,3718,3718,3719,3719,3719,3719,3719,3720,3720,3720,3720,3720,3721,3721,3721,3721,3722,3722,3722,/**/
			3712,3712,3712,3712,3713,3713,3713,3713,3714,3714,3714,3714,3715,3715,3715,3715,3716,3716,3716,3716,3717,3717,3717,3717,3718,3718,3718,3718,/**/3711,3711,3711,3711,3711,3712,3712,3712,3712,3712,3713,3713,3713,3713,3713,3714,3714,3714,3714,3714,3715,3715,3715,3715,3715,/**/3722,3722,3722,3722,3722,3723,3723,3723,3723,3723,3724,3724,3724,3724,3724,3725,3725,3725,3725,3725,3726,3726,3726,3726,3726,/**/3725,3725,3725,3725,3725,3726,3726,3726,3726,3726,3727,3727,3727,3727,3727,3728,3728,3728,3728,3728,3729,3729,3729,3729,3729,/**/3730,3730,3730,3730,3730,3731,3731,3731,3731,3731,3732,3732,3732,3732,3732,3733,3733,3733,3733,3733,3734,3734,3734,3734,3734,/**/3727,3727,3727,3728,3728,3728,3728,3728,3729,3729,3729,3729,3729,3730,3730,3730,3730,3730,3731,3731,3731,3731,3731,/**/3723,3723,3723,3723,3724,3724,3724,3724,3724,3725,3725,3725,3725,3725,3726,3726,3726,3726,3726,3727,3727,3727,3727,3727,/**/
			3726,3726,3726,3727,3727,3727,3727,3728,3728,3728,3728,3728,3729,3729,3729,3729,3729,3730,3730,3730,3730,3730,
/* 32 */			3287,3287,3288,3288,3288,3289,3289,3289,3289,3290,3290,3290,3290,3290,3291,3291,3291,3291,3291,3291,3292,3292,3293,3293,3293,3293,3293,3294,/**/3292,3292,3292,3292,3292,3292,3293,3293,3293,3293,3293,3294,3294,3294,3294,3294,3294,3295,3295,3295,3295,3295,3295,3295,3295,3295,3296,3296,3296,3296,3296,3296,3296,3296,3296,3296,3296,3297,3297,3297,3297,3297,3297,3297,3297,3297,3297,3297,3297,3298,3298,3298,3298,3298,3298,3298,3298,3298,3299,3299,3299,3299,3299,3299,3299,3299,3299,3299,3299,3300,3300,3300,3300,3300,3300,3300,3300,3300,3300,3301,3301,
		},
	};


/*
WORLDMAP 2: (not-walk able places)
01 - Lumbridge cows
*/
	public static int worldmap2[][] = {
		{
/* 01 */		3257,3258,3260,3261,3261,3262,3261,3262,3257,3258,3257,3258,3254,3255,3254,3255,3252,3252,3250,3251,3250,3251,3249,3250,3249,3250,3242,3242,3243,3243,3257,3244,3245,3244,3245,3247,3248,3250,3251,3255,3256,3255,3256,3259,3260,
		},
		{
/* 01 */		3256,3256,3256,3256,3266,3266,3267,3267,3270,3270,3271,3271,3272,3272,3273,3273,3275,3276,3277,3277,3278,3278,3279,3279,3280,3280,3285,3286,3289,3290,3289,3297,3297,3298,3298,3298,3298,3297,3297,3297,3297,3298,3298,3297,3297,
		},
	};

public int remove = 2; // 1 = removes equipment, 2 = doesn't remove - xerozcheez
public static int removeschaos[] = {1,2,2,2};

    public static int randomremoveschaos()
    {
    	return removeschaos[(int)(Math.random()*removeschaos.length)];
    }

public void gfxAll(int id, int Y, int X)
{
for (Player p : server.playerHandler.players)
{
if(p != null)
{
client person = (client)p;
if((person.playerName != null || person.playerName != "null"))
{
if(person.distanceToPoint(X, Y) <= 60)
{
person.stillgfx2(id, Y, X);
}
}
}
}
}

public int GetNPCBlockAnim(int id)
{
switch (id) {


case 53:
case 1158:
return 1186;
case 54:
case 2256:
return 403;
case 21:
return 403;
case 2745:
return 2653;
case 50:
return 89;
case 2627:
return 2622;
case 1160:
return 1179;
case 2741:
return 2635;
case 2743:
return 2645;
case 2740:
return 2629;
case 2630:
return 2626;
case 18:
return 403;
case 92:
return 0;
case 55:
return 89;

default:
return 1834;

}
}

        public boolean AttackPlayerRanged(int NPCID) {
		int Player = npcs[NPCID].StartKilling;
		if (server.playerHandler.players[Player] == null) {
			ResetAttackPlayer(NPCID);
			return false;
        } else if (server.playerHandler.players[Player].DirectionCount < 2) {
        	handleClipping(NPCID);
            return false;
        }
                client plr = (client) server.playerHandler.players[Player];
		int EnemyX = server.playerHandler.players[Player].absX;
		int EnemyY = server.playerHandler.players[Player].absY;
                npcs[NPCID].enemyX = EnemyX;
                npcs[NPCID].enemyY = EnemyY;
                //if(EnemyX != npcs[NPCID].absX && EnemyY != npcs[NPCID].absY) {
                //npcs[NPCID].viewX = EnemyX;
                //npcs[NPCID].viewY = EnemyY;
                //npcs[NPCID].faceToUpdateRequired = true;
                //}
		int EnemyHP = server.playerHandler.players[Player].playerLevel[server.playerHandler.players[Player].playerHitpoints];
		int EnemyMaxHP = getLevelForXP(server.playerHandler.players[Player].playerXP[server.playerHandler.players[Player].playerHitpoints]);
		boolean RingOfLife = false;
		if (server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerRing] == 2570) {
			RingOfLife = true;
		}
                //if(EnemyX != npcs[NPCID].absX && EnemyY != npcs[NPCID].absY) // Xerozcheez: stops client crashing
                //plr.viewTo(npcs[NPCID].absX, npcs[NPCID].absY); // Xerozcheez: Player turns to npc

                if(server.playerHandler.players[Player].attacknpc == NPCID) {
                server.playerHandler.players[Player].faceNPC = NPCID; // Xerozcheez: sets npc index for player to view
                server.playerHandler.players[Player].faceNPCupdate = true; // Xerozcheez: updates face npc index so player faces npcs
                server.playerHandler.players[Player].attacknpc = NPCID; // Xerozcheez: makes it so if player runs away the player attacks back when npc follows
                server.playerHandler.players[Player].IsAttackingNPC = true; // Xerozcheez: makes it so if player runs away the player attacks back when npc follows
                }
		int hitDiff = 0;
		hitDiff = misc.random(npcs[NPCID].MaxHit);
                                          if(npcs[NPCID].npcType != 3200 && npcs[NPCID].npcType != 1645) {
                       FollowPlayerCB(NPCID, Player);
                       handleClipping(NPCID);
                                          }
		if (GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 1) == true || npcs[NPCID].npcType == 3200) {
			if (npcs[NPCID].actionTimer == 0) {
				if (RingOfLife == true && EnemyHP <= (int)((double)((double)EnemyMaxHP / 10.0) + 0.5)) {
					server.playerHandler.players[Player].SafeMyLife = true;
				} else {
					if (server.playerHandler.players[Player].IsDead == true) {
						ResetAttackPlayer(NPCID);
					} else {

					if (npcs[NPCID].npcType == 3200) { // chaos elemental
							npcs[NPCID].animNumber = 0x326;
                                                        remove = randomremoveschaos();
                                                        if(remove == 1)
                                                        {
                                                        server.playerHandler.players[Player].removeequipped();
                                                        }
                                                       }
                                                else if(npcs[NPCID].npcType == 752) {
                                                npcs[NPCID].animNumber = 0x326;

						
						} else if (npcs[NPCID].npcType == 35) {
							npcs[NPCID].animNumber = 1080; //Guard Attack
						} else if (npcs[NPCID].npcType == 53) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 54) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 55) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 941) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 1590) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 1591) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 1592) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 3647) {
                                                        npcs[NPCID].animNumber = 0x03B; //cow attack
                                                } else if (npcs[NPCID].npcType == 451) {
                                                        npcs[NPCID].animNumber = 2363; //paladin attack
						} else if (npcs[NPCID].npcType == 41) {
							npcs[NPCID].animNumber = 0x037; //chicken attack
						} else if (npcs[NPCID].npcType == 87) {
							npcs[NPCID].animNumber = 0x08A; //rat attack
						} else if (npcs[NPCID].npcType == 3200) { // chaos elemental
							npcs[NPCID].animNumber = 0x326;
                                                        remove = randomremoveschaos();
                                                        if(remove == 1)
                                                        {
                                                        server.playerHandler.players[Player].removeequipped();
                                                        }
                                                       }

                                                else if(npcs[NPCID].npcType == 752) {
                                                npcs[NPCID].animNumber = 0x326;
                                                }
						else {
							npcs[NPCID].animNumber = 0x326; //human attack
						}
                                                plr.startAnimation(plr.GetBlockAnim(plr.playerEquipment[plr.playerWeapon]));
						npcs[NPCID].animUpdateRequired = true;
						npcs[NPCID].updateRequired = true;
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
						server.playerHandler.players[Player].hitDiff = hitDiff;
						server.playerHandler.players[Player].updateRequired = true;
						server.playerHandler.players[Player].hitUpdateRequired = true;
						server.playerHandler.players[Player].appearanceUpdateRequired = true;
						npcs[NPCID].actionTimer = 7;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean AttackPlayer(int NPCID) {
		int Player = npcs[NPCID].StartKilling;
		if (server.playerHandler.players[Player] == null) {
			ResetAttackPlayer(NPCID);
			return false;
		} else if (server.playerHandler.players[Player].DirectionCount < 2) {
			return false;
		}
                client plr = (client) server.playerHandler.players[Player];
		int EnemyX = server.playerHandler.players[Player].absX;
		int EnemyY = server.playerHandler.players[Player].absY;
                npcs[NPCID].enemyX = EnemyX;
                npcs[NPCID].enemyY = EnemyY;
                //if(EnemyX != npcs[NPCID].absX && EnemyY != npcs[NPCID].absY) {
                //npcs[NPCID].viewX = EnemyX;
                //npcs[NPCID].viewY = EnemyY;
                //npcs[NPCID].faceToUpdateRequired = true;
                //}
		int EnemyHP = server.playerHandler.players[Player].playerLevel[server.playerHandler.players[Player].playerHitpoints];
		int EnemyMaxHP = getLevelForXP(server.playerHandler.players[Player].playerXP[server.playerHandler.players[Player].playerHitpoints]);
		boolean RingOfLife = false;
		if (server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerRing] == 2570) {
			RingOfLife = true;
		}
                //if(EnemyX != npcs[NPCID].absX && EnemyY != npcs[NPCID].absY) // Xerozcheez: stops client crashing
                //plr.viewTo(npcs[NPCID].absX, npcs[NPCID].absY); // Xerozcheez: Player turns to npc

                if(server.playerHandler.players[Player].attacknpc == NPCID) {
                server.playerHandler.players[Player].faceNPC = NPCID; // Xerozcheez: sets npc index for player to view
                server.playerHandler.players[Player].faceNPCupdate = true; // Xerozcheez: updates face npc index so player faces npcs
                server.playerHandler.players[Player].attacknpc = NPCID; // Xerozcheez: makes it so if player runs away the player attacks back when npc follows
                server.playerHandler.players[Player].IsAttackingNPC = true; // Xerozcheez: makes it so if player runs away the player attacks back when npc follows
                }
		int hitDiff = 0;
		hitDiff = misc.random(npcs[NPCID].MaxHit);
                                          if(npcs[NPCID].npcType != 3200 && npcs[NPCID].npcType != 1645) {
                       FollowPlayerCB(NPCID, Player);
                                          }
		if (GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 1) == true || npcs[NPCID].npcType == 3200) {
			if (npcs[NPCID].actionTimer == 0) {
				if (RingOfLife == true && EnemyHP <= (int)((double)((double)EnemyMaxHP / 10.0) + 0.5)) {
					server.playerHandler.players[Player].SafeMyLife = true;
				} else {
					if (server.playerHandler.players[Player].IsDead == true) {
						ResetAttackPlayer(NPCID);
					} else {
						if (npcs[NPCID].npcType == 81 || npcs[NPCID].npcType == 397 || npcs[NPCID].npcType == 1766 || npcs[NPCID].npcType == 1767 || npcs[NPCID].npcType == 1768) {
							npcs[NPCID].animNumber = 0x03B; //cow attack
						} else if (npcs[NPCID].npcType == 41) {
							npcs[NPCID].animNumber = 0x037; //chicken attack
						} else if (npcs[NPCID].npcType == 87) {
							npcs[NPCID].animNumber = 0x08A; //rat attack
                                                } else if (npcs[NPCID].npcType == 21) {
							npcs[NPCID].animNumber = 451; //Hero attack
                                                        hitDiff = 4 + misc.random(9);
                                                } else if (npcs[NPCID].npcType == 1958) {
							npcs[NPCID].animNumber = 422; //Hero attack
                                                        hitDiff = 4 + misc.random(18 );
                                                } else if (npcs[NPCID].npcType == 2256) {
							npcs[NPCID].animNumber = 451; //Paladin attack
                                                        hitDiff = 4 + misc.random(8);
                                                } else if (npcs[NPCID].npcType == 18) {
							npcs[NPCID].animNumber = 451; //Al-K'harid Warrior Attack
                                                        hitDiff = 4 + misc.random(2);
						} else if (npcs[NPCID].npcType == 53) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 54) {
							npcs[NPCID].animNumber = 80; //Dragon
                                                } else if (npcs[NPCID].npcType == 1158) {
                                                        npcs[NPCID].animNumber = 1184; //KQ attack
                                                        hitDiff = 4 + misc.random(35);
						} else if (npcs[NPCID].npcType == 55) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 2627) {
							npcs[NPCID].animNumber = 2621; 
						} else if (npcs[NPCID].npcType == 2630) {
							npcs[NPCID].animNumber = 2625; 
						} else if (npcs[NPCID].npcType == 1160) {
							npcs[NPCID].animNumber = 1177; 
						} else if (npcs[NPCID].npcType == 941) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 1590) {
							npcs[NPCID].animNumber = 80; //Dragon
						} else if (npcs[NPCID].npcType == 1591) {
							npcs[NPCID].animNumber = 2741; //Dragon
						} else if (npcs[NPCID].npcType == 2637) {
							npcs[NPCID].animNumber = 80; //Dragon
                                                } else if (npcs[NPCID].npcType == 1160) {
                                                        npcs[NPCID].animNumber = 1184; //KQ attack
                                                        hitDiff = 4 + misc.random(35);
						} else if (npcs[NPCID].npcType == 2026) {
							npcs[NPCID].animNumber = 2067; //Dharok
                                                          hitDiff = 4 + misc.random(15);
						} else if (npcs[NPCID].npcType == 2027) {
							npcs[NPCID].animNumber = 2080; //Guthan
                                                          hitDiff = 4 + misc.random(15);
						} else if (npcs[NPCID].npcType == 2029) {
							npcs[NPCID].animNumber = 2068; //Torag
                                                          hitDiff = 4 + misc.random(15);
						} else if (npcs[NPCID].npcType == 2030) {
							npcs[NPCID].animNumber = 2062; //Verac
                                                 	  hitDiff = 4 + misc.random(15);
						} else if (npcs[NPCID].npcType == 83) {
							npcs[NPCID].animNumber = 64; //Greater Demon
                                                } else if (npcs[NPCID].npcType == 82) {
							npcs[NPCID].animNumber = 64; //Lesser Demon
                                                        hitDiff = 4 + misc.random(13);
                                                }  else if (npcs[NPCID].npcType == 2745 && misc.random(3) == 1) {
                                                        npcs[NPCID].animNumber = 2652; //range
                                                        npcs[NPCID].actionTimer = 7;
                                                        hitDiff = 4 + misc.random(25);
                                                } else if (npcs[NPCID].npcType == 2745 && misc.random(3) == 2) {
                                                        npcs[NPCID].animNumber = 2655; //melee
                                                        gfxAll(451, EnemyY, EnemyX);
                                                        npcs[NPCID].actionTimer = 1;
                                                        hitDiff = 4 + misc.random(30);
                                                }  else if (npcs[NPCID].npcType == 2745 && misc.random(3) == 3) {
                                                        npcs[NPCID].animNumber = 2656; //mage
                                                        gfxAll(451, EnemyY, EnemyX);
                                                        npcs[NPCID].actionTimer = 1;
                                                        hitDiff = 4 + misc.random(35);
//fight caves
                                                }  else if (npcs[NPCID].npcType == 2627) {
								npcs[NPCID].animNumber = 2655; //poke
                                                        	npcs[NPCID].actionTimer = 7;
                                                        	hitDiff = 1;
                                                        }  else if (npcs[NPCID].npcType == 2630) {
								npcs[NPCID].animNumber = 2655; //poke
								npcs[NPCID].actionTimer = 7;
                                                        	hitDiff = 2;
               			}  else if (npcs[NPCID].npcType == 2740 && misc.random(2) == 2) {
							npcs[NPCID].animNumber = 2628; //poke
							npcs[NPCID].actionTimer = 7;
                       					hitDiff = 3;
                       		}  else if (npcs[NPCID].npcType == 2740 && misc.random(2) == 1) {
							npcs[NPCID].animNumber = 2633; //poke
                                                        gfxAll(328, EnemyY, EnemyX);
							npcs[NPCID].actionTimer = 7;
                       					hitDiff = 3;
              			}  else if (npcs[NPCID].npcType == 2743 && misc.random(2) == 2) {
							npcs[NPCID].animNumber = 2644; //poke
							npcs[NPCID].actionTimer = 7;
                       					hitDiff = 3;
                       		}  else if (npcs[NPCID].npcType == 2743 && misc.random(2) == 1) {
							npcs[NPCID].animNumber = 2647; //poke
                                                        gfxAll(346, EnemyY, EnemyX);
							npcs[NPCID].actionTimer = 7;
                       					hitDiff = 3;

              			}  else if (npcs[NPCID].npcType == 50 && misc.random(2) == 2) {
							npcs[NPCID].animNumber = 91; //poke
							npcs[NPCID].actionTimer = 7;
                       					hitDiff = 10 + misc.random(17);

                       		}  else if (npcs[NPCID].npcType == 50 && misc.random(2) == 1) {
							npcs[NPCID].animNumber = 81; //poke
                                                        gfxAll(393, EnemyY, EnemyX);
							npcs[NPCID].actionTimer = 7;
                       					hitDiff = 10 + misc.random(35);

                        }  else if (npcs[NPCID].npcType == 2741) {
							npcs[NPCID].animNumber = 2637; //poke
							npcs[NPCID].actionTimer = 7;
                        	hitDiff = 4;
                        }  else if (npcs[NPCID].npcType == 2746) {
							npcs[NPCID].animNumber = 2655; //poke
							npcs[NPCID].actionTimer = 7;
                            hitDiff = 5;
                        }  else if (npcs[NPCID].npcType == 2610) {
							npcs[NPCID].animNumber = 2655; //poke
							npcs[NPCID].actionTimer = 7;
                            hitDiff = 6;
                            //fight caves
						} else if (npcs[NPCID].npcType == 64) {
							npcs[NPCID].animNumber = 0x326; //Ogre
						} else if (npcs[NPCID].npcType == 3647) {
						} else if (npcs[NPCID].npcType == 3200) { // chaos elemental
							npcs[NPCID].animNumber = 0x326;
                                                        remove = randomremoveschaos();
                                                        if(remove == 1)
                                                        {
                                                        server.playerHandler.players[Player].removeequipped();
                                                        }
                                                       }

                                                else if(npcs[NPCID].npcType == 2745) {
                                                npcs[NPCID].animNumber = 2656;
                                                npcs[NPCID].actionTimer = 7;
                                                hitDiff = 4 + misc.random(25);
                                                }
                                                else if(npcs[NPCID].npcType == 752) {
                                                npcs[NPCID].animNumber = 0x326;
                                                }
						 else {
							npcs[NPCID].animNumber = 0x326; //human attack
						}
                                                plr.startAnimation(plr.GetBlockAnim(plr.playerEquipment[plr.playerWeapon]));
						npcs[NPCID].animUpdateRequired = true;
						npcs[NPCID].updateRequired = true;
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
						server.playerHandler.players[Player].hitDiff = hitDiff;
						server.playerHandler.players[Player].updateRequired = true;
						server.playerHandler.players[Player].hitUpdateRequired = true;
						server.playerHandler.players[Player].appearanceUpdateRequired = true;
						npcs[NPCID].actionTimer = 7;
					}
				}
				return true;
			}
		}
		return false;
	}
public boolean AttackPlayerMage(int NPCID) {
		int Player = npcs[NPCID].StartKilling;
                client p = (client) server.playerHandler.players[Player];
		if (server.playerHandler.players[Player] == null) {
			ResetAttackPlayer(NPCID);
			return false;
		} else if (server.playerHandler.players[Player].DirectionCount < 2) {
			return false;
		}
		int EnemyX = server.playerHandler.players[Player].absX;
		int EnemyY = server.playerHandler.players[Player].absY;
		int EnemyHP = server.playerHandler.players[Player].playerLevel[server.playerHandler.players[Player].playerHitpoints];
		int EnemyMaxHP = getLevelForXP(server.playerHandler.players[Player].playerXP[server.playerHandler.players[Player].playerHitpoints]);
		boolean RingOfLife = false;
		if (server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerRing] == 2570) {
			RingOfLife = true;
		}
		int hitDiff = 0;
		//hitDiff = misc.random(npcs[NPCID].MaxHit);
			if (npcs[NPCID].actionTimer == 0) {
				if (RingOfLife == true && EnemyHP <= (int)((double)((double)EnemyMaxHP / 10.0) + 0.5)) {
					server.playerHandler.players[Player].SafeMyLife = true;
				} else {
					if (server.playerHandler.players[Player].IsDead == true) {
						ResetAttackPlayer(NPCID);
					} else {

                                               if(npcs[NPCID].npcType == 1914) {
                                               npcs[NPCID].animNumber = 1914; // mage attack
                                               p.stillgfx(381, p.absY, p.absX);
                                               hitDiff = 6 + misc.random(49);
                                               }
                                               if(npcs[NPCID].npcType == 1913) {
                                               npcs[NPCID].animNumber = 1979;
                                               p.stillgfx(369, p.absY, p.absX);
                                               hitDiff = 6 + misc.random(43);
                                               }
                                               if(npcs[NPCID].npcType == 1974) {
                                               hitDiff = 2 + misc.random(3);
                                               }
                                               if(npcs[NPCID].npcType == 1975) {
                                               npcs[NPCID].animNumber = 406;
                                               hitDiff = 6 + misc.random(50);
                                               }
                                               if(npcs[NPCID].npcType == 1977) {
                                               npcs[NPCID].animNumber = 1125;
                                               hitDiff = 10 + misc.random(40);
                                               }
                                               if(npcs[NPCID].npcType == 1105) {
                                               npcs[NPCID].animNumber = 1264;
                                               p.stillgfx(406, p.absY, p.absX);
                                               hitDiff = 6 + misc.random(25);
                                               }
                                               if(npcs[NPCID].npcType == 1053) {
                                               npcs[NPCID].animNumber = 1979;
                                               p.stillgfx(265, p.absY, p.absX);
                                               hitDiff = 6 + misc.random(25);
                                               }
                                               if(npcs[NPCID].npcType == 509) {
                                               hitDiff = 8 + misc.random(20);
                                               }
                                               if(npcs[NPCID].npcType == 1241) {
                                               p.stillgfx(363, p.absY, p.absX);
                                               hitDiff = 2 + misc.random(19);
                                               }
                                               if (npcs[NPCID].npcType == 124) {
				               npcs[NPCID].animNumber = 1833;
                                               hitDiff = 4 + misc.random(35);
                                               }
                                               if(npcs[NPCID].npcType == 1246) {
                                               p.stillgfx(368, npcs[NPCID].absY, npcs[NPCID].absX);
                                               p.stillgfx(367, p.absY, p.absX);
                                               hitDiff = 4 + misc.random(35);
                                               }
                                               if(npcs[NPCID].npcType == 1159) {
                                               p.stillgfx(552, p.absY, p.absX);
                                               hitDiff = 2 + misc.random(88);
                                               }
                                               if(npcs[NPCID].npcType == 54) {
                                               p.stillgfx(197, p.absY, p.absX);
                                               hitDiff = 2 + misc.random(96);
                                               }
                                               server.playerHandler.players[Player].setPlrAnimation(server.playerHandler.players[Player].GetPlrBlockAnim(server.playerHandler.players[Player].playerEquipment[server.playerHandler.players[Player].playerWeapon]));
						npcs[NPCID].animUpdateRequired = true;
						npcs[NPCID].updateRequired = true;
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
						server.playerHandler.players[Player].hitDiff = hitDiff;
						server.playerHandler.players[Player].updateRequired = true;
						server.playerHandler.players[Player].hitUpdateRequired = true;
						server.playerHandler.players[Player].appearanceUpdateRequired = true;
						npcs[NPCID].actionTimer = 7;
					}
				}
				return true;
		}
		return false;
	}
public boolean AttackNPCMage(int NPCID) {
		int EnemyX = server.npcHandler.npcs[npcs[NPCID].attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[npcs[NPCID].attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[npcs[NPCID].attacknpc].HP;
		int hitDiff = 0;
                int Npchitdiff = 0;
                int wepdelay = 0;
		//hitDiff = misc.random(npcs[NPCID].MaxHit);
			if (npcs[NPCID].actionTimer == 0) {
                         if (server.npcHandler.npcs[npcs[NPCID].attacknpc].IsDead == true) {
					ResetAttackNPC(NPCID);
					//npcs[NPCID].textUpdate = "Oh yeah I win bitch!";
					//npcs[NPCID].textUpdateRequired = true;
                                        npcs[NPCID].animNumber = 2103;
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
				} else  {

                                              if(npcs[NPCID].npcType == 1914) {
                                               gfxAll(381, EnemyY, EnemyX);
                                               hitDiff = 6 + misc.random(49);
                                               }
                                              if(npcs[NPCID].npcType == 1105) {
                                               gfxAll(406, EnemyY, EnemyX);
                                               hitDiff = 6 + misc.random(25);
                                               }
                                               if(npcs[NPCID].npcType == 1913) {
                                               gfxAll(369, EnemyY, EnemyX);
                                               hitDiff = 6 + misc.random(43);
                                               }
                                               if(npcs[NPCID].npcType == 1977) {
                                               gfxAll(78, EnemyY, EnemyX);
                                               hitDiff = 10 + misc.random(40);
                                               }
                                               if(npcs[NPCID].npcType == 1645) {
                                               gfxAll(369, EnemyY, EnemyX);
                                               hitDiff = 6 + misc.random(43);
                                               }
                                              if(npcs[NPCID].npcType == 1053) {
                                               gfxAll(265, EnemyY, EnemyX);
                                               hitDiff = 6 + misc.random(25);
                                               }
                                              if(npcs[NPCID].npcType == 1974) {
                                               hitDiff = 2 + misc.random(3);
                                               }
                                              if(npcs[NPCID].npcType == 1975) {
                                               hitDiff = 6 + misc.random(50);
                                               }
                                               if(npcs[NPCID].npcType == 509) {
                                               hitDiff = 8 + misc.random(20);
                                               }
                                               if(npcs[NPCID].npcType == 1241) {
                                               gfxAll(363, EnemyY, EnemyX);
                                               hitDiff = 2 + misc.random(19);
                                               }
                                               if(npcs[NPCID].npcType == 1246) {
                                               gfxAll(368, npcs[NPCID].absY, npcs[NPCID].absX);
                                               gfxAll(367, EnemyY, EnemyX);
                                               hitDiff = 4 + misc.random(35);
                                               }
                                               if(npcs[NPCID].npcType == 1159) {
                                               gfxAll(552, EnemyY, EnemyX);
                                               hitDiff = 2 + misc.random(88);
                                               }
                                               if(npcs[NPCID].npcType == 54) {
                                               gfxAll(197, EnemyY, EnemyX);
                                               hitDiff = 2 + misc.random(96);
                                               }
						npcs[NPCID].animUpdateRequired = true;
						npcs[NPCID].updateRequired = true;
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
					server.npcHandler.npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].updateRequired = true;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
					npcs[NPCID].actionTimer = 7;
				        return true;
		}
		return false;
	}
        return false;
}
public boolean AttackNPC(int NPCID) {
    if(server.npcHandler.npcs[npcs[NPCID].attacknpc] != null) {
		int EnemyX = server.npcHandler.npcs[npcs[NPCID].attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[npcs[NPCID].attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[npcs[NPCID].attacknpc].HP;
		int hitDiff = 0;
                int Npchitdiff = 0;
                int wepdelay = 0;
                hitDiff = misc.random(npcs[NPCID].MaxHit);
		if (GoodDistance(EnemyX, EnemyY, npcs[NPCID].absX, npcs[NPCID].absY, 1) == true) {
				if (server.npcHandler.npcs[npcs[NPCID].attacknpc].IsDead == true) {
					ResetAttackNPC(NPCID);
					npcs[NPCID].textUpdate = "Oh yeah I win bitch!";
					npcs[NPCID].textUpdateRequired = true;
                    npcs[NPCID].animNumber = 2103;
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
				} else  {
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
                                        if(npcs[NPCID].npcType == 9)
                                        npcs[NPCID].animNumber = 386;
                                        if(npcs[NPCID].npcType == 3200)
					npcs[NPCID].animNumber = 0x326; // drags: chaos ele emote ( YESSS )
                                        if(npcs[NPCID].npcType == 1605) {
					npcs[NPCID].animNumber = 386; // drags: abberant spector death ( YAY )
					}
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].updateRequired = true;
					server.npcHandler.npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
					npcs[NPCID].actionTimer = 7;
				        return true;
                                        }
				}
                }
		return false;
	}
	public boolean ResetAttackNPC(int NPCID) {
		npcs[NPCID].IsUnderAttackNpc = false;
		npcs[NPCID].IsAttackingNPC = false;
		npcs[NPCID].attacknpc = -1;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}
	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 135; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp)
				return lvl;
		}
		return 0;
	}
	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
		  for (int j = 0; j <= distance; j++) {
			if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			}
		  }
		}
		return false;
	}
	public boolean ResetAttackPlayer(int NPCID) {
		npcs[NPCID].IsUnderAttack = false;
		npcs[NPCID].StartKilling = 0;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}

	public boolean loadAutoSpawn(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]), Integer.parseInt(token3[2]), Integer.parseInt(token3[3]), Integer.parseInt(token3[4]), Integer.parseInt(token3[5]), Integer.parseInt(token3[6]), Integer.parseInt(token3[7]), Integer.parseInt(token3[8]), GetNpcListHP(Integer.parseInt(token3[0])), true);
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}

	public int GetNpcListHP(int NpcID) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == NpcID) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}

	public boolean loadNPCList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1], Integer.parseInt(token3[2]), Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}

	public void println(String str) {
		System.out.println(str);
	}
}