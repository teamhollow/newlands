import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

public class Generator {
	public static List<String> blocks = Arrays.asList(
			"new_lands:cobble_dirt",
			"new_lands:sandy_cobblestone",
			"new_lands:shore_rock",
			"new_lands:tropical_palm_planks",
			"new_lands:magnolia_planks"
	);
	public static List<String> logBlocks = Arrays.asList(
			"new_lands:magnolia_log",
			"new_lands:stripped_tropical_palm_log",
			"new_lands:tropical_palm_log",
			"new_lands:stripped_magnolia_log"
	);
	
	public static List<String> blocksNoLoot = Arrays.asList(
			"new_lands:magnolia_leaves"
	);
	
	public static List<String> leaves = Arrays.asList(
			"new_lands:tropical_palm_leaves"
	);
	
	public static List<String[]> translationBlocks = Arrays.asList(
			new String[]{"new_lands:cobble_dirt", "Dirty Cobblestone"},
			new String[]{"new_lands:sandy_cobblestone", "Sandy Cobblestone"},
			new String[]{"new_lands:magnolia_leaves", "Magnolia Leaves"},
			new String[]{"new_lands:magnolia_planks", "Magnolia Planks"},
			new String[]{"new_lands:shore_rock", "Shore Rock"},
			new String[]{"new_lands:tropical_palm_leaves", "Tropical Palm Leaves"},
			new String[]{"new_lands:stripped_tropical_palm_log", "Stripped Tropical Palm Log"},
			new String[]{"new_lands:tropical_palm_log", "Tropical Palm Log"},
			new String[]{"new_lands:tropical_palm_planks", "Tropical Palm Planks"},
			new String[]{"new_lands:tropical_palm_door", "Tropical Palm Door"},
			new String[]{"new_lands:tropical_palm_trapdoor", "Tropical Palm Trapdoor"},
			new String[]{"new_lands:tropical_palm_sapling", "Tropical Palm Sapling"},
			new String[]{"new_lands:sand_layer", "Sand Layer"}
	);
	
	public static List<String[]> translationGroups = Arrays.asList(
			new String[]{"new_lands:newlands_building", "New Lands Building Blocks"},
			new String[]{"new_lands:newlands_worldgen", "New Lands World Generation"}
	);
	
	public static final String gameDir = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		blocks.forEach((b)-> LogGenerator.generateBlock(b,true));
		blocksNoLoot.forEach((b)-> LogGenerator.generateBlock(b,false));
		leaves.forEach(LeavesGenerator::generateBlock);
		logBlocks.forEach((b)->LogGenerator.generateBlock(b,true));
		StringBuilder translation = new StringBuilder("{\n");
		doTranslations(translationBlocks, translation, blockTranslationTemplate);
		doTranslations(translationGroups, translation, itemGroupTranslationTemplate);
		translation.append("\"\":\"\"}");
		System.out.println(translation.toString());
		
		String path = gameDir + "\\src\\main\\resources\\assets\\new_lands\\";
		File lang = new File(path + "lang\\en_us.json");
		try {
			if (!lang.exists()) {
				lang.createNewFile();
			}
			FileWriter writer = new FileWriter(lang);
			writer.write(translation.toString());
			writer.close();
		} catch (Throwable ignored) {
		}
	}
	
	public static void doTranslations(List<String[]> toAdd, StringBuilder translation, String template) {
		toAdd.forEach((t) -> {
			String name = t[0];
			String modName = name.split(":")[0].replace(":", "");
			String blockName = name.split(":")[1].replace(":", "");
			String translationname = t[1];
			
			translation.append(template.replace("%modid%", modName).replace("%blockid%", blockName).replace("%blockname%", translationname));
		});
		translation.append("\n");
	}
	
	private static final String blockTranslationTemplate = "  \"block.%modid%.%blockid%\": \"%blockname%\",\n";
	
	private static final String itemGroupTranslationTemplate = "  \"itemGroup.%modid%_%blockid%\": \"%blockname%\",\n";
}
