import java.io.File;
import java.io.FileWriter;

public class LeavesGenerator {
	public static void generateBlock(String name) {
		String modName = name.split(":")[0].replace(":", "");
		String blockName = name.split(":")[1].replace(":", "");
		
		String path = DataGenerator.gameDir + "\\src\\main\\resources\\assets\\" + modName + "\\";
		File blockstate = new File(path + "blockstates\\" + blockName + ".json");
		try {
			if (!blockstate.exists()) {
				blockstate.createNewFile();
			}
			FileWriter writer = new FileWriter(blockstate);
			writer.write(leavesBlockState.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		File blockmodel = new File(path + "models\\block\\" + blockName + ".json");
		try {
			if (!blockmodel.exists()) {
				blockmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(blockmodel);
			writer.write(leavesBlockModel.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
		File itemmodel = new File(path + "models\\item\\" + blockName + ".json");
		try {
			if (!itemmodel.exists()) {
				itemmodel.createNewFile();
			}
			FileWriter writer = new FileWriter(itemmodel);
			writer.write(leavesBlockItemModel.replace("%modid%", modName).replace("%blockid%", blockName));
			writer.close();
		} catch (Throwable ignored) {
		}
	}
	
	private static final String leavesBlockState = "{\n" + "    \"variants\": {\n" + "        \"\": { \"model\": \"%modid%:block/%blockid%\" }\n" + "    }\n" + "}\n";
	
	private static final String leavesBlockModel = "{\n" + "    \"parent\": \"block/leaves\",\n" + "    \"textures\": {\n" + "        \"all\": \"%modid%:block/%blockid%\"\n" + "    }\n" + "}\n";
	
	private static final String leavesBlockItemModel = "{\n" + "    \"parent\": \"%modid%:block/%blockid%\"\n" + "}";
}
