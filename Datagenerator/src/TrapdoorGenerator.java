import java.io.File;
import java.io.FileWriter;

public class TrapdoorGenerator {
	public static void generateBlock(String name, boolean loot) {
		String modName = name.split(":")[0].replace(":", "");
		String blockName = name.split(":")[1].replace(":", "");
		
		String path = DataGenerator.gameDir + "\\src\\main\\resources\\assets\\" + modName + "\\";
		String path2 = DataGenerator.gameDir + "\\src\\main\\resources\\data\\" + modName + "\\";
		File blockstate = new File(path + "blockstates\\" + blockName + ".json");
		try {
			if (!blockstate.exists()) {
				blockstate.createNewFile();
			}
			FileWriter writer = new FileWriter(blockstate);
			writer.write(blockState.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		File blockmodel = new File(path + "models\\block\\" + blockName + "_top.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(top.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_bottom.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(bottom.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_open.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(open.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		File itemmodel = new File(path + "models\\item\\" + blockName + ".json");
		try {
			if (!itemmodel.exists()) {
				itemmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(itemmodel);
			writer.write(normalBlockItemModel.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		if (loot) {
			File loottable = new File(path2 + "loot_tables\\blocks\\" + blockName + ".json");
			try {
				if (!loottable.exists()) {
					loottable.createNewFile();
				}
				FileWriter writer = new FileWriter(loottable);
				writer.write(normalBlockLootTable.replace("%modid%", modName).replace("%blockid%", blockName));
				writer.close();
			} catch (Throwable ignored) {
			}
		}
	}
	
	private static final String blockState = "{\n" + "    \"variants\": {\n" + "        \"facing=north,half=bottom,open=false\": { \"model\": \"%modid%:block/%blockid%_bottom\" },\n" + "        \"facing=south,half=bottom,open=false\": { \"model\": \"%modid%:block/%blockid%_bottom\", \"y\": 180  },\n" + "        \"facing=east,half=bottom,open=false\": { \"model\": \"%modid%:block/%blockid%_bottom\", \"y\": 90  },\n" + "        \"facing=west,half=bottom,open=false\": { \"model\": \"%modid%:block/%blockid%_bottom\", \"y\": 270 },\n" + "        \"facing=north,half=top,open=false\": { \"model\": \"%modid%:block/%blockid%_top\" },\n" + "        \"facing=south,half=top,open=false\": { \"model\": \"%modid%:block/%blockid%_top\", \"y\": 180  },\n" + "        \"facing=east,half=top,open=false\": { \"model\": \"%modid%:block/%blockid%_top\", \"y\": 90  },\n" + "        \"facing=west,half=top,open=false\": { \"model\": \"%modid%:block/%blockid%_top\", \"y\": 270  },\n" + "        \"facing=north,half=bottom,open=true\": { \"model\": \"%modid%:block/%blockid%_open\" },\n" + "        \"facing=south,half=bottom,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"y\": 180 },\n" + "        \"facing=east,half=bottom,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"y\": 90 },\n" + "        \"facing=west,half=bottom,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"y\": 270 },\n" + "        \"facing=north,half=top,open=true\": { \"model\": \"%modid%:block/%blockid%_open\" },\n" + "        \"facing=south,half=top,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"y\": 180 },\n" + "        \"facing=east,half=top,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"y\": 90 },\n" + "        \"facing=west,half=top,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"y\": 270 }\n" + "    }\n" + "}";
	
	private static final String bottom = "{\n" + "    \"parent\": \"block/template_trapdoor_bottom\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%\"\n" + "    }\n" + "}\n";
	
	private static final String top = "{\n" + "    \"parent\": \"block/template_trapdoor_top\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%\"\n" + "    }\n" + "}\n";
	
	private static final String open = "{\n" + "    \"parent\": \"block/template_trapdoor_open\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%\"\n" + "    }\n" + "}\n";
	
	private static final String normalBlockItemModel = "{\n" + "    \"parent\": \"%modid%:block/%blockid%_bottom\"\n" + "}";
	
	private static final String normalBlockLootTable = "{\n" + "  \"type\": \"minecraft:block\",\n" + "  \"pools\": [\n" + "    {\n" + "      \"rolls\": 1,\n" + "      \"entries\": [\n" + "        {\n" + "          \"type\": \"minecraft:item\",\n" + "          \"name\": \"%modid%:%blockid%\"\n" + "        }\n" + "      ],\n" + "      \"conditions\": [\n" + "        {\n" + "          \"condition\": \"minecraft:survives_explosion\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
}
