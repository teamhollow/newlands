import java.io.File;
import java.io.FileWriter;

public class LogGenerator {
	public static void generateBlock(String name, boolean loot) {
		String modName = name.split(":")[0].replace(":", "");
		String blockName = name.split(":")[1].replace(":", "");
		
		String path = Generator.gameDir + "\\src\\main\\resources\\assets\\" + modName + "\\";
		String path2 = Generator.gameDir + "\\src\\main\\resources\\data\\" + modName + "\\";
		File blockstate = new File(path + "blockstates\\" + blockName + ".json");
		try {
			if (!blockstate.exists()) {
				blockstate.createNewFile();
			}
			FileWriter writer = new FileWriter(blockstate);
			writer.write(logBlockState.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		File blockmodel = new File(path + "models\\block\\" + blockName + ".json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(normalBlockModel.replace("%modid%", modName).replace("%blockid%", blockName));
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
	
	private final static String logBlockState = "" +
			"{\n" +
			"    \"variants\": {\n" +
			"        \"axis=y\":    { \"model\": \"%modid%:block/%blockid%\" },\n" +
			"        \"axis=z\":     { \"model\": \"%modid%:block/%blockid%\", \"x\": 90 },\n" +
			"        \"axis=x\":     { \"model\": \"%modid%:block/%blockid%\", \"x\": 90, \"y\": 90 }\n" +
			"    }\n" +
			"}\n";
	
	private final static String normalBlockModel = "" +
			"{\n" +
			"    \"parent\": \"block/cube_column\",\n" +
			"    \"textures\": {\n" +
			"        \"end\": \"%modid%:block/%blockid%\",\n" +
			"        \"side\": \"%modid%:block/%blockid%\"\n" +
			"    }\n" +
			"}\n";
	
	private final static String normalBlockItemModel = "{\n" + "    \"parent\": \"%modid%:block/%blockid%\"\n" + "}";
	
	private final static String normalBlockLootTable = "{\n" + "  \"type\": \"minecraft:block\",\n" + "  \"pools\": [\n" + "    {\n" + "      \"rolls\": 1,\n" + "      \"entries\": [\n" + "        {\n" + "          \"type\": \"minecraft:item\",\n" + "          \"name\": \"%modid%:%blockid%\"\n" + "        }\n" + "      ],\n" + "      \"conditions\": [\n" + "        {\n" + "          \"condition\": \"minecraft:survives_explosion\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
}
