import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGenerator {
	public static List<String> blocks = Arrays.asList(
			"new_lands:cobble_dirt",
			"new_lands:sandy_cobblestone",
			"new_lands:shorerock",
			"new_lands:tropical_palm_planks",
			"new_lands:magnolia_planks",
			"new_lands:polished_shorerock",
			"new_lands:polished_shorerock_bricks",
			"new_lands:polished_shorerock_bricks_seaweed",
			"new_lands:chiseled_polished_shorerock"
	);
	
	public static List<String> logBlocks = Arrays.asList(
			"new_lands:magnolia_log",
			"new_lands:stripped_tropical_palm_log",
			"new_lands:tropical_palm_log",
			"new_lands:stripped_magnolia_log"
	);
	
	public static List<String> blocksNoLoot = Arrays.asList(
			"new_lands:magnolia_leaves",
			"new_lands:flowering_magnolia_leaves"
	);
	
	public static List<String> leaves = Arrays.asList(
			"new_lands:tropical_palm_leaves"
	);
	
	public static List<String> tagLeaves = Arrays.asList(
			"new_lands:tropical_palm_leaves",
			"new_lands:magnolia_leaves",
			"new_lands:flowering_magnolia_leaves"
	);
	
	public static List<String> tagPlanks = Arrays.asList(
			"new_lands:tropical_palm_planks",
			"new_lands:magnolia_planks"
	);
	
	public static List<String> doors = Arrays.asList(
			"new_lands:magnolia_door",
			"new_lands:tropical_palm_door"
	);
	
	public static List<String> trapdoors = Arrays.asList(
			"new_lands:magnolia_trapdoor",
			"new_lands:tropical_palm_trapdoor"
	);
	
	public static List<String> fences = Arrays.asList(
			"new_lands:magnolia_fence",
			"new_lands:tropical_palm_fence"
	);
	
	public static List<String> stairs = Arrays.asList(
			"new_lands:magnolia_stairs",
			"new_lands:tropical_palm_stairs"
	);
	
	public static List<String[]> translationBlocks = Arrays.asList(
			new String[]{"new_lands:cobble_dirt", "Dirty Cobblestone"},
			new String[]{"new_lands:sandy_cobblestone", "Sandy Cobblestone"},
			new String[]{"new_lands:magnolia_leaves", "Magnolia Leaves"},
			new String[]{"new_lands:magnolia_planks", "Magnolia Planks"},
			new String[]{"new_lands:shorerock", "Shorerock"},
			new String[]{"new_lands:tropical_palm_leaves", "Tropical Palm Leaves"},
			new String[]{"new_lands:stripped_tropical_palm_log", "Stripped Tropical Palm Log"},
			new String[]{"new_lands:tropical_palm_log", "Tropical Palm Log"},
			new String[]{"new_lands:tropical_palm_planks", "Tropical Palm Planks"},
			new String[]{"new_lands:tropical_palm_door", "Tropical Palm Door"},
			new String[]{"new_lands:tropical_palm_fence", "Tropical Palm Fence"},
			new String[]{"new_lands:tropical_palm_stairs", "Tropical Palm Stairs"},
			new String[]{"new_lands:tropical_palm_fence_gate", "Tropical Palm Fence Gate"},
			new String[]{"new_lands:tropical_palm_trapdoor", "Tropical Palm Trapdoor"},
			new String[]{"new_lands:tropical_palm_sapling", "Tropical Palm Sapling"},
			new String[]{"new_lands:polished_shorerock", "Polished Shorerock"},
			new String[]{"new_lands:polished_shorerock_bricks", "Polished Shorerock Bricks"},
			new String[]{"new_lands:chiseled_polished_shorerock", "Chiseled Polished Shorerock"},
			new String[]{"new_lands:stripped_magnolia_log", "Stripped Magnolia Log"},
			new String[]{"new_lands:magnolia_log", "Magnolia Log"},
			new String[]{"new_lands:magnolia_stairs", "Magnolia Stairs"},
			new String[]{"new_lands:flowering_magnolia_leaves", "Flowering Magnolia Leaves"},
			new String[]{"new_lands:magnolia_trapdoor", "Magnolia Trapdoor"},
			new String[]{"new_lands:magnolia_door", "Magnolia Door"},
			new String[]{"new_lands:magnolia_fence", "Magnolia Fence"},
			new String[]{"new_lands:magnolia_fence_gate", "Magnolia Fence Gate"},
			new String[]{"new_lands:sand_layer", "Sand Layer"}
	);
	
	public static List<String[]> translationGroups = Arrays.asList(
			new String[]{"new_lands:newlands_building", "New Lands Building Blocks"},
			new String[]{"new_lands:newlands_worldgen", "New Lands World Generation"}
	);
	
	public static final String gameDir = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		blocks.forEach((b) -> BlockGenerator.generateBlock(b, true));
		blocksNoLoot.forEach((b) -> BlockGenerator.generateBlock(b, false));
		leaves.forEach(LeavesGenerator::generateBlock);
		logBlocks.forEach((b) -> LogGenerator.generateBlock(b, true));
		doors.forEach((b) -> DoorGenerator.generateBlock(b, true));
		trapdoors.forEach((b) -> TrapdoorGenerator.generateBlock(b, true));
		fences.forEach((b) -> {
			FenceGenerator.generateBlock(b, true);
			FenceGateGenerator.generateBlock(b + "_gate", true);
		});
		
		Tag doorsTag = new Tag();
		doorsTag.populate(doors);
		doorsTag.write("\\tags\\blocks\\wooden_doors.json");
		doorsTag.write("\\tags\\items\\wooden_doors.json");
		
		Tag trapdoorsTag = new Tag();
		trapdoorsTag.populate(trapdoors);
		trapdoorsTag.write("\\tags\\blocks\\wooden_trapdoors.json");
		trapdoorsTag.write("\\tags\\items\\wooden_trapdoors.json");
		
		Tag logsTag = new Tag();
		logsTag.populate(logBlocks);
		logsTag.write("\\tags\\blocks\\logs.json");
		logsTag.write("\\tags\\items\\logs.json");
		
		Tag leavesTag = new Tag();
		leavesTag.populate(tagLeaves);
		leavesTag.write("\\tags\\blocks\\leaves.json");
		leavesTag.write("\\tags\\items\\leaves.json");
		
		Tag planksTag = new Tag();
		planksTag.populate(tagPlanks);
		planksTag.write("\\tags\\blocks\\planks.json");
		planksTag.write("\\tags\\items\\planks.json");
		
		Tag fencesTag = new Tag();
		fencesTag.populate(fences);
		fencesTag.write("\\tags\\blocks\\wooden_fences.json");
		fencesTag.write("\\tags\\items\\wooden_fences.json");
		
		Tag fenceGatesTag = new Tag();
		ArrayList<String> fenceGates = new ArrayList<>();
		fences.forEach((f) -> fenceGates.add(f + "_gate"));
		fenceGatesTag.populate(fenceGates);
		fenceGatesTag.write("forge", "\\tags\\blocks\\fence_gates/wooden.json");
		
		StringBuilder translation = new StringBuilder("{\n");
		doTranslations(translationBlocks, translation, blockTranslationTemplate);
		doTranslations(translationGroups, translation, itemGroupTranslationTemplate);
		translation.append("\"\":\"\"}");
		
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
		
		doPlanks("magnolia");
		doPlanks("tropical_palm");
	}
	
	private static void doPlanks(String planks) {
		RecipeGenerator.generate(new String[]{"##", "##", "##"}, new String[]{"\"#\": {\"item\": \"new_lands:" + planks + "_planks\"}"}, planks + "_door", "new_lands", "new_lands:" + planks + "_door", "wooden_door", 3);
		RecipeGenerator.generate(new String[]{"###", "###"}, new String[]{"\"#\": {\"item\": \"new_lands:" + planks + "_planks\"}"}, planks + "_trapdoor", "new_lands", "new_lands:" + planks + "_trapdoor", "wooden_trapdoor", 2);
		RecipeGenerator.generate(new String[]{"W#W", "W#W"}, new String[]{"\"#\": {\"item\": \"minecraft:stick\"}", "\"W\": {\"item\": \"new_lands:" + planks + "_planks\"}"}, planks + "_fence", "new_lands", "new_lands:" + planks + "_fence", "wooden_fence", 3);
		RecipeGenerator.generate(new String[]{"#W#", "#W#"}, new String[]{"\"#\": {\"item\": \"minecraft:stick\"}", "\"W\": {\"item\": \"new_lands:" + planks + "_planks\"}"}, planks + "_fence_gate", "new_lands", "new_lands:" + planks + "_fence_gate", "wooden_fence_gate", 1);
		RecipeGenerator.generate(new String[]{"#  ", "## ", "###"}, new String[]{"\"#\": {\"item\": \"new_lands:" + planks + "_planks\"}"}, planks + "_stairs", "new_lands", "new_lands:" + planks + "_stairs", "wooden_stairs", 4);
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
	
	private static final String itemGroupTranslationTemplate = "  \"itemGroup.%blockid%\": \"%blockname%\",\n";
}
