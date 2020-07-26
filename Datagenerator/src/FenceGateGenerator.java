import java.io.File;
import java.io.FileWriter;

public class FenceGateGenerator {
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
		File blockmodel = new File(path + "models\\block\\" + blockName + "_open.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(open.replace("%modid%", modName).replace("%blockid%", blockName).replace("_fence_gate%", "_planks"));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + ".json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(closed.replace("%modid%", modName).replace("%blockid%", blockName).replace("_fence_gate%", "_planks"));
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
	
	private static final String open = "{\n" + "    \"parent\": \"block/template_fence_gate_open\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%%\"\n" + "    }\n" + "}\n";
	
	private static final String closed = "{\n" + "    \"parent\": \"block/template_fence_gate\",\n" + "    \"textures\": {\n" + "        \"texture\": \"%modid%:block/%blockid%%\"\n" + "    }\n" + "}\n";
	
	private static final String blockState = "{\n" + "    \"variants\": {\n" + "        \"facing=south,open=false\": { \"model\": \"%modid%:block/%blockid%\", \"uvlock\": true },\n" + "        \"facing=west,open=false\":  { \"model\": \"%modid%:block/%blockid%\", \"uvlock\": true, \"y\": 90 },\n" + "        \"facing=north,open=false\": { \"model\": \"%modid%:block/%blockid%\", \"uvlock\": true, \"y\": 180 },\n" + "        \"facing=east,open=false\":  { \"model\": \"%modid%:block/%blockid%\", \"uvlock\": true, \"y\": 270 },\n" + "        \"facing=south,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"uvlock\": true },\n" + "        \"facing=west,open=true\":  { \"model\": \"%modid%:block/%blockid%_open\", \"uvlock\": true, \"y\": 90 },\n" + "        \"facing=north,open=true\": { \"model\": \"%modid%:block/%blockid%_open\", \"uvlock\": true, \"y\": 180 },\n" + "        \"facing=east,open=true\":  { \"model\": \"%modid%:block/%blockid%_open\", \"uvlock\": true, \"y\": 270 }\n" + "    }\n" + "}\n";
	
	private static final String normalBlockLootTable = "{\n" + "  \"type\": \"minecraft:block\",\n" + "  \"pools\": [\n" + "    {\n" + "      \"rolls\": 1,\n" + "      \"entries\": [\n" + "        {\n" + "          \"type\": \"minecraft:item\",\n" + "          \"name\": \"%modid%:%blockid%\"\n" + "        }\n" + "      ],\n" + "      \"conditions\": [\n" + "        {\n" + "          \"condition\": \"minecraft:survives_explosion\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
	
	private static final String itemModel = "{\n" + "    \"parent\": \"%modid%:block/%blockid%\"\n" + "}\n";
}
