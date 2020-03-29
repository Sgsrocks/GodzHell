/*
 * Class ThievingHandler
 *
 * Version 1
 *
 * Sunday 25th April, 2009
 *
 * Created by Primadude
 */

import java.io.*;



public class ThievingHandler {

	/**
	 * Constructor
	 */
	public ThievingHandler(client p) {
		this.p = p;
	}
	

	public void ThiefStart(client p) {
		p.thiefNPC = false;

		switch (p.thievingType) {

			case 1:
			case 2:
			case 3:
				startPP(1, "Man");
				break;

			case 4:
			case 5:
			case 6:
				startPP(1, "Woman");
				break;

			case 7:
				startPP(10, "Farmer");
				break;

			case 15:
			case 18:
				startPP(25, "Warrior");
				break;

			case 9:
				startPP(40, "Guard");
				break;
				
			case 1715:
				startPP(15, "H.a.m Member");
				break;
				
			case 1714:
				startPP(20, "H.a.m Member");
				break;
				

		}
	}

	public void ThiefFinish(client p) {
		switch (p.thievingType) {

			case 1:
			case 2:
			case 3:
				finishPP(1, 30, 995, 100, 1, "Man");
				break;

			case 4:
			case 5:
			case 6:
				finishPP(1, 30, 995, 100, 1, "Woman");
				break;

			case 7:
				finishPP(7, 80, 995, 300, 3, "Farmer");
				break;

			case 15:
			case 18:
				finishPP(25, 320, 995, 800, 4, "Warrior");
				break;

			case 9:
				finishPP(40, 900, 995, 1200, 5, "Guard");
				break;
				
			case 1715:
				finishPP(15, 18, Item2.randomhammember(), 1, 5, "H.A.M. Member");
				break;


		}
	}

	/**
	 * Start PickPocketing
	 */
	public void startPP(int lvlReq, String npcName) {
		if (System.currentTimeMillis() - p.stunDelay >= 5000) {
			if (System.currentTimeMillis() - p.thiefTimer >= 2000) {
				if (p.playerLevel[17] >= lvlReq) {
					p.thiefTimer = System.currentTimeMillis();
					p.thiefDelay = 3;
					p.startAnimation(881);
					p.sendMessage("You attempt to pickpocket the "+ npcName +"...");
				} else {
					p.sendMessage("You need to have level "+ lvlReq +" thieving to pickpocket this Npc.");
				}
			}
		} else {
			p.sendMessage("You are stunned!");
		}
	}

	/**
	 * Finish PickPocketing
	 */
	public void finishPP(int lvlReq, int exp, int itemid, int cashAmt, int npcHit, String name) {
	    	NPC n = server.npcHandler.npcs[p.ThiefNPC];
		int chance = 3 + (p.playerLevel[17] - lvlReq);

		if (misc.random(chance) >= 3) {
			p.addItem(itemid, cashAmt);
			p.sendMessage("You pickpocket the "+ name +".");
			p.addSkillXP(exp, 17);
		} else {
			p.sendMessage("You fail to pickpocket the "+ name +".");
			p.stunDelay = System.currentTimeMillis();
			p.appendHit(misc.random(npcHit));
			p.gfx100(245);
			p.startAnimation(p.GetBlockAnim());
			//n.requestText("What do you think you're doing?!");
			//n.TurnNPCTo(p.absX, p.absY);
		}
	}

	client p;
	public long thiefTimer;
	
}