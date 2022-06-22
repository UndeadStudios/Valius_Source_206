package com.mayhem.rs2.content.mayhembot;

/**
 * Holds the MayhemBot Data
 * @author Daniel
 *
 */
public enum MayhemBotData {	
	  DATA_1("What color is prestige tier 5?", "red"),
	  DATA_2("How many times can you prestige a skill?", "5", "five"),
	  DATA_3("The barrows brother Verac hits through which prayer?", "Protect from melee"),
	  DATA_4("Which MOB has four enourmous tentacles?", "Kraken", "kraken"),
	  DATA_5("I am a rock that turns into a crab what am i called?", "Rock crab", "Rock crabs"),
	  DATA_6("What is the max level possible reached in any Valius skill?", "99"),
	  DATA_7("Which NPC allows you set bank pin in Valius?", "Valius guard", "guard"),
	  DATA_8("In Valius how many NPCs drop the Chaos Elemental pet?", "2", "two"),
	  DATA_9("How many times can you prestige a skill?", "5", "five"),
	  DATA_10("Corporeal Beast drops how many onyx bolts(e)?", "175"),
	  DATA_11("Tanzanite fang turns into what when cut with chisel?", "toxic Blowpipe", "blowpipe"),
	  DATA_12("What is the name of the NPC that drops Wyvern bones?", "skeletal Wyvern", "skeletal", "wyverns"),
	  DATA_13("How many colors of infinity are there?", "3", "Three"),
	  DATA_14("What is the max combat level you can achieve in Valius?", "126"),
	  DATA_15("How many times can you prestige a skill?", "5", "five"),
	  DATA_16("Which of the barrows warriors is based on magic?","Ahrim", "Ahrim"),
	  DATA_17("What is the required fishing level for sharks?","76", "76"),
	  DATA_18("What is the smithing level required to create a DFS?", "90"),
	  DATA_19("Purple is prestige color for what prestige level?","2nd", "second", "2"),
	  DATA_20("How many waves is fight caves in Valius?","fifteen", "15"),
	  DATA_21("What is the minimum required amount to bet against the Gambler?", "500k"),
	  DATA_22("How much special attack does Magic Short Bow require?", "55%", "55"),
	  DATA_23("Berserker ring is dropped by which NPC?","Dagannoth King Rex", "Dagannoth Rex", "Rex"),
	  DATA_24("How many friends fit on the Valius friendslist?", "200"),
	  DATA_25("What is the short term used for 'Staff Of The Dead'?", "Sotd"),
	  DATA_26("Anagram - odsanb", "bandos"),
	  DATA_27("Where are spiritual mages found?", "Godwars", "Godwars dungeon"),
	  DATA_28("What is the required level for Smite?", "52"),
	  DATA_29("Toxic blowpipe is a drop from what NPC?", "Zulrah"),
	  DATA_30("What is the name of the first teleport in the teleport interface?", "Rock Crabs", "rock crabs"),
	  DATA_31("Which npc teleports you to rune essence mine in Valius?", "Mage of zamorak", "Zamorak mage"),
	  DATA_32("What is the required level for Smite?", "52"),
	  DATA_33("How many times can you prestige one stat in Valius?", "5", "five"),
	  DATA_34("Which gamemode in Valius cannot use banks?", "Ultimate Ironman", "Ult"),
	  DATA_35("What does the npc 'Fairy Good Titler' Sell?", "loyalty titles", "titles", "player titles"),
	  DATA_36("How many bank booths are in edgeville bank?", "4", "four"),
	  DATA_37("What slayer level is required to kill Kraken?", "87", "87"),
	  DATA_38("What is the required attack level to wield a godsword?", "75"),
	  DATA_39("How many barrows brothers are there?", "6"),
	  DATA_40("How many tiers of membership are there?", "4", "four"),
	  DATA_41("What game is wildly addictive?", "Valius", "this game"),
	  DATA_42("What NPC helps Iron accounts?", "adam"),
	  DATA_43("How many total achievements are there?", "72", "seventy two"),
	  DATA_44("How many prayers are there?", "26", "twenty six", "twenty-six"),
	  DATA_45("What is the maximum amount of friends allowed?", "200", "two hundred", "two-hundred"),
	  DATA_46("What is the Woodcutting level required to wield a dragon hatchet?", "61", "sixty one", "sixty-one"),	  
	  DATA_47("Who can you talk to if you want to see the NPC drop tables?", "james", "James"),
	  DATA_48("Where is home in Valius?", "zeah", "the great kourend", "great kourend"),
	  DATA_49("What is the name of the NPC you can get skillcapes from?", "wise old man"),
	  DATA_50("What color party hat does the wise old man wear?", "blue"),
	  DATA_51("What is the name of the NPC used to travel around Valius?", "sailor"),
	  DATA_52("What skill involves buring logs?", "firemaking"),
	  DATA_53("What is the required defence level to wear dragon armour?", "60", "sixty"),
	  DATA_54("What NPC allows you to reset combat stats in Valius?", "genie"),
	  DATA_55("What minigame offers void armour as a reward?", "pest control"),
	  DATA_56("What NPC can you talk to if you want to claim a donation?", "donor spirit", "donator spirit", "member spirit"),
	  DATA_57("How many Thieving stalls are there at home?", "5", "five"),  
	  DATA_58("What is the best F2P armour?", "rune", "rune armour"),	  
	  DATA_59("How much money does normal membership cost?", "$5", "5 dollars", "5"),  
	  DATA_60("What is the best crossbow in game?", "armadyl crossbow", "acb"),	  
	  DATA_61("What level magic does the spell High Alchemy require?", "55", "fifty-five", "fifty five"),
	  
	  ;
	
	private final String question;
	private final String[] answers;
	
	private MayhemBotData(String question, String... answers) {
		this.question = question;
		this.answers = answers;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String[] getAnswers() {
		return answers;
	}
	
}
