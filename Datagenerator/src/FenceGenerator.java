import java.io.File;
import java.io.FileWriter;

public class FenceGenerator {
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
		File blockmodel = new File(path + "models\\block\\" + blockName + "_side.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(side.replace("%modid%", modName).replace("%blockid%", blockName).replace("_fence%", "_planks"));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_post.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(post.replace("%modid%", modName).replace("%blockid%", blockName).replace("_fence%", "_planks"));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_inventory.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(inventory.replace("%modid%", modName).replace("%blockid%", blockName).replace("_fence%", "_planks"));
			writer.close();
		} catch (Throwable ignored) {
		}
		File itemmodel = new File(path + "models\\item\\" + blockName + ".json");
		try {
			if (!itemmodel.exists()) {
				itemmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(itemmodel);
			writer.write(itemModel.replace("%modid%", modName).replace("%blockid%", blockName));
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
	
	private static final String blockState = "{\n" + "    \"multipart\": [\n" + "        {   \"apply\": { \"model\": \"%modid%:block/%blockid%_post\" }},\n" + "        {   \"when\": { \"north\": \"true\" },\n" + "            \"apply\": { \"model\": \"%modid%:block/%blockid%_side\", \"uvlock\": true }\n" + "        },\n" + "        {   \"when\": { \"east\": \"true\" },\n" + "            \"apply\": { \"model\": \"%modid%:block/%blockid%_side\", \"y\": 90, \"uvlock\": true }\n" + "        },\n" + "        {   \"when\": { \"south\": \"true\" },\n" + "            \"apply\": { \"model\": \"%modid%:block/%blockid%_side\", \"y\": 180, \"uvlock\": true }\n" + "        },\n" + "        {   \"when\": { \"west\": \"true\" },\n" + "            \"apply\": { \"model\": \"%modid%:block/%blockid%_side\", \"y\": 270, \"uvlock\": true }\n" + "        }\n" + "    ]\n" + "}\n";
	
	private static final String side = "{\n" + "    \"parent\": \"block/fence_side\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%%\"\n" + "    }\n" + "}\n";
	
	private static final String post = "{\n" + "    \"parent\": \"block/fence_post\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%%\"\n" + "    }\n" + "}\n";
	
	private static final String inventory = "{\n" + "    \"parent\": \"block/fence_inventory\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%%\"\n" + "    }\n" + "}\n";
	
	private static final String normalBlockLootTable = "{\n" + "  \"type\": \"minecraft:block\",\n" + "  \"pools\": [\n" + "    {\n" + "      \"rolls\": 1,\n" + "      \"entries\": [\n" + "        {\n" + "          \"type\": \"minecraft:item\",\n" + "          \"name\": \"%modid%:%blockid%\"\n" + "        }\n" + "      ],\n" + "      \"conditions\": [\n" + "        {\n" + "          \"condition\": \"minecraft:survives_explosion\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
	
	private static final String itemModel = "{\n" + "    \"parent\": \"%modid%:block/%blockid%_inventory\"\n" + "}\n";
}
