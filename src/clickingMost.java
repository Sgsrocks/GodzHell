public class clickingMost { 
	
public void clicking(int a){
client c = (client) server.playerHandler.players[a];
		switch(c.actionButtonId) {

		case 151:
		break;
		case 33206:
			c.sendFrame126("@dre@Attack", 8716);
					for(int i = 8720;i<8799;i++) {
						c.sendFrame126("",i);
					}
					c.sendFrame126("1",8720);
					c.sendFrame126("Bronze",8760);
					c.sendFrame126("1",8721);
					c.sendFrame126("Iron", 8761);
					c.sendFrame126("5",8722);
					c.sendFrame126("Steel",8762);
					c.sendFrame126("10",8723);
					c.sendFrame126("Black", 8763);
					c.sendFrame126("20",8724);
					c.sendFrame126("Mithril", 8764);
					c.sendFrame126("30",8725);
					c.sendFrame126("Adamant",8765);
					c.sendFrame126("40",8726);
					c.sendFrame126("Rune", 8766);
					c.sendFrame126("60",8727);
					c.sendFrame126("Dragon", 8767);
					c.sendFrame126("70", 8728);
					c.sendFrame126("Barrows", 8768);
					c.sendFrame126("70",8729);
					c.sendFrame126("Abyssal",8769);
					c.sendFrame126("70",8730);
					c.sendFrame126("Saradomin sword",8770);
					c.sendFrame126("75",8731);
					c.sendFrame126("Godswords",8771);
					c.sendFrame126("99",8732);
					c.sendFrame126("Skill Cape",8772);
					c.sendFrame126("120",8733);
					c.sendFrame126("Master Skill cape",8773);
					c.sendFrame126("",8823);
					c.sendFrame126("",8824);
					c.sendFrame126("",8859);
					c.sendFrame126("",8862);
					c.sendFrame126("",8865);
					c.sendFrame126("",15303);
					c.sendFrame126("",15306);
					c.sendFrame126("",15309);
					c.sendFrame126("",8849);// members only skill
					c.sendFrame126("",8827);//steel
					c.sendFrame126("",8837);//mithril
					c.sendFrame126("",8840);
					c.sendFrame126("",8843);
					c.sendFrame126("",8846); //the first choose thing
					
					c.sendFrame34(8847,1205,0,1);
					c.sendFrame34(8847,1203,1,1);
					c.sendFrame34(8847,1207,2,1);
					c.sendFrame34(8847,1217,3,1);
					c.sendFrame34(8847,1209,4,1);
					c.sendFrame34(8847,1211,5,1);
					c.sendFrame34(8847,1213,6,1);
					c.sendFrame34(8847,1215,7,1);
					c.sendFrame34(8847,4718,8,1);
					c.sendFrame34(8847,4151,9,1);
					c.sendFrame34(8847,15351,10,1);
					c.sendFrame34(8847,15333,11,1);
					c.sendFrame34(8847,14073,12,1);
					c.showInterface(8714);
			break;

		case 58074:
			c.getBankPin().close();
			break;

		case 58025:
		case 58026:
		case 58027:
		case 58028:
		case 58029:
		case 58030:
		case 58031:
		case 58032:
		case 58033:
		case 58034:
			c.getBankPin().pinEnter(c.actionButtonId);
			break;

}
}
}
