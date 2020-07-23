import java.io.File;
import java.io.FileWriter;

public class DoorGenerator {
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
			writer.write(doorBlockState.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		File blockmodel = new File(path + "models\\block\\" + blockName + "_top.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(topHalf.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_top_hinge.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(topHinge.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_bottom.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(bottomHalf.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		blockmodel = new File(path + "models\\block\\" + blockName + "_bottom_hinge.json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(bottomHinge.replace("%modid%", modName).replace("%blockid%", blockName));
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
				writer.write(lootTable.replace("%modid%", modName).replace("%blockid%", blockName));
				writer.close();
			} catch (Throwable ignored) {
			}
		}
	}
	
	private static final String doorBlockState = "{\n" + "  \"variants\": {\n" + "    \"facing=east,half=lower,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\"\n" + "    },\n" + "    \"facing=east,half=lower,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=east,half=lower,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\"\n" + "    },\n" + "    \"facing=east,half=lower,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=east,half=upper,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\"\n" + "    },\n" + "    \"facing=east,half=upper,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=east,half=upper,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\"\n" + "    },\n" + "    \"facing=east,half=upper,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=north,half=lower,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=north,half=lower,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\"\n" + "    },\n" + "    \"facing=north,half=lower,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=north,half=lower,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=north,half=upper,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=north,half=upper,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\"\n" + "    },\n" + "    \"facing=north,half=upper,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=north,half=upper,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=south,half=lower,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=south,half=lower,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=south,half=lower,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=south,half=lower,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\"\n" + "    },\n" + "    \"facing=south,half=upper,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=south,half=upper,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=south,half=upper,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=south,half=upper,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\"\n" + "    },\n" + "    \"facing=west,half=lower,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=west,half=lower,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=west,half=lower,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom_hinge\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=west,half=lower,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_bottom\",\n" + "      \"y\": 90\n" + "    },\n" + "    \"facing=west,half=upper,hinge=left,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=west,half=upper,hinge=left,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\",\n" + "      \"y\": 270\n" + "    },\n" + "    \"facing=west,half=upper,hinge=right,open=false\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top_hinge\",\n" + "      \"y\": 180\n" + "    },\n" + "    \"facing=west,half=upper,hinge=right,open=true\": {\n" + "      \"model\": \"%modid%:block/%blockid%_top\",\n" + "      \"y\": 90\n" + "    }\n" + "  }\n" + "}";
	
	private static final String topHinge = "{\n" + "  \"parent\": \"block/door_top_rh\",\n" + "  \"textures\": {\n" + "    \"top\": \"%modid%:block/%blockid%_top\",\n" + "    \"bottom\": \"%modid%:block/%blockid%_bottom\"\n" + "  }\n" + "}";
	
	private static final String bottomHinge = "{\n" + "  \"parent\": \"block/door_bottom_rh\",\n" + "  \"textures\": {\n" + "    \"top\": \"%modid%:block/%blockid%_top\",\n" + "    \"bottom\": \"%modid%:block/%blockid%_bottom\"\n" + "  }\n" + "}";
	
	private static final String bottomHalf = "{\n" + "  \"parent\": \"block/door_bottom\",\n" + "  \"textures\": {\n" + "    \"top\": \"%modid%:block/%blockid%_top\",\n" + "    \"bottom\": \"%modid%:block/%blockid%_bottom\"\n" + "  }\n" + "}";
	
	private static final String topHalf = "{\n" + "  \"parent\": \"block/door_top\",\n" + "  \"textures\": {\n" + "    \"top\": \"%modid%:block/%blockid%_top\",\n" + "    \"bottom\": \"%modid%:block/%blockid%_bottom\"\n" + "  }\n" + "}";
	
	private static final String itemModel = "{\n" + "    \"parent\": \"item/generated\",\n" + "    \"textures\": {\n" + "        \"layer0\": \"%modid%:item/%blockid%\"\n" + "    }\n" + "}\n";
	
	private static final String lootTable = "{\n" + "  \"type\": \"minecraft:block\",\n" + "  \"pools\": [\n" + "    {\n" + "      \"rolls\": 1,\n" + "      \"entries\": [\n" + "        {\n" + "          \"type\": \"minecraft:item\",\n" + "          \"conditions\": [\n" + "            {\n" + "              \"condition\": \"minecraft:block_state_property\",\n" + "              \"block\": \"%modid%:%blockid%\",\n" + "              \"properties\": {\n" + "                \"half\": \"lower\"\n" + "              }\n" + "            }\n" + "          ],\n" + "          \"name\": \"%modid%:%blockid%\"\n" + "        }\n" + "      ],\n" + "      \"conditions\": [\n" + "        {\n" + "          \"condition\": \"minecraft:survives_explosion\"\n" + "        }\n" + "      ]\n" + "    }\n" + "  ]\n" + "}";
}
